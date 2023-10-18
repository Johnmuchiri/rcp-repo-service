package com.red.pharma.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.red.pharma.controllers.RequestUrlBuilder;
import com.red.pharma.dtos.GitHubRepositoryDTO;

public class TestHelper {
    public static  final String base_url = "https://api.github.com/search/repositories";
    public static String createGitHubResponse() throws JsonProcessingException {
        GitHubRepositoryDTO repositoryDTO  = GitHubRepositoryDTO.builder().build();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(repositoryDTO);
    }
    public static String createRequestUrl(){
        return RequestUrlBuilder.createRequestUrl("2010-01","java", "stars","desc", 0,1, base_url);
    }
}
