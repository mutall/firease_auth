package com.mutall.firebaseauth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int RC_SIGN_IN = 12345;
    Button login;
    private static final String TAG = "MainActivity";
    List<AuthUI.IdpConfig> providers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        // Choose authentication providers
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
                );
}

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: Login btn clicked");
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(), RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK){
                Log.d(TAG, "onActivityResult: "+response);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(MainActivity.this, Logout.class);
                intent.putExtra("user", user.getDisplayName());
                //the sigin was success
                startActivity(intent);
            }else {
                Log.e(TAG, "onActivityResult: "+response );
                //error occured
                Log.e(TAG, "onActivityResult: Something went wrong");
            }
        }

    }
}
