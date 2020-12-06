package anuja.divekar.userappfitness.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import anuja.divekar.userappfitness.R;
import anuja.divekar.userappfitness.activities.MainActivity;
import anuja.divekar.userappfitness.model.User;
import anuja.divekar.userappfitness.services.MyInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationFragment extends Fragment {
    EditText nameInput_reg, emailInput_reg, phoneInput_reg, passwordInput_reg, dob_reg, editTextotp;
    Spinner genderInput_reg;
    RadioGroup radioGroup;
    RadioButton radio_female, radio_male;
    Button button_reg, buttonConfirm;
    MyInterface myInterface_reg;
    DatePickerDialog picker;
    TextView gender;
    ImageView imageView;
    String image;


    public static String name;
    public static String phone;
    private LayoutInflater mInflater;
    private static final int IMG_REQUES=777;


    EditText area_interest;


    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        nameInput_reg = view.findViewById(R.id.nameInput);
        emailInput_reg = view.findViewById(R.id.emailInput);
        phoneInput_reg = view.findViewById(R.id.phoneInput);
        passwordInput_reg = view.findViewById(R.id.passwordInput);

       // imageView = view.findViewById(R.id.imageView);
        radio_male = view.findViewById(R.id.radio_male);
        radio_female = view.findViewById(R.id.radio_female);
        radioGroup =(RadioGroup) view.findViewById(R.id.radio_but);

       /* area_interest=view.findViewById(R.id.area_interest);
        area_interest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.area_interest);

            CheckBox doctor=dialog.findViewById(R.id.doctor);
            CheckBox gym=dialog.findViewById(R.id.gym);
            CheckBox yoga=dialog.findViewById(R.id.yoga);

        Button button_ok = dialog.findViewById(R.id.ok);
        Button button_cancle = dialog.findViewById(R.id.cancle);

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(doctor.isChecked())
                {
               String d= doctor.getText().toString();
                    area_interest.setText(d);
                    Toast.makeText(getActivity(), "docotr", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else if(gym.isChecked())
                {
                    String g= gym.getText().toString();
                    area_interest.setText(g);
                    Toast.makeText(getActivity(), "docotr", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }

        });
        dialog.show();
    }
});*/

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int option = radioGroup.getCheckedRadioButtonId();
                //boolean checked = ((RadioButton) view).isChecked();
                switch (option) {

                    case R.id.radio_male:
                        if (radio_male.isChecked()) {
                            break;
                        }

                    case R.id.radio_female:
                        if (radio_female.isChecked()) {
                            break;

                        }
                }
            }
        });

        //  genderInput_reg = (Spinner) view.findViewById(R.id.genderInput);
        //  String [] usertype =
        //         {"Male","Female"};
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, usertype);
        // adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        // genderInput_reg.setAdapter(adapter);
        dob_reg=view.findViewById(R.id.dobInput);

   /*     imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.imageView:
                        selectImage();
                        image = imageToString();
                        break;
                }
            }
        });*/
        dob_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dob_reg.setText( year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                picker.show();
            }
        });


        button_reg=view.findViewById(R.id.regBtn);
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }

            private void registerUser() {
              //  Toast.makeText(getActivity(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                String name=nameInput_reg.getText().toString().trim();
                String email=emailInput_reg.getText().toString().trim();
                String phone=phoneInput_reg.getText().toString().trim();
                String password=passwordInput_reg.getText().toString().trim();
                String dob=dob_reg.getText().toString().trim();
                // String interest=inte.getText().toString().trim();
                // String image = imageToString();


                if(TextUtils.isEmpty(name)){
                    // MainActivity.appPreferences.showToast("Enter your name");
                    Snackbar.make(nameInput_reg,"Enter Your Name",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#20d2bb"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    nameInput_reg.setText("");


                                }
                            }).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    // MainActivity.appPreferences.showToast("Your email is invalid");
                    Snackbar.make(nameInput_reg,"Your Email Is Invalid",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#20d2bb"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    emailInput_reg.setText("");
                                }
                            }).show();
                }
                else if (radioGroup.getCheckedRadioButtonId() == -1)
                {
                    // MainActivity.appPreferences.showToast("Enter your gender");
                    Snackbar.make(nameInput_reg, "Enter Your Gender", Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#20d2bb"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {

                                }
                            }).show();

                }
                else if(TextUtils.isEmpty(dob)){
                    // MainActivity.appPreferences.showToast("Enter your DOb");
                    Snackbar.make(nameInput_reg,"Enter Your Date Of Birth",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#20d2bb"))
                            .setActionTextColor(Color.parseColor("red"))

                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    dob_reg.setText("");

                                }
                            }).show();
                } else if(TextUtils.isEmpty(phone)){
                    // MainActivity.appPreferences.showToast("Enter your phone");
                    Snackbar.make(nameInput_reg,"Enter Your Phone No",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#20d2bb"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }
                                private void clearText1() {
                                    phoneInput_reg.setText("");
                                }
                            }).show();
                } else if (phone.length()>10 || phone.length()<10){

                    //  MainActivity.appPreferences.showToast("Enter correct no");
                    Snackbar.make(nameInput_reg,"Enter Correct Phone No",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#20d2bb"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    phoneInput_reg.setText("");
                                }
                            }).show();
                }
                else if(TextUtils.isEmpty(password)){
                    //MainActivity.appPreferences.showToast("Enter your pass");
                    Snackbar.make(nameInput_reg,"Enter Your Password",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#20d2bb"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {
                                    passwordInput_reg.setText("");

                                }
                            }).show();
                }  else if(password.length()<6)
                {
                    //   MainActivity.appPreferences.showToast("your email length will not match to patern");
                    Snackbar.make(nameInput_reg,"Your password length should be greater than 5 ",Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#20d2bb"))
                            .setActionTextColor(Color.parseColor("red"))
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                    clearText1();
                                }

                                private void clearText1() {

                                    passwordInput_reg.setText("");

                                }
                            }).show();
                }

                else
                {
                    String gender1 = ((RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                    Call<User> userCall= MainActivity.serviceApi.doRegistration(name,email,gender1,dob,password,phone,image);
                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(@NonNull Call<User> call,@NonNull Response<User> response) {

                            if(response.body().getResponse().matches("inserted"))
                            {
                                show_Message("Welcome "+name," Check Mail to Activate Account");

                                // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                            }else if(response.body().getResponse().matches("exists"))
                            {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                                builder1.setMessage("Already Registered User");
                                builder1.setCancelable(true);
                                builder1.setPositiveButton(
                                        "Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                                myInterface_reg.logout();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                                //show_Message("Already registered user!!","Welcome "+name);
                                //  Toast.makeText(getActivity(), "User already exists!!!!", Toast.LENGTH_SHORT).show();
                            }

                            Log.i("My response",response.body().getResponse());
                        }

                        private void show_Message(String title, String input) {
                           final  AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle(title);
                            builder.setMessage(input);
                          //  builder.setCancelable(false);
                            final View customLayout = getLayoutInflater().inflate(R.layout.dialog_verify, null);
                            builder.setView(customLayout);
                            final AlertDialog alertDialog = builder.show();
                            buttonConfirm =  customLayout.findViewById(R.id.buttonConfirm);
                            editTextotp = customLayout.findViewById(R.id.editTextOtp);
                            buttonConfirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                               //     builder.setCancelable(true);
                                    String otp =editTextotp.getText().toString();
                                    Call<User> userCall = MainActivity.serviceApi.doverify(otp);
                                    userCall.enqueue(new Callback<User>() {
                                        @Override
                                        public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                                            if(response.body().getResponse().equals("correct"))
                                            {

                                                MainActivity.appPreferences.setLoginStatus(true);
                                                myInterface_reg.logout();
                                                Toast.makeText(getActivity(), "Verify  Successfull", Toast.LENGTH_SHORT).show();
                                                alertDialog.dismiss();
                                                Snackbar.make(button_reg,"Register Successfull",Snackbar.LENGTH_LONG)
                                                        .setBackgroundTint(Color.parseColor("#20d2bb"))
                                                        .setActionTextColor(Color.parseColor("red"))
                                                        .setAction("", new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                //Toast.makeText(getActivity(), "retry", Toast.LENGTH_SHORT).show();
                                                                // clearText1();
                                                            }

                                                        }).show();
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
                        public void onFailure(Call<User> call, Throwable t) {
                            System.out.println("myerror"+t.getMessage());
                            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }

        });

        return  view;
    }


    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        Activity activity= (Activity) context;
        myInterface_reg= (MyInterface) activity;
    }
}