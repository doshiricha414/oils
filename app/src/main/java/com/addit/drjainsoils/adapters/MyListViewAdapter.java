package com.addit.drjainsoils.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.addit.drjainsoils.adapters.pairs.Pairs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MyListViewAdapter<T1 extends Pairs, T2> extends ArrayAdapter<T1> {
    private ArrayList<T1> list;

    public MyListViewAdapter(Context context) {
        super(context, 0);
        this.list = new ArrayList<>();
    }

    public MyListViewAdapter(Context context, ArrayList<T1> list) {
        super(context, 0);
        this.list = list;
    }

    public void addItem(T2 data) {
        list.add(parse((data)));
    }

    public T1 remove(int position) {
        return list.remove(position);
    }

    public ArrayList<T1> getList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T1 getItem(int position) {
        return list.get(position);
    }

    @Override
    public void remove(T1 object) {
        list.remove(object);
    }

    @Override
    public void insert(T1 object, int index) {
        list.add(index, object);
    }

    @Override
    public void add(T1 object) {
        list.add(object);
    }

    @SafeVarargs
    @Override
    public final void addAll(T1... items) {
        Collections.addAll(list, items);
    }

    @Override
    public int getPosition(T1 item) {
        return list.indexOf(item);
    }

    @Override
    public void sort(Comparator<? super T1> comparator) {
        Collections.sort(list, comparator);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T1 t = list.get(position);
        View view = t.getView(super.getContext(), position, convertView, parent);
        try {
            t.bindView(super.getContext(), position, t.getLayoutResID(), view);
            return view;
        } catch (Exception ex) {
            Exception e=ex;
            Log.d("error",ex.toString());
            return bindingErrorHandler(ex, t, t.getLayoutResID(), view, position);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    // You must override these following methods

    public T1 parse(T2 data) {
        return (T1) data;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View bindingErrorHandler(Exception ex, T1 data, @LayoutRes int viewType, @Nullable View view, int position) {
        return view;
    }
}