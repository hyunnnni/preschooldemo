package com.preschool.preschooldemo.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

public class WebMvcConfiguration implements WebMvcConfigurer {
    private final String imgFolder;//yaml에 있는 file.dir과 연결

    public WebMvcConfiguration(@Value("${file.dir}")String imgFolder){
        this.imgFolder = imgFolder;
    }//final은 명시적 초기화 외엔 생성자로 밖에 못 넣는다
    //여기선 애노테이션을 사용하지 못하기 때문에 안된다.
    //이미지가 파일을 저장한 형태라 띄우기 위해서 작성하는 부분 절대경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/pic/**").addResourceLocations("file:"+ imgFolder);
        //("/pic/**")로 요청이 오면 static에 pic이란 폴더가 있는지 찾지말고 file:경로로 된 pic을 찾아 연결
        //이렇게 작성 시 컨트롤러 >> 외부 >> 스태틱 순으로 탐색되게 함
        //원래는 컨트롤러 >> 스태틱 순으로 탐색
        //압축일 때 외부에 파일을 저장함 그래서 외부에 있는 파일을 가져와서 응답에 사용할 수 있는 작업을 해준 것

        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/static/**")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);
                        // If we actually hit a file, serve that. This is stuff like .js and .css files.
                        if (requestedResource.exists() && requestedResource.isReadable()) {
                            return requestedResource;
                        }
                        // Anything else returns the index.
                        return new ClassPathResource("/static/index.html");
                    }
                });
    }
}
