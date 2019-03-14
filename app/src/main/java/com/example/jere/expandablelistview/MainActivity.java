package com.example.jere.expandablelistview;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.jere.expandablelistview.model.ChildItem;
import com.example.jere.expandablelistview.model.GroupItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.jere.expandablelistview.ExpandableListViewModel.NOT_SELECT_ANY;
import static com.example.jere.expandablelistview.ExpandableListViewModel.SELECT_ALL;
import static com.example.jere.expandablelistview.ExpandableListViewModel.SELECT_SOME;

/**
 * @author jere
 */
public class MainActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListViewAdapter;
    private ExpandableListViewModel mExpandableListViewModel;
    private ImageView mSelectAllIV;
    private Observer<String> selectAllBtnObserver = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String selectAllBtnStatus) {
            if (!TextUtils.isEmpty(selectAllBtnStatus)) {
                if (Objects.equals(selectAllBtnStatus, SELECT_ALL)) {
                    mSelectAllIV.setImageResource(R.drawable.icon_list_selected);
                } else if (Objects.requireNonNull(selectAllBtnStatus).equals(NOT_SELECT_ANY)){
                    mSelectAllIV.setImageResource(R.drawable.icon_list_unselect);
                } else {
                    mSelectAllIV.setImageResource(R.drawable.select_middle_icon);
                }
                ((MyExpandableListViewAdapter) mExpandableListViewAdapter).notifyDataSetChanged();
            }
        }
    };

//    private Observer<List<Boolean>> groupSelectStatusListObserver = new Observer<List<Boolean>>() {
//        @Override
//        public void onChanged(@Nullable List<Boolean> groupSelectStatusList) {
//            if (groupSelectStatusList != null) {
//                List<Integer> selectedIdsList = new ArrayList<>();
//
//                mExpandableListViewModel.saveGroupSelectedIdsList();
//            }
//        }
//    };

    private List<Integer> getGroupItemsSelectedIdsList() {
        List<Integer> groupItemSelectedIdList = new ArrayList<>();
        if (mExpandableListViewAdapter != null) {
            for (int i = 0; i < mExpandableListViewAdapter.getGroupCount(); i++) {
                GroupItem groupItem = (GroupItem) mExpandableListViewAdapter.getGroup(i);
                if (groupItem.isSelected()) {
                    groupItemSelectedIdList.add(groupItem.getId());
                }
            }
        } else {
            List<Integer> emptyList = new ArrayList<>();
            return emptyList;
        }
        return groupItemSelectedIdList;
    }

    private List<Integer> getChildItemsSelectedIdList() {
        List<Integer> childItemSelectedIdList = new ArrayList<>();
        if (mExpandableListViewAdapter != null) {
            for (int i = 0; i < mExpandableListViewAdapter.getGroupCount(); i++) {
                GroupItem groupItem = (GroupItem) mExpandableListViewAdapter.getGroup(i);
                List<ChildItem> secondLevelItemList = groupItem.getChildItemList();
                for (ChildItem secondLevelItem : secondLevelItemList) {
                    if (secondLevelItem.isSelected()) {
                        childItemSelectedIdList.add(secondLevelItem.getId());
                    }
                }
            }
        } else {
            List<Integer> emptyList = new ArrayList<>();
            return emptyList;
        }

        return childItemSelectedIdList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        mExpandableListViewModel = ViewModelProviders.of(this).get(ExpandableListViewModel.class);
        mExpandableListViewModel.getSelectedAllBtnStatus().observe(this, selectAllBtnObserver);
        mExpandableListViewModel.initGroupExpandStatusList();
//        mExpandableListViewModel.getGroupSelectedStatusList().observe(this, groupSelectStatusListObserver);


        List<GroupItem> groupListData = mExpandableListViewModel.getDummyGroupListData();
        mExpandableListViewAdapter = new MyExpandableListViewAdapter(this, groupListData, mExpandableListViewModel);

        mExpandableListView.setAdapter(mExpandableListViewAdapter);

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                List<Boolean> groupExpandStatusList = mExpandableListViewModel.getGroupExpandStatusList().getValue();
                if (groupExpandStatusList.get(groupPosition)) {
                    groupExpandStatusList.set(groupPosition, false);
                } else {
                    groupExpandStatusList.set(groupPosition, true);
                }
                mExpandableListViewModel.setGroupExpandStatusList(groupExpandStatusList);
                return false;
            }
        });


    }

    private void findView() {
        mExpandableListView = findViewById(R.id.elv);
        mSelectAllIV = findViewById(R.id.ivSelectAll);

        mSelectAllIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(mExpandableListViewModel.getSelectedAllBtnStatus().getValue(), SELECT_ALL)) {
                    mExpandableListViewModel.setSelectedAllBtnStatus(NOT_SELECT_ANY);
                } else {
                    mExpandableListViewModel.setSelectedAllBtnStatus(SELECT_ALL);
                }
            }
        });
    }

}
