package com.example.ExpandableListExample;

import java.util.Calendar;

public class ProjectWeek {
    private int projectId;
    private String name;
    ProjectDayEntry[] dayEntries;

    public ProjectWeek(final int projectId, final String name, final Calendar startDate) {
        this.projectId = projectId;
        this.name = name;
        createDayEntries(startDate);
    }

    public ProjectWeek(final int projectId, final String name, ProjectDayEntry[] dayEntries) {
        this.projectId = projectId;
        this.name = name;

        // todo - check validity of day entries - i.e. start on sunday, have 7 entries, have appropriate dates

        this.dayEntries = dayEntries;
    }

    private void createDayEntries(final Calendar startDate) {
        if(startDate == null || startDate.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            throw new RuntimeException("Project Week must start on Sunday");
        }

        dayEntries = new ProjectDayEntry[7];

        Calendar entryDate = Calendar.getInstance();
        entryDate.setTime(startDate.getTime());

        for(int i = 0; i < 7; i++) {
            ProjectDayEntry entry = new ProjectDayEntry(projectId, entryDate, 0, null);
            dayEntries[i] = entry;
            entryDate.add(Calendar.DATE, 1);
        }
    }

    public int getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectDayEntry[] getDayEntries() {
        return dayEntries;
    }

    public ProjectDayEntry getDayEntry(int index) {
        return dayEntries[index];
    }

    public void setDayEntries(ProjectDayEntry[] dayEntries) {
        this.dayEntries = dayEntries;
    }

    public String getDisplayText() {
        return name;
    }
}
