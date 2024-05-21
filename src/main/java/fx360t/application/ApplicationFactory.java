package fx360t.application;

import fx360t.application.concurrent.TwoThreadApplication;
import fx360t.application.socket.SocketClientApplication;
import fx360t.application.socket.SocketServerApplication;

/**
 * A factory class for creating applications based on the given parameters.
 */
public class ApplicationFactory {

    public Application createPlayerApp(ApplicationParameters parameters) {
        return switch (parameters.getAppType()) {
            case SOCKET_INIT -> new SocketServerApplication(parameters.getPort(), parameters.getInitMessage());
            case SOCKET_REPLY -> new SocketClientApplication(parameters.getPort());
            case TWO_THREADS -> new TwoThreadApplication(parameters.getInitMessage());
        };
    }
}
