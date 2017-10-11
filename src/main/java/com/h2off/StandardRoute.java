package com.h2off;

import com.google.common.net.MediaType;
import com.h2off.endpoints.Endpoint;
import com.h2off.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public class StandardRoute extends Route {

    private static Logger log = LoggerFactory.getLogger(StandardRoute.class);
    private Endpoint endpoint;

    protected StandardRoute(String path, Endpoint endpoint) {
        super(path);
        this.endpoint = endpoint;
    }

    @Override
    public Object handle(Request request, Response response) {
        response.type(MediaType.JSON_UTF_8.toString());

        try {

            Object result = endpoint.handle(request, response);
            return Serializer.toJson(result);

        } catch (NotFoundException e) {
            response.status(404);
            return "{ \"error\" : \"Not Found\" }";

        } catch (UnauthorizedException e) {
            response.status(401);
            return "{ \"error\" : \"Unauthorized\" }";

        } catch (ForbiddenException e) {
            response.status(403);
            return "{ \"error\" : \"Forbidden\" }";

        } catch (BadRequestException e) {
            response.status(400);
            return "{ \"error\" : \"Bad Request\", \"message\" : \"" + e.getMessage() + "\" }";

        } catch (ServiceException e) {
            response.status(400);
            if (e.getCode() != null) {
                return "{ \"error\" : \"Bad Request\", \"code\": \"" + e.getCode().name().toLowerCase() + "\", \"message\" : \"" + e.getMessage() + "\" }";
            } else {
                return "{ \"error\" : \"Bad Request\", \"message\" : \"" + e.getMessage() + "\" }";
            }

        } catch (Exception e) {
            log.error("Unhandled Exception", e);
            response.status(500);
            return String.format("{ \"error\" : \"%s\" }", e.getMessage());
        }
    }
}
