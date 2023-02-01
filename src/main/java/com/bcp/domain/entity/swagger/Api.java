package com.bcp.domain.entity.swagger;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Api {
    private String versionOpenApi;
    private String apiId;
    private String apiIdPAPI;
    private String version;
    private String versionMajor;
    private String versionMajorUri;
    private String title;
    private String description;
    private String type;
    private String typeName;
    private String owner;
    private String serverUrl;
    private String serverUrlDescription;
    private String businessDomain;
    private String serviceDomain;
    private DateTime createdAt;
    private List<ApiEndpoint> apiEndpointList;




   /* @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }*/
}