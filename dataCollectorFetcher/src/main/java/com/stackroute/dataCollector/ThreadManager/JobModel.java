package com.stackroute.dataCollector.ThreadManager;

public class JobModel {
    private String userId;
    private String agentType;
    private String agentAPI;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getAgentAPI() {
        return agentAPI;
    }

    public void setAgentAPI(String agentAPI) {
        this.agentAPI = agentAPI;
    }

    @Override
    public String toString() {
        return "{" +
                "userId='" + userId + '\'' +
                ", agentType='" + agentType + '\'' +
                ", agentAPI='" + agentAPI + '\'' +
                '}';
    }
}
