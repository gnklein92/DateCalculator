package com.example.kennethfechter.datepicker;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.android.datetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
 import java.util.Calendar;
import java.util.List;
import java.util.Locale;



public class DatePickingActivity extends Activity implements DatePickerDialog.OnDateSetListener{

    public List<Calendar> customDates;
    private Calendar startCalendar;
    private Calendar endCalendar;
    private Calendar customCalendar;
    private DateFormat dateFormat;

    private Button startButton;
    private Button endButton;
    private Button customDateButton;
    private Button calculateButton;

    private CheckBox customDate;
    private CheckBox chkExcludeSaturdays;
    private CheckBox chkExcludeSundays;
    private CheckBox chkOutputYears;
    private CheckBox chkExcludeDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picking);

        startButton = (Button) findViewById(R.id.btnDatePickerStart);
        endButton = (Button) findViewById(R.id.btnDatePickerEnd);
        customDateButton = (Button) findViewById(R.id.customDatebtn);
        calculateButton = (Button) findViewById(R.id.calculateButton);
        customDate = (CheckBox) findViewById(R.id.chkCustomDate);
        chkExcludeSaturdays = (CheckBox) findViewById(R.id.chkExcludeSaturdays);
        chkExcludeSundays = (CheckBox) findViewById(R.id.chkExcludeSundays);
        chkOutputYears = (CheckBox) findViewById(R.id.chkOutputYears);
        chkExcludeDays = (CheckBox) findViewById(R.id.chkOutputDays);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculateDaysBetween();
            }
        });
        endCalendar = Calendar.getInstance();
        customCalendar = Calendar.getInstance();
        startCalendar = Calendar.getInstance();

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

    private void CalculateDaysBetween() {
        int totalSaturdays = 0;
        int totalSundays = 0;

        Calendar endCalInstance = Calendar.getInstance();
        Calendar startCalInstance = Calendar.getInstance();
        endCalInstance.setTime(endCalendar.getTime());
        startCalInstance.setTime(startCalendar.getTime());


        if(endCalendar.getTimeInMillis() < startCalendar.getTimeInMillis())
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(DatePickingActivity.this);
            builder1.setMessage("Start date must occur before end date.");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else
        {
            while(startCalInstance.getTimeInMillis() < endCalInstance.getTimeInMillis()){
                startCalInstance.add(Calendar.DAY_OF_WEEK,1);
                if(startCalInstance.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    ++totalSaturdays;
                }
            }

            Calendar endCalInstance2 = Calendar.getInstance();
            Calendar startCalInstance2 = Calendar.getInstance();
            endCalInstance2.setTime(endCalendar.getTime());
            startCalInstance2.setTime(startCalendar.getTime());

            while(startCalInstance2.getTimeInMillis() < endCalInstance2.getTimeInMillis()){
                startCalInstance2.add(Calendar.DAY_OF_WEEK,1);
                if(startCalInstance2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                    ++totalSundays;
                }
            }

            long intervalmillis = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
            // int days = (int) ((intervalmillis / (1000*60*60*24)) % 7);
            int years = (int) ((intervalmillis / (1000*60*60*24*7))/52);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(DatePickingActivity.this);
            builder1.setMessage("Calculated interval is: " + years + "years");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Calculate Again",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ResetInterface();
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();

        }
    }

    private void ResetInterface() {
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
