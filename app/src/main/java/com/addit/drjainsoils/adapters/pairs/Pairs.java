package com.addit.drjainsoils.adapters.pairs;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

abstract public class Pairs implements Serializable {
    private int LayoutResID;
    private String id;

    public Pairs(@LayoutRes int layoutResID, String id) {
        LayoutResID = layoutResID;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getLayoutResID() {
        return LayoutResID;
    }

    //abstract public View getView(Context context, int position, View convertView, ViewGroup parent);

    abstract public void bindView(Context context, int position, @LayoutRes int viewType, View convertView) throws Exception;

    public void setLayoutResID(int layoutResID) {
        LayoutResID = layoutResID;
    }

    public View getView(Context context, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(LayoutResID, parent, false);
            /*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(LayoutResID, null);*/
        }
        return convertView;
    }
}
