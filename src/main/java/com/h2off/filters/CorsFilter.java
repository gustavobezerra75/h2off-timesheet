package com.h2off.filters;

import com.google.common.net.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Filter;
import spark.Request;
import spark.Response;

public class CorsFilter extends Filter {
    
    private static Logger log = LoggerFactory.getLogger(CorsFilter.class);
    
    @Override
    public String getAcceptType() {
        return MediaType.ANY_TYPE.toString();
    }
    
    @Override
    public void handle(Request request, Response response) {
        response.header("Access-Control-Allow-Origin", "*");
    }

}
