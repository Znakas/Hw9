package com.example.moti.homework9;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements LoginEventHandler, RegisterEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            commitHeadlessFragment();
            commitLoginFragment();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void commitLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, loginFragment, "login")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void commitRegisterFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, registerFragment, "register")
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void commitHeadlessFragment() {
        RetainInstanceFragment dataFragment = new RetainInstanceFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(dataFragment, "headless")
                .commit();
    }

    @Override
    public void onBtnRegisterPressed() {
        commitRegisterFragment();
    }

    @Override
    public void onBtnLoginPressed(String login, String password) {
        if (!getDataFragment().isUserRegistered(login, password))
            new AlertDialog
                    .Builder(this)
                    .setTitle(R.string.alert_loginfail_title)
                    .setMessage(R.string.alert_loginfail_message)
                    .setPositiveButton(R.string.alert_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        else {
            User user = getDataFragment().getCurrent();

            String welcome = getText(R.string.alert_loginsuccess_message).toString();
            welcome += user.getGender().equals("male") ? " Mr. " : " Mrs. ";
            welcome += user.getFirstName() + " " + user.getLastName();

            new AlertDialog
                    .Builder(this)
                    .setTitle(R.string.alert_loginsuccess_title)
                    .setMessage(welcome)
                    .setPositiveButton(R.string.alert_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }
    }

    public RetainInstanceFragment getDataFragment() {
        return (RetainInstanceFragment) getSupportFragmentManager().findFragmentByTag("headless");
    }

    @Override
    public void onBtnConfirmPressed(User user) {
        Bundle dialogArgs = new Bundle();
        RegistrationDialogFragment dialogFragment = new RegistrationDialogFragment();

        if (getDataFragment().isUserRegistered(user)) {

            dialogArgs.putString(RegistrationDialogFragment.MESSAGE_ID,
                    user.getFirstName() + " " + user.getLastName() + " \nalready exists");
            dialogFragment.setArguments(dialogArgs);
            dialogFragment.show(getFragmentManager(), "dialogFragment");

        } else {

            getDataFragment().addUser(user);

            dialogArgs.putString(RegistrationDialogFragment.MESSAGE_ID,
                    user.getFirstName() + " " + user.getLastName() + " \nregistered");
            dialogFragment.setArguments(dialogArgs);
            dialogFragment.show(getFragmentManager(), "dialogFragment");

            onBackPressed();

        }
    }

}