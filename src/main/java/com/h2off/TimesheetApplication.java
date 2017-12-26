package com.h2off;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;
import com.h2off.controllers.TestController;
import com.h2off.endpoints.Endpoint;
import com.h2off.endpoints.Endpoints;
import com.h2off.models.Job;
import com.h2off.models.Staff;
import com.h2off.models.Supplier;
import com.h2off.models.Timesheet;
import com.h2off.models.TimesheetItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import spark.servlet.SparkApplication;
import spark.servlet.SparkFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

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
        TestController testController = new TestController();
        get("/api/v1/test", Endpoints.from(testController, "test"));
    }

    private void get(String path, Endpoint endpoint) {
        Spark.get(new StandardRoute(path, endpoint));
    }

}
