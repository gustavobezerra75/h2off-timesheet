package com.h2off.controllers;

import com.h2off.models.Job;
import com.h2off.services.TimesheetService;
import spark.Request;
import spark.Response;

import java.util.List;

public class TimesheetController {
    private TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    public List<Job> getJobs(Request request, Response response) {
        return timesheetService.getJobs();
    }
}
