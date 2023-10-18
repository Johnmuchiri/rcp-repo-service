package com.red.pharma.controllers;

import com.red.pharma.services.GitHubRepositoriesService;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Observed
@RequestMapping(value ="/api/v1", consumes = {MediaType.APPLICATION_JSON_VALUE})
@SecurityRequirement(name = "X-API-KEY")
public class RepositoriesController  extends  BaseController{

    private final GitHubRepositoriesService gitHubRepositoriesService;

    @Value("${red.github_url}")
    private String url;

    public RepositoriesController(GitHubRepositoriesService gitHubRepositoriesService) {
        this.gitHubRepositoriesService = gitHubRepositoriesService;
    }

    @GetMapping(path= "/repositories", consumes = {MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity fetchRepositories(
            @RequestParam(name = "created",required = false, defaultValue = "2019-01") String created,
            @RequestParam(name= "language", required = false, defaultValue = "java") String language,
            @RequestParam(name = "sort",required = false, defaultValue = "stars") String sort,
            @RequestParam(name = "order",required = false, defaultValue = "desc") String order,
            @RequestParam(name = "page",required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "per_page",required = false, defaultValue = "50") Integer per_page
    ){
        String requestUrl = RequestUrlBuilder.createRequestUrl(created, language, sort,order,page,per_page, url);
        return ResponseEntity.ok(this.gitHubRepositoriesService.fetchRepositories(requestUrl));
    }
}
