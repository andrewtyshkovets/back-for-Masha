package com.burger.burger.Exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represent an error returned to the client
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(of = "code")
public class ApiError {

    /**
     * Error code returned to the client
     */
    @Getter
    private String code;

    /**
     * Error message returned to the client
     */
    @Getter
    @Setter
    private String message;


    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiError(String code) {
        this(code, null);
    }

    private ApiError(IApiError error) {
        this(error.getCode(), error.getMessage());
    }

    public static ApiError of(IApiError error) {
        return new ApiError(error);
    }

    public static ApiError of(IApiError error, Throwable t) {
        var sbt = new StringBuilder(error.getMessage());
        if (t.getCause() != null) {
            sbt.append(". ");
            sbt.append(t.getCause().getMessage());
        }
        return new ApiError(error.getCode(), sbt.toString());
    }

    /**
     * Methods for api messages with variables {}
     */
    public static ApiError of(IApiError error, Object... args) {
        var sbt = new StringBuilder();
        if (error.getMessage().contains("{}")) {
            String[] messages = error.getMessage().split("}");
            int i = 0;
            for (Object arg : args) {
                sbt.append(messages[i].replace("{", arg.toString()));
                i++;
            }
            if (args.length == 1 && messages.length > 1)
                sbt.append(messages[i]);
        } else {
            sbt.append(error.getMessage());
            for (Object arg : args) {
                sbt.append(". ");
                sbt.append(arg.toString());
            }
        }
        return new ApiError(error.getCode(), sbt.toString());
    }
}
