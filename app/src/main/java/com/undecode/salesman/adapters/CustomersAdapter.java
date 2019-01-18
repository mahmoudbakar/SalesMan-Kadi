package com.undecode.salesman.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.undecode.salesman.R;
import com.undecode.salesman.SalesOrderActivity;
import com.undecode.salesman.models.Customer;
import com.undecode.salesman.models.Reason;
import com.undecode.salesman.models.local.Payment;
import com.undecode.salesman.models.local.SalesOrder;
import com.undecode.salesman.models.local.UnSuccessfulVisit;
import com.undecode.salesman.models.local.Visit;
import com.undecode.salesman.utils.FillSpinner;
import com.undecode.salesman.utils.MyDate;
import com.undecode.salesman.utils.MyNumbers;
import com.undecode.salesman.utils.Sender;
import com.undecode.salesman.utils.network.API;
import com.undecode.salesman.utils.network.OnDataReady;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.ViewHolder> {
    Context context;
    Activity activity;
    List<Customer> customers;
    int visit = 0;
    MyDate myDate;
    Realm realm;
    FillSpinner fillSpinner;
    MyNumbers myNumbers;
    RealmResults<Reason> reasons;

    public CustomersAdapter(Context context, Activity activity, List<Customer> customers)
    {
        this.context = context;
        this.activity = activity;
        this.customers = customers;
        myDate = new MyDate();
        realm = Realm.getDefaultInstance();
        reasons = realm.where(Reason.class).findAll();
        fillSpinner = new FillSpinner();
        myNumbers = new MyNumbers();
    }

    public void setCustomers(List<Customer> customers)
    {
        this.customers = customers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_customers, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        final Customer customer = customers.get(i);
        viewHolder.txtID.setText(String.valueOf(customer.getCustomerID()));
        viewHolder.txtName.setText(customer.getCustomerNameA());
        viewHolder.txtBalance.setText(String.valueOf(myNumbers.round(customer.getBalance(), 2)));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = activity.getLayoutInflater().inflate(R.layout.dialog_three_choice, null);
                final View view2 = activity.getLayoutInflater().inflate(R.layout.dialog_payment, null);
                final View view3 = activity.getLayoutInflater().inflate(R.layout.dialog_unsuccessful, null);
                new AlertDialog.Builder(context)
                        .setCancelable(false)
                        .setTitle(context.getString(R.string.start_visit))
                        .setIcon(context.getResources().getDrawable(R.drawable.ic_visit))
                        .setView(view)
                        .setPositiveButton(context.getString(R.string.start), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Dialog dialog2 =Dialog.class.cast(dialog);
                                RadioGroup rg = (RadioGroup) dialog2.findViewById(R.id.rg);
                                Intent intent;
                                Gson gson = new Gson();
                                switch (rg.getCheckedRadioButtonId())
                                {
                                    case R.id.rd1:
                                        intent = new Intent(context, SalesOrderActivity.class);
                                        intent.putExtra("customer",customer.getCustomerID());
                                        context.startActivity(intent);
                                        break;
                                    case R.id.rd2:

                                        new AlertDialog.Builder(context)
                                                .setCancelable(false)
                                                .setTitle(context.getString(R.string.collect_payment))
                                                .setIcon(context.getResources().getDrawable(R.drawable.ic_payment))
                                                .setMessage(customer.getCustomerNameE() +context.getString(R.string.balance_collect) + String.valueOf(customer.getBalance()))
                                                .setView(view2)
                                                .setPositiveButton(context.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Dialog dialog2 =Dialog.class.cast(dialog);
                                                        RadioGroup rg = (RadioGroup) dialog2.findViewById(R.id.rg);
                                                        EditText edValue = (EditText) dialog2.findViewById(R.id.edValue);
                                                        EditText edNumber = (EditText) dialog2.findViewById(R.id.edNumber);
                                                        EditText edNote = (EditText) dialog2.findViewById(R.id.edNote);
                                                        if (!edValue.getText().toString().equals("") || !edNumber.getText().toString().equals(""))
                                                        {
                                                            int paymentType = 0;
                                                            Intent intent;
                                                            switch (rg.getCheckedRadioButtonId())
                                                            {
                                                                case R.id.rd1:
                                                                    paymentType = 0;
                                                                    break;
                                                                case R.id.rd2:
                                                                    paymentType = 1;
                                                                    break;
                                                                case R.id.rd3:
                                                                    paymentType = 2;
                                                                    break;
                                                            }
                                                            try
                                                            {
                                                                Number num = realm.where(Visit.class).max("visitID");
                                                                int nextID;
                                                                if(num == null)
                                                                {
                                                                    nextID = 1;
                                                                } else {
                                                                    nextID = num.intValue() + 1;
                                                                }
                                                                Visit visit = new Visit(nextID, customer.getCustomerID(), customer.getCustomerNameE(), myDate.getCurrentStringDateTime(), "Payment: " + edValue.getText().toString()+" LE");
                                                                realm.beginTransaction();
                                                                realm.copyToRealmOrUpdate(visit);
                                                                realm.commitTransaction();
                                                                Sender sender = new Sender(context, activity);
                                                                sender.sendSms("01110128800", "Sales Man Started Payment With\n"+customer.getCustomerNameE());
                                                                //Toast.makeText(context, "SMS SENT.", Toast.LENGTH_SHORT).show();
                                                            }catch (Exception e)
                                                            {

                                                            }
                                                            Number num = realm.where(Payment.class).max("id");
                                                            int nextID;
                                                            if(num == null)
                                                            {
                                                                nextID = 1;
                                                            } else {
                                                                nextID = num.intValue() + 1;
                                                            }
                                                            Payment payment = new Payment();
                                                            payment.setCustomerID(customer.getCustomerID());
                                                            payment.setLatitude(31.00);
                                                            payment.setLongitude(31.00);
                                                            payment.setNotes(edNote.getText().toString());
                                                            payment.setPaymentValue(Double.parseDouble(edValue.getText().toString()));
                                                            payment.setPaymentType(paymentType);
                                                            payment.setRecieptNO(edNumber.getText().toString());
                                                            payment.setSaved(false);
                                                            payment.setPaymentID(0);
                                                            payment.setId(nextID);
                                                            payment.setPaymentDate(myDate.getCurrentStringDateTime());
                                                            realm.beginTransaction();
                                                            realm.copyToRealmOrUpdate(payment);
                                                            realm.commitTransaction();
                                                            Toast.makeText(context, String.valueOf(visit), Toast.LENGTH_SHORT).show();
                                                        }else
                                                        {
                                                            Toast.makeText(context, context.getString(R.string.value_chique_empty), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                })
                                                .setNegativeButton(context.getString(R.string.cancel), null).show();
                                        break;
                                    case R.id.rd3:
                                        final int[] reasonID = {0};
                                        final Spinner spinner = (Spinner) view3.findViewById(R.id.spReasons);
                                        fillSpinner.fillSpinner(context, reasons, spinner);
                                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                                        {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                                            {
                                                reasonID[0] = reasons.get(position).getReasonID();
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent)
                                            {

                                            }
                                        });

                                        new AlertDialog.Builder(context)
                                                .setCancelable(false)
                                                .setTitle(context.getString(R.string.collect_payment))
                                                .setIcon(context.getResources().getDrawable(R.drawable.ic_payment))
                                                .setMessage(customer.getCustomerNameE() +context.getString(R.string.balance_collect)+ String.valueOf(customer.getBalance()))
                                                .setView(view3)
                                                .setPositiveButton(context.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Dialog dialog2 =Dialog.class.cast(dialog);
                                                        EditText edNote = (EditText) dialog2.findViewById(R.id.edNote);
                                                        try
                                                        {

                                                            Number num = realm.where(Visit.class).max("visitID");
                                                            int nextID;
                                                            if(num == null)
                                                            {
                                                                nextID = 1;
                                                            } else {
                                                                nextID = num.intValue() + 1;
                                                            }
                                                            Visit visit = new Visit(nextID, customer.getCustomerID(), customer.getCustomerNameE(), myDate.getCurrentStringDateTime(), "Unsuccessful");
                                                            realm.beginTransaction();
                                                            realm.copyToRealmOrUpdate(visit);
                                                            realm.commitTransaction();
                                                            Sender sender = new Sender(context, activity);
                                                            sender.sendSms("01110128800", "Sales Man Reported Unsuccessful Visit With\n"+customer.getCustomerNameE());
                                                            //Toast.makeText(context, "SMS SENT.", Toast.LENGTH_SHORT).show();
                                                        }catch (Exception e)
                                                        {

                                                        }
                                                        Number num = realm.where(Payment.class).max("id");
                                                        int nextID;
                                                        if(num == null)
                                                        {
                                                            nextID = 1;
                                                        } else {
                                                            nextID = num.intValue() + 1;
                                                        }
                                                        UnSuccessfulVisit unSuccessfulVisit = new UnSuccessfulVisit();
                                                        unSuccessfulVisit.setCustomerID(customer.getCustomerID());
                                                        unSuccessfulVisit.setReason(((Reason)spinner.getSelectedItem()).getReasonNameE());
                                                        unSuccessfulVisit.setLatitude(31.00);
                                                        unSuccessfulVisit.setLongitude(31.00);
                                                        unSuccessfulVisit.setNotes(edNote.getText().toString());
                                                        unSuccessfulVisit.setSaved(false);
                                                        unSuccessfulVisit.setReasonID(reasonID[0]);
                                                        unSuccessfulVisit.setTransDate(myDate.getCurrentStringDateTime());
                                                        unSuccessfulVisit.setId(nextID);
                                                        realm.beginTransaction();
                                                        realm.copyToRealmOrUpdate(unSuccessfulVisit);
                                                        realm.commitTransaction();
                                                        Toast.makeText(context, String.valueOf(visit), Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .setNegativeButton(context.getString(R.string.cancel), null).show();
                                        break;
                                }

                                Toast.makeText(context, String.valueOf(visit), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(context.getString(R.string.cancel), null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtID)
        TextView txtID;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtBalance)
        TextView txtBalance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
