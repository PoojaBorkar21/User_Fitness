package anuja.divekar.userappfitness.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import anuja.divekar.userappfitness.R;


public class AppPreference {

    SharedPreferences sharedPreferences; //storing some info
    SharedPreferences.Editor editor;
    Context context;
    private static final String PREF_NAME = "snow-intro-slider";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";




    public AppPreference(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(String.valueOf(R.string.s_pref_file),context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setLoginStatus( boolean status)
    {
        editor.putBoolean(String.valueOf(R.string.s_pref_login_status),status);
        editor.commit();

    }
    public boolean getLoginStatus()
    {
        return sharedPreferences.getBoolean(String.valueOf(R.string.s_pref_login_status),false);

    }
    //Name
    public void setDiaplayName(String name)
    {

        editor.putString(String.valueOf(R.string.s_pref_login_name),name);
        editor.commit();

    }
    public String getDiaplayName()
    {

        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_name),"name");
    }



    //Email
    public void setDiaplayEmail(String email)
    {

        editor.putString(String.valueOf(R.string.s_pref_login_email),email);
        editor.commit();

    }
    public String getDiaplayEmail()
    {

        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_email),"email");
    }

    //Date
    public void setDiaplayDate(String date)
    {

        editor.putString(String.valueOf(R.string.s_pref_login_date),date);
        editor.commit();

    }
    public String getDiaplayDate()
    {

        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_date),"date");
    }
    public void setDiaplayStatus(String status)
    {

        editor.putString(String.valueOf(R.string.s_pref_login_date),status);
        editor.commit();

    }
    public String getDiaplayStatus()
    {

        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_date),"status");
    }


    public void showToast(String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}