package fx360t.application.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TwoThreadApplicationTest {

    @Test
    public void whenRunWithValidMessage_thenReturnExpectedResult() {
        TwoThreadApplication app = new TwoThreadApplication("Hello");
        final String result = app.run();
        assertEquals("Hello1122334455667788991010", result);
    }

    @Test
    public void whenRunWithInvalidInitMessage_thenThrowException() {
        assertThrows(
                NullPointerException.class,
                () -> new TwoThreadApplication(null)
        );
    }
}