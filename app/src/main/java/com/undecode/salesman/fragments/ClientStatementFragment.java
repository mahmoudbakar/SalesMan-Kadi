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
import com.undecode.salesman.models.Customer;
import com.undecode.salesman.models.Item;
import com.undecode.salesman.utils.EqualSpacingItemDecoration;
import com.undecode.salesman.utils.network.API;
import com.undecode.salesman.utils.network.OnDataReady;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class ClientStatementFragment extends Fragment
{
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    RealmResults<Customer> customers;
    Realm realm;
    SearchView searchView;
    MenuItem search;
    CustomerStatementAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_client_statement, container, false);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        realm = Realm.getDefaultInstance();
        customers = realm.where(Customer.class).findAll();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler.addItemDecoration(new EqualSpacingItemDecoration(15, EqualSpacingItemDecoration.VERTICAL));
        recycler.setLayoutManager(mLayoutManager);
        adapter = new CustomerStatementAdapter(getContext(), getActivity(), customers);
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
                customers = realm.where(Customer.class).contains("customerNameA", query, Case.INSENSITIVE).or().contains("customerNameE", query, Case.INSENSITIVE).findAll();
                adapter.setCustomers(customers);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                customers = realm.where(Customer.class).contains("customerNameA", newText, Case.INSENSITIVE).or().contains("customerNameE", newText, Case.INSENSITIVE).findAll();
                adapter.setCustomers(customers);
                return false;
            }
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }
}
