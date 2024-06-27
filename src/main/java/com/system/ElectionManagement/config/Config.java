package com.system.ElectionManagement.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${cloud.api.name}")
    private String apiName;
    @Value("${cloud.api.key}")
    private String apiKey;
    @Value("${cloud.api.secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", apiName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "resource_type", "auto"));
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
