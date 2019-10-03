package com.bignerdranch.android.travelplanner.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bignerdranch.android.travelplanner.R;
import com.bignerdranch.android.travelplanner.data.Country;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private ArrayList<Country> mLoadedCountries;
    private ItemClickListener mItemClickListener;

    public CountryAdapter(ArrayList<Country> countries) {
        this.mLoadedCountries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_travel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mLoadedCountries.get(position));
    }

    @Override
    public int getItemCount() {
        return mLoadedCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTvCountry;
        private TextView mTvDate;
        private CheckBox mCbDone;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvCountry = itemView.findViewById(R.id.tv_list_item_travel_country);
            mTvDate = itemView.findViewById(R.id.tv_list_item_travel_date);
            mCbDone = itemView.findViewById(R.id.cb_list_item_done);
            itemView.setOnClickListener(this);
        }

        public void setData(final Country country) {

            mTvCountry.setText(country.getCountry());
            mTvDate.setText(country.getDate());
            mCbDone.setChecked(country.getDone());
            mCbDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    country.setDone(b);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null)
                mItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
