package com.addit.drjainsoils.app;
import com.addit.drjainsoils.fcm.FcmService;
import android.preference.PreferenceManager;

import com.addit.drjainsoils.net.MyServerInfoPlain;
import com.addit.drjainsoils.request.VolleyJsonObjectRequest;
import com.addit.drjainsoils.request.VolleyRequest;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class AppContext extends com.addit.drjainsoils.javaClass.AppContext {

    public static VolleyRequest volleyGcmUpdateNotifier = null;

    private static AppContext appContext;

    public static final String EMAIL_ID = "JITO_EMAIL_ID", USER_ID = "JITO_USER_ID", GCM_NOTIFIED = "GCM_NOTIFIED";

    public static String ExternalTempFilesDir;

    public static LoginDetails loginDetails = new LoginDetails(null, null,null, false);


    public static class LoginDetails {
        public String email = null;
        public String userID = null;
        public boolean notifiedGcm = false;
        public String profilePicture = null;

        public LoginDetails(String email, String userID,String image, boolean notifiedGcm) {
            this.email = email;
            this.userID = userID;
            this.notifiedGcm = notifiedGcm;
            this.profilePicture = image;
        }



    }

    public AppContext() {
        appContext = this;
    }

    // AppContext method

    public static AppContext getContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FcmService.start(this);
        String email = PreferenceManager.getDefaultSharedPreferences(this).getString(EMAIL_ID, null);
        String userID = PreferenceManager.getDefaultSharedPreferences(this).getString(USER_ID, null);
        boolean notifiedGcm = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(GCM_NOTIFIED, false);
        if (email != null && userID != null) {
            loginDetails.email = email;
            loginDetails.userID = userID;
            loginDetails.notifiedGcm = notifiedGcm;
            // if not notified --> notify our server our GCM credentials
            if (!notifiedGcm && (com.addit.drjainsoils.app.AppContext.volleyGcmUpdateNotifier == null || com.addit.drjainsoils.app.AppContext.volleyGcmUpdateNotifier.hasHadResponseDelivered()))
                (com.addit.drjainsoils.app.AppContext.volleyGcmUpdateNotifier = new VolleyGcmUpdateNotifier()).executeRequest();
        }

        File temp = new File((ExternalTempFilesDir = getExternalFilesDir() + "temp"));
        if (!temp.exists() || !temp.isDirectory()) {
            if (!temp.mkdir())
                ExternalTempFilesDir = null;
            else
                ExternalTempFilesDir += "/";
        } else
            ExternalTempFilesDir += "/";
    }

    public void storeLoginDetails() {
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString(EMAIL_ID, loginDetails.email)
                .putString(USER_ID, loginDetails.userID)
                .putBoolean(GCM_NOTIFIED, loginDetails.notifiedGcm)
                .apply();
    }

    // other methods




    // Volley Request

    public static class VolleyGcmUpdateNotifier extends VolleyJsonObjectRequest {
        public VolleyGcmUpdateNotifier() {
            super(Request.Method.POST, MyServerInfoPlain.GcmCredentialsAPI, false);
        }

        @Override
        public String setRequestBody() {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(MyServerInfoPlain.JsonRequestParams.UserDataID, loginDetails.userID).put(MyServerInfoPlain.JsonRequestParams.email, loginDetails.email)
                        .put(MyServerInfoPlain.JsonRequestParams.GCM_RegId, FcmService.getRegistrationID()).put(MyServerInfoPlain.JsonRequestParams.GCM_Token, FcmService.getRegistrationToken());
            } catch (Exception ignored) {
            }
            return jsonObject.toString();
        }

        @Override
        public void onResponse(JSONObject response) {
            try {
                response = response.getJSONArray(MyServerInfoPlain.JsonResponseParams.records).getJSONObject(0);
                if (response.getString(MyServerInfoPlain.JsonResponseParams.Status).equals(MyServerInfoPlain.JsonResponseParams.Success)) {
                    PreferenceManager.getDefaultSharedPreferences(AppContext.getContext()).edit()
                            .putBoolean(GCM_NOTIFIED, true)
                            .apply();
                }
            } catch (Exception ignored) {
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
        }

        @Override
        protected void onNetworkConnectionError(NETWORK_ERROR networkError) {
            try {
                switch (networkError) {
                    case NETWORK_CONN_UNAVAILABLE:
                        break;
                    case NETWORK_CONN_LOST:
                        break;
                }
            } catch (Exception ignored) {
            }
        }
    }
}
