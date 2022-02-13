package com.desafio.desafiocompass.exception;

public class ResourceNotFoundDetails extends ErrorDetails {
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

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setStatus_code(status_code);
            resourceNotFoundDetails.setMessage(message);
            return resourceNotFoundDetails;
        }
    }
}
