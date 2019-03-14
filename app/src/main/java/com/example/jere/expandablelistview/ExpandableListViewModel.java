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
    public static final String SELECT_ALL = "SELECT_ALL";
    public static final String SELECT_SOME = "SELECT_SOME";
    public static final String NOT_SELECT_ANY = "NOT_SELECT_ANY";

    private ExpandableListRepository mRepository;
    private MutableLiveData<List<Boolean>> mGroupExpandStatusList;
    private MutableLiveData<List<Boolean>> mGroupSelectedStatusList;
    private MutableLiveData<List<Boolean>> mChildSelectedStatusList;
    private MutableLiveData<String> mSelectedAllBtnStatus;

    public ExpandableListViewModel(@NonNull Application application) {
        super(application);
        mRepository = ExpandableListRepository.getRepository(application);
        mGroupExpandStatusList = new MutableLiveData<>();
        mGroupSelectedStatusList = new MutableLiveData<>();
        mChildSelectedStatusList = new MutableLiveData<>();
        mSelectedAllBtnStatus = new MutableLiveData<>();
        mSelectedAllBtnStatus.postValue(NOT_SELECT_ANY);
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

    public MutableLiveData<String> getSelectedAllBtnStatus() {
        return mSelectedAllBtnStatus;
    }

    public void setSelectedAllBtnStatus(String selectedStatus) {
        this.mSelectedAllBtnStatus.postValue(selectedStatus);
    }

    public List<GroupItem> getDummyGroupListData() {
        return mRepository.getDummyGroupListData();
    }

    public void saveGroupSelectedIdsList(List<Integer> groupSelectedIdsList) {
        mRepository.saveGroupSelectedIdsList(groupSelectedIdsList);
    }

}
