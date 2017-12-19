package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.R;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db.CustomerLab;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Customer;

public class RegisterActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private CustomerLab mCustomerLab;

    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mAddress;
    private AutoCompleteTextView mProvince;
    private EditText mCity;
    private EditText mPostalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mCustomerLab = new CustomerLab(this);

        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mFirstName = findViewById(R.id.firstName);
        mLastName = findViewById(R.id.lastName);
        mAddress = findViewById(R.id.address);
        mProvince = findViewById(R.id.province);
        mCity = findViewById(R.id.city);
        mPostalCode = findViewById(R.id.postal_code);

        mPostalCode.setOnEditorActionListener(this);

        Button mEmailSignInButton = findViewById(R.id.email_register_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            attemptRegister();
            return true;
        }
        return false;
    }

    private void attemptRegister() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mFirstName.setError(null);
        mLastName.setError(null);
        mAddress.setError(null);
        mProvince.setError(null);
        mCity.setError(null);
        mPostalCode.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();
        String address = mAddress.getText().toString();
        String province = mProvince.getText().toString();
        String city = mCity.getText().toString();
        String postalCode = mPostalCode.getText().toString();

        boolean cancel = false;
        View focusView = null;

        String errorFieldRequired = getString(R.string.error_field_required);

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(errorFieldRequired);
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailAvailable(email)) {
            mEmailView.setError(getString(R.string.error_email_registered));
            focusView = mEmailView;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(errorFieldRequired);
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mPasswordView;
            cancel = true;
        } else if (TextUtils.isEmpty(firstName)) {
            mFirstName.setError(errorFieldRequired);
            focusView = mFirstName;
            cancel = true;
        } else if (TextUtils.isEmpty(lastName)) {
            mLastName.setError(errorFieldRequired);
            focusView = mLastName;
            cancel = true;
        } else if (TextUtils.isEmpty(address)) {
            mAddress.setError(errorFieldRequired);
            focusView = mAddress;
            cancel = true;
        } else if (TextUtils.isEmpty(province)) {
            mProvince.setError(errorFieldRequired);
            focusView = mProvince;
            cancel = true;
        }else if (TextUtils.isEmpty(city)) {
            mCity.setError(errorFieldRequired);
            focusView = mCity;
            cancel = true;
        }else if (TextUtils.isEmpty(postalCode)) {
            mPostalCode.setError(errorFieldRequired);
            focusView = mPostalCode;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            Customer customer = new Customer(email,password,firstName,lastName,address,province,city,postalCode);
            mCustomerLab.addCustomer(customer);
            Toast.makeText(this, R.string.message_registration_complete, Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }


    private boolean isEmailValid(String email) {
        return email.contains("@") && email.length() > 3;
    }

    private boolean isEmailAvailable(String email) {
        return mCustomerLab.isEmailAvailable(email);
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 3;
    }
}