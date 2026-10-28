package com.example.spannableandautocomplete;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryAdapter extends BaseAdapter implements Filterable {

    private final Context context;
    private final List<String> allCountries;
    private List<String> filteredCountries;

    public CountryAdapter(Context context, List<String> countries) {
        this.context = context;
        this.allCountries = new ArrayList<>(countries);
        this.filteredCountries = new ArrayList<>(countries);
    }

    @Override
    public int getCount() {
        return filteredCountries.size();
    }

    @Override
    public String getItem(int position) {
        return filteredCountries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        TextView textView;

        if (convertView instanceof TextView) {
            textView = (TextView) convertView;
        } else {
            textView = new TextView(context);
            textView.setPadding(24, 20, 24, 20);
            textView.setTextSize(16);
        }

        textView.setBackgroundColor(0xFFFFFDF5);
        textView.setTextColor(0xFF000000);
        textView.setText(getItem(position));

        return textView;
    }

    @Override
    public Filter getFilter() {
        return countryFilter;
    }

    private final Filter countryFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<String> resultsList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                resultsList.addAll(allCountries);
            } else {
                String query = constraint.toString()
                        .toLowerCase(Locale.ROOT);

                for (String country : allCountries) {
                    if (country.toLowerCase(Locale.ROOT)
                            .contains(query)) {
                        resultsList.add(country);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = resultsList;
            results.count = resultsList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            filteredCountries = new ArrayList<>();

            if (results.values != null) {
                filteredCountries.addAll(
                        (List<String>) results.values);
            }

            notifyDataSetChanged();
        }
    };
}