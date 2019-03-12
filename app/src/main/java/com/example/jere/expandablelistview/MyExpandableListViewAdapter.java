package com.example.jere.expandablelistview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jere.expandablelistview.model.ExpandableListViewChildItem;
import com.example.jere.expandablelistview.model.ExpandableListViewGroupItem;

import java.util.HashMap;
import java.util.List;

/**
 * @author jere
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<ExpandableListViewGroupItem> mParentListData;
    private HashMap<String, List<ExpandableListViewChildItem>> mChildMapData;

    public MyExpandableListViewAdapter(Context context , @Nullable List<ExpandableListViewGroupItem> groupListData,
                                       @Nullable HashMap<String, List<ExpandableListViewChildItem>> childMapData) {
        this.mContext = context;
        this.mParentListData = groupListData;
        this.mChildMapData = childMapData;
    }

    @Override
    public int getGroupCount() {
        return mParentListData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildMapData.get(mParentListData.get(groupPosition).getGroupName()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mParentListData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildMapData.get(mParentListData.get(groupPosition).getGroupName()).get(childPosition);
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

        ExpandableListViewGroupItem groupItem = (ExpandableListViewGroupItem) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.elv_group_list_item , null);
        }
        TextView textView = convertView.findViewById(R.id.tvGroupName);
        textView.setText(groupItem.getGroupName());

        ImageView ivArrowIcon = convertView.findViewById(R.id.ivGroupArrowIcon);

        if(isExpanded) {
            ivArrowIcon.setImageResource(R.mipmap.up_arrow_icon);
        }else{
            ivArrowIcon.setImageResource(R.mipmap.down_arrow_icon);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ExpandableListViewChildItem childItem = (ExpandableListViewChildItem) getChild(groupPosition, childPosition);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.elv_child_list_item, null);
        }

        TextView textChild = (TextView)convertView.findViewById(R.id.tvChildName);
        ImageView mTickImage = (ImageView) convertView.findViewById(R.id.ivChildSelectIcon);
        textChild.setText(childItem.getChildName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
