package com.example.houserental.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.houserental.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText email;
    private EditText password;
    private EditText rPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        Button btn = findViewById(R.id.register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rPassword = findViewById(R.id.rPassword);

        btn.setOnClickListener(view -> {
            if (!String.valueOf(password.getText()).equals(String.valueOf(rPassword.getText()))) {
                password.setError("Password does not match");
                rPassword.setError("Password does not match");

                return;
            }

            mAuth.createUserWithEmailAndPassword(String.valueOf(email.getText()), String.valueOf(password.getText()))
                    .addOnCompleteListener(RegisterActivity.this, task -> {
                        if (task.isSuccessful()) {
                            Log.d("TEST", "user created");
                        } else {
                            Log.d("TEST", "failed to create a user");
                        }
                    });
        });


    }
}