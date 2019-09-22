package h3s1.tubesday.api;

import lombok.Getter;

public class ServiceException extends RuntimeException {

    @Getter
    ServiceErrorMessage code;

    public ServiceException(ServiceErrorMessage code, String detailMessage) {
        super(detailMessage);
        this.code = code;
    }
}
