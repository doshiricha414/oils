package com.addit.drjainsoils.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.addit.drjainsoils.R;
import com.addit.drjainsoils.picasso.RoundedTransformation;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Paresh N. Mayani
 * @Website http://www.technotalkative.com
 */
public class ReadFileAssetsActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oils_details);
        final String mimeType = "text/html";
        Intent intent = getIntent();
        String filename = intent.getStringExtra("URL");
        String image = intent.getStringExtra("IMG");
        String name = intent.getStringExtra("NAME");
        Toolbar tool=(Toolbar)findViewById(R.id.toolbar);
        tool.setTitle(name);

        final String encoding = "UTF-8";
        WebView txtContent = (WebView) findViewById(R.id.txtContent);
        TextView txtFileName = (TextView) findViewById(R.id.txtFileName);
        ImageView imgAssets = (ImageView) findViewById(R.id.imgAssets);
        WebSettings webSettings = txtContent.getSettings();
        AssetManager assetManager = getAssets();
        webSettings.setDefaultFontSize(18);
        // To get names of all files inside the "Files" folder
        try {
            String[] files = assetManager.list("Files");

            for (int i = 0; i < files.length; i++) {
                txtFileName.append("\n" + " file" + " :" + i + "" + " name" + "> " + files[i]);
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // To load text file
        InputStream input;
        try {
            input = assetManager.open(filename);
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            // byte buffer into a string
            String text = new String(buffer);

            //txtContent.setText(Html.fromHtml(text));
            //txtContent is webView object
            txtContent.loadDataWithBaseURL("", text, mimeType, encoding, "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // To load image

        // get input stream
        int id = getResources().getIdentifier("com.addit.drjainsoils:drawable/" + image, null, null);
        Log.d("ID", Integer.toString(id));
        if (id == 0)
            Log.d("Name", image);
        Picasso.with(this).load(id).error(R.mipmap.ic_launcher).placeholder(R.drawable.ic_logo)
                .transform(new RoundedTransformation(300, 0))
                .resize(600, 600).into(imgAssets);

    }
    /*@Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
