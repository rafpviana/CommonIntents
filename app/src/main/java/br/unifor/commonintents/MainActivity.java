package br.unifor.commonintents;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonCreateAlarm;
    private EditText editTextAlarmMessage;
    private EditText editTextAlarmHour;
    private EditText editTextAlarmMinutes;
    private String alarmMessage;
    private int alarmHour;
    private int alarmMinutes;

    private Button buttonCreateCall;
    private EditText editTextPhoneNumber;
    private String phoneNumber;

    private Button buttonCreateSearch;
    private EditText edittextSearch;
    private String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAlarmMessage = (EditText) findViewById(R.id.editTextAlarmMessageId);
        editTextAlarmHour = (EditText) findViewById(R.id.editTextAlarmHourId);
        editTextAlarmMinutes = (EditText) findViewById(R.id.editTextAlarmMinutesId);

        buttonCreateAlarm = (Button) findViewById(R.id.buttonCreateAlarmId);
        buttonCreateAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmMessage = editTextAlarmMessage.getText().toString();
                alarmHour = Integer.parseInt(editTextAlarmHour.getText().toString());
                alarmMinutes = Integer.parseInt(editTextAlarmMinutes.getText().toString());

                createAlarm(alarmMessage, alarmHour, alarmMinutes);
            }
        });

        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumberId);

        buttonCreateCall = (Button) findViewById(R.id.buttonCreateCallId);
        buttonCreateCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = editTextPhoneNumber.getText().toString();
                dialPhoneNumber(phoneNumber);
            }
        });

        edittextSearch = (EditText) findViewById(R.id.editTextSearchId);

        buttonCreateSearch = (Button) findViewById(R.id.buttonCreateSearchId);
        buttonCreateSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = edittextSearch.getText().toString();
                searchWeb(search);
            }
        });
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
