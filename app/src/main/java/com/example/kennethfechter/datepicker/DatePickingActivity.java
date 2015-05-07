package com.example.kennethfechter.datepicker;

import android.opengl.Visibility;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;
import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Locale;



public class DatePickingActivity extends Activity implements DatePickerDialog.OnDateSetListener{

    private Calendar startCalendar;
    private Calendar endCalendar;
    private Calendar customCalendar;
    private DateFormat dateFormat;

    private Button startButton;
    private Button endButton;
    private Button customDateButton;
    private Button calculateButton;

    private CheckBox customDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picking);

        startButton = (Button) findViewById(R.id.btnDatePickerStart);
        endButton = (Button) findViewById(R.id.btnDatePickerEnd);
        customDateButton = (Button) findViewById(R.id.customDatebtn);
        calculateButton = (Button) findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateButton.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });
        endCalendar = customCalendar = startCalendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        customDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
                        customCalendar.set(year, monthOfYear, dayOfMonth);
                        customDateButton.setText(dateFormat.format(customCalendar.getTime()));

                    }
                }, customCalendar.get(Calendar.YEAR), customCalendar.get(Calendar.MONTH), customCalendar.get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");            }
        });

        customDate = (CheckBox) findViewById(R.id.chkCustomDate);
        customDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked())
                {
                    customDateButton.setVisibility(View.VISIBLE);
                }
                else
                {
                    customDateButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
                        startCalendar.set(year, monthOfYear, dayOfMonth);
                        startButton.setText(dateFormat.format(startCalendar.getTime()));

                    }
                }, startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
                        endCalendar.set(year, monthOfYear, dayOfMonth);
                        endButton.setText(dateFormat.format(endCalendar.getTime()));
                    }
                }, endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_date_picking, menu);
        return true;
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
