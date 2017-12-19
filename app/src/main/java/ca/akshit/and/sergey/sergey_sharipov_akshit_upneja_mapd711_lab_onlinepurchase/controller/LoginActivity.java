package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.R;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db.ClerkLab;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db.CustomerLab;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.util.SharedPreferencesHelper;

public class LoginActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    SharedPreferencesHelper mSharedPrefHelper;
    private ClerkLab mClerkLab;
    private CustomerLab mCustomerLab;
    private EditText mEmailView;
    private EditText mPasswordView;
    private RadioGroup userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle(R.string.action_sign_in);

        mSharedPrefHelper = SharedPreferencesHelper.get(getSharedPreferences(
                SharedPreferencesHelper.NAME, MODE_PRIVATE));

        mClerkLab = new ClerkLab(this);
        mCustomerLab = new CustomerLab(this);

        userType = findViewById(R.id.radio_group_user_type);

        mEmailView = findViewById(R.id.email);

        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(this);

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        findViewById(R.id.email_register_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            attemptLogin();
            return true;
        }
        return false;
    }

    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            switch (userType.getCheckedRadioButtonId()){
                case R.id.radio_customer:
                    checkCustomerData(email,password);
                    break;
                case R.id.radio_clerk:
                    checkClerkData(email,password);
                    break;
            }
        }
    }

    private void checkCustomerData(String email, String password){
        if(mCustomerLab.isCustomerDataCorrect(email,password)){
            mSharedPrefHelper.saveCurrentUser(mCustomerLab.getCustomer(email).getId(),email,password,true);
            Intent intent = new Intent(this, ProductsActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(this, R.string.message_incorrect_login_pass, Toast.LENGTH_SHORT).show();
    }

    private void checkClerkData(String email, String password){
        if(mClerkLab.isClerkDataCorrect(email,password)){
            mSharedPrefHelper.saveCurrentUser(mClerkLab.getClerk(email).getId(),email,password,false);
            Intent intent = new Intent(this, CustomersActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(this, R.string.message_incorrect_login_pass, Toast.LENGTH_SHORT).show();
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}