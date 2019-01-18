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

import com.google.gson.Gson;
import com.undecode.salesman.CustomerVisitsActivity;
import com.undecode.salesman.R;
import com.undecode.salesman.models.Customer;
import com.undecode.salesman.utils.MyDate;
import com.undecode.salesman.utils.MyNumbers;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class CustomersVisitsAdapter extends RecyclerView.Adapter<CustomersVisitsAdapter.ViewHolder> {
    Context context;
    Activity activity;
    List<Customer> customers;
    int visit = 0;
    MyDate myDate;
    Realm realm;
    Gson gson;
    MyNumbers myNumbers;

    public CustomersVisitsAdapter(Context context, Activity activity, List<Customer> customers)
    {
        this.context = context;
        this.activity = activity;
        this.customers = customers;
        myDate = new MyDate();
        realm = Realm.getDefaultInstance();
        gson = new Gson();
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
        viewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, CustomerVisitsActivity.class);
                intent.putExtra("customer",customer.getCustomerID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return customers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.txtID)
        TextView txtID;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtBalance)
        TextView txtBalance;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
