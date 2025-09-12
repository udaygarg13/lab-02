package com.example.listycity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            if (position == selectedPosition) {
                view.setBackgroundColor(Color.TRANSPARENT);
                selectedPosition = -1;
            }
            else {
                if (selectedPosition != -1) {
                    cityList.getChildAt(selectedPosition).setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundColor(Color.LTGRAY);
                selectedPosition = position;
            }
        });

        findViewById(R.id.add_button).setOnClickListener( view -> {
            findViewById(R.id.input_field).setVisibility(View.VISIBLE);
            findViewById(R.id.confirm_button).setVisibility(View.VISIBLE);
        });

        findViewById(R.id.confirm_button).setOnClickListener( view ->  {
            EditText inputField = findViewById(R.id.input_field);
            String newCity = inputField.getText().toString();
            dataList.add(newCity);
            cityAdapter.notifyDataSetChanged();
            inputField.setText("");
            inputField.setVisibility(View.GONE);
            findViewById(R.id.confirm_button).setVisibility(View.GONE);
        });

        findViewById(R.id.delete_button).setOnClickListener( view -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityList.getChildAt(selectedPosition).setBackgroundColor(Color.TRANSPARENT);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });
    }

}