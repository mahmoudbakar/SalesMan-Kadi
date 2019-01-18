package com.undecode.salesman.utils.network;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.undecode.salesman.R;
import com.undecode.salesman.models.Customer;
import com.undecode.salesman.models.Groups;
import com.undecode.salesman.models.Item;
import com.undecode.salesman.models.LoginResponse;
import com.undecode.salesman.models.Reason;
import com.undecode.salesman.models.Suppliers;
import com.undecode.salesman.models.Unit;
import com.undecode.salesman.models.local.Payment;
import com.undecode.salesman.models.local.Response;
import com.undecode.salesman.models.local.SalesOrder;
import com.undecode.salesman.models.local.UnSuccessfulVisit;
import com.undecode.salesman.utils.Constants;
import com.undecode.salesman.utils.MyPreferance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class API
{
    private BakarRequests bakarRequests;
    private Gson gson;
    private static API api = null;
    Realm realm;

    private API()
    {
        this.bakarRequests = BakarRequests.getInstance();
        gson = new Gson();
        realm = Realm.getDefaultInstance();
    }

    public static API getInstance()
    {
        if (api == null)
        {
            api = new API();
        }
        return api;
    }

    public void getItems(final Context context, final OnDataReady.ArrayReady listener)
    {

        bakarRequests.jsonArrayGetRequest(context, Constants.API.GET_ITEMS, null, new OnBakarResponse.JsonResponse()
        {
            @Override
            public void onSuccess(JSONObject response)
            {

            }

            @Override
            public void onSuccess(final JSONArray response)
            {
                getUnits(context, new OnDataReady.ArrayReady()
                {
                    @Override
                    public void onArrayReady(List list)
                    {
                        List<Item> items = Arrays.asList(gson.fromJson(response.toString(), Item[].class));
                        for (int i = 0; i < items.size(); i++)
                        {
                            for (Unit unit:((List<Unit>)list))
                            {
                                if (items.get(i).getUnitID() == unit.getUnitID())
                                {
                                    items.get(i).setUnit(unit);
                                }
                            }
                        }
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(items);
                        realm.commitTransaction();
                        //listener.onArrayReady(items);
                    }

                    @Override
                    public void onError(Object object)
                    {

                    }
                });
            }

            @Override
            public void onError(VolleyError error)
            {
                listener.onError(false);
            }
        }, true, context.getString(R.string.loading_items));
    }

    public void getReasons(final Context context, final OnDataReady.ArrayReady listener)
    {

        bakarRequests.jsonArrayGetRequest(context, Constants.API.GET_REASONS, null, new OnBakarResponse.JsonResponse()
        {
            @Override
            public void onSuccess(JSONObject response)
            {

            }

            @Override
            public void onSuccess(final JSONArray response)
            {
                getUnits(context, new OnDataReady.ArrayReady()
                {
                    @Override
                    public void onArrayReady(List list)
                    {
                        List<Reason> items = Arrays.asList(gson.fromJson(response.toString(), Reason[].class));
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(items);
                        realm.commitTransaction();
                        //listener.onArrayReady(items);
                    }

                    @Override
                    public void onError(Object object)
                    {

                    }
                });
            }

            @Override
            public void onError(VolleyError error)
            {
//                listener.onError(false);
            }
        }, true, context.getString(R.string.loading_items));
    }

    public void getSuppliers(Context context, final OnDataReady.ArrayReady listener)
    {

        bakarRequests.jsonArrayGetRequest(context, Constants.API.GET_SUPPLIERS, null, new OnBakarResponse.JsonResponse()
        {
            @Override
            public void onSuccess(JSONObject response)
            {

            }

            @Override
            public void onSuccess(JSONArray response)
            {
                List<Suppliers> suppliers = Arrays.asList(gson.fromJson(response.toString(), Suppliers[].class));
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(suppliers);
                realm.commitTransaction();
                listener.onArrayReady(suppliers);
            }

            @Override
            public void onError(VolleyError error)
            {
                listener.onError(false);
            }
        }, true, context.getString(R.string.loading_suppliers));
    }

    public void getGroups(Context context, final OnDataReady.ArrayReady listener)
    {

        bakarRequests.jsonArrayGetRequest(context, Constants.API.GET_GROUPS, null, new OnBakarResponse.JsonResponse()
        {
            @Override
            public void onSuccess(JSONObject response)
            {

            }

            @Override
            public void onSuccess(JSONArray response)
            {
                List<Groups> groups = Arrays.asList(gson.fromJson(response.toString(), Groups[].class));
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(groups);
                realm.commitTransaction();
                listener.onArrayReady(groups);
            }

            @Override
            public void onError(VolleyError error)
            {
                listener.onError(false);
            }
        }, true, context.getString(R.string.loading_groups));
    }

    public void getUnits(Context context, final OnDataReady.ArrayReady listener)
    {

        bakarRequests.jsonArrayGetRequest(context, Constants.API.GET_UNITS, null, new OnBakarResponse.JsonResponse()
        {
            @Override
            public void onSuccess(JSONObject response)
            {

            }

            @Override
            public void onSuccess(JSONArray response)
            {
                List<Unit> units = Arrays.asList(gson.fromJson(response.toString(), Unit[].class));
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(units);
                realm.commitTransaction();
                listener.onArrayReady(units);
            }

            @Override
            public void onError(VolleyError error)
            {
                listener.onError(false);
            }
        }, true, context.getString(R.string.loading_units));
    }

    public void getCustomers(Context context, final OnDataReady.ArrayReady listener)
    {
        bakarRequests.jsonArrayGetRequest(context, Constants.API.GET_CUSTOMERS, null, new OnBakarResponse.JsonResponse()
        {
            @Override
            public void onSuccess(JSONObject response)
            {

            }

            @Override
            public void onSuccess(JSONArray response)
            {
                List<Customer> customers = Arrays.asList(gson.fromJson(response.toString(), Customer[].class));
                int i = 0;
                for (Customer customer:customers)
                {
                    try
                    {
                        customers.get(i).getLastInvoicesR().addAll(customer.getLastInvoices());
                    }catch (NullPointerException e1){}
                    try
                    {
                        customers.get(i).getLastPaymentsR().addAll(customer.getLastPayments());
                    }catch (NullPointerException e1){}
                    i++;
                }
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(customers);
                realm.commitTransaction();
                //listener.onArrayReady(customers);
            }

            @Override
            public void onError(VolleyError error)
            {
                //listener.onError(false);
            }
        }, true, context.getString(R.string.loading_customers));
    }

    public void submitUnsuccessfulVisit(Context context, List<UnSuccessfulVisit> unSuccessfulVisit, final OnDataReady.ObjectReady ready)
    {
        try
        {
            Toast.makeText(context, String.valueOf(unSuccessfulVisit.size()), Toast.LENGTH_SHORT).show();
            String json = gson.toJson(unSuccessfulVisit);
            JSONArray jsonArray = new JSONArray(json);
            bakarRequests.jsonArrayPostRequest(context, Constants.API.SaveUnSuccessfulVisits, jsonArray, new OnBakarResponse.JsonResponse()
            {
                @Override
                public void onSuccess(JSONObject response)
                {
                    List<Response> responses = Arrays.asList(gson.fromJson(response.toString(), Response[].class));
                    ready.onObjectReady(responses);
                }

                @Override
                public void onSuccess(JSONArray response)
                {
                    List<Response> responses = Arrays.asList(gson.fromJson(response.toString(), Response[].class));
                    ready.onObjectReady(responses);
                }

                @Override
                public void onError(VolleyError error)
                {

                }
            }, true, context.getString(R.string.uploading));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void submitPayment(Context context, List<Payment> payments, final OnDataReady.ObjectReady ready)
    {
        try
        {
            Toast.makeText(context, String.valueOf(payments.size()), Toast.LENGTH_SHORT).show();
            String json = gson.toJson(payments);
            JSONArray jsonArray = new JSONArray(json);
            bakarRequests.jsonArrayPostRequest(context, Constants.API.SavePayments, jsonArray, new OnBakarResponse.JsonResponse()
            {
                @Override
                public void onSuccess(JSONObject response)
                {
                    List<Response> responses = Arrays.asList(gson.fromJson(response.toString(), Response[].class));
                    ready.onObjectReady(responses);
                }

                @Override
                public void onSuccess(JSONArray response)
                {
                    List<Response> responses = Arrays.asList(gson.fromJson(response.toString(), Response[].class));
                    ready.onObjectReady(responses);
                }

                @Override
                public void onError(VolleyError error)
                {

                }
            }, true, context.getString(R.string.uploading));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void submitSalesOrder(Context context, List<SalesOrder> salesOrders, final OnDataReady.ObjectReady ready)
    {
        try
        {
            //Toast.makeText(context, String.valueOf(salesOrders.size()), Toast.LENGTH_SHORT).show();
            String json = gson.toJson(salesOrders);
            JSONArray jsonArray = new JSONArray(json);
            bakarRequests.jsonArrayPostRequest(context, Constants.API.SaveSalesOrders, jsonArray, new OnBakarResponse.JsonResponse()
            {
                @Override
                public void onSuccess(JSONObject response)
                {
                    List<Response> responses = Arrays.asList(gson.fromJson(response.toString(), Response[].class));
                    ready.onObjectReady(responses);
                }

                @Override
                public void onSuccess(JSONArray response)
                {
                    List<Response> responses = Arrays.asList(gson.fromJson(response.toString(), Response[].class));
                    ready.onObjectReady(responses);
                }

                @Override
                public void onError(VolleyError error)
                {

                }
            }, true, context.getString(R.string.uploading));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void login(final Context context, final String user, final String password, final OnDataReady.ObjectReady ready, boolean progress)
    {
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("username", user);
            jsonObject.put("password", password);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.Login, jsonObject,
                    new OnBakarResponse.JsonResponse()
                    {
                        @Override
                        public void onSuccess(JSONObject response)
                        {
                            LoginResponse loginResponse = gson.fromJson(response.toString(), LoginResponse.class);
                            loginResponse.setUser(user);
                            loginResponse.setPassword(password);
                            loginResponse.setId(1);
                            ready.onObjectReady(loginResponse);
                            realm.beginTransaction();
                            realm.copyToRealmOrUpdate(loginResponse);
                            realm.commitTransaction();
                        }

                        @Override
                        public void onSuccess(JSONArray response)
                        {

                        }

                        @Override
                        public void onError(VolleyError error)
                        {
                            RealmResults<LoginResponse> loginResponses = realm.where(LoginResponse.class).findAll();
                            if (loginResponses.size() != 0)
                            {
                                ready.onObjectReady(loginResponses.get(0));
                            }else
                            {
                                ready.onObjectReady(null);
                            }
                        }
                    }, progress, "Signing in");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
