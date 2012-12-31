package com.example.ExpandableListExample;

import android.app.ExpandableListActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyListActivity extends ExpandableListActivity {

    ExpandableListAdapter mAdapter;
    List<ProjectWeek> projectWeeks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // todo - get the data
        projectWeeks = makeSampleProjectWeeks();

        // Set up our adapter
        mAdapter = new JellicoExpandableListAdapter(this, projectWeeks);
        setListAdapter(mAdapter);

        ExpandableListView listView = getExpandableListView();

        registerForContextMenu(listView);
    }

    private List<ProjectWeek> makeSampleProjectWeeks() {
        List<ProjectWeek> projects = new ArrayList<ProjectWeek>();

        Calendar startDate = Calendar.getInstance();

        try{
            startDate.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-30"));
        } catch(ParseException ex) {
            throw new RuntimeException(ex);
        }

        ProjectWeek week = new ProjectWeek(1, "Sample Project 1", startDate);
        projects.add(week);

        projects.add(new ProjectWeek(2, "Proj2", startDate));


        return projects;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Sample menu");
        menu.add(0, 0, 0, "Sample action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();

        String title = ((TextView) info.targetView).getText().toString();

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);
            Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos, Toast.LENGTH_SHORT).show();
            return true;
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
            Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

}

