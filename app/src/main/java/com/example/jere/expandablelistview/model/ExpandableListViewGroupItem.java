package com.example.jere.expandablelistview.model;

/**
 * @author jere
 */
public class ExpandableListViewGroupItem {
    private String mGroupName;
    private Boolean isGroupItemSelected = false;

    public ExpandableListViewGroupItem(String groupName){
        this.mGroupName = groupName;
    }

    public String getGroupName() {
        return mGroupName;
    }

    public void setGroupName(String mGroupName) {
        this.mGroupName = mGroupName;
    }

    public Boolean getGroupItemSelected() {
        return isGroupItemSelected;
    }

    public void setGroupItemSelected(Boolean isSelected) {
        isGroupItemSelected = isSelected;
    }
}
