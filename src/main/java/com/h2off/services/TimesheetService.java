package com.h2off.services;

import com.google.common.collect.Lists;
import com.h2off.models.Job;
import com.h2off.models.Staff;
import com.h2off.models.Supplier;
import com.h2off.models.Timesheet;
import com.h2off.operations.CreateTimesheet;
import org.joda.time.DateTime;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class TimesheetService {

    public List<Job> getJobs() {
        List<Job> jobList = Lists.newArrayList();//ofy().load().type(Job.class).list();
        Job job = new Job();
        job.setAddress("TETATETEAEATAETEAT");
        job.setCreatedAt(DateTime.now());
        job.setJobNumber("!@#!@#!@#!@#!@#!@!@#");
        job.setOngoing(true);
        Supplier supplier = new Supplier();
        supplier.setCoverageAllowed("yes");
        supplier.setName("AAAAAAAAAAAAAAAAAAAA");
        supplier.setProduct("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        supplier.setProductDetails("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        job.setSupplier(supplier);

        jobList.add(job);
        System.out.println("HELLO");
        /*if(jobList != null) {
            return jobList;
        }
*/
        return jobList;
    }

    public Timesheet createTimesheet(Long staffId, CreateTimesheet createTimesheet) {
        if(staffId == null) {
            return null;
        }

        Staff staff = ofy().load().type(Staff.class).id(staffId).now();

        if(staff != null && createTimesheet != null) {
            Timesheet timesheet = new Timesheet();
            timesheet.setTimesheetItemsList(createTimesheet.getTimesheetItemsList());
            timesheet.setFromDate(createTimesheet.getFromDate());
            timesheet.setToDate(createTimesheet.getToDate());
            timesheet.setCreatedAt(DateTime.now());
        }

        //TODO
        return null;
    }
}
