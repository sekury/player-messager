package fx360t;

import fx360t.application.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("PID: " + ProcessHandle.current().pid());
        ApplicationParameters parameters = new ApplicationParameters(args);
        Application application = new ApplicationFactory().createPlayerApp(parameters);
        String result = application.run();
        System.out.println("Result: " + result);
    }
}