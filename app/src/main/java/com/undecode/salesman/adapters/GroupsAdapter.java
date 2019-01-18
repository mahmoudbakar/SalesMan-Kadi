package com.undecode.salesman.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.undecode.salesman.R;
import com.undecode.salesman.models.Groups;
import com.undecode.salesman.utils.OnCategoryPressed;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder>
{
    Context context;
    Activity activity;
    List<Groups> groups;
    List<Integer> ids;
    OnCategoryPressed onCategoryPressed;

    public GroupsAdapter(Context context, Activity activity, List<Groups> groups, OnCategoryPressed onCategoryPressed)
    {
        this.context = context;
        this.activity = activity;
        this.groups = new ArrayList<>();
        for (Groups group:groups)
        {
            this.groups.add(new Groups(group.getGroupNameE(), group.getGroupNameA(), group.getGroupID()));
        }
        ids = new ArrayList<>();
        this.onCategoryPressed = onCategoryPressed;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i)
    {
        viewHolder.setIsRecyclable(false);
        viewHolder.txtName.setChecked(groups.get(i).isChecked());
        viewHolder.txtName.setTextOn(groups.get(i).getGroupName());
        viewHolder.txtName.setTextOff(groups.get(i).getGroupName());
        viewHolder.txtName.setText(groups.get(i).getGroupName());
        viewHolder.txtName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                groups.get(i).setChecked(isChecked);
                if (isChecked)
                {
                    ids.add(groups.get(i).getGroupID());
                }else
                {
                    for (int x = 0; x < groups.size(); x++)
                    {
                        for (int z = 0; z < ids.size(); z++)
                        {
                            if (ids.get(z) == groups.get(x).getGroupID())
                            {
                                ids.remove(z);
                                z = ids.size();
                                x = groups.size();
                            }
                        }
                    }
                }
                int[] categoryIDs = new int[ids.size()];
                for (int i = 0; i < ids.size(); i++)
                {
                    categoryIDs[i] = ids.get(i);
                }
                onCategoryPressed.notify(categoryIDs);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return groups.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.txtName)
        ToggleButton txtName;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
