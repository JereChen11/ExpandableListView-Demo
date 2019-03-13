package com.example.jere.expandablelistview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jere.expandablelistview.model.ChildItem;
import com.example.jere.expandablelistview.model.GroupItem;

import java.util.HashMap;
import java.util.List;

/**
 * @author jere
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<GroupItem> mParentListData;
    private ExpandableListViewModel mViewModel;

    public MyExpandableListViewAdapter(Context context, @Nullable List<GroupItem> groupListData,
                                       @Nullable ExpandableListViewModel mExpandableListViewModel) {
        this.mContext = context;
        this.mParentListData = groupListData;
        this.mViewModel = mExpandableListViewModel;
    }

    @Override
    public int getGroupCount() {
        return mParentListData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mParentListData.get(groupPosition).getChildItemList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mParentListData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mParentListData.get(groupPosition).getChildItemList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupItem groupItem = (GroupItem) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.elv_group_list_item , null);
        }
        TextView textView = convertView.findViewById(R.id.tvGroupName);
        textView.setText(groupItem.getName());

        ImageView ivArrowIcon = convertView.findViewById(R.id.ivGroupArrowIcon);
        ImageView ivGroupSelectIcon = convertView.findViewById(R.id.ivGroupSelectIcon);

        Boolean selectedAllBtnStatus = mViewModel.getSelectedAllBtnStatus().getValue();
        if (selectedAllBtnStatus) {
            groupItem.setSelected(true);
        }

        if (groupItem.isSelected()) {
            ivGroupSelectIcon.setImageResource(R.drawable.icon_list_selected);
        } else {
            ivGroupSelectIcon.setImageResource(R.drawable.icon_list_unselect);
        }

        ivGroupSelectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if(isExpanded) {
            ivArrowIcon.setImageResource(R.drawable.up_arrow_icon);
        }else{
            ivArrowIcon.setImageResource(R.drawable.down_arrow_icon);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildItem childItem = (ChildItem) getChild(groupPosition, childPosition);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.elv_child_list_item, null);
        }

        TextView textChild = (TextView)convertView.findViewById(R.id.tvChildName);
        ImageView ivChildSelectIcon = (ImageView) convertView.findViewById(R.id.ivChildSelectIcon);
        textChild.setText(childItem.getName());

//        if (childItem.isSelected()) {
//            ivChildSelectIcon.setImageResource(R.drawable.icon_list_selected);
//        } else {
//            ivChildSelectIcon.setImageResource(R.drawable.icon_list_unselect);
//        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
