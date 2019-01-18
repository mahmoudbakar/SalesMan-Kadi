package com.undecode.salesman.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.undecode.salesman.adapters.GroupsAdapter;
import com.undecode.salesman.adapters.ItemsAdapter;
import com.undecode.salesman.models.Groups;
import com.undecode.salesman.models.Item;
import com.undecode.salesman.utils.EqualSpacingItemDecoration;
import com.undecode.salesman.utils.OnCategoryPressed;
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

public class CatalogFragment extends Fragment implements OnCategoryPressed
{
    @BindView(R.id.itemsRecycler)
    RecyclerView itemsRecycler;
    @BindView(R.id.categoriesRecycler)
    RecyclerView categoriesRecycler;
    Unbinder unbinder;
    GroupsAdapter groupsAdapter;
    ItemsAdapter itemsAdapter;
    List<Item> itemListFiltered;
    RealmResults<Item> itemList;
    RealmResults<Groups> groups;
    Realm realm;
    SearchView searchView;
    MenuItem search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        realm = Realm.getDefaultInstance();
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        getActivity().setTitle(getContext().getResources().getString(R.string.catalog));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        categoriesRecycler.addItemDecoration(new EqualSpacingItemDecoration(5, EqualSpacingItemDecoration.VERTICAL));
        categoriesRecycler.setLayoutManager(mLayoutManager);
        GridLayoutManager gLayoutManager = new GridLayoutManager(getContext(), 5);
        itemsRecycler.addItemDecoration(new EqualSpacingItemDecoration(10, EqualSpacingItemDecoration.GRID));
        itemsRecycler.setLayoutManager(gLayoutManager);

        itemListFiltered = new ArrayList<>();
        itemListFiltered =  realm.where(Item.class).findAll();
        itemList = realm.where(Item.class).findAll();
        groups = realm.where(Groups.class).findAll();
        groupsAdapter = new GroupsAdapter(getContext(), getActivity(), groups, CatalogFragment.this);
        categoriesRecycler.setAdapter(groupsAdapter);
        itemsAdapter = new ItemsAdapter(getContext(), getActivity(), itemListFiltered, null);
        itemsRecycler.setAdapter(itemsAdapter);
//        API.getInstance().getGroups(getContext(), new OnDataReady.ArrayReady()
//        {
//            @Override
//            public void onArrayReady(List list)
//            {
//                groupsAdapter = new GroupsAdapter(getContext(), getActivity(), list, CatalogFragment.this);
//                categoriesRecycler.setAdapter(groupsAdapter);
//                API.getInstance().getItems(getContext(), new OnDataReady.ArrayReady()
//                {
//                    @Override
//                    public void onArrayReady(List list)
//                    {
//                        itemList = ((List<Item>)list);
//                        itemsAdapter = new ItemsAdapter(getContext(), getActivity(), list, null);
//                        itemsRecycler.setAdapter(itemsAdapter);
//                    }
//
//                    @Override
//                    public void onError(Object object) {
//
//                    }
//                });
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

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void notify(int[] categoriesIDs)
    {
        try
        {
            itemListFiltered = new ArrayList<>();
        }catch (Exception e)
        {

        }
        if (categoriesIDs.length > 0)
        {
            for (int i : categoriesIDs)
            {
                for (Item item:itemList)
                {
                    if (item.getGroupID() == i)
                    {
                        itemListFiltered.add(item);
                    }
                }
            }
        }else
        {
            itemListFiltered = itemList;
        }
        itemsAdapter.setItems(itemListFiltered);
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
                itemListFiltered = realm.where(Item.class).contains("itemNameA", query, Case.INSENSITIVE).or().contains("itemNameE", query, Case.INSENSITIVE).findAll();
                itemsAdapter.setItems(itemListFiltered);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                itemListFiltered = realm.where(Item.class).contains("itemNameA", newText, Case.INSENSITIVE).or().contains("itemNameE", newText, Case.INSENSITIVE).findAll();
                itemsAdapter.setItems(itemListFiltered);
                return false;
            }
        });
    }
}
