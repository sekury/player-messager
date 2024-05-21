package fx360t.task;

import fx360t.channel.MessageChannel;

/**
 * Replies to messages received from a MessageChannel.
 */
public class ReplierPlayer extends ChannelTask {

    public ReplierPlayer(String name, MessageChannel channel) {
        super(name, channel);
    }

    /**
     * Replies to messages until it encounters one of the following conditions:
     * either the current thread has been interrupted, the incoming message is null, or a message dispatch has failed.
     *
     * @return The last received message, or null if no message was received.
     */
    @Override
    protected String start() {
        var messagesSent = 1;
        String incomingMessage, lastMessageReceived = null;
        while (isThreadRunning()) {
            incomingMessage = receiveIncomingMessage();
            if (incomingMessage == null) {
                break;  // failed to receive a message or the player is disconnected
            }
            lastMessageReceived = incomingMessage;
            if (!sendMessageWithCounter(incomingMessage, messagesSent)) {
                break;  // failed to send a message or the player is disconnected
            }
            messagesSent++;
        }
        return lastMessageReceived;
    }

    private boolean isThreadRunning() {
        return !Thread.currentThread().isInterrupted();
    }
}
