package com.desafio.desafiocompass.exception;

public class ValidationErrorDetails extends ErrorDetails {
    private String field;
    private String fieldMessage;

    public ValidationErrorDetails() {
    }

    public String getField() {
        return field;
    }


    public String getFieldMessage() {
        return fieldMessage;
    }



    public static final class Builder {
        private Integer status_code;
        private String message;
        private String field;
        private String fieldMessage;

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

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
            validationErrorDetails.setStatus_code(status_code);
            validationErrorDetails.setMessage(message);
            validationErrorDetails.fieldMessage =fieldMessage;
            validationErrorDetails.field = field;
            return validationErrorDetails;
        }
    }
}
