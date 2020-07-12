package com.sanjana.menusandalerts;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button datePicker, timePicker;
    int m, d, y, h, min, s;
    TextView date_result, time_result;
    String format;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker = findViewById(R.id.datePicker_button); //ctrl + d for duplicate entire line
        /* fot\r delete entire line shift + delete */
        timePicker = findViewById(R.id.timePicker_button); //ctrl + d for duplicate entire line
        date_result = findViewById(R.id.tv);
        datePicker.setOnClickListener(this);
        time_result = findViewById(R.id.tv2);
        timePicker.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menus, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.alert:
                showAlert();
                Toast.makeText(this, "alert item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.org :
                Toast.makeText(this, "APSSDC item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search :
                Toast.makeText(this, "search item", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void  showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are You Sure");
        builder.setMessage("Do You Want To Close This App");
        builder.setIcon(R.drawable.ic_baseline_warning_24);
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Bye.......", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Stay!!!!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        final Calendar calendar = Calendar.getInstance();
        d = calendar.get(Calendar.DAY_OF_MONTH);
        m = calendar.get(Calendar.MONTH);
        y = calendar.get(Calendar.YEAR);
        h = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                date_result.setText(date);
                Toast.makeText(MainActivity.this, "Date is : " + date, Toast.LENGTH_SHORT).show();
            }
        }, d, m, y);
        datePickerDialog.show();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                String time = hour + ":" + minutes;
                time_result.setText(time);
                Toast.makeText(MainActivity.this, "Time is : " + time, Toast.LENGTH_SHORT).show();
            }
        }, h, min,true);
        timePickerDialog.show();
    }
}