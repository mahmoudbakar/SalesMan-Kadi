package com.undecode.salesman.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.undecode.salesman.R;
import com.undecode.salesman.adapters.CustomerStatementAdapter;
import com.undecode.salesman.adapters.SalesOrderAdapter;
import com.undecode.salesman.models.Customer;
import com.undecode.salesman.models.local.SalesOrder;
import com.undecode.salesman.utils.EqualSpacingItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class SalesOrdersReportsFragment extends Fragment
{
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    SearchView searchView;
    MenuItem search;
    SalesOrderAdapter adapter;
    RealmResults<SalesOrder> salesOrders;
    Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sales_orders_reports, container, false);
        unbinder = ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        setHasOptionsMenu(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //recycler.addItemDecoration(new EqualSpacingItemDecoration(15, EqualSpacingItemDecoration.VERTICAL));
        recycler.setLayoutManager(mLayoutManager);
        salesOrders = realm.where(SalesOrder.class).findAll();
        adapter = new SalesOrderAdapter(getContext(), getActivity(), salesOrders);
        recycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        search = menu.findItem(R.id.search);
        searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                salesOrders = realm.where(SalesOrder.class).contains("customerName", query, Case.INSENSITIVE).findAll();
                adapter.setSalesOrders(salesOrders);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                salesOrders = realm.where(SalesOrder.class).contains("customerName", newText, Case.INSENSITIVE).findAll();
                adapter.setSalesOrders(salesOrders);
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
