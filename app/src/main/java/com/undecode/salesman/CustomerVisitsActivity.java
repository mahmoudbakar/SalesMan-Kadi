package com.undecode.salesman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.undecode.salesman.adapters.PaymentsAdapter;
import com.undecode.salesman.adapters.SalesOrderAdapter;
import com.undecode.salesman.adapters.UnsuccessfulAdapter;
import com.undecode.salesman.models.Customer;
import com.undecode.salesman.utils.MyNumbers;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class CustomerVisitsActivity extends AppCompatActivity {
    Customer customer;
    Gson gson = new Gson();
    @BindView(R.id.recyclerSalesOrder)
    RecyclerView recyclerSalesOrder;
    @BindView(R.id.recyclerPayments)
    RecyclerView recyclerPayments;
    @BindView(R.id.recyclerUnsuccessful)
    RecyclerView recyclerUnsuccessful;
    @BindView(R.id.txtClientName)
    TextView txtClientName;
    @BindView(R.id.txtCredit)
    TextView txtCredit;
    MyNumbers myNumbers;
    RealmResults<Customer> customers;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_visits);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        customers = realm.where(Customer.class).equalTo("customerID", getIntent().getIntExtra("customer", 0)).findAll();
        customer = customers.get(0);
        myNumbers = new MyNumbers();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //customer = gson.fromJson(getIntent().getStringExtra("customer"), Customer.class);
        setTitle(customer.getCustomerNameE());
        txtClientName.setText(customer.getCustomerNameE());
        txtCredit.setText(String.valueOf(myNumbers.round(customer.getBalance(), 2))+" "+getString(R.string.le));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //recycler.addItemDecoration(new EqualSpacingItemDecoration(15, EqualSpacingItemDecoration.VERTICAL));
        recyclerSalesOrder.setLayoutManager(mLayoutManager);
        recyclerSalesOrder.setAdapter(new SalesOrderAdapter(this, this, customer.getCustomerID()));

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //recycler.addItemDecoration(new EqualSpacingItemDecoration(15, EqualSpacingItemDecoration.VERTICAL));
        recyclerPayments.setLayoutManager(mLayoutManager1);
        recyclerPayments.setAdapter(new PaymentsAdapter(this, this, customer.getCustomerID()));

        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //recycler.addItemDecoration(new EqualSpacingItemDecoration(15, EqualSpacingItemDecoration.VERTICAL));
        recyclerUnsuccessful.setLayoutManager(mLayoutManager2);
        recyclerUnsuccessful.setAdapter(new UnsuccessfulAdapter(this, this, customer.getCustomerID()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
