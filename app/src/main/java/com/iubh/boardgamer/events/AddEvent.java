package com.iubh.boardgamer.events;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.firebase.ui.auth.IdpResponse;
import com.iubh.boardgamer.Auth.User;
import com.iubh.boardgamer.Game.Spieltermin;
import com.iubh.boardgamer.R;
import com.squareup.okhttp.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEvent extends AppCompatActivity {

    private CalendarView calendarView;
    private Button btnAddEvent;
    private Date calDate;
    private DateFormat dateFormat = new SimpleDateFormat("dd  MMMM yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        calendarView = (CalendarView) findViewById(R.id.CalendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.clear();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DATE, dayOfMonth);
                calDate = cal.getTime();
                //Toast.makeText(AddEvent.this,calDate.toString(),Toast.LENGTH_SHORT).show();



            }
        });

        btnAddEvent = (Button) findViewById(R.id.btnAddEvent);
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calDate != null) {
                    Spieltermin[] spieltermine = new Spieltermin[0];
                    checkSpieltermine(spieltermine);
                } else{
                    Toast.makeText(AddEvent.this,"Please pick a date first",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @SuppressLint("RestrictedApi")
    public void checkSpieltermine(Spieltermin[] spieltermine){
                        IdpResponse response = null;
                        if(response.isSuccessful()) {
                            boolean exists = false;
                            String strDate = dateFormat.format(calDate);
                            for(int i=0; i < spieltermine.length; i++){
                                if(spieltermine[i].getEventDate().gettransformdate().equals(strDate)) {
                                    Toast.makeText(AddEvent.this,"An event already exists on this date",Toast.LENGTH_SHORT).show();
                                    exists = true;
                                    break;
                                }
                            }
                            if(exists == false) {
                                Spieltermin spieltermin = new Spieltermin();
                                EventDate eventD = new EventDate();
                                eventD.setType("Date");
                                eventD.setIso(calDate);
                                spieltermin.setEventDate(eventD);
                                User user = new User();
                                spieltermin.setAusrichter(user);
                                addSpieltermin(spieltermin);
                            }
                        } else {
                            Toast.makeText(AddEvent.this, "Error: something went wrong", Toast.LENGTH_SHORT ).show();
                        }
                    }

    private void addSpieltermin(Spieltermin spieltermin) {
    }


    public void onFailure() {
        Toast.makeText(AddEvent.this, "Error" , Toast.LENGTH_SHORT).show();
    }

    public void addSpieltermin() {

            Response response = null;
            if (response.isSuccessful()) {
                                updateHostCounter();
                            } else {
                                Toast.makeText(AddEvent.this, "Error: something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }

    private void updateHostCounter() {
    }


    public void addTeilnehmer(String objectId) {
        Spieltermin spieltermin = new Spieltermin();
        spieltermin.setObjectId(objectId);
        spieltermin.setType("Pointer");
        spieltermin.setClassName("Spieltermin");
        User user = new User();
        spieltermin.setAusrichter(user);
        Teilnehmer tnr = new Teilnehmer();
        tnr.setUserId(user);
    }

}
