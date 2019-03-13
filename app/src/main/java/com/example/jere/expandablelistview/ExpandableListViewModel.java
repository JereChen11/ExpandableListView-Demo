package com.example.jere.expandablelistview;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.jere.expandablelistview.model.ChildItem;
import com.example.jere.expandablelistview.model.GroupItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author jere
 */
public class ExpandableListViewModel extends AndroidViewModel {
    private ExpandableListRepository mRepository;
    private MutableLiveData<List<Boolean>> mGroupExpandStatusList;
    private MutableLiveData<List<Boolean>> mGroupSelectedStatusList;
    private MutableLiveData<List<Boolean>> mChildSelectedStatusList;
    private MutableLiveData<Boolean> mSelectedAllBtnStatus;

    public ExpandableListViewModel(@NonNull Application application) {
        super(application);
        mRepository = ExpandableListRepository.getRepository(application);
        mGroupExpandStatusList = new MutableLiveData<>();
        mGroupSelectedStatusList = new MutableLiveData<>();
        mChildSelectedStatusList = new MutableLiveData<>();
        mSelectedAllBtnStatus = new MutableLiveData<>();
        mSelectedAllBtnStatus.postValue(false);
    }

    public MutableLiveData<List<Boolean>> getGroupExpandStatusList() {
        return mGroupExpandStatusList;
    }

    public void setGroupExpandStatusList(List<Boolean> groupExpandStatusList) {
        this.mGroupExpandStatusList.postValue(groupExpandStatusList);
    }

    public void initGroupExpandStatusList() {
       List<GroupItem> groupItemList = getDummyGroupListData();
       List<Boolean> groupItemStatusList = new ArrayList<>();
       for (GroupItem groupItem : groupItemList) {
           if (groupItem.isSelected()) {
               groupItemStatusList.add(true);
           } else {
               groupItemStatusList.add(false);
           }
       }
       setGroupExpandStatusList(groupItemStatusList);
    }

    public MutableLiveData<List<Boolean>> getGroupSelectedStatusList() {
        return mGroupSelectedStatusList;
    }

    public void setGroupSelectedStatusList(List<Boolean> groupSelectedStatusList) {
        this.mGroupSelectedStatusList.postValue(groupSelectedStatusList);
    }

    public MutableLiveData<List<Boolean>> getChildSelectedStatusList() {
        return mChildSelectedStatusList;
    }

    public void setChildSelectedStatusList(List<Boolean> childSelectedStatusList) {
        this.mChildSelectedStatusList.postValue(childSelectedStatusList);
    }

    public MutableLiveData<Boolean> getSelectedAllBtnStatus() {
        return mSelectedAllBtnStatus;
    }

    public void setSelectedAllBtnStatus(Boolean isSelectedAll) {
        this.mSelectedAllBtnStatus.postValue(isSelectedAll);
    }

    public List<GroupItem> getDummyGroupListData() {
        return mRepository.getDummyGroupListData();
    }

//    public HashMap<String, List<ChildItem>> getDummyChildMapData(List<GroupItem> groupListData) {
//        return mRepository.getDummyChildMapData(groupListData);
//    }


}
