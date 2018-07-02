package com.addit.drjainsoils.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.content.res.AssetManager;
import android.widget.Toast;

import com.addit.drjainsoils.R;
import com.addit.drjainsoils.adapters.MyListViewAdapter;
import com.addit.drjainsoils.adapters.pairs.MyRecyclerViewAdapter;
import com.addit.drjainsoils.pairs.membersAttendance;
import com.addit.drjainsoils.utils.Json;
import com.addit.drjainsoils.utils.XFragment;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * Created by user on 2/25/2017.
 */

public class FragmentHow extends XFragment {
    String data = "";
    TextView result;
    RequestQueue queue;
    CheckBox c1;
    View v1;
    MyListViewAdapter adapter;
    ListView listView;
    JsonObjectRequest jsonObjReq;
    JSONObject respond = new JSONObject();
    public static FragmentHow newInstance() {
        return new FragmentHow();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        adapter = new MyListViewAdapter<>(getActivity());
    }

    public String readJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getActivity().getAssets().open("data.json");
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
    @Override
    public void onDetach() {
        super.onDetach();


        adapter = null;
    }

    @Override
    protected int getContentView() {
        return R.layout.tab_fragment_3;
    }

    @Override
    protected void getXmlViews(View view, @Nullable Bundle savedInstanceState) {

        listView = (ListView) getActivity().findViewById(R.id.recyler3);

    }

    @Override
    protected void initXmlViews(View view) {
        listView.setAdapter(adapter);

        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray array = obj.getJSONArray("INFO");
            // for (int i = 0; i < 10; i++) {
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = null;
                a = array.getJSONObject(i);
                adapter.addItem(new membersAttendance(a.getString("NAME"), a.getString("IMG"),a.getString("URL"), i + 1));

            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Toast.makeText(getActivity(), " some error occured", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}



