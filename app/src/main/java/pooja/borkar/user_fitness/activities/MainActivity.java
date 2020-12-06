package pooja.borkar.user_fitness.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import pooja.borkar.user_fitness.R;
import pooja.borkar.user_fitness.constants.Constant;
import pooja.borkar.user_fitness.extras.AppPreference;
import pooja.borkar.user_fitness.fragment.LoginFragment;
import pooja.borkar.user_fitness.fragment.RegistrationFragment;
import pooja.borkar.user_fitness.services.MyInterface;
import pooja.borkar.user_fitness.services.RetrofitClient;
import pooja.borkar.user_fitness.services.ServiceApi;

public class MainActivity extends AppCompatActivity implements MyInterface {
    public static AppPreference appPreferences;
    public static ServiceApi serviceApi;
    FrameLayout container_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container_fragment=findViewById(R.id.fragment_container);
        appPreferences=new AppPreference(this);
        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        if(container_fragment!=null)
        {
            if(savedInstanceState!=null)
                if(savedInstanceState!=null)
                {
                    return;
                }
            if(appPreferences.getLoginStatus())
            {

                startActivity(new Intent(this, Home.class));

            }
            else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LoginFragment()).commit();

            }
        }


    }

    @Override
    public void register() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegistrationFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void login(String name, String email, String created_at) {
        appPreferences.setDiaplayEmail(email);
        appPreferences.setDiaplayName(name);
        appPreferences.setDiaplayDate(created_at);


        Intent intent=new Intent(this, Home.class);
        startActivity(intent);


        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment())
        // .addToBackStack(null)
        //.commit();

    }

    @Override
    public void logout() {
        appPreferences.setLoginStatus(false);
        appPreferences.setDiaplayName("Name");
        appPreferences.setDiaplayEmail("Email");
        appPreferences.setDiaplayDate("Date");

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LoginFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();

        if (manager.getBackStackEntryCount() > 0) {

            manager.popBackStack();

        } else {
            // Otherwise, ask user if he wants to leave :)
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton("no", null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            finishAffinity();
                            finish();
                            moveTaskToBack(true);
                        }
                    }).create().show();
        }
    }
    //@Override
    //public void verify() {
    // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new OtpVerifyFragment())
    //   .addToBackStack(null)
    //     .commit();
    //}
}