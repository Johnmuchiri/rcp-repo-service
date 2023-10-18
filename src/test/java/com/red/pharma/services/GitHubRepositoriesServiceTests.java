package com.red.pharma.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.red.pharma.controllers.RequestUrlBuilder;
import com.red.pharma.exception.RequestValidationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static com.red.pharma.services.TestHelper.base_url;
import static com.red.pharma.services.TestHelper.createRequestUrl;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GitHubRepositoriesServiceTests {


    private GitHubRepositoriesService gitHubRepositoriesService;

    @BeforeAll
    public void setUp(){
        gitHubRepositoriesService = new GitHubRepositoriesServiceImpl();
    }

    @Test
    public void test_FetchTop2RepositoriesInvalidUrlThrows(){
        assertThrows(RequestValidationException.class, () -> {
            gitHubRepositoriesService.fetchRepositories("invalid url");
        });
        }

    @Test
    public void test_FetchTop2RepositoriesValidUrlReturnsSuccess() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockServer.expect(requestTo(createRequestUrl())).andRespond(withSuccess(TestHelper.createGitHubResponse(), MediaType.APPLICATION_JSON));
        assertNotNull(gitHubRepositoriesService.fetchRepositories(createRequestUrl()));
    }

    @Test
    public void test_requestBuilder(){
        String url= RequestUrlBuilder.createRequestUrl("2010-01","java", "stars","desc", 0,1, base_url);
        String expectedUrl = "https://api.github.com/search/repositories?order=desc&sort=stars&page=0&q=created:2010-01 language:java&per_page=1";
        assertEquals(expectedUrl, url);
    }
}
