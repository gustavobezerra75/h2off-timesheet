package com.h2off.controllers;

import spark.Request;
import spark.Response;
import spark.Route;


public class CorsOptionsController extends Route {

    public CorsOptionsController(String path) {
        super(path);
    }
    
    
    @Override
    public Object handle(Request request, Response response) {
        
        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
        if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
        }

        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
        if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
        }
        
        // cache 10 mins - max in webkit 
        response.header("Access-Control-Max-Age", "600");
        
        return "OK";
        
    }
    
}
