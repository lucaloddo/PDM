package com.example.athletify.ui.autenticazione;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.athletify.R;
import com.example.athletify.repositories.user.IUserRepository;
import com.example.athletify.repositories.user.UserRepository;
import com.example.athletify.viewmodels.UserViewModel;
import com.example.athletify.viewmodels.UserViewModelFactory;

public class MainFragment extends Fragment {

    private IUserRepository userRepository;
    private UserViewModel userViewModel;

    public MainFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userRepository = new UserRepository(requireActivity().getApplication());
        userViewModel = new ViewModelProvider(this, new UserViewModelFactory(requireActivity().getApplication(), userRepository)).get(UserViewModel.class);

        if(userViewModel.getAuthenticationToken() != null) {
            NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_mainActivity);
            requireActivity().finish();
        } else {
            NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_loginFragment);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

}
