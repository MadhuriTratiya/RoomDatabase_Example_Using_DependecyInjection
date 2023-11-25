package madhuri.com.musicplayerapp.Fragments;

import android.content.Intent;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import madhuri.com.musicplayerapp.R;

public class SigninFragment extends Fragment {

    private TextView donthaveanaccount;
    private TextView resetpassword;
    private FrameLayout frameLayout;

    private EditText signinemail;
    private EditText signinpasssword;
    private Button signinButton;
    private ProgressBar signinprogressbar;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        donthaveanaccount = view.findViewById(R.id.donthaveanaccount);
        resetpassword = view.findViewById(R.id.resetpassword);

        frameLayout = getActivity().findViewById(R.id.register_frame_layout);
        signinemail = view.findViewById(R.id.signinemail);
        signinpasssword = view.findViewById(R.id.signinpassword);
        signinButton = view.findViewById(R.id.signinButton);
        signinprogressbar = view.findViewById(R.id.signinprogressBar);
        mAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        donthaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ResetPasswordFragment());

            }
        });
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ResetPasswordFragment());
            }
        });
        signinemail.addTextChangedListener(new TextWatcher() {
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
        signinpasssword.addTextChangedListener(new TextWatcher() {
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

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinButton.setEnabled(true);
                signinButton.setTextColor(getResources().getColor(R.color.white));
                signInWithFireBase();
            }
        });
    }

    private void signInWithFireBase() {
        if (signinemail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            signinprogressbar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(signinemail.getText().toString(),signinpasssword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            signinprogressbar.setVisibility(View.INVISIBLE);
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getActivity(),MainActivity.class);
                                getActivity().startActivity(intent);
                                getActivity().finish();
                            }
                            else{
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                signinButton.setEnabled(true);
                                signinButton.setTextColor(getResources().getColor(R.color.white));
                            }
                        }
                    });
        } else {
            signinemail.setError("Invalid Email!");
            signinButton.setEnabled(true);
            signinButton.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void checkInputs() {
        if (!signinemail.getText().toString().isEmpty()) {
            if (!signinpasssword.getText().toString().isEmpty()) {
                signinButton.setEnabled(true);
                signinButton.setTextColor(getResources().getColor(R.color.white));
            } else {
                signinButton.setEnabled(false);
                signinButton.setTextColor(getResources().getColor(R.color.transwhite));
            }
        }else
            {
                signinButton.setEnabled(false);
                signinButton.setTextColor(getResources().getColor(R.color.transwhite));
            }
        }
    private void setFragment(ResetPasswordFragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_right,R.anim.out_from_left);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}
