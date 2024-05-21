package fx360t.application.socket;

import fx360t.application.Application;
import fx360t.channel.MessageChannel;
import fx360t.tasks.ChannelTask;
import fx360t.tasks.ReplierPlayer;

import java.io.IOException;
import java.net.Socket;

/**
 * A client that connects to a server on a specified port and communicates using a MessageChannel.
 * It starts a Player instance that replies to incoming messages from the server.
 */
public class SocketClientApplication extends AbstractSocketApplication implements Application {

    private static final int SO_TIMEOUT = 5000;
    private static final String HOST = "localhost";

    /**
     * Constructs a new SocketClientApp with the specified port number.
     *
     * @param port the port number for the server to listen on
     * @throws IllegalArgumentException if the port number is invalid
     */
    public SocketClientApplication(int port) {
        super(port);
    }

    /**
     * Runs the client application by connecting to a server on a specified host and port.
     * It establishes a socket connection with the server and handles the communication using a MessageChannel.
     *
     * @return The response received from the server after sending and receiving messages.
     * @throws RuntimeException if an IOException occurs while establishing the socket connection.
     */
    @Override
    public String run() {
        try (Socket socket = new Socket(HOST, getPort())) {
            socket.setSoTimeout(SO_TIMEOUT);
            System.out.println("Connected to " + socket.getRemoteSocketAddress());
            return handleSocketConnection(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected ChannelTask getChannelTask(MessageChannel channel) {
        return new ReplierPlayer("Replier", channel);
    }
}
