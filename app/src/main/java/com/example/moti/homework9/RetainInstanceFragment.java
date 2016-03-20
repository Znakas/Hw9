package com.example.moti.homework9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by moti on 3/13/16.
 */
public class RetainInstanceFragment extends Fragment {

    private static final String DATA_KEY = "92f76ad0";
    private HashMap<String, User> dataBase;
    private User current;

    public User getCurrent() {
        return current;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        } else {
            dataBase = new HashMap<>();
        }
        setRetainInstance(true);
        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(DATA_KEY, dataBase);
    }

    @SuppressWarnings("unchecked")
    private void restoreState(Bundle savedInstanceState) {
        try {
            dataBase = (HashMap<String, User>) savedInstanceState.getSerializable(DATA_KEY);
        } catch (ClassCastException e) {
            Log.e("Homework", e.toString());
        }
    }

    public void addUser(User user) {
        dataBase.put(user.getLogin(), user);
        current = user;
    }

    public boolean isUserRegistered(User user) {
        return dataBase.containsKey(user.getLogin());
    }

    public boolean isUserRegistered(String login, String password) {
        return dataBase.containsKey(login) && dataBase.get(login).getPassword().equals(password);
    }
}