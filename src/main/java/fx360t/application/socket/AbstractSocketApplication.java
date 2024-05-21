package fx360t.application.socket;

import fx360t.application.Application;
import fx360t.channel.MessageChannel;
import fx360t.channel.SocketMessageChannel;
import fx360t.task.ChannelTask;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A socket-based application.
 */
public abstract class AbstractSocketApplication implements Application {

    private static final int MAX_PORT_NUMBER = 65535;
    private static final int MIN_PORT_NUMBER = 1;
    private static final int SOCKET_TIMEOUT_MS = 3000;

    private final int port;

    protected AbstractSocketApplication(int port) {
        checkPort(port);
        this.port = port;
    }

    /**
     * Executes the process of handling a socket connection and returns the result.
     *
     * @return the result of handling the socket connection
     * @throws RuntimeException if an IOException occurs during the process
     */
    @Override
    public String run() {
        try {
            return handleSocketConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a ChannelTask for the given MessageChannel.
     * Subclasses should implement this method to provide the specific ChannelTask implementation.
     *
     * @param channel the MessageChannel to create the ChannelTask for
     * @return the created ChannelTask
     */
    protected abstract ChannelTask createChannelTask(MessageChannel channel);

    protected abstract Socket connectToSocket();

    protected String handleSocketConnection() throws IOException {
        try (SocketMessageChannel channel = SocketMessageChannel.createChannel(this::connectToSocket, SOCKET_TIMEOUT_MS)) {
            return executeTask(channel);
        }
    }

    protected String executeTask(SocketMessageChannel channel) {
        var channelTask = createChannelTask(channel);
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            return CompletableFuture.supplyAsync(channelTask, executor).join();
        }
    }

    protected int getPort() {
        return port;
    }

    private static void checkPort(int port) {
        if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
            throw new IllegalArgumentException("Invalid port number");
        }
    }
}
