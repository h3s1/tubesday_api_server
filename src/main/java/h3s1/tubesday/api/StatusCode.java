package h3s1.tubesday.api;

public enum StatusCode {
    OK("OOK00001"),
    UNKNOWN_ERROR("ERR99999");

    private String code;

    StatusCode(String code) {
        this.code = code;
    }
}
