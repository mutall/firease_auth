package com.mutall.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Logout extends AppCompatActivity implements View.OnClickListener{
    Button logout;
    private static final String TAG = "Logout";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);
        if (getIntent().hasExtra("user")){
           String message = "Welcome "+getIntent().getStringExtra("user");
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: Logout button clicked");
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...

                        Intent intent = new Intent(Logout.this, MainActivity.class);
                        intent.putExtra("logout", "You have logged out");
                        startActivity(intent);

                    }
                });


    }
}
