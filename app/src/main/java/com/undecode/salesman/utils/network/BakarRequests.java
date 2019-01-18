package com.undecode.salesman.utils.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BakarRequests
{
    private static final String TAG = "BakarRequests";
    private static BakarRequests instance = null;

    public BakarSingleton bakarSingleton;
    //lets test

    private BakarRequests(Context context)
    {
        bakarSingleton = BakarSingleton.getInstance(context);
    }

    public static synchronized BakarRequests getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new BakarRequests(context);
        }
        return instance;
    }

    public static synchronized BakarRequests getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException(BakarRequests.class.getSimpleName() + " is not initialized, Call getInstance(sendContextHere) first");
        }
        return instance;
    }

    public void jsonObjectPostRequest(Context context, String url, final JSONObject jsonObject, final OnBakarResponse.JsonResponse listener, final boolean progress, String message)
    {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        if (progress)
        {
            try
            {
                progressDialog.setMessage(message);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.setIndeterminate(true);
                progressDialog.show();
            }catch (Exception e){}
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        progressDialog.dismiss();
                        Log.wtf("Bakar response", response.toString());
                        try
                        {
                            listener.onSuccess(response);
                            if (progress) progressDialog.dismiss();
                        }catch (Exception e){}
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Log.wtf("Bakar response", error.getMessage());
                        listener.onError(error);
                        try
                        {
                            if (progress) progressDialog.dismiss();
                        }catch (Exception e){}
                    }
                })
        {
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }
        };
        request.setTag("R");
        request.setShouldCache(false);
        bakarSingleton.addToRequestQueue(request);
    }

    public void jsonObjectGetRequest(Context context, String url, final OnBakarResponse.JsonResponse listener, final boolean progress, String message)
    {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        if (progress)
        {

            progressDialog.setMessage(message);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        progressDialog.dismiss();
                        Log.wtf("Bakar response", response.toString());
                        listener.onSuccess(response);
                        if (progress) progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Log.wtf("Bakar response", error.getMessage());
                        listener.onError(error);
                        if (progress) progressDialog.dismiss();
                    }
                });
        request.setTag("R");
        request.setShouldCache(false);
        bakarSingleton.addToRequestQueue(request);
    }

    public void jsonArrayGetRequest(Context context, String url, JSONArray jsonArray, final OnBakarResponse.JsonResponse listener, final boolean progress, String message)
    {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        if (progress)
        {

            progressDialog.setMessage(message);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();
                        Log.wtf("Bakar response", response.toString());
                        listener.onSuccess(response);
                        if (progress) progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Log.wtf("Bakar response", error.getMessage());
                        listener.onError(error);
                        if (progress) progressDialog.dismiss();
                    }
                });
        request.setTag("R");
        request.setShouldCache(false);
        bakarSingleton.addToRequestQueue(request);
    }

    public void jsonArrayPostRequest(Context context, String url, JSONArray jsonArray, final OnBakarResponse.JsonResponse listener, final boolean progress, String message)
    {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        if (progress)
        {

            progressDialog.setMessage(message);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();
                        Log.wtf("Bakar response", response.toString());
                        listener.onSuccess(response);
                        if (progress) progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Log.wtf("Bakar response", error.getMessage());
                        listener.onError(error);
                        if (progress) progressDialog.dismiss();
                    }
                });
        request.setTag("R");
        request.setShouldCache(false);
        bakarSingleton.addToRequestQueue(request);
    }

    public void multipartRequest(final Context context, String url, final Map<String, String> txtData, final FileModel fileModel, final OnBakarResponse.StringResponse listener, final boolean progress, String message)
    {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        if (progress)
        {

            progressDialog.setMessage(message);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>()
        {
            @Override
            public void onResponse(NetworkResponse response)
            {
                progressDialog.dismiss();
                String s = new String(response.data);
                listener.onResponse(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                progressDialog.dismiss();
                listener.onError(error);
            }
        })
        {
//            @Override
//            public String getBodyContentType()
//            {
//                return "Content-Type: form-data; charset=utf-8";
//            }

            @Override
            protected Map<String, String> getParams()
            {
                return txtData;
            }

            @Override
            protected Map<String, DataPart> getByteData()
            {
                Map<String, DataPart> files = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                if (fileModel != null)
                {
                    files.put("file", new DataPart(fileModel.getName(), fileModel.getFile(), fileModel.getMime()));
                }

                return files;
            }
        };
        multipartRequest.setTag("R");
        bakarSingleton.addToRequestQueue(multipartRequest);
    }
}
