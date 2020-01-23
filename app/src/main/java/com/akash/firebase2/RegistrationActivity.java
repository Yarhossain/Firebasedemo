package com.akash.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.akash.firebase2.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding registrationBinding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();

        registrationBinding.regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = registrationBinding.emailedit.getText().toString().trim();
                String pass = registrationBinding.passedit.getText().toString().trim();
                registration(email,pass);
            }
        });
    }

    public void registration(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    Log.d("chk","success"+user.getEmail());
                    Toast.makeText(RegistrationActivity.this,"Registration Succesfull",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                    intent.putExtra("getEmail", user.getEmail());
                    startActivity(intent);
                    finish();
                }
                else {
                    Log.d("chk","failed"+task.getException().getMessage());
                }
            }
        });
    }
}
