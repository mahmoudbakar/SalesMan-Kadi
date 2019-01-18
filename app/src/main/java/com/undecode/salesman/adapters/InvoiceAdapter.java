package com.undecode.salesman.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.undecode.salesman.R;
import com.undecode.salesman.models.LastInvoicesItem;
import com.undecode.salesman.models.LastPaymentsItem;
import com.undecode.salesman.utils.MyNumbers;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder>
{
    Context context;
    Activity activity;
    List<LastInvoicesItem> lastInvoicesItems;
    MyNumbers myNumbers;

    public InvoiceAdapter(Context context, Activity activity, List<LastInvoicesItem> lastInvoicesItems)
    {
        this.context = context;
        this.activity = activity;
        this.lastInvoicesItems = lastInvoicesItems;
        myNumbers = new MyNumbers();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_payment_invoice, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        LastInvoicesItem lastInvoicesItem = lastInvoicesItems.get(i);
        viewHolder.txtID.setText(String.valueOf(lastInvoicesItem.getInvoiceID()));
        viewHolder.txtDate.setText(lastInvoicesItem.getInvoiceDate());
        viewHolder.txtValue.setText(String.valueOf(myNumbers.round(lastInvoicesItem.getInvoiceValue(), 2)));
    }

    @Override
    public int getItemCount() {
        try
        {

            return lastInvoicesItems.size();
        }catch (NullPointerException e)
        {
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.txtID)
        TextView txtID;
        @BindView(R.id.txtDate)
        TextView txtDate;
        @BindView(R.id.txtValue)
        TextView txtValue;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
