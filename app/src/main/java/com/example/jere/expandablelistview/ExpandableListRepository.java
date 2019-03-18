package com.example.jere.expandablelistview;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author jere
 */
public class ExpandableListRepository {

    private Context mContext;
    private static ExpandableListRepository mExpandableListRepository;
    private SharedPreferences mPreferences;
    private final static String KEY_GROUP_SELECT_IDS = "KEY_GROUP_SELECT_IDS";
    private final static String KEY_CHILD_SELECT_IDS = "KEY_CHILD_SELECT_IDS";

    public static ExpandableListRepository getRepository(Context context) {
        if (mExpandableListRepository == null) {
            synchronized (ExpandableListRepository.class) {
                if (mExpandableListRepository == null) {
                    mExpandableListRepository = new ExpandableListRepository(context);
                }
            }
        }
        return mExpandableListRepository;
    }

    public ExpandableListRepository(Context context) {
        mContext = context;
        mPreferences = context.getApplicationContext().getSharedPreferences("expandable_list_preference", Context.MODE_PRIVATE);
    }

    public void saveGroupSelectedIdsList(List<Integer> groupSelectedIdsList) {
        SharedPreferences.Editor editor = mPreferences.edit();
        StringBuilder groupSelectedIdSB = new StringBuilder();
        for (Integer id : groupSelectedIdsList) {
            groupSelectedIdSB.append(id).append(',');
        }
        editor.putString(KEY_GROUP_SELECT_IDS, groupSelectedIdSB.toString());
        editor.apply();
    }

    public void saveChildSelectedIdsList(List<Integer> childSelectedIdsList) {
        SharedPreferences.Editor editor = mPreferences.edit();
        StringBuilder childSelectedIdSB = new StringBuilder();
        for (Integer id: childSelectedIdsList) {
            childSelectedIdSB.append(id).append(',');
        }
        editor.putString(KEY_CHILD_SELECT_IDS, childSelectedIdSB.toString());
        editor.apply();
    }

    public List<Integer> getGroupSelectedIdsList() {
        String groupSelectedIdsString = mPreferences.getString(KEY_GROUP_SELECT_IDS, "");
        List<Integer> groupSelectedIdsList = new ArrayList<>();
        if (!TextUtils.isEmpty(groupSelectedIdsString)) {
            StringTokenizer selectedPrefsTokenizer = new StringTokenizer(groupSelectedIdsString, ",");
            while (selectedPrefsTokenizer.hasMoreTokens()) {
                int id = Integer.parseInt(selectedPrefsTokenizer.nextToken());
                groupSelectedIdsList.add(id);
            }
        }
        return groupSelectedIdsList;
    }

    public List<Integer> getChildSelectedIdsList() {
        String childSelectedIdsString = mPreferences.getString(KEY_CHILD_SELECT_IDS, "");
        List<Integer> childSelectedIdsList = new ArrayList<>();
        if (!TextUtils.isEmpty(childSelectedIdsString)) {
            StringTokenizer selectedPrefsTokenizer = new StringTokenizer(childSelectedIdsString, ",");
            while (selectedPrefsTokenizer.hasMoreTokens()) {
                int id = Integer.parseInt(selectedPrefsTokenizer.nextToken());
                childSelectedIdsList.add(id);
            }
        }
        return childSelectedIdsList;
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("dummyDataTest.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
