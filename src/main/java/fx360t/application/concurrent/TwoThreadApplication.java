package fx360t.application.concurrent;

import fx360t.application.Application;
import fx360t.channel.BlockingQueueMessageChannel;
import fx360t.channel.MessageChannel;
import fx360t.tasks.ChannelTask;
import fx360t.tasks.InitiatorPlayer;
import fx360t.tasks.ReplierPlayer;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * An application that runs on two threads.
 */
public class TwoThreadApplication implements Application {
    private static final int THREADS_NUMBER = 2;
    private static final int MESSAGE_TIMEOUT_SECONDS = 3;
    private static final int SHUTDOWN_TIMEOUT_SECONDS = MESSAGE_TIMEOUT_SECONDS + 1;
    private final String initMessage;

    public TwoThreadApplication(String initMessage) {
        Objects.requireNonNull(initMessage, "init message cannot be null");
        this.initMessage = initMessage;
    }

    /**
     * Executes the application by running two threads.
     *
     * @return The last message received by the InitiatorTask.
     */
    @Override
    public String run() {
        try (ExecutorService executor = Executors.newFixedThreadPool(THREADS_NUMBER);
             ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor()) {
            scheduled.schedule(executor::shutdownNow, SHUTDOWN_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            return executeTasks(executor);
        }
    }

    private String executeTasks(ExecutorService executor) {
        MessageChannel channel = new BlockingQueueMessageChannel(MESSAGE_TIMEOUT_SECONDS);
        ChannelTask initiatorTask = new InitiatorPlayer("Initiator", channel, initMessage);
        ChannelTask replierTask = new ReplierPlayer("Replier", channel);

        CompletableFuture.supplyAsync(replierTask, executor);
        return CompletableFuture.supplyAsync(initiatorTask, executor).join();
    }
}
