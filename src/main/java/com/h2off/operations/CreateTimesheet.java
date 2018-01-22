package com.h2off.operations;

import com.h2off.models.TimesheetItem;
import org.joda.time.DateTime;

import java.util.List;

public class CreateTimesheet {
    private Long id;
    private DateTime createdAt;
    private String fromDate;
    private String toDate;
    private List<TimesheetItem> timesheetItemsList;
    private Integer totalWeekHours;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<TimesheetItem> getTimesheetItemsList() {
        return timesheetItemsList;
    }

    public void setTimesheetItemsList(List<TimesheetItem> timesheetItemsList) {
        this.timesheetItemsList = timesheetItemsList;
    }

    public Integer getTotalWeekHours() {
        return totalWeekHours;
    }

    public void setTotalWeekHours(Integer totalWeekHours) {
        this.totalWeekHours = totalWeekHours;
    }
}
