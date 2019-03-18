package com.example.jere.expandablelistview.model;

import org.json.JSONObject;

/**
 * @author jere
 */
public class ChildItem {
    private int mId;
    private String mName;
    private Boolean isSelected;

    public ChildItem(JSONObject jsonObject) {
        this.mId = jsonObject.optInt("child_item_id");
        this.mName = jsonObject.optString("child_item_name");
        this.isSelected = jsonObject.optBoolean("child_is_select");
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
}
