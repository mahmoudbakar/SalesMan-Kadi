package com.undecode.salesman.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.view.View;

import com.undecode.salesman.R;

import java.util.ArrayList;

public class Sender implements ActivityCompat.OnRequestPermissionsResultCallback
{
    private Context context;
    private Activity activity;
    static final int MAX_SMS_LENGHT = 160;
    static final int SMS_PERMISSION = 95;
    static final String SMS_SENT = "SMS_SENT";
    static final String SMS_DELIVERED= "SMS_DELIVERED";
    private String phone, message;

    public Sender(Context context, Activity activity)
    {
        this.context = context;
        this.activity = activity;
    }

    public void sendSms(String phone, String message)
    {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            this.phone = phone;
            this.message = message;
            new AlertDialog.Builder(context)
                    .setTitle(R.string.sms_permission)
                    .setMessage(R.string.sms_dialog)
                    .setIcon(context.getResources().getDrawable(R.drawable.ic_email))
                    .setCancelable(false)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION);
                        }
                    })
                    .show();
        }else
        {
            SmsManager smsManager = SmsManager.getDefault();
            //PendingIntent piSend = PendingIntent.getBroadcast(context, 0, new Intent(SMS_SENT), 0);
            //PendingIntent piDelivered = PendingIntent.getBroadcast(context, 0, new Intent(SMS_DELIVERED), 0);
            int length = message.length();
            if (length > MAX_SMS_LENGHT)
            {
                ArrayList<String> messagelist = smsManager.divideMessage(message);
                smsManager.sendMultipartTextMessage(phone, null, messagelist, null, null);
            }else
            {
                smsManager.sendTextMessage(phone, null, message, null, null);
            }
        }
    }

    public boolean sendEmail()
    {
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] strings, @NonNull int[] grantResults) {
        switch (requestCode) {
            case SMS_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    sendSms(phone, message);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }


//    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
//    {
//        @Override
//        public void onReceive(Context context, Intent intent)
//        {
//            String event = "";
//            String message = "";
//            switch (getResultCode()) {
//                case Activity.RESULT_OK:
//                    event = "sendmessage:success";
//                    break;
//                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                    event = "sendmessage:failed";
//                    message = "Message not sent.";
//                    break;
//                case SmsManager.RESULT_ERROR_NO_SERVICE:
//                    event = "sendmessage:failed";
//                    message = "No service.";
//                    break;
//                case SmsManager.RESULT_ERROR_NULL_PDU:
//                    event = "sendmessage:failed";
//                    message = "Error: Null PDU.";
//                    break;
//                case SmsManager.RESULT_ERROR_RADIO_OFF:
//                    event = "sendmessage:failed";
//                    message = "Error: Radio off.";
//                    break;
//            }
//
//            Log.wtf("BAKAR SMS", event + "\n" + message);
//            context.unregisterReceiver(broadcastReceiver);
//        }
//    };
}
