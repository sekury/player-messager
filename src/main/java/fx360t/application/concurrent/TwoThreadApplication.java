package fx360t.application.concurrent;

import fx360t.application.Application;
import fx360t.channel.BlockingQueueMessageChannel;
import fx360t.channel.MessageChannel;
import fx360t.task.ChannelTask;
import fx360t.task.InitiatorPlayer;
import fx360t.task.ReplierPlayer;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An application that runs on two threads.
 */
public class TwoThreadApplication implements Application {

    private static final int THREADS_NUMBER = 2;
    private static final int MESSAGE_TIMEOUT_SECONDS = 3;

    private final String initMessage;

    public TwoThreadApplication(String initMessage) {
        Objects.requireNonNull(initMessage, "init message cannot be null");
        this.initMessage = initMessage;
    }

    @Override
    public String run() {
        try (ExecutorService executor = Executors.newFixedThreadPool(THREADS_NUMBER)) {
            MessageChannel channel = new BlockingQueueMessageChannel(MESSAGE_TIMEOUT_SECONDS);
            ChannelTask initiatorTask = new InitiatorPlayer("Initiator", channel, initMessage);
            ChannelTask replierTask = new ReplierPlayer("Replier", channel);

            CompletableFuture.supplyAsync(replierTask, executor);
            return CompletableFuture.supplyAsync(initiatorTask, executor).join();
        }
    }
}
