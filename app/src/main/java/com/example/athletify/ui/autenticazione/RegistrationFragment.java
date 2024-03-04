package com.example.athletify.ui.autenticazione;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

public class RegistrationFragment extends Fragment {

    private UserViewModel userViewModel;

    public RegistrationFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IUserRepository userRepository = new UserRepository(requireActivity().getApplication());
        userViewModel = new ViewModelProvider(this,
                new UserViewModelFactory(requireActivity().getApplication(), userRepository)).get(UserViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registrazione, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText emailEditText = view.findViewById(R.id.email);
        EditText passwordEditText = view.findViewById(R.id.password);
        Button registrationButton = view.findViewById(R.id.registration);

        registrationButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            register(email, password);
        });
    }

    private void register(String email, String password) {
        userViewModel.createUser(email, password).observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse != null && loginResponse.isSuccess()) {
                    NavHostFragment.findNavController(RegistrationFragment.this).
                            navigate(R.id.action_registrationFragment_to_mainActivity);
                    userViewModel.setLogged(true);
                } else {
                    userViewModel.setLogged(false);
                    showErrorMessage();
                }
            }
        });
    }

    private void showErrorMessage() {
        Snackbar.make(requireView(), R.string.registration_failed, Snackbar.LENGTH_SHORT).show();
    }
}
