package com.h2off.controllers;

import spark.Request;
import spark.Response;

public class TestController {

    public String test(Request request, Response response) {
        return "Hello";
    }
}
