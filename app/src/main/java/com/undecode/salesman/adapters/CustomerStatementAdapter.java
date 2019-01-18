package com.undecode.salesman.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.undecode.salesman.R;
import com.undecode.salesman.models.Customer;
import com.undecode.salesman.utils.EqualSpacingItemDecoration;
import com.undecode.salesman.utils.MyNumbers;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class CustomerStatementAdapter extends RecyclerView.Adapter<CustomerStatementAdapter.ViewHolder>
{
    Context context;
    Activity activity;
    //List<Customer> customers;
    MyNumbers myNumbers;
    Realm realm;
    RealmResults<Customer> customers;

    public CustomerStatementAdapter(Context context, Activity activity, List<Customer> customers)
    {
        this.context = context;
        this.activity = activity;
        realm = Realm.getDefaultInstance();
        this.customers = realm.where(Customer.class).findAll();
        myNumbers = new MyNumbers();
    }

    public void setCustomers(RealmResults<Customer> customers)
    {
        this.customers = customers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_customer_statement, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        Customer customer = customers.get(i);
        viewHolder.txtID.setText(String.valueOf(customer.getCustomerID()));
        viewHolder.txtName.setText(customer.getCustomerNameA());
        viewHolder.txtBalance.setText(String.valueOf(myNumbers.round(customer.getBalance(), 2)));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        viewHolder.payments.addItemDecoration(new EqualSpacingItemDecoration(8, EqualSpacingItemDecoration.VERTICAL));
        viewHolder.payments.setLayoutManager(mLayoutManager);
        viewHolder.invoices.addItemDecoration(new EqualSpacingItemDecoration(8, EqualSpacingItemDecoration.VERTICAL));
        viewHolder.invoices.setLayoutManager(mLayoutManager1);
        viewHolder.payments.setAdapter(new PaymentAdapter(context, activity, customer.getLastPaymentsR()));
        viewHolder.invoices.setAdapter(new InvoiceAdapter(context, activity, customer.getLastInvoicesR()));
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.invoices)
        RecyclerView invoices;
        @BindView(R.id.payments)
        RecyclerView payments;
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
