package com.example.jere.expandablelistview.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jere
 */
public class GroupItem {
    private int mId;
    private String mName;
    private Boolean isSelected;
    private List<ChildItem> mChildItemList;

    public GroupItem(JSONObject jsonObject) {
        this.mName = jsonObject.optString("name");
        this.mId = jsonObject.optInt("id");
        this.isSelected = jsonObject.optBoolean("is_select");
        JSONArray childItemsJSONArray = jsonObject.optJSONArray("child_items_list");
        this.mChildItemList = convertJSONArrayToList(childItemsJSONArray);
    }

    public List<ChildItem> convertJSONArrayToList(JSONArray jsonArray) {
        List<ChildItem> childItemList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            childItemList.add(new ChildItem(jsonObject));
        }
        return childItemList;
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

    public void setSelected(Boolean isSelect) {
        isSelected = isSelect;
    }

    public List<ChildItem> getChildItemList() {
        return mChildItemList;
    }
}
