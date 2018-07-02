package com.addit.drjainsoils.request;

import com.android.volley.AuthFailureError;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public abstract class VolleyJsonArrayRequest extends VolleyRequest<JSONArray> {

    public VolleyJsonArrayRequest(int method, String url) {
        super(method, url);
    }

    public VolleyJsonArrayRequest(int method, String url, boolean customCaching) {
        super(method, url, customCaching);
    }

    public VolleyJsonArrayRequest(int method, String url, int cacheExpireInXHours, int cacheRefreshInXMin) {
        super(method, url, cacheExpireInXHours, cacheRefreshInXMin);
    }

    @Override
    protected JSONArray parse(String data) throws Exception {
        return new JSONArray(data);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> map = new HashMap<>();
        map.put("content-type", PROTOCOL_CONTENT_TYPE_JSON);
        return map;
    }
}
