package fx360t.application;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum representing different types of applications.
 */
public enum ApplicationType {
    SOCKET_INIT("soinit"),
    SOCKET_REPLY("soreply"),
    TWO_THREADS("threads");

    private static final Map<String, ApplicationType> APPLICATION_TYPE_MAP;

    private final String type;

    static {
        APPLICATION_TYPE_MAP = Stream.of(ApplicationType.values())
                .collect(Collectors.toMap(ApplicationType::getType, Function.identity()));
    }

    ApplicationType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static ApplicationType getAppType(String type) {
        ApplicationType appType = APPLICATION_TYPE_MAP.get(type);
        if (appType == null) {
            throw new IllegalArgumentException("Invalid AppType: " + type);
        }
        return appType;
    }
}
