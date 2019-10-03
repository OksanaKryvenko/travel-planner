package com.bignerdranch.android.travelplanner.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bignerdranch.android.travelplanner.R;
import com.bignerdranch.android.travelplanner.data.Country;

public class TravelFragment extends Fragment {
    private static final String BUNDLE_COUNTRY = "BUNDLE_COUNTRY";
    private static final String BUNDLE_CITY = "BUNDLE_CITY";
    private static final String BUNDLE_HOTEL = "BUNDLE_HOTEL";
    private static final String BUNDLE_DATE = "BUNDLE_DATE";
    private static final String BUNDLE_DONE = "BUNDLE_DONE";
    private TextView mTvCountry;
    private TextView mTvCity;
    private TextView mTvHotel;
    private TextView mTvDate;
    private CheckBox mCbDone;

    public static TravelFragment newInstance(Country country) {
        Bundle args = new Bundle(5);
        args.putString(BUNDLE_COUNTRY, country.getCountry());
        args.putString(BUNDLE_CITY, country.getCity());
        args.putString(BUNDLE_HOTEL, country.getHotel());
        args.putString(BUNDLE_DATE, country.getDate());
        args.putBoolean(BUNDLE_DONE, country.getDone());
        TravelFragment fragment = new TravelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel, container, false);
        mTvCountry = view.findViewById(R.id.tv_country);
        mTvCity = view.findViewById(R.id.tv_city);
        mTvHotel = view.findViewById(R.id.tv_hotel);
        mTvDate = view.findViewById(R.id.tv_date);
        mCbDone = view.findViewById(R.id.cb_done);

        Bundle args = getArguments();
        if(args!=null) {
            mTvCountry.setText(args.getString(BUNDLE_COUNTRY));
            mTvCity.setText(args.getString(BUNDLE_CITY));
            mTvHotel.setText(args.getString(BUNDLE_HOTEL));
            mTvDate.setText(args.getString(BUNDLE_DATE));
            mCbDone.setChecked(args.getBoolean(BUNDLE_DONE));
        }
        return view;
    }

    public void updateView(final Country country) {
        mTvCountry.setText(country.getCountry());
        mTvCity.setText(country.getCity());
        mTvHotel.setText(country.getHotel());
        mTvDate.setText(country.getDate());
        mCbDone.setChecked(country.getDone());
        mCbDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                country.setDone(isChecked);
            }
        });
    }
}
