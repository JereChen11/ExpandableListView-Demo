package com.example.jere.expandablelistview.model;

/**
 * @author jere
 */
public class ExpandableListViewChildItem {

    private String mChildName;
    private Boolean isChildItemSelected;

    public ExpandableListViewChildItem(String mChildName) {
        this.mChildName = mChildName;
    }

    public String getChildName() {
        return mChildName;
    }

    public void setChildName(String mChildName) {
        this.mChildName = mChildName;
    }

    public Boolean getChildItemSelected() {
        return isChildItemSelected;
    }

    public void setChildItemSelected(Boolean isChildItemSelected) {
        isChildItemSelected = isChildItemSelected;
    }
}
