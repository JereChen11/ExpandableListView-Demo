package com.example.jere.expandablelistview;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.jere.expandablelistview.model.ChildItem;
import com.example.jere.expandablelistview.model.GroupItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ExpandableListRepository {
    private Context mContext;
    private static ExpandableListRepository mExpandableListRepository;
    private SharedPreferences mPreferences;


    public static ExpandableListRepository getRepository(Context context) {
        if (mExpandableListRepository == null) {
            synchronized (ExpandableListRepository.class) {
                if (mExpandableListRepository == null) {
                    mExpandableListRepository = new ExpandableListRepository(context);
                }
            }
        }
        return mExpandableListRepository;
    }

    public ExpandableListRepository(Context context) {
        mContext = context;
        mPreferences = context.getApplicationContext().getSharedPreferences("expandable_list_preference", Context.MODE_PRIVATE);
    }

    public List<GroupItem> getDummyGroupListData() {
        ChildItem childItemOne = new ChildItem(100, "Child-Item-One");
        ChildItem childItemTwo = new ChildItem(101, "Child-Item-Two");
        ChildItem childItemThree = new ChildItem(102, "Child-Item-Three");
        List<ChildItem> childItemsListData = Arrays.asList(childItemOne, childItemTwo, childItemThree);

        GroupItem groupItemOne = new GroupItem(0, "Group-Item-One", childItemsListData);
        GroupItem groupItemTwo = new GroupItem(1, "Group-Item-Two", childItemsListData);
        GroupItem groupItemThree = new GroupItem(2, "Group-Item-Three", childItemsListData);
        GroupItem groupItemFour = new GroupItem(3, "Group-Item-Four", childItemsListData);
        GroupItem groupItemFive = new GroupItem(4, "Group-Item-Five", childItemsListData);
        return Arrays.asList(groupItemOne, groupItemTwo, groupItemThree, groupItemFour, groupItemFive);
    }

//    public HashMap<String, List<ChildItem>> getDummyChildMapData(List<GroupItem> groupListData) {
//        HashMap<String, List<ChildItem>> childMapData = new HashMap<>();
//        for (int i = 0; i < groupListData.size(); i++) {
//            ChildItem childItemOne = new ChildItem("Child-Item-One");
//            ChildItem childItemTwo = new ChildItem("Child-Item-Two");
//            ChildItem childItemThree = new ChildItem("Child-Item-Three");
//
//            List<ChildItem> childListData = Arrays.asList(childItemOne, childItemTwo, childItemThree);
//
//            String childMapDataKey = groupListData.get(i).getName();
//            childMapData.put(childMapDataKey, childListData);
//        }
//        return childMapData;
//    }

}
