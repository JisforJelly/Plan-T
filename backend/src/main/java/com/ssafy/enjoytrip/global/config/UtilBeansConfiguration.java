package com.ssafy.enjoytrip.global.config;

import com.ssafy.enjoytrip.global.code.meta.ContentTypeCode;
import com.ssafy.enjoytrip.global.code.meta.GugunCode;
import com.ssafy.enjoytrip.global.code.meta.MetaDataFactory;
import com.ssafy.enjoytrip.global.code.meta.SidoCode;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UtilBeansConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MetaDataFactory metaDataFactory() {
        MetaDataFactory factory = new MetaDataFactory();
        factory.put("sido", SidoCode.class);
        factory.put("gugun", GugunCode.class);
        factory.put("content", ContentTypeCode.class);
        return factory;
    }
}
