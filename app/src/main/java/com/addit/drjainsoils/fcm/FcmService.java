package com.addit.drjainsoils.fcm;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.addit.drjainsoils.javaClass.AppContext;
import com.addit.drjainsoils.utils.Network;

import java.io.IOException;

public class FcmService extends IntentService {
    private static final String GCM_ERROR = "GCM-ERROR";
    private static final String REGISTER_ID = "REGISTER_ID";
    private static final String REGISTER_TOKEN = "REGISTER_TOKEN";
    private static String RegistrationID = null, RegistrationToken = null;

    public FcmService() {
        super(FcmService.class.getSimpleName());
    }

    private static void subscribeTopics(Context context, String[] topics) throws IOException {
       /* GcmPubSub pubSub = GcmPubSub.getInstance(context);
        for (String topic : topics) {
            pubSub.subscribe(RegistrationToken, "/topics/" + topic, null);
        }*/
    }

    private static boolean initRegistrationToken(Context context) throws IOException {
       // InstanceID instanceID = InstanceID.getInstance(context);
        String RegToken = FirebaseInstanceId.getInstance().getToken();
        if (RegToken != null && (RegistrationToken == null || !RegToken.equals(RegistrationToken))) {
            // store this registrationToken in sharedPref
            storeRegistrationTokenInSharedPrefs(RegistrationToken = RegToken);
            return true;
        }
        return false;
    }

   /* private static boolean initRegistrationID(Context context) throws IOException {
        GoogleCloudMessaging gcmObj = GoogleCloudMessaging.getInstance(context);
        String RegID = gcmObj.register(FcmConstants.SENDER_OR_PROJECT_ID);
        if (RegID != null && (RegistrationID == null || !RegID.equals(RegistrationID))) {
            // store this registrationID in sharedPref
            FcmService.storeRegistrationIDinSharedPrefs(RegistrationID = RegID);
            return true;
        }
        return false;
    }*/

    private static void register(Context context) throws IOException          /// This Method must be called by "onHandleIntent()" method only
    {
        // 1. get new registrationToken from GCM server for this device
        boolean isNewTokenAvailable = initRegistrationToken(context);

        // 2. If we get token, subscribe to topics
        // subscribe to various topics so that when server sends data under this 'category topic', you will receive the message.
        // Otherwise server will have to send you message to all devices individually(for which server must have your RegistrationID of each device)
        if (RegistrationToken != null)
            subscribeTopics(context, FcmConstants.TOPICS);

        // 3. get new registrationID from GCM server
        boolean isNewRegIdAvailable = true;//initRegistrationID(context);

        // 4. Notify our server
        if ((isNewTokenAvailable || isNewRegIdAvailable) && com.addit.drjainsoils.app.AppContext.loginDetails.userID != null) {
            PreferenceManager.getDefaultSharedPreferences(AppContext.getContext()).edit()
                    .putBoolean(com.addit.drjainsoils.app.AppContext.GCM_NOTIFIED, false)
                    .apply();
            if (com.addit.drjainsoils.app.AppContext.volleyGcmUpdateNotifier == null || com.addit.drjainsoils.app.AppContext.volleyGcmUpdateNotifier.hasHadResponseDelivered())
                (com.addit.drjainsoils.app.AppContext.volleyGcmUpdateNotifier = new com.addit.drjainsoils.app.AppContext.VolleyGcmUpdateNotifier()).executeRequest();
        }
    }

    public static void start(Context context) {
        // setup GCM
        getRegistrationID();
        getRegistrationToken();
        if (RegistrationID != null && RegistrationToken != null) {
            //       } else if (!checkPlayServices(this)) {
            //           Toast.makeText(this, "Google Play Service is not available!", Toast.LENGTH_SHORT).show();   /// Google Play Service Not Avilable
        } else if (!Network.isNetworkAvailable(AppContext.getContext())) {
            Log.e(GCM_ERROR, "Network Connection not available!");       /// Internet connection not available
        } else {
            context.startService(new Intent(context, FcmService.class));
        }
    }

    private static void storeRegistrationIDinSharedPrefs(String regId) {
        RegistrationID = regId;
        PreferenceManager.getDefaultSharedPreferences(AppContext.getContext()).edit().putString(REGISTER_ID, RegistrationID).apply();
    }

    private static void storeRegistrationTokenInSharedPrefs(String token) {
        RegistrationToken = token;
        PreferenceManager.getDefaultSharedPreferences(AppContext.getContext()).edit().putString(REGISTER_TOKEN, RegistrationToken).apply();
    }

    private static String getRegistrationIdFromSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(AppContext.getContext()).getString(REGISTER_ID, null);
    }

    private static String getRegistrationTokenFromSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(AppContext.getContext()).getString(REGISTER_TOKEN, null);
    }

    public static String getRegistrationID() {
        if (RegistrationID == null)
            RegistrationID = getRegistrationIdFromSharedPrefs();
        return RegistrationID;
    }

    public static String getRegistrationToken() {
        if (RegistrationToken == null)
            RegistrationID = getRegistrationTokenFromSharedPrefs();
        return RegistrationToken;
    }

   /* public static boolean checkPlayServices(Activity activity) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(AppContext.getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.e(GCM_ERROR, "This Device Doesn't support play services!");
            }
            return false;
        } else {
            return true;
        }
    }*/

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            register(this);
        } catch (Exception ex) {
            Log.e(GCM_ERROR, "GCM Registration Unsuccessful!");
        }
    }
}
