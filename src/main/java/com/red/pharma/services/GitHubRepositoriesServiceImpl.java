package com.red.pharma.services;

import com.red.pharma.dtos.GitHubRepositoryDTO;
import com.red.pharma.exception.RequestValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

public class GitHubRepositoriesServiceImpl implements GitHubRepositoriesService {
    private static final Logger log = LoggerFactory.getLogger(GitHubRepositoriesServiceImpl.class);

//    @SneakyThrows
    public GitHubRepositoryDTO fetchRepositories(String uri){
        try {
            this.isValidURL(uri);
        } catch (MalformedURLException e) {
            throw new RequestValidationException("Ivalid url provided");
        }
        log.info("Request url: "+ uri + " isValid? "+ true);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(uri, GitHubRepositoryDTO.class).getBody();
    }

   private void isValidURL(String url) throws MalformedURLException{
            new URL(url);
    }
}
