package com.undecode.salesman;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.undecode.salesman.adapters.GroupsAdapter;
import com.undecode.salesman.adapters.ItemsAdapter;
import com.undecode.salesman.adapters.SelectedItemsAdapter;
import com.undecode.salesman.fragments.CatalogFragment;
import com.undecode.salesman.models.Customer;
import com.undecode.salesman.models.Groups;
import com.undecode.salesman.models.Item;
import com.undecode.salesman.models.Reason;
import com.undecode.salesman.models.local.OffersInvoicesItem;
import com.undecode.salesman.models.local.OffersResponse;
import com.undecode.salesman.models.local.Payment;
import com.undecode.salesman.models.local.SalesOrder;
import com.undecode.salesman.models.local.SalesOrderDtlsItem;
import com.undecode.salesman.models.local.UnSuccessfulVisit;
import com.undecode.salesman.models.local.Visit;
import com.undecode.salesman.utils.EqualSpacingItemDecoration;
import com.undecode.salesman.utils.MyClicks;
import com.undecode.salesman.utils.MyDate;
import com.undecode.salesman.utils.MyFragmentManager;
import com.undecode.salesman.utils.MyNumbers;
import com.undecode.salesman.utils.MyPreferance;
import com.undecode.salesman.utils.OnCategoryPressed;
import com.undecode.salesman.utils.Sender;
import com.undecode.salesman.utils.network.API;
import com.undecode.salesman.utils.network.OnDataReady;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class SalesOrderActivity extends AppCompatActivity implements OnCategoryPressed, MyClicks
{

    @BindView(R.id.selectedRecycler)
    RecyclerView selectedRecycler;
    @BindView(R.id.txtSubTotal)
    TextView txtSubTotal;
    @BindView(R.id.txtDiscount)
    TextView txtDiscount;
    @BindView(R.id.txtTax)
    TextView txtTax;
    @BindView(R.id.btnPay)
    Button btnPay;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnNotes)
    Button btnNotes;
    @BindView(R.id.btnDiscount)
    Button btnDiscount;
    @BindView(R.id.itemsRecycler)
    RecyclerView itemsRecycler;
    @BindView(R.id.categoriesRecycler)
    RecyclerView categoriesRecycler;

    GroupsAdapter groupsAdapter;
    ItemsAdapter itemsAdapter;
    RealmResults<Item> itemList;
    RealmResults<Groups> groups;
    List<Item> selectedItems, itemListFiltered;
    double subTotal, total, tax, discount;
    MyNumbers myNumbers;
    SelectedItemsAdapter selectedItemsAdapter;
    Realm realm;
    Customer customer;
    Gson gson;
    private MyPreferance preferance;
    private OffersResponse offers;
    MyDate myDate;
    RealmResults<Customer> customers;
    SearchView searchView;
    MenuItem search;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);
        gson = new Gson();
        myDate = new MyDate();
        preferance = new MyPreferance(this);
        gson = new Gson();
        offers = gson.fromJson(preferance.getOffers(), OffersResponse.class);

        realm = Realm.getDefaultInstance();
        customers = realm.where(Customer.class).equalTo("customerID", getIntent().getIntExtra("customer", 0)).findAll();
        customer = customers.get(0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(customer.getCustomerNameE());

        try
        {
            Sender sender = new Sender(this, this);
            sender.sendSms("01110128800", "Sales Man Started Sales Order With\n"+customer.getCustomerNameE());
            //Toast.makeText(this, "SMS SENT.", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {

        }

        tax = 0;
        discount = 0;
        total = 0;
        subTotal = 0;
        selectedItems = new ArrayList<>();
        myNumbers = new MyNumbers();

        selectedItemsAdapter = new SelectedItemsAdapter(this, selectedItems, this);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ((LinearLayoutManager) mLayoutManager1).setStackFromEnd(true);
        selectedRecycler.addItemDecoration(new EqualSpacingItemDecoration(2, EqualSpacingItemDecoration.VERTICAL));
        selectedRecycler.setLayoutManager(mLayoutManager1);
        selectedRecycler.setAdapter(selectedItemsAdapter);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        categoriesRecycler.addItemDecoration(new EqualSpacingItemDecoration(5, EqualSpacingItemDecoration.VERTICAL));
        categoriesRecycler.setLayoutManager(mLayoutManager);
        GridLayoutManager gLayoutManager = new GridLayoutManager(this, 3);
        itemsRecycler.addItemDecoration(new EqualSpacingItemDecoration(10, EqualSpacingItemDecoration.GRID));
        itemsRecycler.setLayoutManager(gLayoutManager);
        itemList = realm.where(Item.class).findAll();
        itemsAdapter = new ItemsAdapter(SalesOrderActivity.this, SalesOrderActivity.this, itemList, SalesOrderActivity.this);
        itemsRecycler.setAdapter(itemsAdapter);

        itemListFiltered = new ArrayList<>();

        groups = realm.where(Groups.class).findAll();
        groupsAdapter = new GroupsAdapter(SalesOrderActivity.this, SalesOrderActivity.this, groups, SalesOrderActivity.this);
        categoriesRecycler.setAdapter(groupsAdapter);

//        API.getInstance().getGroups(this, new OnDataReady.ArrayReady()
//        {
//            @Override
//            public void onArrayReady(List list)
//            {
////                groupsAdapter = new GroupsAdapter(SalesOrderActivity.this, SalesOrderActivity.this, list, SalesOrderActivity.this);
////                categoriesRecycler.setAdapter(groupsAdapter);
////                API.getInstance().getItems(SalesOrderActivity.this, new OnDataReady.ArrayReady()
////                {
////                    @Override
////                    public void onArrayReady(List list)
////                    {
////                        itemList = ((List<Item>)list);
////                        itemsAdapter = new ItemsAdapter(SalesOrderActivity.this, SalesOrderActivity.this, list, SalesOrderActivity.this);
////                        itemsRecycler.setAdapter(itemsAdapter);
////                    }
////
////                    @Override
////                    public void onError(Object object)
////                    {
////
////                    }
////                });
//            }
//
//            @Override
//            public void onError(Object object)
//            {
//
//            }
//        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == android.R.id.home)
        {
            if (selectedItems.size() == 0)
            {
                finish();
            }else
            {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.alert)
                        .setMessage(R.string.cancel_dialog)
                        .setIcon(this.getResources().getDrawable(R.drawable.ic_warning))
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                Toast.makeText(SalesOrderActivity.this, getString(R.string.canceled), Toast.LENGTH_SHORT).show();
                                finish();
                            }})
                        .setNegativeButton(R.string.no, null).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem scanner = menu.findItem( R.id.barcode);
        final MenuItem requestCatalog = menu.findItem( R.id.action_request_catalog);
        final MenuItem sync = menu.findItem( R.id.sync);
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
                Intent intent = new Intent(SalesOrderActivity.this, FeedbackActivity.class);
                intent.putExtra("subject", "Request Catalog");
                startActivity(intent);
                return false;
            }
        });

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
        return true;
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

    @OnClick(R.id.btnPay)
    public void onBtnPayClicked()
    {
        if (offers.getOffersInvoices().size() > 0)
        {
            if (subTotal >= offers.getOffersInvoices().get(0).getTotalInvoice())
            {
                if (offers.getOffersInvoices().get(0).getDiscPercent() > 0)
                {
                    discount = ((subTotal / 100) * offers.getOffersInvoices().get(0).getDiscPercent());
                }else if (offers.getOffersInvoices().get(0).getDiscValue() > 0)
                {
                    discount = ((subTotal / 100) * offers.getOffersInvoices().get(0).getDiscValue());
                }
            }
        }
        new AlertDialog.Builder(this)
                .setTitle(R.string.confirm)
                .setMessage(getString(R.string.order_value_confirmation)+"\n"+String.valueOf(total)+" "+getString(R.string.le))
                .setIcon(getResources().getDrawable(R.drawable.ic_payment))
                .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        Number num = realm.where(SalesOrder.class).max("id");
                        int nextID;
                        if(num == null)
                        {
                            nextID = 1;
                        } else {
                            nextID = num.intValue() + 1;
                        }
                        SalesOrder salesOrder = new SalesOrder();
                        salesOrder.setCustomerName(customer.getCustomerNameE());
                        salesOrder.setCustomerID(customer.getCustomerID());
                        salesOrder.setTotalValue(total - discount);
                        salesOrder.setDiscountValue(discount);
                        salesOrder.setLatitude(31.55);
                        salesOrder.setLongitude(31.22);
                        salesOrder.setTransDate(myDate.getCurrentStringDateTime());
                        salesOrder.setSaved(false);
                        salesOrder.setNotes("");
                        salesOrder.setId(nextID);
                        RealmList<SalesOrderDtlsItem> salesOrderDtlsItems = new RealmList<>();
                        for (Item item:selectedItems)
                        {
                            SalesOrderDtlsItem salesOrderDtlsItem = new SalesOrderDtlsItem();
                            salesOrderDtlsItem.setItemID(item.getItemID());
                            salesOrderDtlsItem.setQuantity(item.getQuantity());
                            salesOrderDtlsItem.setPrice(item.getPrice1());
                            salesOrderDtlsItem.setUnitID(item.getUnitID());
                            salesOrderDtlsItems.add(salesOrderDtlsItem);
                        }
                        salesOrder.setSalesOrderDtls(salesOrderDtlsItems);
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(salesOrder);
                        realm.commitTransaction();

                        Number num1 = realm.where(Visit.class).max("visitID");
                        int nextID1;
                        if(num1 == null)
                        {
                            nextID1 = 1;
                        } else {
                            nextID1 = num1.intValue() + 1;
                        }
                        Visit visit = new Visit(nextID1, customer.getCustomerID(), customer.getCustomerNameE(), myDate.getCurrentStringDateTime(), "Sales Order: "+salesOrder.getTotalValue()+" LE");
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(visit);
                        realm.commitTransaction();

                        Toast.makeText(SalesOrderActivity.this, getString(R.string.confirmed), Toast.LENGTH_SHORT).show();
                        finish();
                    }})
                .setNegativeButton(getString(R.string.cancel),null).show();
    }

    @OnClick(R.id.btnCancel)
    public void onBtnCancelClicked()
    {
        if (selectedItems.size() == 0)
        {
            finish();
        }else
        {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.alert))
                    .setMessage(R.string.sales_order_cancel)
                    .setIcon(this.getResources().getDrawable(R.drawable.ic_warning))
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            Toast.makeText(SalesOrderActivity.this, getString(R.string.canceled), Toast.LENGTH_SHORT).show();
                            finish();
                        }})
                    .setNegativeButton(getString(R.string.no), null).show();
        }
    }

    @OnClick(R.id.btnNotes)
    public void onBtnNotesClicked()
    {
        View view3 = getLayoutInflater().inflate(R.layout.dialog_input, null);
        new android.app.AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(getString(R.string.note))
                .setIcon(getResources().getDrawable(R.drawable.ic_visit))
                .setView(view3)
                .setPositiveButton(getString(R.string.save), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dialog dialog2 =Dialog.class.cast(dialog);
                        EditText edNote = (EditText) dialog2.findViewById(R.id.edNote);

                    }
                })
                .setNegativeButton(getString(R.string.cancel), null).show();
    }

    @Override
    public void onBackPressed() {
        if (selectedItems.size() == 0)
        {
            finish();
        }else
        {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.alert))
                    .setMessage(getString(R.string.sales_order_cancel))
                    .setIcon(this.getResources().getDrawable(R.drawable.ic_warning))
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            Toast.makeText(SalesOrderActivity.this, getString(R.string.canceled), Toast.LENGTH_SHORT).show();
                            finish();
                        }})
                    .setNegativeButton(getString(R.string.no), null).show();
        }
    }

    @OnClick(R.id.btnDiscount)
    public void onBtnDiscountClicked()
    {
    }

    @Override
    public void onItemClicked(Object o)
    {
        try
        {
            subTotal = 0;
            Item temp = ((Item) o);
            Item item = new Item(temp.getSupplierID(), temp.getPrice2(), temp.getImg(), temp.getPrice1(), temp.getUnitID(), temp.getItemNameA(),
                    temp.getItemID(),temp.getItemNameE(), temp.getGroupID(), 0, null, this);
            if (selectedItems.size() > 0)
            {
                for (int i = 0; i < selectedItems.size(); i++)
                {
                    Item itemQ = selectedItems.get(i);
                    if (itemQ.getItemID() == item.getItemID())
                    {
                        selectedItems.get(i).setQuantity(itemQ.getQuantity() + 1);
                        i = selectedItems.size();
                    }else if (i == selectedItems.size()-1)
                    {
                        if (item.getQuantity() > 1)
                        {

                        }else
                        {
                            item.setQuantity(1);
                        }
                        selectedItems.add(item);
                        i = selectedItems.size();
                    }
                }
            }else if (selectedItems.size() == 0)
            {
                if (item.getQuantity() > 1)
                {

                }else
                {
                    item.setQuantity(1);
                }
                selectedItems.add(item);
            }
            selectedItemsAdapter.setItemList(selectedItems);
            for (Item itemLoop : selectedItems)
            {
                subTotal += itemLoop.getTotal();
            }
            subTotal = myNumbers.round(subTotal, 2);
            discount = myNumbers.round(discount, 2);
            total = subTotal - discount;
            tax = (total / 100) * 14;
            tax = myNumbers.round(tax, 2);
            total += tax;
            total = myNumbers.round(total, 2);

            txtSubTotal.setText(String.valueOf(subTotal));
            txtDiscount.setText(String.valueOf(discount));
            txtTax.setText(String.valueOf(tax));
            btnPay.setText(getString(R.string.order_process)+ "\n" + String.valueOf(total)+ " "+getString(R.string.le));
        }catch (ClassCastException e)
        {
//            Customer customer = ((Customer) o);
//            textSelected.setText("Selected Client: "+customer.getName());
//            getActivity().setTitle("Order To: "+customer.getName());
        }
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
}
