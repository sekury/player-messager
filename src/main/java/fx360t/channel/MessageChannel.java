package fx360t.channel;

/**
 * A channel interface for sending and receiving messages.
 */
public interface MessageChannel {
    boolean sendMessage(String message) throws InterruptedException;
    String receiveMessage() throws InterruptedException;
}
