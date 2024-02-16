package com.preschool.preschooldemo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final String imgFolder;

    public WebMvcConfiguration(@Value("${file.dir}") String imgFolder) { //value : yaml에 있는 file.dir에 있는 속성값을 di 해줌  imgFolder에 담아줌
        this.imgFolder = imgFolder;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/pic/**") //pic으로 요청이 오면  컨트롤러  -> 외부 경로 -> 스태틱 순
                .addResourceLocations("file:"+imgFolder); //외부 경로인 이 경로까지 찾아라
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/static/**")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);
                        if (requestedResource.exists() && requestedResource.isReadable()) {
                            return requestedResource;
                        }

                        return new ClassPathResource("/static/index.html");
                    }
                });
    }
}