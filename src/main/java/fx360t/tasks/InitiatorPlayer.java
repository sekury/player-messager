package fx360t.tasks;

import fx360t.channel.MessageChannel;

/**
 * A task that sends messages to a player and retrieves the last received message.
 * <p>
 * Continuously sends messages until the maximum message count is reached or the current thread is interrupted.
 * <p>
 * If a null message is received or the thread is interrupted, the loop breaks and the last received message is
 * returned as the result.
 */
public class InitiatorPlayer extends ChannelTask {

    private static final int MAX_MESSAGE_COUNT = 10;

    private final String initialMessage;

    public InitiatorPlayer(String name, MessageChannel channel, String initialMessage) {
        super(name, channel);
        this.initialMessage = initialMessage;
    }

    /**
     * Retrieves the last received message from the player.
     *
     * @return the last received message from the player, or null if no messages were received or the thread was
     * interrupted
     */
    @Override
    protected String connect() {
        String currentMessage = initialMessage;
        String lastMessageReceived = null;
        long messagesSent = 1;

        while (isThreadRunning()
                && messagesSent <= MAX_MESSAGE_COUNT
                && sendMessage(currentMessage, messagesSent)) {
            currentMessage = receiveIncomingMessage();
            if (currentMessage == null) {
                break;
            }
            lastMessageReceived = currentMessage;
            messagesSent++;
        }
        return lastMessageReceived;
    }

    private boolean isThreadRunning() {
        return !Thread.currentThread().isInterrupted();
    }

    private boolean sendMessage(String message, long counter) {
        return sendMessage(message + counter);
    }
}
