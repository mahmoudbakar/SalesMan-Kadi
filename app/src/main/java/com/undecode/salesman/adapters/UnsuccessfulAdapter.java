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
import com.undecode.salesman.models.local.UnSuccessfulVisit;
import com.undecode.salesman.utils.MyDate;
import com.undecode.salesman.utils.MyNumbers;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class UnsuccessfulAdapter extends RecyclerView.Adapter<UnsuccessfulAdapter.ViewHolder> {
    Context context;
    Activity activity;
    RealmResults<UnSuccessfulVisit> unSuccessfulVisits;
    Realm realm;
    MyDate myDate;
    MyNumbers myNumbers;

    public UnsuccessfulAdapter(Context context, Activity activity, int customerID)
    {
        this.context = context;
        this.activity = activity;
        realm = Realm.getDefaultInstance();
        myDate = new MyDate();
        myNumbers = new MyNumbers();
        unSuccessfulVisits = realm.where(UnSuccessfulVisit.class).equalTo("customerID", customerID).findAll();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_unsuccessful, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        UnSuccessfulVisit unSuccessfulVisit = unSuccessfulVisits.get(i);
        viewHolder.txtID.setText(String.valueOf(unSuccessfulVisit.getId()));
        viewHolder.txtReason.setText(unSuccessfulVisit.getReason());
        viewHolder.txtDate.setText(myDate.getAmPmStringOfDateTime(unSuccessfulVisit.getTransDate()));
    }

    @Override
    public int getItemCount()
    {
        return unSuccessfulVisits.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.txtID)
        TextView txtID;
        @BindView(R.id.txtReason)
        TextView txtReason;
        @BindView(R.id.txtDate)
        TextView txtDate;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
