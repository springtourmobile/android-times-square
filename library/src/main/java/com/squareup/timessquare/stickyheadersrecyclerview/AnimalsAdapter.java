package com.squareup.timessquare.stickyheadersrecyclerview;

import android.support.v7.widget.RecyclerView;


import com.squareup.timessquare.MonthDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * Adapter holding a list of animal names of type String. Note that each item must be unique.
 */
public abstract class AnimalsAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    private List<MonthDescriptor> items = new ArrayList<>();

    public AnimalsAdapter() {
        setHasStableIds(true);
    }

    public void addAll(Collection<? extends MonthDescriptor> collection) {
        if (collection != null) {
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void addAll(MonthDescriptor... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove(String object) {
        items.remove(object);
        notifyDataSetChanged();
    }

    public String getItem(int position) {
        if (items.size() > 0) {
            return items.get(position).getMonth() + "";
        }
        return "";
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
