package com.addit.drjainsoils.request;


public abstract class VolleyStringRequest extends VolleyRequest<String> {

    public VolleyStringRequest(int method, String url) {
        super(method, url);
    }

    public VolleyStringRequest(int method, String url, boolean customCaching) {
        super(method, url, customCaching);
    }

    public VolleyStringRequest(int method, String url, int cacheExpireInXHours, int cacheRefreshInXMin) {
        super(method, url, cacheExpireInXHours, cacheRefreshInXMin);
    }

    @Override
    protected String parse(String data) throws Exception {
        return data;
    }
}
