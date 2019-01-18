package com.undecode.salesman;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.undecode.salesman.adapters.SelectedItemsAdapter;
import com.undecode.salesman.adapters.SelectedItemsReportAdapter;
import com.undecode.salesman.models.local.SalesOrder;
import com.undecode.salesman.models.local.Visit;
import com.undecode.salesman.utils.EqualSpacingItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class SalesOrdersReportActivity extends AppCompatActivity
{
    Realm realm;
    RealmResults<SalesOrder> salesOrders;
    @BindView(R.id.txtClientID)
    TextView txtClientID;
    @BindView(R.id.txtClientName)
    TextView txtClientName;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.txtTotal)
    TextView txtTotal;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    SelectedItemsReportAdapter selectedItemsAdapter;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_orders_report);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        salesOrders = realm.where(SalesOrder.class).equalTo("id", getIntent().getIntExtra("id", 0)).findAll();
        txtClientID.setText(String.valueOf(salesOrders.get(0).getCustomerID()));
        txtClientName.setText(salesOrders.get(0).getCustomerName());
        txtDate.setText(salesOrders.get(0).getTransDate());
        txtTotal.setText(String.valueOf(salesOrders.get(0).getTotalValue()));
        selectedItemsAdapter = new SelectedItemsReportAdapter(this, salesOrders.get(0).getSalesOrderDtls11(), null);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ((LinearLayoutManager) mLayoutManager1).setStackFromEnd(true);
        recycler.addItemDecoration(new EqualSpacingItemDecoration(2, EqualSpacingItemDecoration.VERTICAL));
        recycler.setLayoutManager(mLayoutManager1);
        recycler.setAdapter(selectedItemsAdapter);
    }

    @OnClick(R.id.btnCancel)
    public void onViewClicked()
    {
        new AlertDialog.Builder(this)
                .setTitle(R.string.alert)
                .setMessage(R.string.cancel_dialog)
                .setIcon(this.getResources().getDrawable(R.drawable.ic_warning))
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        RealmResults<Visit> visits = realm.where(Visit.class).equalTo("visitID", salesOrders.get(0).getId()).findAll();
                        realm.beginTransaction();
                        salesOrders.deleteAllFromRealm();
                        visits.deleteAllFromRealm();
                        realm.commitTransaction();
                        Toast.makeText(SalesOrdersReportActivity.this, getString(R.string.canceled), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, null).show();
    }
}
