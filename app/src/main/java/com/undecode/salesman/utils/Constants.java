package com.undecode.salesman.utils;

public interface Constants
{
    public class API
    {
        public static final String LOCAL = "http://192.168.1.44:30001/";
        public static final String GLOBAL = "http://197.50.93.44:30001/";
        public static final String APIS = GLOBAL + "api/sales/";
        public static final String IMAGES = GLOBAL + "images/";
        public static final String GET_ITEMS = APIS +"getitems";
        public static final String GET_SUPPLIERS = APIS +"GetSuppliers";
        public static final String GET_GROUPS = APIS +"GetGroups";
        public static final String GET_UNITS = APIS +"GetUnits";
        public static final String GET_CUSTOMERS = APIS +"GetCustomers";
        public static final String GET_REASONS = APIS +"GetReasons";
        public static final String SaveUnSuccessfulVisits = APIS +"SaveUnSuccessfulVisits";
        public static final String SavePayments = APIS +"SavePayments";
        public static final String GET_OFFERS = APIS +"GetOffers";
        public static final String SaveSalesOrders = APIS +"SaveSalesOrders";
        public static final String Login = GLOBAL + "api/Account/LogIn";
    }

    public class OREDR_TYPE
    {
        public static final int PENDING = 0;
        public static final int ACCEPTED = 1;
        public static final int DELIVERED = 2;
        public static final int REFUSED = 3;
    }

    public class KEY
    {
        public static final String ROOM_ID = "roomID";
        public static final String ITEM = "ITEM";
        public static final String USER = "reciverID";
        public static final String ID = "ID";
        public static final String PHONE = "PHONE";
        public static final String TOKEN = "TOKEN";
        public static final String PASSWORD = "PASSWORD";
    }
}
