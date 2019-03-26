package com.jameslittle.ighs.ighs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_home, container, false);


    //    TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);
     //   welcomeMessage.setText("Welcome " + username);

        //       TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        //     EditText etUsername = (EditText) findViewById(R.id.etUsername);
        //   EditText etEmail = (EditText) findViewById(R.id.etEmail);

        // String message = ("Welcome " + name + " login successful.");
        //tvWelcomeMsg.setText(message);
        //etUsername.setText(username);
        //etEmail.setText(email);
    }

}
