package worldline.ssm.rd.ux.wltwitter;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class WLTwitterLoginActivity extends Activity implements OnClickListener {

    // The EditText in which the user type login
    private EditText mLoginEditText;

    // The EditText in which the user type password
    private EditText mPasswordEditText;


    protected void onResume() {
        super.onResume();
        mLoginEditText = (EditText) findViewById(R.id.loginEditText);
        mLoginEditText.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load our XML layout to display GUI
        setContentView(R.layout.activity_login);

        // Keep a reference to the EditText
        mLoginEditText = (EditText) findViewById(R.id.loginEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);

        // Now add a listener when we click on the Login button
        findViewById(R.id.loginButton).setOnClickListener(this);

        final String storedLogin = PreferenceUtils.getLogin();
        final String storedPassword = PreferenceUtils.getPassword();
        if ((!TextUtils.isEmpty(storedLogin)) && (!TextUtils.isEmpty(storedPassword))) {
            final Intent homeIntent = getHomeActivityIntent(storedLogin);
            startActivity(homeIntent);
        }
    }

    @Override
    public void onClick(View view) {
        // Check if a login is set
        if (TextUtils.isEmpty(mLoginEditText.getText())) {
            // Display a Toast to the user
            Toast.makeText(this, R.string.error_no_login, Toast.LENGTH_LONG).show();
            return;
        }

        // Check if a password is set
        if (TextUtils.isEmpty(mPasswordEditText.getText())) {
            // Display a Toast to the user
            Toast.makeText(this, R.string.error_no_password, Toast.LENGTH_LONG).show();
            return;
        }

        // Before launching the second Activity, just save the values in SharedPreferences
        PreferenceUtils.setLogin(mLoginEditText.getText().toString());
        PreferenceUtils.setPassword(mPasswordEditText.getText().toString());

        // Here we are, a login and password are set, try to login
        // For now just launch the second activity, to do that create an Intent
        final Intent homeIntent = getHomeActivityIntent(mLoginEditText.getText().toString());
        startActivity(homeIntent);
    }

    private Intent getHomeActivityIntent(String userName) {
        final Intent intent = new Intent(this, WLTwitterActivity.class);
        final Bundle extras = new Bundle();
        extras.putString(Constants.Login.EXTRA_LOGIN, userName);
        intent.putExtras(extras);
        return intent;
    }

}

