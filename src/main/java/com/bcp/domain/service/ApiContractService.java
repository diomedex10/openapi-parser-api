package com.bcp.domain.service;

import com.bcp.domain.entity.swagger.Api;
import com.bcp.domain.entity.swagger.ApiEndpoint;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiContractService {
    private Api api;

    private final String TYPE_NAME_EXPERIENCE = "experience";
    private final String TYPE_NAME_BUSINESS = "business";
    private final String TYPE_NAME_CORE = "core";

    private final String TYPE_CODE_EXPERIENCE = "ux";
    private final String TYPE_CODE_BUSINESS = "bs";
    private final String TYPE_CODE_CORE = "cr";

    private final String NAME_TAG_ID = "x-bcp-api-id";
    private final String NAME_TAG_TYPE = "x-bcp-api-type";
    private final String NAME_TAG_OWNER = "x-bcp-api-owner";

    public ApiContractService(OpenAPI openApi){
        this.setContractApi(openApi);
    }

    public void setContractApi(OpenAPI openApi){
        String typeApi = (String)openApi.getExtensions().get(NAME_TAG_TYPE);
        switch (typeApi.toLowerCase()){
            case TYPE_CODE_BUSINESS:
                this.setContractApiBusiness(openApi);
            break;
            case TYPE_CODE_EXPERIENCE:
                this.setContractApiExperience(openApi);
            break;
            case TYPE_CODE_CORE:
                this.setContractApiCore(openApi);
            break;
        }
    }

    private void setContractApiBusiness(OpenAPI openApi){
        api = new Api();

        String version = openApi.getInfo().getVersion();
        System.out.println(version);
        String[] parts = version.split("\\.");

        String versionMajorUri = "v" + parts[0];

        api.setVersionOpenApi(openApi.getOpenapi());
        api.setApiId((String)openApi.getExtensions().get(NAME_TAG_ID));
        api.setVersion(version);
        api.setVersionMajor(parts[0]);
        api.setVersionMajorUri(versionMajorUri);
        api.setType((String)openApi.getExtensions().get(NAME_TAG_TYPE));
        api.setApiIdPAPI((api.getType()).toLowerCase()+ "-" +api.getApiId()+ "-" +api.getVersionMajorUri());
        api.setTitle((openApi.getInfo().getTitle()));
        api.setDescription(openApi.getInfo().getDescription());
        api.setTypeName(TYPE_NAME_BUSINESS);
        api.setOwner((String)openApi.getExtensions().get(NAME_TAG_OWNER));

        openApi.getServers().forEach((item) -> {
            api.setServerUrl(item.getUrl());
            api.setServerUrlDescription(item.getDescription());
        });

        String serverUrl = StringUtils.stripStart(api.getServerUrl(), "/");
        String[] bianParts = (serverUrl).split("/");
        api.setBusinessDomain(bianParts[0]);
        api.setServiceDomain(bianParts[1]);

        api.setApiEndpointList(this.getEndpoints(openApi));

        this.showResult(api);
        //System.out.println(api.toString()); System.exit(0);
    }

    private void setContractApiExperience(OpenAPI openApi){
        api = new Api();

        String version = openApi.getInfo().getVersion();
        System.out.println(version);
        String[] parts = version.split("\\.");

        String versionMajorUri = "v" + parts[0];

        api.setVersionOpenApi(openApi.getOpenapi());
        api.setApiId((String)openApi.getExtensions().get(NAME_TAG_ID));
        api.setVersion(version);
        api.setVersionMajor(parts[0]);
        api.setVersionMajorUri(versionMajorUri);
        api.setType((String)openApi.getExtensions().get(NAME_TAG_TYPE));
        api.setApiIdPAPI((api.getType()).toLowerCase()+ "-" +api.getApiId()+ "-" +api.getVersionMajorUri());
        api.setTitle((openApi.getInfo().getTitle()));
        api.setDescription(openApi.getInfo().getDescription());
        api.setTypeName(TYPE_NAME_EXPERIENCE);
        api.setOwner("");
        api.setBusinessDomain(null);
        api.setServiceDomain(null);

        openApi.getServers().forEach((item) -> {
            api.setServerUrl(item.getUrl());
            api.setServerUrlDescription(item.getDescription());
        });

        api.setOwner((String)openApi.getExtensions().get(NAME_TAG_OWNER));

        api.setApiEndpointList(this.getEndpoints(openApi));

        this.showResult(api);
    }

    private void setContractApiCore(OpenAPI openApi){

    }

    private List<ApiEndpoint> getEndpoints(OpenAPI openApi){
        List<ApiEndpoint> listApiEndpoint = new ArrayList<>();

        openApi.getPaths().forEach((key, item) -> {
            Operation operation = null;
            String httpMethod = null;
            if(item.getPost() != null) {
                operation = item.getPost();
                httpMethod = "POST";
            } else if (item.getGet() != null) {
                operation = item.getGet();
                httpMethod = "GET";
            } else if (item.getPut() != null) {
                operation = item.getPut();
                httpMethod = "PUT";
            } else if (item.getPatch() != null) {
                operation = item.getPatch();
                httpMethod = "PATCH";
            } else if (item.getDelete() != null) {
                operation = item.getDelete();
                httpMethod = "DELETE";
            }

            if(operation != null){
                ApiEndpoint apiEndpoint = new ApiEndpoint();
                apiEndpoint.setHttpMethod(httpMethod);
                apiEndpoint.setDescription(operation.getSummary());
                apiEndpoint.setDescription(operation.getSummary());
                apiEndpoint.setName(operation.getSummary());
                apiEndpoint.setOperationId(operation.getOperationId());
                apiEndpoint.setPath(key);
                apiEndpoint.setCreatedAt(LocalDateTime.now().toDateTime());
                listApiEndpoint.add(apiEndpoint);
            }
        });

        return listApiEndpoint;
    }

    private void showResult(Api api){
        System.out.println("INFORMACION API");

        System.out.println("\n OpenApi: " + api.getVersionOpenApi());
        System.out.println(" apiId: " + api.getApiId());
        System.out.println(" apiIdPAPI: " + api.getApiIdPAPI());
        System.out.println(" version: " + api.getVersion());
        System.out.println(" versionMajor: " + api.getVersionMajor());
        System.out.println(" versionMajorUri: " + api.getVersionMajorUri());
        System.out.println(" title: " + api.getTitle());
        System.out.println(" Description: " + api.getDescription());
        System.out.println(" type: " + api.getType());
        System.out.println(" typeName: " + api.getTypeName());
        System.out.println(" owner: " + api.getOwner());
        System.out.println(" serverUrl: " + api.getServerUrl());
        System.out.println(" serverUrlDescription: " + api.getServerUrlDescription());
        System.out.println(" businessDomain: " + api.getBusinessDomain());
        System.out.println(" serviceDomain: " + api.getServiceDomain());
        System.out.println(" Creado: " + api.getCreatedAt() + " \n");

        List<ApiEndpoint> apiEndpointList = api.getApiEndpointList();

        System.out.println("\nENDPOINTS");
        apiEndpointList.forEach((ApiEndpoint apiEndpoint)->{
            System.out.println("\nHttp Method: " + apiEndpoint.getHttpMethod());
            System.out.println("Path : " + apiEndpoint.getPath());
            System.out.println("Description : " + apiEndpoint.getDescription());
            System.out.println("Name : " + apiEndpoint.getName());
            System.out.println("OperationId : " + apiEndpoint.getOperationId());
            System.out.println("Creado :" + apiEndpoint.getCreatedAt());
        });
    }
}
