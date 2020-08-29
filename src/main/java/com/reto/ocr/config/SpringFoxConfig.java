package com.reto.ocr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Bean
    public Docket api() { 
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(getApiInfo());
		docket.useDefaultResponseMessages(false);
		ApiSelectorBuilder asb = getBuilder(docket);
		return asb.build();                                          
    }
	
	protected ApiInfo getApiInfo() {
		Contact contact = new Contact("RETOS", "https://github.com/kenyoJoel903/ms-ocr", "");
		ApiInfoBuilder api = new ApiInfoBuilder();
		api.title("PROCESAMIENTO DE IMAGENES");
		api.description("PROCESAMIENTO DE IMAGENES CON Tesseract");
		api.contact(contact);
		api.version("1.0.0");
		api.license("abc 2020");
		api.licenseUrl("");
		return api.build();
	}
	
	protected ApiSelectorBuilder getBuilder(Docket docket) {
		ApiSelectorBuilder asb = docket.select().apis(RequestHandlerSelectors.any());
		asb.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")));
		asb.paths(Predicates.not(PathSelectors.regex("/error.*")));
		return asb;
	}
	
	
}