package com.h2off.endpoints;

import spark.Request;
import spark.Response;

public interface Endpoint {
    public Object handle (Request request, Response response);
}
