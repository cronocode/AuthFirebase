package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.firebaseauth.databinding.ActivityForgotPasswordBinding;
import com.example.firebaseauth.databinding.ActivityLoginBinding;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnResetPassword.setOnClickListener(t -> {
            String email = binding.email.getText().toString();

            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, task -> {
                        if (!task.isSuccessful()) {
                            Toast.makeText(this, "Account not found", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(this,"Please check email", Toast.LENGTH_SHORT).show();
                    });
        });
    }
}