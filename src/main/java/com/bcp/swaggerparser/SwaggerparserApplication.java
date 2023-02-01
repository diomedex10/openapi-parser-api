package com.bcp.swaggerparser;

import com.bcp.domain.entity.swagger.Api;
import com.bcp.domain.service.ApiContractService;
import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.oas.models.PathItem;


import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.apache.catalina.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;

@SpringBootApplication
public class SwaggerparserApplication {
	private static OpenAPI openAPI;
	private Api api;

	public static void main(String[] args) {
		System.out.println("entro");
		openAPI = new OpenAPIV3Parser().read("./swaggerBS.json");

        ApiContractService service = new ApiContractService(openAPI);

		//SwaggerParseResult result = new OpenAPIParser().readContents("https://petstore3.swagger.io/api/v3/openapi.json", null, null);
		//OpenAPI openAPI = new OpenAPIV3Parser().read("https://petstore3.swagger.io/api/v3/openapi.json");
		//openAPI = new OpenAPIV3Parser().read("./swaggerBS.json");

		//openAPI = result.getOpenAPI();

		/*System.out.println("OpenApi Version: " + openAPI.getOpenapi());

		System.out.println("INFO: ");
		System.out.println("Version: " + openAPI.getInfo().getVersion());
		System.out.println("Title: " + openAPI.getInfo().getTitle());
		System.out.println("Description: " + openAPI.getInfo().getDescription());

		System.out.println("External DOCS: ");
		System.out.println("URL: " + openAPI.getExternalDocs().getUrl());
		System.out.println("Description: " + openAPI.getExternalDocs().getDescription());

		System.out.println("Servers: ");
		openAPI.getServers().forEach((item) -> {
			System.out.println("URL: " + item.getUrl());
			System.out.println("URL: " + item.getDescription());
		});


		System.out.println("Extensiones: " + openAPI.getExtensions().get("x-bcp-api-type"));
		System.out.println("Extensiones: " + openAPI.getExtensions());
*/

		/*for (String clave : (openAPI.getExtensions()).keySet()) {
			Object valor = (openAPI.getExtensions()).get(clave);
			System.out.println("Clave: " + clave + " --> Valor: " + valor);
		}*/
/*
		openAPI.getPaths().forEach((key, item) -> {
			//System.out.println(key);
			//System.out.println(item);

			if(item.getPost() != null) {
				System.out.println("*** POST (path) " + key+ " --> Summary"+ item.getPost().getSummary());
				//System.out.println(item.getPost().getDescription());
			}
			if(item.getGet() != null) {
				System.out.println("*** GET (path) " + key+ " --> Summary"+ item.getGet().getSummary());
				//System.out.println(item.getGet().getDescription());


			}

		});*/

		//SpringApplication.run(SwaggerparserApplication.class, args);
	}

}
