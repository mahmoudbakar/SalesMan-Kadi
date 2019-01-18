package com.undecode.salesman;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.undecode.salesman.adapters.CustomersAdapter;
import com.undecode.salesman.models.Customer;
import com.undecode.salesman.models.local.Visit;
import com.undecode.salesman.utils.EqualSpacingItemDecoration;
import com.undecode.salesman.utils.network.API;
import com.undecode.salesman.utils.network.OnDataReady;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class VisitActivity extends AppCompatActivity
{
    @BindView(R.id.recycler)
    RecyclerView recycler;
    RealmResults<Customer> customers;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        realm = Realm.getDefaultInstance();
        customers = realm.where(Customer.class).findAll();
        setTitle(getString(R.string.start_visit));
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GridLayoutManager gLayoutManager = new GridLayoutManager(this, 2);
        recycler.addItemDecoration(new EqualSpacingItemDecoration(15, EqualSpacingItemDecoration.GRID));
        recycler.setLayoutManager(gLayoutManager);
        adapter = new CustomersAdapter(VisitActivity.this, VisitActivity.this, customers);
        recycler.setAdapter(adapter);
    }
    private CustomersAdapter adapter;
    private SearchView searchView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem scanner = menu.findItem( R.id.barcode);
        final MenuItem requestCatalog = menu.findItem( R.id.action_request_catalog);
        final MenuItem sync = menu.findItem( R.id.sync);

        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                customers = realm.where(Customer.class)
                        .contains("customerNameA", s).or()
                        .contains("customerNameE", s)
                        .findAll();
                adapter.setCustomers(customers);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                customers = realm.where(Customer.class)
                        .contains("customerNameA", s).or()
                        .contains("customerNameE", s)
                        .findAll();
                adapter.setCustomers(customers);
                return false;
            }
        });

        requestCatalog.setVisible(false);
        sync.setVisible(false);
        scanner.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                scan();
                return true;
            }
        });

        requestCatalog.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(VisitActivity.this, FeedbackActivity.class);
                intent.putExtra("subject", "Request Catalog");
                startActivity(intent);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void scan()
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)
        {
            if (result.getContents() == null)
            {
                Toast.makeText(this, getString(R.string.canceled), Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                //edBarcode.setText(result.getContents());
            }
        } else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
