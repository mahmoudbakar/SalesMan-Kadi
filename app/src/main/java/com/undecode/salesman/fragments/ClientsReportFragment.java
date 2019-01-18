package com.undecode.salesman.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.undecode.salesman.R;
import com.undecode.salesman.adapters.CustomersVisitsAdapter;
import com.undecode.salesman.adapters.SalesOrderAdapter;
import com.undecode.salesman.models.Customer;
import com.undecode.salesman.models.local.SalesOrder;
import com.undecode.salesman.models.local.Visit;
import com.undecode.salesman.utils.EqualSpacingItemDecoration;
import com.undecode.salesman.utils.network.API;
import com.undecode.salesman.utils.network.OnDataReady;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class ClientsReportFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    Realm realm;
    RealmResults<Visit> visits;
    RealmResults<Customer> customers;
    SearchView searchView;
    MenuItem search;
    CustomersVisitsAdapter adapter;
    List<Customer> temp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_visit, container, false);
        realm = Realm.getDefaultInstance();
        visits = realm.where(Visit.class).findAll();
        customers = realm.where(Customer.class).findAll();
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        GridLayoutManager gLayoutManager = new GridLayoutManager(getContext(), 2);
        recycler.addItemDecoration(new EqualSpacingItemDecoration(15, EqualSpacingItemDecoration.GRID));
        recycler.setLayoutManager(gLayoutManager);
        temp = new ArrayList<>();
        adapter = new CustomersVisitsAdapter(getContext(), getActivity(), temp);
        recycler.setAdapter(adapter);
        visitsReady();

//        API.getInstance().getCustomers(getContext(), new OnDataReady.ArrayReady()
//        {
//            @Override
//            public void onArrayReady(List list)
//            {
//                List<Customer> customers = ((List<Customer>)list);
//                List<Customer> temp = new ArrayList<>();
//                if (visits.size() > 0)
//                {
//                    for (Customer customer:customers)
//                    {
//                        for (int i = 0; i < visits.size(); i++)
//                        {
//                            if (customer.getCustomerID() == visits.get(i).getCustomerID())
//                            {
//                                temp.add(customer);
//                                i = visits.size();
//                            }
//                        }
//                    }
//                    recycler.setAdapter(new CustomersVisitsAdapter(getContext(), getActivity(), temp));
//                }
//            }
//
//            @Override
//            public void onError(Object object)
//            {
//
//            }
//        });
        return view;
    }

    private void visitsReady()
    {
        temp = new ArrayList<>();
        if (visits.size() > 0)
        {
            for (Customer customer:customers)
            {
                for (int i = 0; i < visits.size(); i++)
                {
                    if (customer.getCustomerID() == visits.get(i).getCustomerID())
                    {
                        temp.add(customer);
                        i = visits.size();
                    }
                }
            }
        }
        adapter.setCustomers(temp);
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
                visitsReady();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                customers = realm.where(Customer.class).contains("customerNameA", newText, Case.INSENSITIVE).or().contains("customerNameE", newText, Case.INSENSITIVE).findAll();
                visitsReady();
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
