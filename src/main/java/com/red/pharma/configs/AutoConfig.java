package com.red.pharma.configs;

import com.red.pharma.services.GitHubRepositoriesService;
import com.red.pharma.services.GitHubRepositoriesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfig {
    @Bean
    public GitHubRepositoriesService gitHubRepositoriesService(){
        return new GitHubRepositoriesServiceImpl();
    }
}
