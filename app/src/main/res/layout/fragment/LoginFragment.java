package anuja.divekar.userappfitness.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import anuja.divekar.userappfitness.R;
import anuja.divekar.userappfitness.activities.MainActivity;
import anuja.divekar.userappfitness.model.User;
import anuja.divekar.userappfitness.services.MyInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    MyInterface myInterface_login;
    Button loginbtn,buttonConfirm;
    EditText emailInput,passwordInput,editTextotp;
    TextView registerTV,tv_forgot_pass;

    public LoginFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        emailInput=view.findViewById(R.id.emailInput);
        passwordInput=view.findViewById(R.id.passwordInput);
        loginbtn=view.findViewById(R.id.loginBtn);
        registerTV=view.findViewById(R.id.registerTV);
        tv_forgot_pass=view.findViewById(R.id.tv_forgot_pass);

        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                forgotPassword("Welcome "," Enter Email ID below to change Account Password");

            }


        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
            }
        });

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Register", Toast.LENGTH_SHORT).show();
                myInterface_login.register();
            }
        });
        return view;
    }

    private void forgotPassword(String title, String input) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(input);
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_verify_password, null);
        final AlertDialog alertDialog = builder.show();
        builder.setView(customLayout);
        builder.setCancelable(false);
        buttonConfirm =  customLayout.findViewById(R.id.buttonConfirm);
        editTextotp = customLayout.findViewById(R.id.editTextOtp);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =editTextotp.getText().toString();
                Call<User> userCall = MainActivity.serviceApi.forgotPassword(email);
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if(response.body().getResponse().equals("email_send"))
                        {
                            alertDialog.dismiss();
                            Toast.makeText(getActivity(), "Email is send to entered Email ID", Toast.LENGTH_SHORT).show();


                        }
                        else if(response.body().getResponse().equals("email_not_send"))
                        {
                            Toast.makeText(getActivity(), "Email not send,Try again", Toast.LENGTH_SHORT).show();
                            editTextotp.setText("");

                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.println("myerror" + t.getMessage());
                        Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        builder.setPositiveButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                builder.setCancelable(true);


            }
        });
        builder.show();
    }

    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //MainActivity.appPreferences.showToast("Your email is invalid");
            Snackbar.make(emailInput,"Your Email Is Invalid",Snackbar.LENGTH_LONG)
                    .setBackgroundTint(Color.parseColor("#20d2bb"))
                    .setActionTextColor(Color.parseColor("red"))
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                            clearText1();
                        }

                        private void clearText1() {
                            emailInput.setText("");
                        }
                    }).show();
        } else if (TextUtils.isEmpty(password)) {
            //MainActivity.appPreferences.showToast("Enter your password");
            Snackbar.make(passwordInput,"Enter Your Password",Snackbar.LENGTH_LONG)
                    .setBackgroundTint(Color.parseColor("#20d2bb"))
                    .setActionTextColor(Color.parseColor("red"))
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                            clearText1();
                        }

                        private void clearText1() {
                            passwordInput.setText("");
                        }
                    }).show();
        } else if (password.length() < 6) {
           // MainActivity.appPreferences.showToast("your password length will not match to pattern");
            Snackbar.make(passwordInput,"Your Password Length Should Be Greater Than 5 ",Snackbar.LENGTH_LONG)
                    .setBackgroundTint(Color.parseColor("#20d2bb"))
                    .setActionTextColor(Color.parseColor("red"))
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                            clearText1();
                        }

                        private void clearText1() {

                            passwordInput.setText("");

                        }
                    }).show();
        } else {
            Call<User> userCall = MainActivity.serviceApi.doLogin( email, password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if(response.body().getResponse().equals("data"))
                    {
                        MainActivity.appPreferences.setLoginStatus(true);
                        myInterface_login.login(response.body().getName(),response.body().getEmail(),response.body().getCreatedAt());
                        Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_SHORT).show();
                    }
                    else if(response.body().getResponse().equals("login_failed"))
                    {
                        Toast.makeText(getActivity(), "login_failed", Toast.LENGTH_SHORT).show();
                        emailInput.setText("");
                        passwordInput.setText("");
                    }
                    else if(response.body().getResponse().equals("verify_otp")) {
                        show_Message("Already Registered"," Verify otp");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println("myerror" + t.getMessage());
                    Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

    private void show_Message(String title, String input) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(input);
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_verify, null);
        builder.setView(customLayout);
        final AlertDialog alertDialog = builder.show();
        buttonConfirm =  customLayout.findViewById(R.id.buttonConfirm);
        editTextotp = customLayout.findViewById(R.id.editTextOtp);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp =editTextotp.getText().toString();
                Call<User> userCall = MainActivity.serviceApi.doverify(otp);
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if(response.body().getResponse().equals("correct"))
                        {

                            myInterface_login.logout();
                            Toast.makeText(getActivity(), "Verify Successfull", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                        else if(response.body().getResponse().equals("incorrect"))
                        {
                            Toast.makeText(getActivity(), "Wrong Otp", Toast.LENGTH_SHORT).show();
                            editTextotp.setText("");

                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.println("myerror" + t.getMessage());
                        Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        alertDialog.show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity= (Activity) context;
        myInterface_login= (MyInterface) activity;
    }


}