package com.addit.drjainsoils.request;

import com.android.volley.AuthFailureError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class VolleyJsonObjectRequest extends VolleyRequest<JSONObject> {

    public VolleyJsonObjectRequest(int method, String url) {
        super(method, url);
    }

    public VolleyJsonObjectRequest(int method, String url, boolean customCaching) {
        super(method, url, customCaching);
    }

    public VolleyJsonObjectRequest(int method, String url, int cacheExpireInXHours, int cacheRefreshInXMin) {
        super(method, url, cacheExpireInXHours, cacheRefreshInXMin);
    }

    @Override
    protected JSONObject parse(String data) throws Exception {
        return new JSONObject(data);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> map = new HashMap<>();
        map.put("content-type", PROTOCOL_CONTENT_TYPE_JSON);
        return map;
    }
}
