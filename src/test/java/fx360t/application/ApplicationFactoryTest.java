package fx360t.application;

import fx360t.application.concurrent.TwoThreadApplication;
import fx360t.application.socket.SocketClientApplication;
import fx360t.application.socket.SocketServerApplication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationFactoryTest {

    private final ApplicationFactory factory = new ApplicationFactory();

    @Test
    void shouldCreateSocketServerApplication() {
        ApplicationParameters parameters = new ApplicationParameters(new String[]{ 
            "soinit", "Test Message", "8000"
        });
        Application app = factory.createPlayerApp(parameters);

        assertNotNull(app);
        assertTrue(app instanceof SocketServerApplication);
    }

    @Test
    void shouldCreateSocketClientApplication() {
        ApplicationParameters parameters = new ApplicationParameters(new String[]{ 
            "soreply", "8001"
        });
        Application app = factory.createPlayerApp(parameters);

        assertNotNull(app);
        assertTrue(app instanceof SocketClientApplication);
    }

    @Test
    void shouldCreateTwoThreadApplication() {
        ApplicationParameters parameters = new ApplicationParameters(new String[]{ 
            "threads", "Another Test Message", null
        });
        Application app = factory.createPlayerApp(parameters);

        assertNotNull(app);
        assertTrue(app instanceof TwoThreadApplication);
    }
}