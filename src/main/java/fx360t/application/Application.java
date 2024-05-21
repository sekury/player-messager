package fx360t.application;

/**
 * An application that can be run and closed.
 */
public interface Application extends AutoCloseable {

    String run();

    @Override
    default void close() throws Exception {}
}
