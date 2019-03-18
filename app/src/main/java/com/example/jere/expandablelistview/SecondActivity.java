package com.example.jere.expandablelistview;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jere.expandablelistview.model.ChildItem;
import com.example.jere.expandablelistview.model.GroupItem;

import java.util.List;

/**
 * @author jere
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ExpandableListViewModel viewModel = ViewModelProviders.of(this).get(ExpandableListViewModel.class);
        StringBuilder sb = new StringBuilder();
        List<Integer> groupSelectedIdsList = viewModel.getGroupSelectedIdsList();
        List<Integer> childSelectedIdsList = viewModel.getChildSelectedIdsList();
        List<GroupItem> dummyGroupListData = viewModel.getDummyGroupListData();
        for (GroupItem groupItem : dummyGroupListData) {
            if (groupSelectedIdsList.contains(groupItem.getId())) {
                int countNumber = 0;
                for (ChildItem childItem : groupItem.getChildItemList()) {
                    if (childSelectedIdsList.contains(childItem.getId())) {
                        countNumber++;
                    }
                }
                sb.append(groupItem.getName())
                        .append(" : ")
                        .append(countNumber)
                        .append("\n");
            }
        }

        TextView tvDisplay = findViewById(R.id.tvDisplay);
        tvDisplay.setText(sb.toString());
    }


}
