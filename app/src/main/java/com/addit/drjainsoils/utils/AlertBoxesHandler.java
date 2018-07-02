package com.addit.drjainsoils.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.addit.drjainsoils.R;

public class AlertBoxesHandler {
    public static void showAlertBox(Context context, @DrawableRes int icon, String title, String msg, String positiveButtonText, DialogInterface.OnClickListener positiveOnClickListener, String negativeButtonText, DialogInterface.OnClickListener negativeOnClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setIcon(icon).setTitle(title)
                .setMessage(msg);
        if (positiveButtonText != null)
            builder.setPositiveButton(positiveButtonText, positiveOnClickListener);
        if (negativeButtonText != null)
            builder.setNegativeButton(negativeButtonText, negativeOnClickListener);
        builder.show();
    }
    public static void showAlertBox(Context context, String title, String msg, String positiveButtonText, DialogInterface.OnClickListener positiveOnClickListener, String negativeButtonText, DialogInterface.OnClickListener negativeOnClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setIcon(R.drawable.ic_logo).setTitle(title)
                .setMessage(msg);
        if (positiveButtonText != null)
            builder.setPositiveButton(positiveButtonText, positiveOnClickListener);
        if (negativeButtonText != null)
            builder.setNegativeButton(negativeButtonText, negativeOnClickListener);
        builder.show();
    }

    public static AlertDialog showAlertBox(Context context, @DrawableRes int icon, View view, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setIcon(icon).setTitle(title);
        builder.setView(view);
        return builder.show();
    }

  /*  public static Dialog showCustomAlertBox(Context context, @LayoutRes int layout, String id, String jobtitle, String jobloc, String jobdesc, String exp, String responsibility, String negativeButtonText, String positiveButtonText, DialogInterface.OnClickListener positiveOnClickListener, DialogInterface.OnClickListener negativeOnClickListener) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.alpha(Color.WHITE)));



        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.job_title);
        TextView text1 = (TextView) dialog.findViewById(R.id.job_location);
        TextView text2 = (TextView) dialog.findViewById(R.id.job_description);
        TextView text3 = (TextView) dialog.findViewById(R.id.experience_required);
        TextView text4 = (TextView) dialog.findViewById(R.id.responsibility);
        text.setText(jobtitle);
        text1.setText(jobloc);
        text2.setText(jobdesc);
        text3.setText(exp);
        text4.setText(responsibility);

        Button dialogButton = (Button) dialog.findViewById(R.id.back);
        Button dialogButton1= (Button) dialog.findViewById(R.id.apply);
        // if button is clicked, close the custom dialog
       *//* dialogButton.setOnClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick() {
                dialog.dismiss();
            }
        });
*//*
        dialog.show();
        return dialog;
    }*/

    /*public static AlertDialog showListViewAlertBox(Context context, @DrawableRes int icon, String name, MyListViewAdapter adapter, AdapterView.OnItemClickListener onItemClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setIcon(icon).setTitle(name);
        View convertView = LayoutInflater.from(context).inflate(R.layout.common_list_layout, null, false);
        ((ListView) convertView.findViewById(R.id.myList)).setAdapter(adapter);
        ((ListView) convertView.findViewById(R.id.myList)).setOnItemClickListener(onItemClickListener);
        builder.setView(convertView);
        return builder.show();
    }

    public static AlertDialog showListViewAlertBox(Context context, @DrawableRes int icon, String name, MyRecyclerViewAdapter adapter, RecyclerView.LayoutManager layoutManager, MyRecyclerViewAdapter.RecyclerItemClickListener onItemClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setIcon(icon).setTitle(name);
        View convertView = LayoutInflater.from(context).inflate(R.layout.common_recycler_list_layout, null, false);
        RecyclerView myList = (RecyclerView) convertView.findViewById(R.id.myList);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
        myList.addOnItemTouchListener(onItemClickListener);
        builder.setView(convertView);
        return builder.show();
    }*/

    public static AlertDialog showListItemsAlertBox(Context context, @DrawableRes int icon, String title, String[] charSequence, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setIcon(icon).setTitle(title);
        builder.setItems(charSequence, onClickListener);
        return builder.show();
    }

  /*  public static AlertDialog showLoader(Context context, String msg, boolean setCanceledOnTouchOutside) {
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(context).inflate(R.layout.loading, null);
        ((TextView) view.findViewById(R.id.text1)).setText(msg);
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(view);
        AlertDialog alertDialog = builder.show();
        alertDialog.setCanceledOnTouchOutside(setCanceledOnTouchOutside);
        return alertDialog;
    }*/
}
