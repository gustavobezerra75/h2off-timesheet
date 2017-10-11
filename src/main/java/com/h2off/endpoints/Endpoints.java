package com.h2off.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Endpoints {
    public static Logger log = LoggerFactory.getLogger(Endpoints.class);

    public static Endpoint from(final Object controller, String method) {
        if (controller == null) {
            throw new NullPointerException("Controller object cannot be null");
        }

        final Method m;
        try {
            m = controller.getClass().getMethod(method, Request.class, Response.class);
        } catch (Exception e) {
            throw new RuntimeException("Unable to bind to method '" + controller.getClass().getName() + "#" + method + "'", e);
        }

        return (request, response) -> {
            try {
                return m.invoke(controller, request, response);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Controller method invocation failed", e);
            } catch (InvocationTargetException e) {
                if (e.getTargetException() instanceof RuntimeException) {
                    throw (RuntimeException) e.getTargetException();
                } else {
                    throw new RuntimeException("Controller method invocation failed", e);
                }
            }
        };
    }

}
