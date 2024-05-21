package fx360t.channel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * An implementation of the {@code MessageChannel} interface that uses a {@code BlockingQueue}
 * to send and receive messages between threads.
 */
public class BlockingQueueMessageChannel implements MessageChannel {

    private final BlockingQueue<String> queue = new SynchronousQueue<>(true);
    private final int timeout;

    public BlockingQueueMessageChannel(int timeoutSeconds) {
        this.timeout = timeoutSeconds;
    }

    @Override
    public boolean sendMessage(String message) throws InterruptedException {
        return queue.offer(message, timeout, TimeUnit.SECONDS);
    }

    @Override
    public String receiveMessage() throws InterruptedException {
        return queue.poll(timeout, TimeUnit.SECONDS);
    }
}
