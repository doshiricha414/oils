package com.addit.drjainsoils.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.addit.drjainsoils.adapters.pairs.ExpandablePairs;

import java.util.ArrayList;

public class MyExpListViewAdapter<T1 extends ExpandablePairs, T2 extends ExpandablePairs, T3, T4> extends BaseExpandableListAdapter {
    private Context activity;
    private ArrayList<NameValue> items;

    public MyExpListViewAdapter(Context activity) {
        this.activity = activity;
        items = new ArrayList<>();
    }

    public void addGroupItem(T3 groupItem) {
        items.add(new NameValue(parseGroup(groupItem), new ArrayList<T2>()));
    }

    // group position starts from 0 to size-1; groupPosition beyond this range will add list of child without adding its parent
    public void addChildItem(int groupPosition, T4 childItem) {
        if (groupPosition >= 0 && groupPosition < items.size())
            items.get(groupPosition).value.add(parseChild(childItem));
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items.get(groupPosition).value.size();
    }

    @Override
    public ArrayList<T2> getGroup(int groupPosition) {
        return items.get(groupPosition).value;       /// Returning an ArrayList
    }

    @Override
    public T2 getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).value.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
        //return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        T1 parentItem = items.get(groupPosition).name;
        convertView = parentItem.getGroupView(activity, groupPosition, isExpanded, convertView, parent);
        try {
            parentItem.bindGroupView(activity, groupPosition, isExpanded, parentItem.getLayoutResID(), convertView, parent);
        } catch (Exception e) {
            onGroupBindingErrorHandler(e, parentItem, parentItem.getLayoutResID(), convertView, groupPosition);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        T2 child = items.get(groupPosition).value.get(childPosition);
        convertView = child.getChildView(activity, groupPosition, childPosition, isLastChild, convertView, parent);
        try {
            child.bindChildView(activity, groupPosition, childPosition, isLastChild, child.getLayoutResID(), convertView, parent);
        } catch (Exception e) {
            onChildBindingErrorHandler(e, child, child.getLayoutResID(), convertView, groupPosition);
        }
        return convertView;
    }

    public T1 parseGroup(T3 data) {
        return (T1) data;
    }

    public T2 parseChild(T4 data) {
        return (T2) data;
    }

    public View onGroupBindingErrorHandler(Exception ex, T1 data, @LayoutRes int viewType, @Nullable View view, int position) {
        return view;
    }

    public View onChildBindingErrorHandler(Exception ex, T2 data, @LayoutRes int viewType, @Nullable View view, int position) {
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        /// return true if you want "onChildClick" event to fire
        return true;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        activity = null;
    }

    private class NameValue {
        private T1 name;
        private ArrayList<T2> value;

        public NameValue(T1 name, ArrayList<T2> value) {
            this.name = name;
            this.value = value;
        }
    }
}
