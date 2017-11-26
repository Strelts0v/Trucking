package com.itechart.trucking.config;

import com.itechart.trucking.auth.encoder.SHA256PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public SHA256PasswordEncoder sha256PasswordEncoder() {
        return new SHA256PasswordEncoder();
    }

}
