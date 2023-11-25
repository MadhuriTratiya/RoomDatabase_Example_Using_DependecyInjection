package madhuri.com.musicplayerapp.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import madhuri.com.musicplayerapp.R;


public class SignupFragment extends Fragment {

    private TextView allreadyhaveanaccount;
    private FrameLayout frameLayout;

    private EditText userName;
    private EditText email;
    private EditText password;
    private EditText confirmpassword;
    private Button signUpButton;
    private ProgressBar signupprogressbar;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        allreadyhaveanaccount = view.findViewById(R.id.allreadyhaveanaccount);
        frameLayout = getActivity().findViewById(R.id.register_frame_layout);

        userName = view.findViewById(R.id.username);
        email = view.findViewById(R.id.emailid);
        password = view.findViewById(R.id.password);
        confirmpassword = view.findViewById(R.id.confirmpassword);
        signUpButton = view.findViewById(R.id.signupButton);
        signupprogressbar = view.findViewById(R.id.signupprogressbar);

        //Initialize FireBase Authentication
        mAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new SignupFragment());
            }
        });
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpWithFirebase();
                signUpButton.setEnabled(true);
                signUpButton.setTextColor(getResources().getColor(R.color.transwhite));
            }

            private void signUpWithFirebase() {
                if(email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                    signupprogressbar.setVisibility(View.VISIBLE);
                    if(password.getText().toString().equals(confirmpassword.getText().toString())){
                        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        signupprogressbar.setVisibility(View.INVISIBLE);
                                        if(task.isSuccessful()){
                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                            getActivity().startActivity(intent);
                                            getActivity().finish();
                                        }
                                        else{
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                            signUpButton.setEnabled(true);
                                            signUpButton.setTextColor(getResources().getColor(R.color.white));
                                        }
                                    }
                                });
                    }
                    else {
                        confirmpassword.setError("Password doesn't match");
                        signUpButton.setEnabled(true);
                        signUpButton.setTextColor(getResources().getColor(R.color.white));
                    }
                }
                else{
                    email.setError("Invalid Email!");
                    signUpButton.setEnabled(true);
                    signUpButton.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

    }
    @SuppressLint("ResourceType")
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_right,R.anim.out_from_left);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
    private void checkInputs(){
        if(!userName.getText().toString().isEmpty()){
            if(!email.getText().toString().isEmpty()){
                if(!password.getText().toString().isEmpty() && password.getText().toString().length() >=6){
                    if(!confirmpassword.getText().toString().isEmpty()){
                        signUpButton.setEnabled(true);
                        signUpButton.setTextColor(getResources().getColor(R.color.white));
                    }
                    else {
                        signUpButton.setEnabled(false);
                        signUpButton.setTextColor(getResources().getColor(R.color.transwhite));
                    }
                }else {
                    signUpButton.setEnabled(false);
                    signUpButton.setTextColor(getResources().getColor(R.color.transwhite));
                }
            }
            else {
                signUpButton.setEnabled(false);
                signUpButton.setTextColor(getResources().getColor(R.color.transwhite));
            }
        }
        else{
            signUpButton.setEnabled(false);
            signUpButton.setTextColor(getResources().getColor(R.color.transwhite));
        }
    }
}