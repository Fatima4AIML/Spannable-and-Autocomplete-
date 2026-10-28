package com.example.spannableandautocomplete;

import android.os.Bundle;
import android.text.Html;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView txtView;
    AutoCompleteTextView arrayAutoComplete;
    AutoCompleteTextView customAutoComplete;

    String[] countries = {
            "Pakistan", "India", "China", "Turkey",
            "Iran", "Indonesia", "Palestine", "Panama"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = findViewById(R.id.txtView);
        arrayAutoComplete = findViewById(R.id.arrayAutoComplete);
        customAutoComplete = findViewById(R.id.customAutoComplete);

        Button btnClear = findViewById(R.id.btnClear);

        btnClear.setOnClickListener(v -> {
            customAutoComplete.setText("");
            arrayAutoComplete.setText("");
        });

        // Colored text using HTML
        String hello = getColoredSpanned("Fatima ", "blue");
        String name = getColoredSpanned("Momin", "green");

        txtView.setText(Html.fromHtml(
                hello + name, Html.FROM_HTML_MODE_LEGACY));

        // Simple ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                countries
        );

        arrayAutoComplete.setAdapter(arrayAdapter);

        arrayAutoComplete.setOnItemClickListener(
                (parent, view, position, id) -> {
                    String selected = parent.getItemAtPosition(position).toString();
                    Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
                });

        // CustomAdapter
        List<String> countryList = new ArrayList<>(
                Arrays.asList(countries));

        CountryAdapter customAdapter = new CountryAdapter(
                this, countryList);

        customAutoComplete.setAdapter(customAdapter);
        customAutoComplete.setThreshold(1);

        customAutoComplete.setOnItemClickListener(
                (parent, view, position, id) -> {
                    String selected = parent.getItemAtPosition(position).toString();
                    Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
                });

    }

    public String getColoredSpanned(String text, String color) {
        return "<font color='" + color + "'>" + text + "</font>";
    }
}