package com.undecode.salesman.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.undecode.salesman.R;
import com.undecode.salesman.SalesOrdersReportActivity;
import com.undecode.salesman.models.local.SalesOrder;
import com.undecode.salesman.utils.MyDate;
import com.undecode.salesman.utils.MyNumbers;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class SalesOrderAdapter extends RecyclerView.Adapter<SalesOrderAdapter.ViewHolder>
{
    Context context;
    Activity activity;
    RealmResults<SalesOrder> salesOrders;
    Realm realm;
    MyDate myDate;
    MyNumbers myNumbers;
    int customerID;

    public SalesOrderAdapter(Context context, Activity activity, RealmResults<SalesOrder> salesOrders)
    {
        this.context = context;
        this.activity = activity;
        realm = Realm.getDefaultInstance();
        myDate = new MyDate();
        myNumbers = new MyNumbers();
        this.salesOrders = salesOrders;
        customerID = 0;
    }

    public SalesOrderAdapter(Context context, Activity activity, int customerID)
    {
        this.context = context;
        this.activity = activity;
        this.customerID = customerID;
        realm = Realm.getDefaultInstance();
        myDate = new MyDate();
        myNumbers = new MyNumbers();
        salesOrders = realm.where(SalesOrder.class).equalTo("customerID", customerID).findAll();
    }

    public void setSalesOrders(RealmResults<SalesOrder> salesOrders)
    {
        this.salesOrders = salesOrders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sales_order, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        final SalesOrder salesOrder = salesOrders.get(i);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, SalesOrdersReportActivity.class);
                intent.putExtra("id", salesOrder.getId());
                context.startActivity(intent);
            }
        });

        if (customerID == 0)
        {
            viewHolder.clientID.setVisibility(View.VISIBLE);
            viewHolder.clientName.setVisibility(View.VISIBLE);
        }else
        {
            viewHolder.clientID.setVisibility(View.GONE);
            viewHolder.clientName.setVisibility(View.GONE);
        }
        viewHolder.clientID.setText(String.valueOf(salesOrder.getCustomerID()));
        viewHolder.clientName.setText(salesOrder.getCustomerName());
        viewHolder.orderID.setText(String.valueOf(salesOrder.getId()));
        viewHolder.orderTotal.setText(String.valueOf(salesOrder.getTotalValue()));
    }

    @Override
    public int getItemCount()
    {
        return salesOrders.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.clientID)
        TextView clientID;
        @BindView(R.id.clientName)
        TextView clientName;
        @BindView(R.id.orderID)
        TextView orderID;
        @BindView(R.id.orderTotal)
        TextView orderTotal;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
