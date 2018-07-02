package com.addit.drjainsoils.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.addit.drjainsoils.javaClass.AppContext;
import com.addit.drjainsoils.utils.Network;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public abstract class VolleyRequest<T> extends Request<T> implements Response.Listener<T>, Response.ErrorListener {
    public enum NETWORK_ERROR {NETWORK_CONN_UNAVAILABLE, NETWORK_CONN_LOST, NETWORK_CONN_PERMISSION_DENIED}

    private static RequestQueue mRequestQueue = null;

    public static final String PROTOCOL_CHARSET = "utf-8";
    public static final String PROTOCOL_CONTENT_TYPE_PLAIN_TEXT = String.format("application/x-www-form-urlencoded; charset=%s", PROTOCOL_CHARSET);
    public static final String PROTOCOL_CONTENT_TYPE_JSON = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private String mRequestBody = null;

    private boolean customCaching = false;
    private int cacheExpireInXHours = 2;
    private int cacheRefreshInXMin = 1;

    static {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(AppContext.getContext());
    }

    {
        setRequestQueue(mRequestQueue);
    }

    public VolleyRequest(int method, String url) {
        super(method, url, null);
        customCaching = false;
        setShouldCache(true);
    }

    public VolleyRequest(int method, String url, boolean customCaching) {
        super(method, url, null);
        this.customCaching = customCaching;
        setShouldCache(this.customCaching);
    }

    public VolleyRequest(int method, String url, int cacheExpireInXHours, int cacheRefreshInXMin) {
        super(method, url, null);
        this.cacheExpireInXHours = cacheExpireInXHours;
        this.cacheRefreshInXMin = cacheRefreshInXMin;
        setShouldCache(this.cacheExpireInXHours > 0 && this.cacheRefreshInXMin > 0);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            Cache.Entry cacheEntry = customCaching ? parseIgnoreCacheHeaders(response) : HttpHeaderParser.parseCacheHeaders(response);
            String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));

            return Response.success(parse(data), cacheEntry);
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (NoConnectionError e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        VolleyLog.wtf("VOLLEY_RESPONSE : %s", "OK");
        this.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError volleyError) {
        volleyError.fillInStackTrace().printStackTrace();
        if (volleyError.networkResponse == null || volleyError.networkResponse.data == null) {
            onNetworkConnectionError(NETWORK_ERROR.NETWORK_CONN_LOST);
            return;
        }
        VolleyLog.wtf("VOLLEY_ERROR_RESPONSE : %s", new String(volleyError.networkResponse.data));
        onErrorResponse(volleyError);
    }

    @Override
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE_JSON;
    }

    @Override
    public byte[] getPostBody() throws AuthFailureError {
        return getBody();
    }

    @Override
    public byte[] getBody() {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return null;
    }

    public Cache.Entry parseIgnoreCacheHeaders(NetworkResponse response) {
        Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
        if (cacheEntry == null) {
            cacheEntry = new Cache.Entry();
        }
        long cacheHitButRefreshed = cacheRefreshInXMin * 60 * 1000; // in X minutes cache will be hit, but also refreshed on background
        long cacheExpired = cacheExpireInXHours * 60 * 60 * 1000; // in X hours this cache entry expires completely
        long now = System.currentTimeMillis();
        long softExpire = now + cacheHitButRefreshed;
        long ttl = now + cacheExpired;
        cacheEntry.data = response.data;
        cacheEntry.softTtl = softExpire;
        cacheEntry.ttl = ttl;
        String headerValue;
        headerValue = response.headers.get("Date");
        if (headerValue != null) {
            cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }
        headerValue = response.headers.get("Last-Modified");
        if (headerValue != null) {
            cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }
        headerValue = response.headers.get("ETag");
        if (headerValue != null) {
            cacheEntry.etag = headerValue;
        }
        cacheEntry.responseHeaders = response.headers;

        return cacheEntry;
    }

    public boolean isCustomCachingEnabled() {
        return customCaching;
    }

    public int getCacheExpireInXHours() {
        return cacheExpireInXHours;
    }

    public int getCacheRefreshInXMin() {
        return cacheRefreshInXMin;
    }

    public String setRequestBody() {
        return null;
    }

    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public void executeRequest() {
        this.mRequestBody = setRequestBody();
        Cache cache = mRequestQueue.getCache();
        Cache.Entry entry = cache.get(getUrl());
        if (entry != null) {
            try {
                String data = new String(entry.data, VolleyRequest.PROTOCOL_CHARSET);
                onResponse(parse(data));
            } catch (Exception e) {
                try {
                    cache.invalidate(getUrl(), false);
                    if (Network.isNetworkAvailable(AppContext.getContext()))
                        addToRequestQueue(this);
                    else
                        onNetworkConnectionError(NETWORK_ERROR.NETWORK_CONN_UNAVAILABLE);
                } catch (Exception ignored) {
                }
            }
        } else if (Network.isNetworkAvailable(AppContext.getContext()))
            addToRequestQueue(this);
        else
            onNetworkConnectionError(NETWORK_ERROR.NETWORK_CONN_UNAVAILABLE);
    }

    private static <T> void addToRequestQueue(Request<T> request) {
        request.setTag(AppContext.class.getSimpleName());
        request.setRetryPolicy(new DefaultRetryPolicy(15 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }

    public static void cancelAllPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static Cache.Entry getCacheEntry(String url) {
        if (mRequestQueue != null) {
            Cache cache = mRequestQueue.getCache();
            return cache.get(url);
        } else
            return null;
    }

    // user must include these methods

    protected abstract T parse(String data) throws Exception;

    protected abstract void onNetworkConnectionError(NETWORK_ERROR networkError);
}
