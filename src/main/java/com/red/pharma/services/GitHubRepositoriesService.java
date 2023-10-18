package com.red.pharma.services;

import com.red.pharma.dtos.GitHubRepositoryDTO;

/**
 * GitHubRepositoriesService Interface
 * **/
public interface GitHubRepositoriesService {
    GitHubRepositoryDTO fetchRepositories(
            String uri);
}
