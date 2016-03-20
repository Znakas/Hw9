package com.example.moti.homework9;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by moti on 3/13/16.
 */
public class LoginFragment extends Fragment {

    private TextView mTextWelcome;
    private EditText mTextLogin;
    private EditText mTextPassword;
    private TextView mBtnRegister;
    private Button mBtnLogin;

    private LoginEventHandler mLoginEventHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_login, container, false);

        mTextWelcome = (TextView) view.findViewById(R.id.text_welcome_FL);
        mTextLogin = (EditText) view.findViewById(R.id.text_login_FL);
        mTextPassword = (EditText) view.findViewById(R.id.text_password_FL);
        mBtnRegister = (TextView) view.findViewById(R.id.btn_register_FL);
        mBtnLogin = (Button) view.findViewById(R.id.btn_login_FL);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginEventHandler.onBtnRegisterPressed();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginEventHandler.onBtnLoginPressed(
                        mTextLogin.getText().toString(),
                        mTextPassword.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getDataFragment().getCurrent() != null) {
            User user = getDataFragment().getCurrent();

            mTextLogin.setText(user.getLogin());
            mTextPassword.setText(user.getPassword());

            String welcome = getText(R.string.text_welcome).toString();
            welcome += user.getGender().equals("male") ? " Mr. " : " Mrs. ";
            welcome += user.getFirstName() + " " + user.getLastName();
            mTextWelcome.setText(welcome);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoginEventHandler = (LoginEventHandler) context;
    }

    public RetainInstanceFragment getDataFragment() {
        return (RetainInstanceFragment) getFragmentManager().findFragmentByTag("headless");
    }

}
