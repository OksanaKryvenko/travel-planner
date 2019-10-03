package com.bignerdranch.android.travelplanner.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bignerdranch.android.travelplanner.R;
import com.bignerdranch.android.travelplanner.adapter.CountryAdapter;
import com.bignerdranch.android.travelplanner.data.Country;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.bignerdranch.android.travelplanner.screen.MainActivity.mDoneList;

public class RecyclerFragment extends Fragment implements CountryAdapter.ItemClickListener{

    public static final String WRITE_READ_TAG = "WRITE_READ_TAG";
    public static final String fileName = "myTravelList.txt";
    private static final String TAG = "log_tag";

    private ArrayList<String> mSavedCountries = new ArrayList<>();
    private ArrayList<Country> mLoadedCountries = new ArrayList<>();

    private RecyclerView mRecycler;
    private CountryAdapter adapter;

    private ArrayList<String> readFromFile(@NonNull FileInputStream fileInputStream) {
        mSavedCountries = new ArrayList<>();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String lineData;
            while ((lineData = bufferedReader.readLine()) != null) {
                mSavedCountries.add(lineData);
            }
        } catch (IOException ex) {
            Log.d(WRITE_READ_TAG, ex.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mSavedCountries;
    }

    public void readCountries() {

        Context ctx2 = getActivity();

        try {
            FileInputStream fileInputStream = ctx2.openFileInput(fileName);
            mSavedCountries = readFromFile(fileInputStream);
        } catch (FileNotFoundException ex) {
            Log.d(WRITE_READ_TAG, ex.getMessage());
        }

        mLoadedCountries = new ArrayList<>();

        for (int i = 0; i < mSavedCountries.size(); i += 4) {
            mLoadedCountries.add(new Country(mSavedCountries.get(i), mSavedCountries.get(i + 1),
                    mSavedCountries.get(i + 2), mSavedCountries.get(i + 3), false));
        }

        for (int i = 0; i < mDoneList.size(); i++) {
            mLoadedCountries.get(i).setDone(mDoneList.get(i));
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        FragmentManager manager = getFragmentManager();

        TravelFragment travelFragment;
        travelFragment = TravelFragment.newInstance(mLoadedCountries.get(position));
        manager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.flContainer, travelFragment)
                .commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        readCountries();
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        mRecycler = view.findViewById(R.id.rvTravels);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CountryAdapter(mLoadedCountries);
        adapter.setClickListener(this);
        mRecycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
