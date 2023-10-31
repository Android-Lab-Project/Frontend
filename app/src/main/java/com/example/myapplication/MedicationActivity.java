package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MedicationActivity extends AppCompatActivity {
    Context context;
    private FloatingActionButton add;
    private TextView empty;
    private Dialog dialog;
    private RecyclerView recyclerView;
    private AdapterReminders adapter;
    private List<Reminders> temp = new ArrayList<>();
    String DateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        Toolbar toolbar1 = findViewById(R.id.ReminderToolbar1);
        setSupportActionBar(toolbar1);
        context = this;

        add = findViewById(R.id.floatingButton);
        empty = findViewById(R.id.empty);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReminder();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MedicationActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        setItemsInRecyclerView();

    }
    public void addReminder() {

        dialog = new Dialog(MedicationActivity.this);
        dialog.setContentView(R.layout.floating_popup);
        Button select, add, cancel;
        select = dialog.findViewById(R.id.selectDate);
        add = dialog.findViewById(R.id.addButton);
        cancel = dialog.findViewById(R.id.cancelButton);
        final EditText message = dialog.findViewById(R.id.reminderMessage);
        final Calendar newCalender = Calendar.getInstance();

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(MedicationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                        final Calendar newDate = Calendar.getInstance();
                        Calendar newTime = Calendar.getInstance();
                        TextView textView = findViewById(R.id.date);
                        TimePickerDialog time = new TimePickerDialog(MedicationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                newDate.set(year,month,dayOfMonth,hourOfDay,minute,0);
                                Calendar tem = Calendar.getInstance();
                                DateTime = newDate.getTime().toString();
                                NotificationScheduler.scheduleNotification(context, newDate.getTimeInMillis());
//                                if(newDate.getTimeInMillis()-tem.getTimeInMillis()>0)
//                                    textView.setText(newDate.getTime().toString());
//                                else
//                                    Toast.makeText(MedicationActivity.this,"Invalid time", Toast.LENGTH_SHORT).show();

                            }
                        },newTime.get(Calendar.HOUR_OF_DAY),newTime.get(Calendar.MINUTE),true);
                        time.show();

                    }
                }, newCalender.get(Calendar.YEAR),newCalender.get(Calendar.MONTH),newCalender.get(Calendar.DAY_OF_MONTH));

                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reminders reminders;
                String reminderDescription = message.getText().toString().trim();

                Date remind;
                try {
                    SimpleDateFormat format =
                            new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                    remind = format.parse(DateTime);

                }
                catch(ParseException pe) {
                    Toast.makeText(MedicationActivity.this, "mainPage error line 133", Toast.LENGTH_SHORT).show();
                    throw new IllegalArgumentException(pe);
                }
                temp.add(new Reminders(reminderDescription, remind));
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
                calendar.setTime(remind);
                calendar.set(Calendar.SECOND,0);
                setItemsInRecyclerView();
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    };
    public void setItemsInRecyclerView(){
        if(temp.size()>0) {
            empty.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        adapter = new AdapterReminders(temp);
        recyclerView.setAdapter(adapter);
    }

    }