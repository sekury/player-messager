package fx360t.application;

/**
 * Parameters required for creating and initializing an application.
 */
public class ApplicationParameters {

    private static final int APP_TYPE_INDEX = 0;
    private static final int INIT_MESSAGE_INDEX = 1;
    private static final int REPLY_PORT_INDEX = 1;
    private static final int INIT_PORT_INDEX = 2;

    public static final String APPLICATION_TYPE_NOT_SPECIFIED = "Application type not specified";
    public static final String INIT_MESSAGE_NOT_SPECIFIED = "Init message not specified";
    public static final String PORT_NOT_SPECIFIED = "Port not specified";

    private final ApplicationType appType;
    private final String initMessage;
    private final Integer port;

    public ApplicationParameters(String[] params) {
        this.appType = getAppTypeFromParams(params);
        this.initMessage = getInitMessageFromParams(appType, params);
        this.port = getPortFromParams(appType, params);
    }

    public ApplicationType getAppType() {
        return appType;
    }

    public String getInitMessage() {
        return initMessage;
    }

    public Integer getPort() {
        return port;
    }

    private static ApplicationType getAppTypeFromParams(String[] params) {
        checkParams(params, APP_TYPE_INDEX, APPLICATION_TYPE_NOT_SPECIFIED);
        return ApplicationType.getAppType(params[APP_TYPE_INDEX]);
    }

    private static String getInitMessageFromParams(ApplicationType appType, String[] params) {
        if (appType == ApplicationType.SOCKET_REPLY) {
            return null;
        }
        checkParams(params, INIT_MESSAGE_INDEX, INIT_MESSAGE_NOT_SPECIFIED);
        return params[INIT_MESSAGE_INDEX];
    }

    private static Integer getPortFromParams(ApplicationType appType, String[] params) {
        Integer portIndex = getPortIndex(appType);
        if (portIndex == null) {
            return null;
        }
        checkParams(params, portIndex, PORT_NOT_SPECIFIED);
        int port = Integer.parseInt(params[portIndex]);
        checkPortNumber(port);
        return port;
    }

    private static Integer getPortIndex(ApplicationType appType) {
        return switch (appType) {
            case SOCKET_INIT -> INIT_PORT_INDEX;
            case SOCKET_REPLY -> REPLY_PORT_INDEX;
            default -> null;
        };
    }

    private static void checkParams(String[] params, int parameterIndex, String errorMessage) {
        if (params.length < parameterIndex + 1) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void checkPortNumber(int port) {
        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("Invalid port number");
        }
    }
}
