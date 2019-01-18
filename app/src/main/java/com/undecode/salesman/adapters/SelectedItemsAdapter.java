package com.undecode.salesman.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.undecode.salesman.R;
import com.undecode.salesman.models.Item;
import com.undecode.salesman.utils.MyClicks;
import com.undecode.salesman.utils.MyNumbers;

import java.util.List;

public class SelectedItemsAdapter extends RecyclerView.Adapter<SelectedItemsAdapter.ViewHolder>
{
    private Context context;
    private List<Item> itemList;
    private MyNumbers myNumbers;
    private MyClicks myClicks;

    public SelectedItemsAdapter(Context context, List<Item> itemList, MyClicks myClicks)
    {
        myNumbers = new MyNumbers();
        this.context = context;
        this.itemList = itemList;
        this.myClicks = myClicks;
    }

    public void setItemList(List<Item> itemList)
    {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_selected, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i)
    {
        final Item item = itemList.get(i);
        viewHolder.txtName.setText(item.getItemName());
        viewHolder.edQuantitty.setText(String.valueOf(item.getQuantity()));
        viewHolder.txtUnitPrice.setText(" X "+String.valueOf(item.getPrice1())+" "+context.getString(R.string.le));
        double total = Double.parseDouble(viewHolder.edQuantitty.getText().toString()) * item.getPrice1();
        viewHolder.txtTotalPrice.setText(String.valueOf(myNumbers.round(total, 2)));
        viewHolder.edQuantitty.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
                {
                    item.setQuantity(Integer.parseInt(viewHolder.edQuantitty.getText().toString()) - 1);
                    myClicks.onItemClicked(item);
                }

                return false;
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName, txtTotalPrice, txtUnitPrice;
        EditText edQuantitty;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            edQuantitty = itemView.findViewById(R.id.edQuantity);
            txtUnitPrice = itemView.findViewById(R.id.txtPrice);
            txtTotalPrice = itemView.findViewById(R.id.txtItemTotal);
        }
    }
}
