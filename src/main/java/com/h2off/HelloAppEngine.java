package com.h2off;

import com.h2off.controllers.TestController;
import com.h2off.endpoints.Endpoint;
import com.h2off.endpoints.Endpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import spark.servlet.SparkApplication;
import spark.servlet.SparkFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

public class HelloAppEngine extends SparkFilter implements SparkApplication {

    public static Logger log = LoggerFactory.getLogger(HelloAppEngine.class);

    @Override
    protected SparkApplication getApplication(FilterConfig filterConfig) throws ServletException {
        return this;
    }

    @Override
    public void init() {
        TestController testController = new TestController();
        get("/api/v1/test", Endpoints.from(testController, "test"));
    }

    private void get(String path, Endpoint endpoint) {
        Spark.get(new StandardRoute(path, endpoint));
    }

}
