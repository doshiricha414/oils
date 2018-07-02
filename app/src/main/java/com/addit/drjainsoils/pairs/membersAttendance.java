package com.addit.drjainsoils.pairs;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.addit.drjainsoils.R;
import com.addit.drjainsoils.activity.ReadFileAssetsActivity;
import com.addit.drjainsoils.adapters.pairs.Pairs;

import com.addit.drjainsoils.picasso.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.io.InputStream;


public class membersAttendance extends Pairs {
    String content, IMG,URL;
    LinearLayout c1;
    boolean flag = false;
    int number;
    CheckBox c2;
    public membersAttendance(String a, String b,String c, int i) {
        super(R.layout.members_attendance, null);
        this.content = a;
        this.URL = c;
        this.IMG = b;
        this.number = i;
    }
    @Override
    public void bindView(final Context context, int position, @LayoutRes int viewType, View convertView) throws Exception {
        TextView t1 = (TextView) convertView.findViewById(R.id.text1);
//IMG="file://android_asset/"+IMG;
        final Context C=context ;
        //ImageView image=(ImageView)convertView.findViewById(R.IMG.hotOfferImage);

        LinearLayout r1 = (LinearLayout) convertView.findViewById(R.id.linearLayout);
        ImageView profile = (ImageView) convertView.findViewById(R.id.Profile);
        final Context context1 = context;
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(C, ReadFileAssetsActivity.class);
                i.putExtra("URL",URL);
                i.putExtra("IMG",IMG);
                i.putExtra("NAME",content);
                C.startActivity(i);

                //clickable=false;
            }
        });
        t1.setText(content);
        /*AssetManager assetManager = C.getAssets();
        InputStream ims = assetManager.open(IMG);

        // create drawable from stream
        Drawable d = Drawable.createFromStream(ims, null);*/

        int id = C.getResources().getIdentifier("com.addit.drjainsoils:drawable/" + IMG, null, null);
        Log.d("ID",Integer.toString(id));
        if(id==0)
            Log.d("Name",IMG);
        Picasso.with(context).load(id).error(R.mipmap.ic_launcher).placeholder(R.drawable.ic_logo)
                .transform(new RoundedTransformation(300, 0))
                .resize(300, 300).into(profile);
        //c2 = (CheckBox) convertView.findViewById(R.IMG.checkText);
       // TextView t2 = (TextView) convertView.findViewById(R.IMG.number);
       // t2.setText(Integer.toString(number));
       /* c1 = (LinearLayout) convertView.findViewById(R.IMG.linear);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    c2.setChecked(false);
                    flag = false;
                } else {
                    c2.setChecked(true);
                    flag = true;
                }
            }
        });*/

    }
}
