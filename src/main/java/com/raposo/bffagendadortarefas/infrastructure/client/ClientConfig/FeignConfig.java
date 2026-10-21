package com.raposo.bffagendadortarefas.infrastructure.client.ClientConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public FeignError feignError(){

        return new FeignError();
    }
}
