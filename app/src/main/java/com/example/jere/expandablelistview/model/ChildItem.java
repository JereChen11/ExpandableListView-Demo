package com.example.jere.expandablelistview.model;

/**
 * @author jere
 */
public class ChildItem {
    private int mId;
    private String mName;
    private Boolean isSelected;

    public ChildItem(int id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Boolean isSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    //    public String getChildName() {
//        return mChildName;
//    }
//
//    public void setChildName(String mChildName) {
//        this.mChildName = mChildName;
//    }
//
//    public Boolean getChildItemSelected() {
//        return isChildItemSelected;
//    }
//
//    public void setChildItemSelected(Boolean isChildItemSelected) {
//        isChildItemSelected = isChildItemSelected;
//    }
}
