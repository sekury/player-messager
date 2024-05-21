package fx360t.application.socket;

import fx360t.channel.MessageChannel;
import fx360t.task.ChannelTask;
import fx360t.task.ReplierPlayer;

import java.io.IOException;
import java.net.Socket;

/**
 * A client that connects to a server on a specified port and communicates using a MessageChannel.
 * It starts a ReplierPlayer instance that replies to incoming messages from the server.
 */
public class SocketClientApplication extends AbstractSocketApplication {

    private static final String HOST = "localhost";

    public SocketClientApplication(int port) {
        super(port);
    }

    @Override
    protected ChannelTask createChannelTask(MessageChannel channel) {
        return new ReplierPlayer("Replier", channel);
    }

    @Override
    protected Socket connectToSocket() {
        try {
            Socket socket = new Socket(HOST, getPort());
            System.out.println("Connected to " + socket.getRemoteSocketAddress());
            return socket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
