package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.firebaseauth.databinding.ActivityLoginBinding;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(t -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        binding.btnForgotPassword.setOnClickListener(t -> {
            startActivity(new Intent(this, ForgotPasswordActivity.class));
        });

        binding.btnLogin.setOnClickListener(t -> {
            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (!task.isSuccessful()) {
                            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}