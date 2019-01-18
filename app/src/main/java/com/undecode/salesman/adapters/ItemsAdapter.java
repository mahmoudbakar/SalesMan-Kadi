package com.undecode.salesman.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.undecode.salesman.R;
import com.undecode.salesman.models.Item;
import com.undecode.salesman.utils.MyClicks;
import com.undecode.salesman.utils.MyNumbers;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>
{
    Context context;
    Activity activity;
    List<Item> items;
    RequestOptions options;
    MyClicks myClicks;
    MyNumbers myNumbers;

    public void setItems(List<Item> items)
    {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<Item> getItems()
    {
        return items;
    }

    public ItemsAdapter(Context context, Activity activity, List<Item> items, MyClicks myClicks)
    {
        this.context = context;
        this.activity = activity;
        this.items = items;
        this.myClicks = myClicks;
        myNumbers = new MyNumbers();
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.broken)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.NORMAL);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        final Item item = items.get(i);
        viewHolder.txtItemName.setText(item.getItemName());
        viewHolder.txtPriceUnit.setText(String.valueOf(myNumbers.round(item.getPrice1(), 2)) + " / " + item.getUnit().getUnitName());
        Glide.with(viewHolder.itemImage).load(item.getImg()).apply(options).into(viewHolder.itemImage);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (myClicks != null)
                {
                    myClicks.onItemClicked(item);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.txtPriceUnit)
        TextView txtPriceUnit;
        @BindView(R.id.itemImage)
        ImageView itemImage;
        @BindView(R.id.txtItemName)
        TextView txtItemName;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
