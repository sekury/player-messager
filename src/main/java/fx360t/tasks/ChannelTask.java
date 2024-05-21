package fx360t.tasks;

import fx360t.channel.MessageChannel;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An abstract class that represents a task to be performed on the channel.
 */
public abstract class ChannelTask implements Supplier<String> {

    private static final String RECEIVE_MESSAGE_TEMPLATE = "%s: %s";

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final String name;
    private final MessageChannel channel;

    public ChannelTask(String name, MessageChannel channel) {
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(channel, "channel cannot be null");
        this.channel = channel;
        this.name = name;
    }

    @Override
    public String get() {
        return connect();
    };

    protected abstract String connect();

    protected boolean sendMessage(String message) {
        try {
            return channel.sendMessage(message);
        } catch (InterruptedException e) {
            handleInterruptedException(e);
            return false;
        }
    }

    protected String receiveIncomingMessage() {
        try {
            String message = channel.receiveMessage();
            log.log(Level.INFO, String.format(RECEIVE_MESSAGE_TEMPLATE, name, message));
            return message;
        } catch (InterruptedException e) {
            handleInterruptedException(e);
            return null;
        }
    }

    private void handleInterruptedException(InterruptedException e) {
        log.log(Level.INFO, "Thread interrupted", e);
        Thread.currentThread().interrupt();
    }
}
