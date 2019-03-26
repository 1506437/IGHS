package com.jameslittle.ighs.ighs;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.v4.os.LocaleListCompat.create;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String email = etEmail.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                Toast.makeText(RegisterActivity.this, "Registration Successful, Please Login", Toast.LENGTH_LONG).show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                                Toast.makeText(RegisterActivity.this, "Registration Failed, Contact Developer", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, email, password, responseListener);
                registerRequest.setShouldCache(false); // Disables Caching for Volley so that multiple registration requests can be submitted.
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

                etName.addTextChangedListener(registerTextWatcher);
                etEmail.addTextChangedListener(registerTextWatcher);
                etUsername.addTextChangedListener(registerTextWatcher);
                etPassword.addTextChangedListener(registerTextWatcher);

            }

            private TextWatcher registerTextWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String nameInput = etName.getText().toString();
                    String passwordInput = etPassword.getText().toString();
                    String usernameInput = etUsername.getText().toString();
                    String emailInput = etEmail.getText().toString();

                    if (!nameInput.isEmpty() && !passwordInput.isEmpty() && !usernameInput.isEmpty() && !emailInput.isEmpty()){
                        bRegister.setEnabled(true);
                    }
                    else{
                        bRegister.setEnabled(false);
                    }

//                    bRegister.setEnabled(!nameInput.isEmpty() && !passwordInput.isEmpty() && !usernameInput.isEmpty() && !emailInput.isEmpty());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };
        });

    }
}