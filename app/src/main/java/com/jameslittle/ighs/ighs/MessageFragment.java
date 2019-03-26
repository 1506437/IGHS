package com.jameslittle.ighs.ighs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageFragment extends Fragment {
    private final static int SEND_SMS_PERMISSION_REQUEST_CODE = 111;
    private Button sendMessage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendMessage = (Button) getActivity().findViewById(R.id.bArrivalTestMessage);
//        Button sendMessage = (Button) getActivity().findViewById(R.id.bArrivalTestMessage);
//        this.sendMessage = sendMessage;
        final EditText phone = (EditText)getActivity().findViewById(R.id.etPhoneNumber);
        final EditText message = (EditText) getActivity().findViewById(R.id.etMessageArrival);
//        sendMessage.setEnabled(false);

        if (checkPermission(Manifest.permission.SEND_SMS)) {
//            sendMessage.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = getActivity().findViewById(R.id.etMessageArrival).toString();
                String phoneNumber = getActivity().findViewById(R.id.etPhoneNumber).toString();

                if (!TextUtils.isEmpty(msg) && !TextUtils.isEmpty(phoneNumber)) {
                    if (checkPermission(Manifest.permission.SEND_SMS)) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNumber, null,msg, null, null);
                    } else {
                        Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Enter a messsage and a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(getActivity(), permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_message, container, false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode){
                case SEND_SMS_PERMISSION_REQUEST_CODE:
                    if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
//                        sendMessage.setEnabled(true);
                    }

                    break;
            }
        }
    }
