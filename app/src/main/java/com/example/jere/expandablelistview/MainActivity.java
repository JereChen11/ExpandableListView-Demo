package com.example.jere.expandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.jere.expandablelistview.model.ExpandableListViewChildItem;
import com.example.jere.expandablelistview.model.ExpandableListViewGroupItem;
import com.example.jere.expandablelistview.model.ExpandableListViewModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author jere
 */
public class MainActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListViewAdapter;
    private ExpandableListViewModel mExpandableListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExpandableListViewModel = new ExpandableListViewModel(getApplication());

        mExpandableListView = findViewById(R.id.elv);

        List<ExpandableListViewGroupItem> groupListData = getDummyGroupListData();
        HashMap<String, List<ExpandableListViewChildItem>> childMapData = getDummyChildMapData(groupListData);
        mExpandableListViewAdapter = new MyExpandableListViewAdapter(this, groupListData, childMapData);

        mExpandableListView.setAdapter(mExpandableListViewAdapter);
    }

    public List<ExpandableListViewGroupItem> getDummyGroupListData() {
        ExpandableListViewGroupItem groupItemOne = new ExpandableListViewGroupItem("Group-Item-One");
        ExpandableListViewGroupItem groupItemTwo = new ExpandableListViewGroupItem("Group-Item-Two");
        ExpandableListViewGroupItem groupItemThree = new ExpandableListViewGroupItem("Group-Item-Three");
        ExpandableListViewGroupItem groupItemFour = new ExpandableListViewGroupItem("Group-Item-Four");
        ExpandableListViewGroupItem groupItemFive = new ExpandableListViewGroupItem("Group-Item-Five");
        return Arrays.asList(groupItemOne, groupItemTwo, groupItemThree, groupItemFour, groupItemFive);
    }

    public HashMap<String, List<ExpandableListViewChildItem>> getDummyChildMapData(List<ExpandableListViewGroupItem> groupListData) {
        HashMap<String, List<ExpandableListViewChildItem>> childMapData = new HashMap<>();
        for (int i = 0; i < groupListData.size(); i++) {
            ExpandableListViewChildItem childItemOne = new ExpandableListViewChildItem("Child-Item-One");
            ExpandableListViewChildItem childItemTwo = new ExpandableListViewChildItem("Child-Item-Two");
            ExpandableListViewChildItem childItemThree = new ExpandableListViewChildItem("Child-Item-Three");

            List<ExpandableListViewChildItem> childListData = Arrays.asList(childItemOne, childItemTwo, childItemThree);

            String childMapDataKey = groupListData.get(i).getGroupName();
            childMapData.put(childMapDataKey, childListData);
        }
        return childMapData;
    }
}
