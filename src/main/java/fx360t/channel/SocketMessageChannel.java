package fx360t.channel;

import java.io.*;
import java.net.Socket;
import java.util.function.Supplier;

/**
 * An implementation of the {@code MessageChannel} interface for sending and receiving messages over a socket connection.
 */
public class SocketMessageChannel implements MessageChannel, AutoCloseable {

    private final PrintWriter out;
    private final BufferedReader in;
    private final Socket socket;

    public static SocketMessageChannel createChannel(Supplier<Socket> socketSupplier, int timeoutMs) {
        try {
            Socket socket = socketSupplier.get();
            return new SocketMessageChannel(socket, timeoutMs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SocketMessageChannel(Socket socket, int timeoutMs) throws IOException {
        this.socket = socket;
        socket.setSoTimeout(timeoutMs);
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
        closeResources(out, in, socket);
    }

    private void closeResources(Closeable... resources) throws IOException {
        for (Closeable resource : resources) {
            if (resource != null) {
                resource.close();
            }
        }
    }
}
