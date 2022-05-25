package com.burger.burger.Exceptions;

/**
 * Exception class used by the REST implementation to report errors
 * which occurs on API layer.
 */
public class ApiException extends RuntimeException {

    private final ApiError apiError;
    private String message;

    /**
     * Construct a new instance with an HTTP status code of 500 and
     * the given error
     *
     * @param apiError error
     */
    public ApiException(ApiError apiError) {
        super();
        this.message = apiError.getMessage();
        this.apiError = apiError;
    }

    /**
     * Construct a new instance with an HTTP status code of 500 and
     * the given error and message
     *
     * @param apiError error
     */
    public ApiException(ApiError apiError, String message) {
        super();
        this.message = message;
        this.setApiError(apiError);
        this.apiError = apiError;
    }

    /**
     * Construct a new instance with an HTTP status code of 500 and
     * the given error and message and cause
     *
     * @param apiError error
     */
    public ApiException(ApiError apiError, String message, Throwable cause) {
        super(cause);
        this.message = message;
        this.apiError = apiError;
    }

    /**
     * Construct a new instance with an HTTP status code of 500 and
     * the given error and cause
     *
     * @param apiError error
     */
    public ApiException(ApiError apiError, Throwable cause) {
        super(cause);
        this.message = apiError.getMessage();
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return super.getMessage();
        } else {
            return message;
        }
    }

    protected void setApiError(ApiError apiError) {
        apiError.setMessage(apiError.getMessage() == null || apiError.getMessage().isEmpty() ? message : apiError.getMessage() + ". " + message);
    }
}
