package com.addit.drjainsoils.pairs;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
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


public class members extends Pairs {
    String content,URL;
    LinearLayout c1;
    boolean flag = false;
    int number;
    CheckBox c2;
    public members(String a,String c, int i) {
        super(R.layout.text_view, null);
        this.content = a;
        this.URL = c;

        this.number = i;
    }
    @Override
    public void bindView(final Context context, int position, @LayoutRes int viewType, View convertView) throws Exception {
        TextView t1 = (TextView) convertView.findViewById(R.id.text2);

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
                i.putExtra("IMG","condition");
                i.putExtra("NAME",content);
                C.startActivity(i);

                //clickable=false;
            }
        });
        t1.setText(content);


    }
}
