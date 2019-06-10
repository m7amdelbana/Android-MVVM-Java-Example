package com.m7amdelbana.mvvmdatabinding.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Objects;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> emailError = new MutableLiveData<>();
    public MutableLiveData<String> passwordError = new MutableLiveData<>();

    private MutableLiveData<User> userMutableLiveData;


    public LoginViewModel() {

    }

    public void onSignInClicked() {
        emailError.setValue(null);
        passwordError.setValue(null);

        User user = new User(email.getValue(), password.getValue());
        if (email.getValue() == null || email.getValue().isEmpty()) {
            emailError.setValue("Enter email address.");
            return;
        }

        if (!user.isEmailValid()) {
            emailError.setValue("Enter a valid email address.");
            return;
        }

        if (password.getValue() == null || password.getValue().isEmpty()) {
            passwordError.setValue("Enter password.");
            return;
        }

        if (!user.isPasswordLengthGreaterThan5()) {
            passwordError.setValue("Password Length should be greater than 5.");
            return;
        }

        userMutableLiveData.setValue(user);
    }

    LiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }
}
