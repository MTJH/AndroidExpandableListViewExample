package com.example.ExpandableListExample;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProjectDayEntry {
    private int projectId;
    private Calendar calendar;
    private int hours;
    private String comment;

    private static final String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    public ProjectDayEntry(int projectId, Calendar calendar, int hours, String comment) {
        this.projectId = projectId;

        this.calendar = Calendar.getInstance();
        this.calendar.setTime(calendar.getTime());

        this.hours = hours;
        this.comment = comment;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectDayEntry that = (ProjectDayEntry) o;

        if (hours != that.hours) return false;
        if (projectId != that.projectId) return false;
        if (!calendar.equals(that.calendar)) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 31 * result + calendar.hashCode();
        result = 31 * result + hours;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProjectDayEntry{" +
                "projectId=" + projectId +
                ", calendar=" + calendar +
                ", hours=" + hours +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getDayText() {
        return days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public String getDateText() {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd");
        return df.format(calendar.getTime());
    }

    public String getDisplayText() {
        return days[calendar.get(Calendar.DAY_OF_WEEK) - 1] + " - " + hours + " hours";
    }
}
