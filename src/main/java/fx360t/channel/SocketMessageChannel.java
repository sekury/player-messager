package fx360t.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A channel for sending and receiving messages over a socket connection.
 */
public class SocketMessageChannel implements MessageChannel, AutoCloseable {

    private final PrintWriter out;
    private final BufferedReader in;

    public SocketMessageChannel(Socket socket) throws IOException {
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public boolean sendMessage(String message) {
        out.println(message);
        return true;
    }

    @Override
    public String receiveMessage() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        closeWriter();
        closeReader();
    }

    private void closeWriter() {
        if (out != null) {
            out.close();
        }
    }

    private void closeReader() throws IOException {
        if (in != null) {
            in.close();
        }
    }
}
