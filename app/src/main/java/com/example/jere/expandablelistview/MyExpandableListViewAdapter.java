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

import java.util.List;
import java.util.Objects;

import static com.example.jere.expandablelistview.ExpandableListViewModel.NOT_SELECT_ANY;
import static com.example.jere.expandablelistview.ExpandableListViewModel.SELECT_ALL;
import static com.example.jere.expandablelistview.ExpandableListViewModel.SELECT_SOME;

/**
 * @author jere
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<GroupItem> mGroupListData;
    private ExpandableListViewModel mViewModel;

    public MyExpandableListViewAdapter(Context context, @Nullable List<GroupItem> groupListData,
                                       @Nullable ExpandableListViewModel mExpandableListViewModel) {
        this.mContext = context;
        this.mGroupListData = groupListData;
        this.mViewModel = mExpandableListViewModel;
    }

    @Override
    public int getGroupCount() {
        return mGroupListData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupListData.get(groupPosition).getChildItemList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupListData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupListData.get(groupPosition).getChildItemList().get(childPosition);
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
        final GroupItem groupItem = (GroupItem) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.elv_group_list_item, null);
        }
        TextView textView = convertView.findViewById(R.id.tvGroupName);
        textView.setText(groupItem.getName());

        ImageView ivArrowIcon = convertView.findViewById(R.id.ivGroupArrowIcon);
        ImageView ivGroupSelectIcon = convertView.findViewById(R.id.ivGroupSelectIcon);

        String selectedAllBtnStatus = mViewModel.getSelectedAllBtnStatus().getValue();
        if (Objects.equals(selectedAllBtnStatus, SELECT_ALL)) {
            groupItem.setSelected(true);
            for (ChildItem childItem : groupItem.getChildItemList()) {
                childItem.setSelected(true);
            }
        } else if (Objects.equals(selectedAllBtnStatus, NOT_SELECT_ANY)) {
            groupItem.setSelected(false);
            for (ChildItem childItem : groupItem.getChildItemList()) {
                childItem.setSelected(false);
            }
        }

        if (groupItem.isSelected()) {
            if (isSelectAllChildItems(groupItem.getChildItemList())) {
                ivGroupSelectIcon.setImageResource(R.drawable.icon_list_selected);
            } else {
                ivGroupSelectIcon.setImageResource(R.drawable.icon_list_unselect);
            }
        } else {
            ivGroupSelectIcon.setImageResource(R.drawable.icon_list_unselect);
        }

        ivGroupSelectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (groupItem.isSelected() && isSelectAllChildItems(groupItem.getChildItemList())) {
                    groupItem.setSelected(false);
                    for (ChildItem childItem : groupItem.getChildItemList()) {
                        childItem.setSelected(false);
                    }

                    if (isNotSelectedAnyGroupItem(mGroupListData)) {
                        mViewModel.setSelectedAllBtnStatus(NOT_SELECT_ANY);
                    } else {
                        mViewModel.setSelectedAllBtnStatus(SELECT_SOME);
                    }
                } else {
                    groupItem.setSelected(true);

                    for (ChildItem childItem : groupItem.getChildItemList()) {
                        childItem.setSelected(true);
                    }

                    if (isSelectedAllItems(mGroupListData)) {
                        mViewModel.setSelectedAllBtnStatus(SELECT_ALL);
                    } else {
                        mViewModel.setSelectedAllBtnStatus(SELECT_SOME);
                    }
                }
                notifyDataSetChanged();
            }
        });

        if (isExpanded) {
            ivArrowIcon.setImageResource(R.drawable.up_arrow_icon);
        } else {
            ivArrowIcon.setImageResource(R.drawable.down_arrow_icon);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildItem childItem = (ChildItem) getChild(groupPosition, childPosition);
        final GroupItem groupItem = (GroupItem) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.elv_child_list_item, null);
        }

        TextView textChild = (TextView) convertView.findViewById(R.id.tvChildName);
        ImageView ivChildSelectIcon = (ImageView) convertView.findViewById(R.id.ivChildSelectIcon);
        textChild.setText(childItem.getName());

        if (childItem.isSelected()) {
            ivChildSelectIcon.setImageResource(R.drawable.icon_list_selected);
        } else {
            ivChildSelectIcon.setImageResource(R.drawable.icon_list_unselect);
        }

        ivChildSelectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childItem.isSelected()) {
                    childItem.setSelected(false);

                    if (isNotSelectedAnyChildItems(groupItem.getChildItemList())) {
                        groupItem.setSelected(false);
                    }

                    if (isNotSelectedAnyItems(mGroupListData)) {
                        mViewModel.setSelectedAllBtnStatus(NOT_SELECT_ANY);
                    } else {
                        mViewModel.setSelectedAllBtnStatus(SELECT_SOME);
                    }
                } else {
                    childItem.setSelected(true);
                    groupItem.setSelected(true);

                    if (isSelectedAllItems(mGroupListData)) {
                        mViewModel.setSelectedAllBtnStatus(SELECT_ALL);
                    } else {
                        mViewModel.setSelectedAllBtnStatus(SELECT_SOME);
                    }
                }
                notifyDataSetChanged();
            }
        });

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

    private Boolean isNotSelectedAnyGroupItem(List<GroupItem> groupItemsList) {
        for (GroupItem groupItem : groupItemsList) {
            if (groupItem.isSelected()) {
                return false;
            }
        }
        return true;
    }

    private Boolean isSelectedAllGroupItems(List<GroupItem> groupItemsList) {
        for (GroupItem groupItem : groupItemsList) {
            if (!groupItem.isSelected()) {
                return false;
            }
        }
        return true;
    }

    private Boolean isNotSelectedAnyChildItems(List<ChildItem> childItemList) {
        for (ChildItem childItem : childItemList) {
            if (childItem.isSelected()) {
                return false;
            }
        }
        return true;
    }

    private Boolean isSelectAllChildItems(List<ChildItem> childItemList) {
        for (ChildItem childItem : childItemList) {
            if (!childItem.isSelected()) {
                return false;
            }
        }
        return true;
    }

    private Boolean isSelectedAllItems(List<GroupItem> groupItemList) {
        for (GroupItem groupItem : groupItemList) {
            if (!groupItem.isSelected()) {
                return false;
            }
            if (!isSelectAllChildItems(groupItem.getChildItemList())) {
                return false;
            }
        }
        return true;
    }

    private Boolean isNotSelectedAnyItems(List<GroupItem> groupItemList) {
        for (GroupItem groupItem : groupItemList) {
            if (groupItem.isSelected()) {
                return false;
            }
            if (!isNotSelectedAnyChildItems(groupItem.getChildItemList())) {
                return false;
            }
        }
        return true;
    }
}
