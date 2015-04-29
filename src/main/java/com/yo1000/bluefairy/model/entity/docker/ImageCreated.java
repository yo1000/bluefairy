package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yoichi.kikuchi on 15/04/27.
 */
public class ImageCreated {
    public static class ProgressDetail {
        @JsonProperty("current")
        private Long current;
        @JsonProperty("total")
        private Long total;

        public Long getCurrent() {
            return current;
        }

        public void setCurrent(Long current) {
            this.current = current;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }
    }

    @JsonProperty("status")
    private String status;
    @JsonProperty("error")
    private String error;
    @JsonProperty("progress")
    private String progress;
    @JsonProperty("progressDetail")
    private ProgressDetail progressDetail;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public ProgressDetail getProgressDetail() {
        return progressDetail;
    }

    public void setProgressDetail(ProgressDetail progressDetail) {
        this.progressDetail = progressDetail;
    }
}
