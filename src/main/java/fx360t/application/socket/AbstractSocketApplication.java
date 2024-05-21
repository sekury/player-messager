package fx360t.application.socket;

import fx360t.channel.MessageChannel;
import fx360t.channel.SocketMessageChannel;
import fx360t.tasks.ChannelTask;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractSocketApplication {

    private final int port;

    protected AbstractSocketApplication(int port) {
        checkPort(port);
        this.port = port;
    }

    protected abstract ChannelTask getChannelTask(MessageChannel channel);

    protected int getPort() {
        return port;
    }

    protected String handleSocketConnection(Socket socket) throws IOException {
        try (SocketMessageChannel channel = new SocketMessageChannel(socket)) {
            return executeTask(channel);
        }
    }

    protected String executeTask(SocketMessageChannel channel) {
        var channelTask = getChannelTask(channel);
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            return CompletableFuture.supplyAsync(channelTask, executor).join();
        }
    }

    private void checkPort(int port) {
        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("Invalid port number");
        }
    }
}
