package com.example.dell.zersey.Session;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dell.zersey.MainActivity;
import com.example.dell.zersey.PHPServer.UserPHP;
import com.example.dell.zersey.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class LoginWithGoogle extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private SignInButton btnSignIn;
    GoogleApiClient mGoogleApiClient;
    int SIGN_IN=100;
    SessionManagar sessionManagar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_login);

        btnSignIn=(SignInButton) findViewById(R.id.sign_in);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(LoginWithGoogle.this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void signIn() {


        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.sign_in){
                   signIn();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();


            if (acct!=null) {

                sessionManagar =new SessionManagar(LoginWithGoogle.this);

                String personName = acct.getDisplayName();


                String email = acct.getEmail();
                String g_id=acct.getId();
           //    new UserPHP(MyAccount.this).execute(email,new SharedToken(MyAccount.this).getUserDetails().get(SharedToken.KEY_TOKEN));

                Log.e("LoginWithGoogle: ",personName +"  " +email);
                Log.e("LoginWithGoogle: ",String.valueOf(g_id));
                sessionManagar.createLoginSession(personName,email);
                sessionManagar.createUserSession(g_id);
                new UserPHP(LoginWithGoogle.this).execute(g_id,personName,email);
                if(sessionManagar.isLoggedIn()){
                    Intent i = new Intent(LoginWithGoogle.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }
        else
        {

            Toast.makeText(LoginWithGoogle.this,"Check Internet Connection",Toast.LENGTH_LONG).show();
        }
    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {

                    @Override
                    public void onResult(@Nullable Status status) {
                         sessionManagar.logoutUser();
                    }
                });
    }

}
