package com.red.pharma.controller;

import com.red.pharma.controllers.RepositoriesController;
import com.red.pharma.dtos.GitHubRepositoryDTO;
import com.red.pharma.exception.RequestValidationException;
import com.red.pharma.services.GitHubRepositoriesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RepositoriesController.class)
@Import(RepositoriesController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RepositoriesControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GitHubRepositoriesService gitHubRepositoriesService;

    @Test
    public void testFetchRepositories_successful() throws Exception {
        String url ="/api/v1/repositories?created=2010-01&language=java&sort=stars&order=desc&page=0&per_page=1";
        when(gitHubRepositoriesService.fetchRepositories(any())).thenReturn(
                GitHubRepositoryDTO.builder().items(
                        new ArrayList<>()
                ).total_count(5).build());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFetchRepositories_InvalidRequestUrlThrows() throws Exception {
        String url ="/api/v1/repositories?created=2010-01&language=java&sort=stars&order=desc&page=0&per_page=1";
        when(gitHubRepositoriesService.fetchRepositories(any())).thenThrow(new RequestValidationException("Invalid url"));
        mockMvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
