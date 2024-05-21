package fx360t.application.socket;

import fx360t.application.Application;
import fx360t.channel.MessageChannel;
import fx360t.tasks.ChannelTask;
import fx360t.tasks.InitiatorPlayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The SocketServerApp class represents a server that listens for incoming connections
 * and communicates with a client using a MessageChannel. It starts a Player instance that sends messages to the client.
 * It implements the PlayerApp interface.
 */
public class SocketServerApplication extends AbstractSocketApplication implements Application {

    private static final int SO_TIMEOUT = 5000;

    private final String initMessage;

    /**
     * Constructs a new SocketServerApp.
     *
     * @param port the port number for the server to listen on
     * @param initMessage the initial message sent to the client
     * @throws IllegalArgumentException if the port number is invalid
     */
    public SocketServerApplication(int port, String initMessage) {
        super(port);
        this.initMessage = initMessage;
    }

    /**
     * Runs the server socket and listens for incoming connections on the specified port.
     * Handles the accepted connection and returns the result of executing the player task on the socket connection.
     *
     * @return the result of executing the task on the socket connection
     * @throws RuntimeException if an I/O error occurs while running the server socket
     */
    @Override
    public String run() {
        try (ServerSocket serverSocket = new ServerSocket(getPort())) {
            System.out.println("Listening on port " + serverSocket.getLocalPort());
            return handleServerSocket(serverSocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String handleServerSocket(ServerSocket serverSocket) {
        try (Socket socket = serverSocket.accept()) {
            socket.setSoTimeout(SO_TIMEOUT);
            System.out.println("Accepted connection from " + socket.getRemoteSocketAddress());
            return handleSocketConnection(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected ChannelTask getChannelTask(MessageChannel channel) {
        return new InitiatorPlayer("Initiator", channel, initMessage);
    }
}
