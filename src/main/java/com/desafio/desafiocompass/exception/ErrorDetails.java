package com.desafio.desafiocompass.exception;

public class ErrorDetails {
    private Integer status_code;
    private String message;

    public ErrorDetails() {
    }

    public ErrorDetails(Integer status_code, String message) {

        this.status_code = status_code;
        this.message = message;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;

    }

    public static final class Builder {
        private Integer status_code;
        private String message;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder status_code(Integer status_code) {
            this.status_code = status_code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setStatus_code(status_code);
            errorDetails.setMessage(message);
            return errorDetails;
        }
    }
}
