package com.example.jere.expandablelistview.model;

import java.util.List;

/**
 * @author jere
 */
public class GroupItem {
    private int mId;
    private String mName;
    private Boolean isSelected = false;
    private List<ChildItem> mChildItemList;

    public GroupItem(int id, String groupName, List<ChildItem> childItemList){
        this.mId = id;
        this.mName = groupName;
        this.mChildItemList = childItemList;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mGroupName) {
        this.mName = mGroupName;
    }

    public Boolean isSelected() {
        return isSelected;
    }

    public void setSelected(Boolean isSelect) {
        isSelected = isSelect;
    }

    public List<ChildItem> getChildItemList() {
        return mChildItemList;
    }
}
