package com.bcp.domain.entity.papi;

import java.util.List;

public class Api {
    private String apiId;
    private String version;
    private String title;
    private String description;
    private String type;
    private String typeName;
    private String owner;
    private String serverUrl;
    private String versionAtlasFramework;
    private List<ApiEndpoint> apiEndpointList;

    public Api(String apiId, String version, String title, String description, String type,
               String typeName, String owner, String serverUrl, String versionAtlasFramework,
               List<ApiEndpoint> apiEndpointList) {
        this.apiId = apiId;
        this.version = version;
        this.title = title;
        this.description = description;
        this.type = type;
        this.typeName = typeName;
        this.owner = owner;
        this.serverUrl = serverUrl;
        this.versionAtlasFramework = versionAtlasFramework;
        this.apiEndpointList = apiEndpointList;
    }
}