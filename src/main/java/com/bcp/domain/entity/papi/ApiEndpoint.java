package com.bcp.domain.entity.papi;

import org.joda.time.DateTime;

public class ApiEndpoint {
    private String httpMethod;
    private String description;
    private String name;
    private String operationId;
    private String uri;
    private DateTime stateModifiedAt;

    public ApiEndpoint(String httpMethod, String description, String name,
                       String operationId, String uri, DateTime stateModifiedAt) {
        this.httpMethod = httpMethod;
        this.description = description;
        this.name = name;
        this.operationId = operationId;
        this.uri = uri;
        this.stateModifiedAt = stateModifiedAt;
    }
}
