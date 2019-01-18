package com.undecode.salesman.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.undecode.salesman.R;
import com.undecode.salesman.models.local.Payment;
import com.undecode.salesman.utils.MyDate;
import com.undecode.salesman.utils.MyNumbers;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.ViewHolder> {
    Context context;
    Activity activity;
    RealmResults<Payment> payments;
    Realm realm;
    MyDate myDate;
    MyNumbers myNumbers;

    public PaymentsAdapter(Context context, Activity activity, int customerID) {
        this.context = context;
        this.activity = activity;
        realm = Realm.getDefaultInstance();
        myDate = new MyDate();
        myNumbers = new MyNumbers();
        payments = realm.where(Payment.class).equalTo("customerID", customerID).findAll();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_payment_report, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Payment payment = payments.get(i);
        viewHolder.txtID.setText(String.valueOf(payment.getId()));
        viewHolder.txtValue.setText(String.valueOf(payment.getPaymentValue()));
        viewHolder.txtVoucher.setText(String.valueOf(payment.getRecieptNO()));
        viewHolder.txtDate.setText(myDate.getAmPmStringOfDateTime(payment.getPaymentDate()));
        switch (payment.getPaymentType())
        {
            case 0:
                viewHolder.txtType.setText(context.getString(R.string.cash));
                break;
            case 1:
                viewHolder.txtType.setText(context.getString(R.string.cheque));
                break;
            case 2:
                viewHolder.txtType.setText(context.getString(R.string.visa));
                break;
        }
    }

    @Override
    public int getItemCount()
    {
        return payments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.txtID)
        TextView txtID;
        @BindView(R.id.txtValue)
        TextView txtValue;
        @BindView(R.id.txtType)
        TextView txtType;
        @BindView(R.id.txtVoucher)
        TextView txtVoucher;
        @BindView(R.id.txtDate)
        TextView txtDate;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
