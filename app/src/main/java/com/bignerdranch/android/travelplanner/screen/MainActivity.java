package com.bignerdranch.android.travelplanner.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.bignerdranch.android.travelplanner.R;
import com.bignerdranch.android.travelplanner.data.Country;
import com.bignerdranch.android.travelplanner.fragment.RecyclerFragment;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    public static final String WRITE_READ_TAG = "WRITE_READ_TAG";
    public static final String fileName = "myTravelList.txt";

    public static SharedPreferences mPrefs;
    private Button mBtnLoad;

    private ArrayList<Country> mCountries = new ArrayList<>();
    public static ArrayList<Boolean> mDoneList = new ArrayList<>();

    public void fillCountries() {
        mCountries.add(new Country("Турция", "Алания", "Zen The Inn *****", "09.2013", true));
        mCountries.add(new Country("Украина", "Ялта", "Отель Интурист ****", "10.2013", true));
        mCountries.add(new Country("Украина", "Киев", "Отель Украина ****", "05.2014", true));
        mCountries.add(new Country("Украина", "Буковель", "Красная Поляна ****", "02.2015", true));
        mCountries.add(new Country("Украина", "Драгобрат", "Зелена Дача ****", "02.2017", true));
        mCountries.add(new Country("Украина", "Буковель", "Zima Ski&Snow ****", "04.2018", true));
        mCountries.add(new Country("Украина", "Буковель", "Villa Bellavista ****", "03.2019", true));
        mCountries.add(new Country("Украина", "Кирилловка", "Аквамарин ****", "06.2019", true));
        mCountries.add(new Country("Египет", "Шарм-Эль-Шейх", "Rixos Sharm El Sheih *****", "02.2020", false));
        mCountries.add(new Country("Испания", "Валенсия", "Vincci Palace ****", "08.2020", false));
        mCountries.add(new Country("Норвегия", "Осло", "Raddison Blue Hotel Nydalen ****", "01.2021", false));
        mCountries.add(new Country("Франция", "Париж", "Hotel Plaza Elysees ****", "08.2021", false));
    }

    public void fillCheckBox() {

        mPrefs.edit().putBoolean("1", true)
                .putBoolean("2", true)
                .putBoolean("3", true)
                .putBoolean("4", true)
                .putBoolean("5", true)
                .putBoolean("6", true)
                .putBoolean("7", true)
                .putBoolean("8", true)
                .putBoolean("9", false)
                .putBoolean("10", false)
                .putBoolean("11", false)
                .putBoolean("12", false)
                .apply();
    }

    public void writeCheckBox() {
        mDoneList.add(mPrefs.getBoolean("1", false));
        mDoneList.add(mPrefs.getBoolean("2", false));
        mDoneList.add(mPrefs.getBoolean("3", false));
        mDoneList.add(mPrefs.getBoolean("4", false));
        mDoneList.add(mPrefs.getBoolean("5", false));
        mDoneList.add(mPrefs.getBoolean("6", false));
        mDoneList.add(mPrefs.getBoolean("7", false));
        mDoneList.add(mPrefs.getBoolean("8", false));
        mDoneList.add(mPrefs.getBoolean("9", false));
        mDoneList.add(mPrefs.getBoolean("10", false));
        mDoneList.add(mPrefs.getBoolean("11", false));
        mDoneList.add(mPrefs.getBoolean("12", false));
    }

    private void writeDataToFile(FileOutputStream fileOutputStream, StringBuilder stringBuilder) {
        BufferedWriter bufferedWriter = null;
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(String.valueOf(stringBuilder));
        } catch (IOException ex) {
            Log.d(WRITE_READ_TAG, ex.getMessage());
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeCountries() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mCountries.size(); i++) {
            stringBuilder.append(mCountries.get(i).getCountry() + '\n')
                    .append(mCountries.get(i).getCity() + '\n')
                    .append(mCountries.get(i).getHotel() + '\n')
                    .append(mCountries.get(i).getDate() + '\n');
        }
        if (TextUtils.isEmpty(stringBuilder)) {
            Toast.makeText(this, "No data to save", Toast.LENGTH_SHORT).show();
            return;
        }

        Context ctx = getBaseContext();
        try {
            FileOutputStream fileOutputStream = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
            writeDataToFile(fileOutputStream, stringBuilder);
        } catch (
                FileNotFoundException ex) {
            Log.d(WRITE_READ_TAG, ex.getMessage());
        }
        Toast.makeText(this, "Data has written successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = getPreferences(MODE_PRIVATE);
        fillCheckBox();
        writeCheckBox();

        fillCountries();
        writeCountries();

        mBtnLoad = findViewById(R.id.btn_load);
        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();

                Fragment fragment = fm.findFragmentById(R.id.flContainer);
                if (fragment == null) {
                    fragment = new RecyclerFragment();
                    fm.beginTransaction()
                            .add(R.id.flContainer, fragment)
                            .commit();
                }
            }
        });
    }
}
