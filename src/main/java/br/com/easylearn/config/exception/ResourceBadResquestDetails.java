package br.com.easylearn.config.exception;

public class ResourceBadResquestDetails {
    private String title;
    private int status;
    private String detail;
    private  long timestamp;
    private String developerMessage;

    private ResourceBadResquestDetails() {
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }


    public static final class BadResquestDetailsBuilder {
        private String title;
        private int status;
        private String detail;
        private  long timestamp;
        private String developerMessage;

        private BadResquestDetailsBuilder() {
        }

        public static BadResquestDetailsBuilder badRequestDetails() {
            return new BadResquestDetailsBuilder();
        }

        public BadResquestDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BadResquestDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public BadResquestDetailsBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public BadResquestDetailsBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public BadResquestDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ResourceBadResquestDetails build() {
            ResourceBadResquestDetails resourceBadResquestDetails = new ResourceBadResquestDetails();
            resourceBadResquestDetails.title = this.title;
            resourceBadResquestDetails.detail = this.detail;
            resourceBadResquestDetails.developerMessage = this.developerMessage;
            resourceBadResquestDetails.status = this.status;
            resourceBadResquestDetails.timestamp = this.timestamp;
            return resourceBadResquestDetails;
        }
    }
}
