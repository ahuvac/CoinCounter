package com.example.coincounterac;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static lib.Utils.showInfoDialog;

public class MainActivity extends AppCompatActivity {
    private CoinCounter mCoinCounter = new CoinCounter();

    private EditText etxtPennies;
    private EditText etxtNickels;
    private EditText etxtDimes;
    private EditText etxtQuarters;
    private TextView btmbar;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("COINCOUNTER", CoinCounter.getJSONStringFrom(mCoinCounter));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCoinCounter = CoinCounter.getCoinCounterObjectFromJSONString(savedInstanceState.getString("COINCOUNTER"));
        displayResult();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpFab();
        initializeViews();
    }
    //onSaveInstanceState, onRestoreInstanceState

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setUpFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fabAction(fab);
    }

    private void fabAction(FloatingActionButton fab) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVariables();
                displayResult();
            }
        });
    }

    private void displayResult() {
        String strResult = "Total in cents: "+ mCoinCounter.getCentsValueTotal() + "";
        btmbar.setText(strResult);
    }

    private void initializeViews() {
        etxtPennies = (EditText) findViewById(R.id.penny_amt);
        etxtNickels = (EditText) findViewById(R.id.nickel_amt);
        etxtDimes = (EditText) findViewById(R.id.dime_amt);
        etxtQuarters = (EditText) findViewById(R.id.quarter_amt);
        btmbar = (TextView) findViewById(R.id.name_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.about) {
            showInfoDialog(MainActivity.this, getString(R.string.info_title), getString(R.string.info_description));
            return true;
        }
        if (id == R.id.clear) {
            clearAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setVariables() {
        mCoinCounter.setCountOfPennies(etxtPennies.getText().toString());
        mCoinCounter.setCountOfNickels(etxtNickels.getText().toString());
        mCoinCounter.setCountOfDimes(etxtDimes.getText().toString());
        mCoinCounter.setCountOfQuarters(etxtQuarters.getText().toString());
    }


    private void clearAll() {
        etxtPennies.getText().clear();
        etxtNickels.getText().clear();
        etxtDimes.getText().clear();
        etxtQuarters.getText().clear();
        btmbar.setText(R.string.name);

        mCoinCounter.setCountOfPennies(0);
        mCoinCounter.setCountOfNickels(0);
        mCoinCounter.setCountOfDimes(0);
        mCoinCounter.setCountOfQuarters(0);

    }
}

