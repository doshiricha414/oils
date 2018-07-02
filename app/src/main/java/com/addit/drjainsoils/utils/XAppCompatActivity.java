package com.addit.drjainsoils.utils;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.addit.drjainsoils.request.VolleyRequest;

public abstract class XAppCompatActivity extends AppCompatActivity implements View.OnClickListener {
    protected AsyncTask<String, Void, String> asyncTask = null, asyncTask2 = null;
    protected VolleyRequest volleyRequest = null;
    @LayoutRes
    int layoutID;
    @MenuRes
    int menuID;

    // constructor
    public XAppCompatActivity(@LayoutRes int layoutID, @MenuRes int menuID) {
        this.layoutID = layoutID;
        this.menuID = menuID;
    }

    // show Toast
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    // Activity methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutID);
        try {
            getIntentData(getIntent());
            if (!getXmlControls()) {
                this.finish();
                return;
            }
            initXmlControls();
        } catch (Exception ex) {
            showToast("Some Error Occurred");
            showToast("appcontext");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuID != 0) {
            getMenuInflater().inflate(menuID, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @CallSuper
    @Override
    public void onBackPressed() {
        if (volleyRequest != null && (!volleyRequest.isCanceled() || !volleyRequest.hasHadResponseDelivered())) {
            volleyRequest.cancel();
            volleyRequest = null;
        } else
            super.onBackPressed();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        releaseNetwork();
    }

    // Activity Organizer methods

    protected void getIntentData(Intent intent) {
    }

    abstract protected boolean getXmlControls();

    abstract protected void initXmlControls() throws Exception;

    // OnClickListener method implementation

    @Override
    public void onClick(View v) {
    }

    // other methods

    private void releaseNetwork() {
        if (asyncTask != null && asyncTask.getStatus().compareTo(AsyncTask.Status.FINISHED) != 0)
            asyncTask.cancel(true);
        asyncTask = null;
        if (asyncTask2 != null && asyncTask2.getStatus().compareTo(AsyncTask.Status.FINISHED) != 0)
            asyncTask2.cancel(true);
        asyncTask2 = null;
        if (volleyRequest != null && !volleyRequest.isCanceled())
            volleyRequest.cancel();
        volleyRequest = null;
    }
}
