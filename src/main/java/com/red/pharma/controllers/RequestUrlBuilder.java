package com.red.pharma.controllers;

import java.util.HashMap;
import java.util.Map;

public class RequestUrlBuilder {
    public static String createRequestUrl(String created,
                            String language,
                            String sort,
                            String order,
                            Integer page,
                            Integer perPage,
                            String url){

        var uri = new StringBuilder(url);
        Map<String, Object> queryParams = new HashMap<>();
        String query = "";
        if(created!=null){
            query =query + "created:"+created;
        }
        if(language!=null){
            query =query + " language:"+language;
        }
        if (!query.equalsIgnoreCase("")){
            queryParams.put("q=", query);
        }
        if (sort != null) {
            queryParams.put("sort=", sort);
        }
        if (order != null) {
            queryParams.put("order=", order);
        }
        if (page != null) {
            queryParams.put("page=", page);
        }
        if (perPage != null) {
            queryParams.put("per_page=", perPage);
        }

        if (!queryParams.isEmpty()) {
            uri.append("?");

            queryParams.forEach((k, v) -> {
                uri.append(k).append(v).append("&");
            });
        }
        String finalUrl = uri.toString();
        if(finalUrl.endsWith("&")){
            finalUrl =finalUrl.substring(0, finalUrl.length()-1);
        }
        return finalUrl;
    }
}
