package com.akash.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.akash.firebase2.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mainBinding.Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mainBinding.emailedit.getText().toString().trim();
                String pass = mainBinding.passedit.getText().toString().trim();
                Intent i = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
                Login(email,pass);

            }
        });


    }
    public void Login(String email, String pass){
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = task.getResult().getUser();
                    Log.d("chk", "success");
                }
                else{
                    Log.d("chk","failed"+task.getException().getMessage());
                }
            }
        });
    }

    public void registrationClick(View view) {
        if(view!=null){
            Intent intent = new Intent ( MainActivity.this,RegistrationActivity.class );
            startActivity ( intent );
            finish ();
        }
    }
}
