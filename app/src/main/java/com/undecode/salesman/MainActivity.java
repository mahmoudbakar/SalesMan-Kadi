package com.undecode.salesman;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.undecode.salesman.adapters.GroupsAdapter;
import com.undecode.salesman.adapters.ItemsAdapter;
import com.undecode.salesman.fragments.CatalogFragment;
import com.undecode.salesman.fragments.ReportsFragment;
import com.undecode.salesman.models.Item;
import com.undecode.salesman.models.local.Payment;
import com.undecode.salesman.models.local.Response;
import com.undecode.salesman.models.local.SalesOrder;
import com.undecode.salesman.models.local.SalesOrderDtlsItem;
import com.undecode.salesman.models.local.UnSuccessfulVisit;
import com.undecode.salesman.models.local.Visit;
import com.undecode.salesman.utils.MyDate;
import com.undecode.salesman.utils.MyFragmentManager;
import com.undecode.salesman.utils.mail.Mailer;
import com.undecode.salesman.utils.mail.Template;
import com.undecode.salesman.utils.network.API;
import com.undecode.salesman.utils.network.OnDataReady;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Realm realm;
    Mailer mailer;
    MyDate myDate;
    int count = 0;
    MenuItem sync;
    RealmResults<Visit> visits;
    MyFragmentManager myFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFragmentManager = new MyFragmentManager(getSupportFragmentManager(), R.id.frame);
        realm = Realm.getDefaultInstance();
        visits = realm.where(Visit.class).findAll();
        count = visits.size();
        myDate = new MyDate();
        mailer = new Mailer("undecode.eg@gmail.com", "Qwerty@12++");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        myFragmentManager.goToFragment(new CatalogFragment(), "CATALOG");
        API.getInstance().getCustomers(MainActivity.this, null);
        API.getInstance().getReasons(MainActivity.this, null);
        API.getInstance().getOffers(MainActivity.this);
        API.getInstance().getGroups(MainActivity.this, new OnDataReady.ArrayReady()
        {
            @Override
            public void onArrayReady(List list)
            {
                API.getInstance().getItems(MainActivity.this,null);
            }

            @Override
            public void onError(Object object)
            {

            }
        });
    }

    private boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
            {
            if (doubleBackToExitPressedOnce)
            {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getString(R.string.back_twice_exit), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable()
            {

                @Override
                public void run()
                {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem scanner = menu.findItem( R.id.barcode);
        final MenuItem requestCatalog = menu.findItem( R.id.action_request_catalog);
        sync = menu.findItem(R.id.sync);
        sync.setIcon(buildCounterDrawable(count,  R.drawable.ic_sync_black_24dp));
        scanner.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                scan();
                return true;
            }
        });

        requestCatalog.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                Intent intent = new Intent(MainActivity.this, FeedbackActivity.class);
                intent.putExtra("subject", "Request Catalog");
                startActivity(intent);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        try
        {
            visits = realm.where(Visit.class).findAll();
            count = visits.size();
            sync.setIcon(buildCounterDrawable(count,  R.drawable.ic_sync_black_24dp));
        }catch (NullPointerException e){}
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sync)
        {
            List<Visit> visitList = new ArrayList<>();
            for (Visit visit:visits)
            {
                visitList.add(new Visit(visit.getVisitID(), visit.getCustomerID(), visit.getName(), myDate.getAmPmStringOfDateTime(visit.getTransDate()), visit.getResult()));
            }
            if (visits.size() > 0)
            {
                new AsyncTask<List<Visit>, String, String>()
                {
                    @Override
                    protected String doInBackground(List<Visit>... lists)
                    {
                        mailer.sendMail("Visits Report", new Template().summaryReport(lists[0]), "undecode.eg@gmail.com", "akramaly88@gmail.com", Mailer.textHTML);
                        return null;
                    }
                }.execute(visitList);
                realm.beginTransaction();
                visits.deleteAllFromRealm();
                realm.commitTransaction();
            }
            RealmResults<SalesOrder> salesOrders = realm.where(SalesOrder.class).findAll();
            RealmResults<Payment> payments = realm.where(Payment.class).findAll();
            RealmResults<UnSuccessfulVisit> unSuccessfulVisits = realm.where(UnSuccessfulVisit.class).findAll();
            if (unSuccessfulVisits.size() > 0)
            {
                UnSuccessfulVisit unSuccessfulVisit;
                List<UnSuccessfulVisit> unSuccessfulVisitList = new ArrayList<>();
                for (UnSuccessfulVisit temp:unSuccessfulVisits)
                {
                    unSuccessfulVisit = new UnSuccessfulVisit();
                    unSuccessfulVisit.setId(temp.getId());
                    unSuccessfulVisit.setReasonID(temp.getReasonID());
                    unSuccessfulVisit.setSaved(temp.isSaved());
                    unSuccessfulVisit.setCustomerID(temp.getCustomerID());
                    unSuccessfulVisit.setLatitude(temp.getLatitude());
                    unSuccessfulVisit.setLongitude(temp.getLongitude());
                    unSuccessfulVisit.setTransDate(temp.getTransDate());
                    unSuccessfulVisit.setNotes(temp.getNotes());
                    unSuccessfulVisitList.add(unSuccessfulVisit);
                }
                API.getInstance().submitUnsuccessfulVisit(this, unSuccessfulVisitList, new OnDataReady.ObjectReady() {
                    @Override
                    public void onObjectReady(Object object)
                    {
                        List<Response> responseList = ((List<Response>) object);
                        for (Response response:responseList)
                        {
                            if (response.isSaved())
                            {
                                RealmResults<UnSuccessfulVisit> delete = realm.where(UnSuccessfulVisit.class).equalTo("id", response.getId()).findAll();
                                realm.beginTransaction();
                                delete.deleteAllFromRealm();
                                realm.commitTransaction();
                            }
                        }
                    }

                    @Override
                    public void onError(Object object) {

                    }
                });
            }
            if (payments.size() > 0)
            {
                Payment payment;
                List<Payment> paymentList = new ArrayList<>();
                for (Payment temp:payments)
                {
                    payment = new Payment();
                    payment.setId(temp.getId());
                    payment.setPaymentID(temp.getPaymentID());
                    payment.setPaymentDate(temp.getPaymentDate());
                    payment.setPaymentType(temp.getPaymentType());
                    payment.setRecieptNO(temp.getRecieptNO());
                    payment.setSaved(temp.isSaved());
                    payment.setCustomerID(temp.getCustomerID());
                    payment.setLatitude(temp.getLatitude());
                    payment.setLongitude(temp.getLongitude());
                    payment.setPaymentValue(temp.getPaymentValue());
                    payment.setNotes(temp.getNotes());
                    paymentList.add(payment);
                }
                API.getInstance().submitPayment(this, paymentList, new OnDataReady.ObjectReady() {
                    @Override
                    public void onObjectReady(Object object)
                    {
                        List<Response> responseList = ((List<Response>) object);
                        for (Response response:responseList)
                        {
                            if (response.isSaved())
                            {
                                RealmResults<Payment> delete = realm.where(Payment.class).equalTo("id", response.getId()).findAll();
                                realm.beginTransaction();
                                delete.deleteAllFromRealm();
                                realm.commitTransaction();
                            }
                        }
                    }

                    @Override
                    public void onError(Object object) {

                    }
                });
            }
            if (salesOrders.size() > 0)
            {
                SalesOrder temp;
                SalesOrderDtlsItem salesOrderDtlsItem;
                List<SalesOrderDtlsItem> salesOrderDtlsItems;
                List<SalesOrder> salesOrderList = new ArrayList<>();
                for (SalesOrder salesOrder:salesOrders)
                {
                    temp = new SalesOrder();
                    temp.setCustomerID(salesOrder.getCustomerID());
                    temp.setTotalValue(salesOrder.getTotalValue());
                    temp.setDiscountValue(salesOrder.getDiscountValue());
                    temp.setLatitude(salesOrder.getLatitude());
                    temp.setLongitude(salesOrder.getLongitude());
                    temp.setTransDate(salesOrder.getTransDate());
                    temp.setSaved(salesOrder.isSaved());
                    temp.setNotes(salesOrder.getNotes());
                    temp.setId(salesOrder.getId());
                    salesOrderDtlsItems = new ArrayList<>();
                    for (SalesOrderDtlsItem tempItem:salesOrder.getSalesOrderDtls())
                    {
                        salesOrderDtlsItem = new SalesOrderDtlsItem();
                        salesOrderDtlsItem.setItemID(tempItem.getItemID());
                        salesOrderDtlsItem.setQuantity(tempItem.getQuantity());
                        salesOrderDtlsItem.setPrice(tempItem.getPrice());
                        salesOrderDtlsItem.setUnitID(tempItem.getUnitID());
                        salesOrderDtlsItems.add(salesOrderDtlsItem);
                    }
                    salesOrderList.add(temp);
                }
                API.getInstance().submitSalesOrder(this, salesOrderList, new OnDataReady.ObjectReady()
                {
                    @Override
                    public void onObjectReady(Object object)
                    {
                        List<Response> responseList = ((List<Response>) object);
                        for (Response response:responseList)
                        {
                            if (response.isSaved())
                            {
                                RealmResults<SalesOrder> delete = realm.where(SalesOrder.class).equalTo("id", response.getId()).findAll();
                                realm.beginTransaction();
                                delete.deleteAllFromRealm();
                                realm.commitTransaction();
                            }
                        }
                    }

                    @Override
                    public void onError(Object object)
                    {

                    }
                });
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.icon_sync_counter, null);
        view.setBackgroundResource(backgroundImageId);

        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) view.findViewById(R.id.count);
            textView.setText("" + count);
        }

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_catalog)
        {
            myFragmentManager.goToFragment(new CatalogFragment(), "CATALOG");
        } else if (id == R.id.nav_visit)
        {
            Intent intent = new Intent(this, VisitActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_reports)
        {
            myFragmentManager.goToFragment(new ReportsFragment(), "REPORTS");
        } else if (id == R.id.nav_share)
        {

        } else if (id == R.id.nav_send)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_feedback)
        {
            Intent intent = new Intent(this, FeedbackActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact_us)
        {
            Intent intent = new Intent(this, ContactUsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
