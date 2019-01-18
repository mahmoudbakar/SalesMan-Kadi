package com.undecode.salesman.utils.network;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface OnBakarResponse
{
    public interface JsonResponse
    {
        public void onSuccess(JSONObject response);
        public void onSuccess(JSONArray response);
        public void onError(VolleyError error);
    }

    public interface StringResponse
    {
        public void onResponse(String stringResponse);
        public void onError(VolleyError error);
    }
}
