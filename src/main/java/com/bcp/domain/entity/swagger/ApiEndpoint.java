package com.bcp.domain.entity.swagger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiEndpoint {
    private String httpMethod;
    private String description;
    private String name;
    private String operationId;
    private String path;
    private DateTime createdAt;

   /* @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }*/
}