package com.example.jere.expandablelistview;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.jere.expandablelistview.model.GroupItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    private MutableLiveData<String> mSelectedAllBtnStatus;

    public ExpandableListViewModel(@NonNull Application application) {
        super(application);
        mRepository = ExpandableListRepository.getRepository(application);
        mGroupExpandStatusList = new MutableLiveData<>();
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

    public MutableLiveData<String> getSelectedAllBtnStatus() {
        return mSelectedAllBtnStatus;
    }

    public void setSelectedAllBtnStatus(String selectedStatus) {
        this.mSelectedAllBtnStatus.postValue(selectedStatus);
    }

    public List<GroupItem> getDummyGroupListData() {
        List<GroupItem> groupItemList = new ArrayList<>();

        String jsonString = mRepository.loadJSONFromAsset(getApplication());
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            groupItemList.add(new GroupItem(jsonObject));
        }
        return groupItemList;
    }

    public void saveGroupSelectedIdsList(List<Integer> groupSelectedIdsList) {
        mRepository.saveGroupSelectedIdsList(groupSelectedIdsList);
    }

    public List<Integer> getGroupSelectedIdsList() {
        return mRepository.getGroupSelectedIdsList();
    }

    public void saveChildSelectedIdsList(List<Integer> childSelectedIdsList) {
        mRepository.saveChildSelectedIdsList(childSelectedIdsList);
    }

    public List<Integer> getChildSelectedIdsList() {
        return mRepository.getChildSelectedIdsList();
    }

}
