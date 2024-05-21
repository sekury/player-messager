package fx360t.application.socket;

import fx360t.channel.MessageChannel;
import fx360t.task.ChannelTask;
import fx360t.task.InitiatorPlayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A server that listens for incoming connections and communicates with a client using a MessageChannel.
 * Starts an InitiatorPlayer instance that sends messages to the client.
 */
public class SocketServerApplication extends AbstractSocketApplication {

    private final String initMessage;
    private final ServerSocket serverSocket;

    public SocketServerApplication(int port, String initMessage) {
        super(port);
        this.initMessage = initMessage;
        this.serverSocket = getServerSocket(port);
    }

    private ServerSocket getServerSocket(int port) {
        try {
            return new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected ChannelTask createChannelTask(MessageChannel channel) {
        return new InitiatorPlayer("Initiator", channel, initMessage);
    }

    @Override
    protected Socket connectToSocket() {
        try {
            System.out.println("Listening on " + serverSocket.getLocalSocketAddress());
            Socket socket = serverSocket.accept();
            System.out.println("Accepted connection from " + socket.getRemoteSocketAddress());
            return socket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
    }
}
