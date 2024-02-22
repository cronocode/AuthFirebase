package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.firebaseauth.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(t -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        binding.btnRegister.setOnClickListener(t -> {
            String name = binding.name.getText().toString();
            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (!task.isSuccessful()) {
                            Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();
                        auth.getCurrentUser().updateProfile(profileUpdate);
                        Toast.makeText(this, "Register success, Welcome " + name, Toast.LENGTH_SHORT).show();
                    });
        });
    }
}