package com.example.ExpandableListExample;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

public class JellicoExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<ProjectWeek> projectWeeks;

    public JellicoExpandableListAdapter(Context context, List<ProjectWeek> projectWeeks) {
        this.context = context;
        this.projectWeeks = projectWeeks;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return projectWeeks.get(groupPosition).getDayEntry(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getChildrenCount(int groupPosition) {
        return projectWeeks.get(groupPosition).getDayEntries().length;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ProjectDayEntry entry = (ProjectDayEntry) getChild(groupPosition, childPosition);

        View childView = convertOrMakeChildView(convertView);

        TextView dayTextView = (TextView) childView.findViewById(R.id.dayTextView);
        dayTextView.setText(entry.getDayText());

        TextView dateView = (TextView) childView.findViewById(R.id.dateView);
        dateView.setText(entry.getDateText());

        EditText hoursEditText = (EditText) childView.findViewById(R.id.hoursEditText);
        hoursEditText.setText("" + entry.getHours());

        return childView;
    }

    public Object getGroup(int groupPosition) {
        return projectWeeks.get(groupPosition);
    }

    public int getGroupCount() {
        return projectWeeks.size();
    }

    public long getGroupId(int groupPosition) {
        return projectWeeks.get(groupPosition).getProjectId();
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ProjectWeek week = (ProjectWeek) getGroup(groupPosition);

        GroupView groupView = convertOrMakeGroupView(convertView);
        groupView.setText(week.getDisplayText());

        return groupView;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public boolean hasStableIds() {
        return true;
    }

    protected GroupView convertOrMakeGroupView(View convertView) {
        return (convertView == null) ? new GroupView(context) : (GroupView) convertView;
    }

    protected View convertOrMakeChildView(View convertView) {

        View childView;

        if(convertView == null) {
            childView =  loadChildView();

            EditText hoursEditText = (EditText) childView.findViewById(R.id.hoursEditText);

            hoursEditText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
                public void onFocusChange(View v, boolean hasFocus){
                    if (hasFocus) {
                        ((EditText)v).selectAll();
                    }
                }
            });

        } else {
            childView = convertView;
        }

        return childView;
    }

    private class GroupView extends TextView {
        public GroupView(Context context) {
            super(context);

            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 64); //ViewGroup.LayoutParams.WRAP_CONTENT);

            setLayoutParams(lp);

            // Center the text vertically
            setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

            // Set the text starting position
            setPadding(64, 0, 0, 0);
        }
    }

    private View loadChildView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View child = inflater.inflate(R.layout.time_entry, null);
        return child;
    }
}