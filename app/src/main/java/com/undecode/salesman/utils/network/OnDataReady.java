package com.undecode.salesman.utils.network;

import java.util.List;

public interface OnDataReady
{
    public interface ArrayReady
    {
        public void onArrayReady(List list);
        public void onError(Object object);
    }

    public interface ObjectReady
    {
        public void onObjectReady(Object object);
        public void onError(Object object);
    }

    public interface ResponseReady
    {
        public void onResponseReady(String stringResponse);
    }
}
