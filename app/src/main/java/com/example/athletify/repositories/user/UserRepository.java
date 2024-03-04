package com.example.athletify.repositories.user;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.example.athletify.models.LoginResponse;
import com.example.athletify.models.User;
import com.example.athletify.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.athletify.utils.Constants.FIREBASE_REALTIME_DB;
import static com.example.athletify.utils.Constants.USER_COLLECTION;

public class UserRepository implements IUserRepository {

    private final Application application;
    private final FirebaseAuth mAuth;
    private MutableLiveData<LoginResponse> loginResultMutableLiveData;
    private final DatabaseReference mDatabase;

    public UserRepository(Application application) {
        this.application = application;
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance(FIREBASE_REALTIME_DB).getReference();
    }

    @Override
    public MutableLiveData<LoginResponse> login(String email, String password) {
        loginResultMutableLiveData = new MutableLiveData<>();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        LoginResponse loginResponse = new LoginResponse();
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            setAuthenticationToken(user.getIdToken(false).getResult().getToken());
                            setUserId(user.getUid());
                            loginResponse.setSuccess(true);
                        } else {
                            loginResponse.setSuccess(false);
                        }
                        loginResultMutableLiveData.postValue(loginResponse);
                    }
                });
        return loginResultMutableLiveData;
    }

    @Override
    public MutableLiveData<LoginResponse> register(String email, String password) {
        loginResultMutableLiveData = new MutableLiveData<>();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        LoginResponse registrationResult = new LoginResponse();
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            setAuthenticationToken(firebaseUser.getIdToken(false).getResult().getToken());
                            User user = new User(email, firebaseUser.getUid());
                            mDatabase.child(USER_COLLECTION).child(user.getId()).setValue(user);
                            setUserId(user.getId());
                            registrationResult.setSuccess(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            registrationResult.setSuccess(false);
                        }
                        loginResultMutableLiveData.postValue(registrationResult);
                    }
                });
        return loginResultMutableLiveData;
    }

    private void setAuthenticationToken(String token) {
        SharedPreferences sharedPref = application.getSharedPreferences(
                Constants.AUTHENTICATION_SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.AUTHENTICATION_TOKEN, token);
        editor.apply();
    }

    private void setUserId(String userId) {
        SharedPreferences sharedPref = application.getSharedPreferences(
                Constants.AUTHENTICATION_SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.USER_ID, userId);
        editor.apply();
    }
}
