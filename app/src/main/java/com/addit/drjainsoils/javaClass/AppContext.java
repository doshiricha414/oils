package com.addit.drjainsoils.javaClass;

import android.app.Application;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import java.io.File;

public class AppContext extends Application {
    private static AppContext context;

    private String ExternalFilesDir, InternalFilesDir;

    public AppContext() {
        context = this;
    }

    /// Overridden Methods

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            ExternalFilesDir = (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable() ?
                    getExternalFilesDir("/").getPath() : getFilesDir().getPath()) + File.separator;

            InternalFilesDir = getFilesDir().getPath() + File.separator;

        } catch (Exception ignore) {

        }
    }

    // other methods

    public static AppContext getContext() {
        return context;
    }

    public String getExternalFilesDir() {
        return ExternalFilesDir;
    }

    public String getInternalFilesDir() {
        return InternalFilesDir;
    }
}