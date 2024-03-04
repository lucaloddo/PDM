package com.example.athletify.ui.autenticazione;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.athletify.R;
import com.example.athletify.models.LoginResponse;
import com.example.athletify.repositories.user.IUserRepository;
import com.example.athletify.repositories.user.UserRepository;
import com.example.athletify.viewmodels.UserViewModel;
import com.example.athletify.viewmodels.UserViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment {

    private UserViewModel userViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IUserRepository userRepository = new UserRepository(requireActivity().getApplication());
        userViewModel = new ViewModelProvider(this,
                new UserViewModelFactory(requireActivity().getApplication(), userRepository)).get(UserViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText emailEditText = view.findViewById(R.id.email);
        EditText passwordEditText = view.findViewById(R.id.password);
        TextView loginButton = view.findViewById(R.id.login);

        TextView registerTextView = view.findViewById(R.id.registerTextView);

        loginButton.setOnClickListener((View v) -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            login(email, password);
        });


        registerTextView.setOnClickListener(v -> {
            NavHostFragment.findNavController(LoginFragment.this).
                    navigate(R.id.action_loginFragment_to_registrationFragment);
        });
    }

    private void login(String username, String password) {
        userViewModel.getUser(username, password).observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse != null) {
                    if (loginResponse.isSuccess()) {
                        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_mainActivity);
                        userViewModel.setLogged(true);
                    } else {
                        userViewModel.setLogged(false);
                        showErrorMessage();
                    }
                }
            }
        });
    }

    private void showErrorMessage() {
        Snackbar.make(requireView(), R.string.login_failed, Snackbar.LENGTH_SHORT).show();
    }
}
