package com.example.jere.expandablelistview.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * @author jere
 */
public class ExpandableListViewModel extends AndroidViewModel {
    public MutableLiveData<ExpandableListViewGroupItem> mExpandableListGroupItem;
    public MutableLiveData<ExpandableListViewChildItem> mExpandableListChildItem;

    public ExpandableListViewModel(@NonNull Application application) {
        super(application);
    }

    public String getGroupItemName() {
        return mExpandableListGroupItem.getValue().getGroupName();
    }

    public void setGroupItemSelected(Boolean isBoolean) {
        mExpandableListGroupItem.getValue().setGroupItemSelected(isBoolean);
    }

}
