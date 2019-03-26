package com.jameslittle.ighs.ighs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;

import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    Button buttonSendArrival;
    Button buttonSendDeparture;
    EditText editTextPhoneNumber;
    EditText editTextMessageArrival;
    EditText editTextMessageDeparture;
    String phoneNumber;
    String arrivalMessage;
    String departureMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        buttonSendArrival = (Button) findViewById(R.id.bArrivalTestMessage);
        buttonSendDeparture = (Button) findViewById(R.id.bDepartureTestMessage);
        editTextPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        editTextMessageArrival = (EditText) findViewById(R.id.etMessageArrival);
        editTextMessageDeparture = (EditText) findViewById(R.id.etMessageDeparture);


        buttonSendArrival.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSArrivalMessage();
            }
        });
        buttonSendDeparture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSDepartureMessage();
            }
        });
    }

    protected void sendSMSArrivalMessage() {
        phoneNumber = editTextPhoneNumber.getText().toString();
        arrivalMessage = editTextMessageArrival.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    protected void sendSMSDepartureMessage() {
        phoneNumber = editTextPhoneNumber.getText().toString();
        departureMessage = editTextMessageDeparture.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, arrivalMessage, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}


