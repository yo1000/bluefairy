package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by yoichi.kikuchi on 15/03/17.
 */
public class State {
    @JsonProperty("Error")
    private String error;
    @JsonProperty("ExitCode")
    private Integer exitCode;
    @JsonProperty("FinishedAt")
    private Date finishedAt;
    @JsonProperty("OOMKilled")
    private Boolean oomKilled;
    @JsonProperty("Paused")
    private Boolean paused;
    @JsonProperty("Pid")
    private Integer pid;
    @JsonProperty("Restarting")
    private Boolean restarting;
    @JsonProperty("Running")
    private Boolean running;
    @JsonProperty("StartedAt")
    private Date startedAt;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getExitCode() {
        return exitCode;
    }

    public void setExitCode(Integer exitCode) {
        this.exitCode = exitCode;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Boolean getOomKilled() {
        return oomKilled;
    }

    public void setOomKilled(Boolean oomKilled) {
        this.oomKilled = oomKilled;
    }

    public Boolean getPaused() {
        return paused;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Boolean getRestarting() {
        return restarting;
    }

    public void setRestarting(Boolean restarting) {
        this.restarting = restarting;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }
}
