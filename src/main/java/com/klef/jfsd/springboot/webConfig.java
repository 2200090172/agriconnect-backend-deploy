package com.klef.jfsd.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class webConfig implements WebMvcConfigurer 
{
    @Override
    public void addCorsMappings(CorsRegistry registry) 
    {
    	registry.addMapping("/**") // Allow CORS 
            .allowedOrigins(
                    "http://localhost:3000",
                    "https://agriconnect-deploy-ihoebi8ze-ch-aravinds-projects-a4857e65.vercel.app",
                    "https://agriconnect-deploy-rd09qa79o-ch-aravinds-projects-a4857e65.vercel.app",
                "https://agriconnect-deploy-9cv6hvw8f-ch-aravinds-projects-a4857e65.vercel.app"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
