package fx360t.tasks;

import fx360t.channel.MessageChannel;

/**
 * ReplierTask maintains an ongoing response to messages received from a MessageChannel.
 * It retrieves the last received message from the message channel and sends a reply to each incoming message.
 */
public class ReplierPlayer extends ChannelTask {

    public ReplierPlayer(String name, MessageChannel channel) {
        super(name, channel);
    }

    /**
     * Retrieves the last received message from the message channel.
     * <p>
     * This method maintains an ongoing response to messages until it encounters one of the following conditions:
     * either the current thread has been interrupted, the incoming message is null, or a message dispatch has failed.
     *
     * @return The last received message, or null if no message was received.
     */
    @Override
    protected String connect() {
        var messagesSent = 1;
        String incomingMessage, lastMessageReceived = null;
        while (isThreadRunning()) {
            incomingMessage = receiveIncomingMessage();
            if (incomingMessage == null) {
                break;  // failed to receive message
            }
            lastMessageReceived = incomingMessage;
            if (!sendMessage(incomingMessage, messagesSent)) {
                break;  // failed to send message
            }
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
