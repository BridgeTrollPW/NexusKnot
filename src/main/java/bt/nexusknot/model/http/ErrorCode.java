package bt.nexusknot.model.http;

public enum ErrorCode {
    MalformedURL(900, "Request URL is malformed"),
    ProtocolError(901, "Protocol error"),
    Timeout(998, "Requested server did not respond"),
    Unknown(999, "Unknown Error");

    private String message;
    private final int code;

    ErrorCode(int errorCode, String message) {
        this.message = message;
        this.code = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}