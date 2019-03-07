package com.stackroute.datacollectorservice.model;

import org.springframework.stereotype.Component;

@Component
public class AgentUrl {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
