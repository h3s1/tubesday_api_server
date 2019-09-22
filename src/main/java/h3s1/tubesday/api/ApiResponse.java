package h3s1.tubesday.api;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private StatusCode statusCode;
    private T data;

    private ApiResponse(StatusCode code, T data) {
        this.statusCode = code;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(StatusCode.OK, data);
    }

    public static ApiResponse<String> error(String message) {
        return new ApiResponse<>(StatusCode.UNKNOWN_ERROR, message);
    }
}
