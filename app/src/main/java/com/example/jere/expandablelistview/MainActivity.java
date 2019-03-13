package com.example.jere.expandablelistview;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.jere.expandablelistview.model.ChildItem;
import com.example.jere.expandablelistview.model.GroupItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jere
 */
public class MainActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListViewAdapter;
    private ExpandableListViewModel mExpandableListViewModel;
    private ImageView mSelectAllIV;
    private Observer<Boolean> selectAllBtnObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            if (aBoolean) {
                mSelectAllIV.setImageResource(R.drawable.icon_list_selected);
            } else {
                mSelectAllIV.setImageResource(R.drawable.icon_list_unselect);
            }
            ((MyExpandableListViewAdapter) mExpandableListViewAdapter).notifyDataSetChanged();
        }
    };

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
                if (mExpandableListViewModel.getSelectedAllBtnStatus().getValue()) {
                    mExpandableListViewModel.setSelectedAllBtnStatus(false);
                } else {
                    mExpandableListViewModel.setSelectedAllBtnStatus(true);
                }
            }
        });
    }

}
