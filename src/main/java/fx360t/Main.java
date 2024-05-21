package fx360t;

import fx360t.application.Application;
import fx360t.application.ApplicationFactory;
import fx360t.application.ApplicationParameters;

public class Main {

    public static void main(String[] args) {
        System.out.println("PID: " + ProcessHandle.current().pid());
        ApplicationParameters parameters = new ApplicationParameters(args);
        try (Application application = new ApplicationFactory().createPlayerApp(parameters)) {
            String result = application.run();
            System.out.println("Result: " + result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}