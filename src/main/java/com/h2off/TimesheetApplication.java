package com.h2off;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;
import com.h2off.controllers.CorsOptionsController;
import com.h2off.controllers.TimesheetController;
import com.h2off.endpoints.Endpoint;
import com.h2off.endpoints.Endpoints;
import com.h2off.filters.CorsFilter;
import com.h2off.models.Job;
import com.h2off.models.Staff;
import com.h2off.models.Supplier;
import com.h2off.models.Timesheet;
import com.h2off.models.TimesheetItem;
import com.h2off.services.TimesheetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import spark.servlet.SparkApplication;
import spark.servlet.SparkFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import static spark.Spark.before;

public class TimesheetApplication extends SparkFilter implements SparkApplication {

    public static Logger log = LoggerFactory.getLogger(TimesheetApplication.class);

    static {
        JodaTimeTranslators.add(ObjectifyService.factory());
        ObjectifyService.register(Job.class);
        ObjectifyService.register(Timesheet.class);
        ObjectifyService.register(Staff.class);
        ObjectifyService.register(Supplier.class);
        ObjectifyService.register(TimesheetItem.class);
    }

    @Override
    protected SparkApplication getApplication(FilterConfig filterConfig) throws ServletException {
        return this;
    }

    @Override
    public void init() {
        TimesheetService timesheetService = new TimesheetService();
        TimesheetController timesheetController = new TimesheetController(timesheetService);

        // Cors thingys
        before(new CorsFilter());

        // cors options requests
        Spark.options(new CorsOptionsController("*"));

        get("/api/v1/jobs", Endpoints.from(timesheetController, "getJobs"));
    }

    private void get(String path, Endpoint endpoint) {
        Spark.get(new StandardRoute(path, endpoint));
    }

    private void post(String path, final Endpoint endpoint) {
        Spark.post(new StandardRoute(path, endpoint));
    }

    private void put(String path, final Endpoint endpoint) {
        Spark.put(new StandardRoute(path, endpoint));
    }

    private void patch(String path, final Endpoint endpoint) {
        Spark.patch(new StandardRoute(path, endpoint));
    }

    private void delete(String path, final Endpoint endpoint) {
        Spark.delete(new StandardRoute(path, endpoint));
    }
}
