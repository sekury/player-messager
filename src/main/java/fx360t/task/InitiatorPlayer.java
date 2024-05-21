package fx360t.task;

import fx360t.channel.MessageChannel;

/**
 * A task that sends messages to a player and returns the last received message.
 */
public final class InitiatorPlayer extends ChannelTask {

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
    protected String start() {
        String currentMessage = initialMessage;
        String lastMessageReceived = null;
        long messagesSent = 1;

        while (isThreadRunning()
                && messagesSent <= MAX_MESSAGE_COUNT
                && sendMessageWithCounter(currentMessage, messagesSent)) {  // failed to send a message or the player is disconnected
            currentMessage = receiveIncomingMessage();
            if (currentMessage == null) {
                break;  // failed to receive a message or the player is disconnected
            }
            lastMessageReceived = currentMessage;
            messagesSent++;
        }
        return lastMessageReceived;
    }

    private boolean isThreadRunning() {
        return !Thread.currentThread().isInterrupted();
    }
}
