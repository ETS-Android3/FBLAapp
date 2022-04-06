package com.nickli.scheduleapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etLoginEmail;
    TextInputEditText etLoginPassword;
    TextView tvRegisterHere;
    Button btnLogin;
    SignInButton googleSignInBtn;

    FirebaseAuth mAuth;

    GoogleSignInClient googleSignInClient;
    GoogleApiClient googleApiClient;

    private static final String TAG = "GOOGLE_SIGN_IN_TAG";
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;
    private SignInClient oneTapClient;

    @Override
    // Called when activity is created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Change frontend layout
        setContentView(R.layout.activity_login);

        etLoginEmail = (TextInputEditText)findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);
        googleSignInBtn = findViewById(R.id.googleSignInBtn);

        // Requests ID of the app and sets up the google sign in
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Sets variable to the google sign in option
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        // Gets Firebase current session
        mAuth = FirebaseAuth.getInstance();

        // When the google sign in button is clicked
        googleSignInBtn.setOnClickListener(view -> {
            // Writes into Logcat (app logs)
            Log.d(TAG, "onClick: begin Google SignIn");
            // Creates an intent/page for sign in options
            Intent intent = googleSignInClient.getSignInIntent();
            // Opens the page
            startActivityForResult(intent,100);
        });

        // OnClickListener to log user in and change activities
        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
        tvRegisterHere.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
    }

    @Override
    // Gets result from google sign in button
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Checks condition to see if its true
        if(requestCode==100)
        {
            // When request code is equal to 100
            // See what google account user clicks on
            Task<GoogleSignInAccount> signInAccountTask=GoogleSignIn
                    .getSignedInAccountFromIntent(data);

            // Checks if user clicked on their account
            if(signInAccountTask.isSuccessful())
            {
                // Shows message to user if sign in is successful
                Toast.makeText(LoginActivity.this, "Google sign in successful", Toast.LENGTH_SHORT).show();
                // Tries to sign into account
                try {
                    // Gets result of trying to sign in
                    GoogleSignInAccount googleSignInAccount=signInAccountTask
                            .getResult(ApiException.class);
                    // Makes sure the google account is valid
                    if(googleSignInAccount!=null)
                    {
                        // Gets credentials of the google account
                        AuthCredential authCredential= GoogleAuthProvider
                                .getCredential(googleSignInAccount.getIdToken()
                                        ,null);
                        // Checks to make sure the credentials are correct
                        mAuth.signInWithCredential(authCredential)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        // If credentials are correct
                                        if(task.isSuccessful())
                                        {
                                            // Allow user to go to main page
                                            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                            // Shows message of success
                                            Toast.makeText(LoginActivity.this, "Firebase authentication successful", Toast.LENGTH_SHORT).show();
                                        }
                                        // If credentials are wrong, or there
                                        else
                                        {
                                            // Shows message of failure
                                            Toast.makeText(LoginActivity.this, "Authentication Failed :"+task.getException()
                                                    .getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
                }
                // If there is an error, the error will be shown
                catch (ApiException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method called when login button clicked
    private void loginUser() {
        // Changes inputted text to string variables
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        // Sets parameters to make sure email and password are not empty
        if (TextUtils.isEmpty(email)) {
            etLoginEmail.setError("Email cannot be empty");
            etLoginEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)) {
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        }
        else {
            // If above is all false, method is called
            // Makes sure that there is such email and password in Firebase Authentication
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // If successful
                    if (task.isSuccessful()) {
                        // Lets the user enter, and shows a log to the user to tell them they are logged in
                        Toast.makeText(LoginActivity.this, "User logged on successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else {
                        // If not successful, shows log in error with error message
                        Toast.makeText(LoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
