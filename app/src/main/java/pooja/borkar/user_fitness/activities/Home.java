package pooja.borkar.user_fitness.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import  pooja.borkar.user_fitness.R;
import pooja.borkar.user_fitness.activities.ui.home.HomeFragment;
import pooja.borkar.user_fitness.activities.ui.profile.ProfileFragment;
import  pooja.borkar.user_fitness.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static  pooja.borkar.user_fitness.activities.MainActivity.appPreferences;


public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView name1,email1;
    ImageView nav_imageView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private SwitchCompat darkModeSwitch;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar_id);
        View header = ((NavigationView)findViewById(R.id.navigationview_id)).getHeaderView(0);
        name1 = ((TextView) header.findViewById(R.id.nav_header_name_id));
        email1  = ((TextView) header.findViewById(R.id.nav_header_emailaddress_id));
        email=appPreferences.getDiaplayEmail();
        nav_imageView = ((ImageView) header.findViewById(R.id.nav_header_circleimageview_id));
        Call<User> call =MainActivity.serviceApi.getAdmin(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                String admin_email=response.body().getEmail().toString().trim();
                String admin_name=response.body().getName().toString().trim();

                name1.setText(admin_name);
                email1.setText(admin_email);
                Glide.with(nav_imageView.getContext()).load(response.body().getImage()).into(nav_imageView);
            }

            @Override
            public void onFailure(Call<User>call, Throwable t) {
                Log.d("Error", t.getMessage());


            }
        });


        initializeViews();
        toggleDrawer();
        initializeDefaultFragment(savedInstanceState,0);


    }

    private void initializeViews() {


        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_id);
        frameLayout = findViewById(R.id.framelayout_id);
        navigationView = findViewById(R.id.navigationview_id);
        navigationView.setNavigationItemSelectedListener(this);

    }


    private void initializeDefaultFragment(Bundle savedInstanceState, int itemIndex){
        if (savedInstanceState == null){
            MenuItem menuItem = navigationView.getMenu().getItem(itemIndex).setChecked(true);
            onNavigationItemSelected(menuItem);
        }
    }

    private void toggleDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }


    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();

        if (manager.getBackStackEntryCount() >1) {

            manager.popBackStack();

        } else {

            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton("no", null)
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            finishAffinity();
                            finish();
                            moveTaskToBack(true);

                        }
                    }).create().show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_profile_id:
                // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                //getSupportActionBar().setDisplayShowHomeEnabled(true);

                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new ProfileFragment()).
                        addToBackStack( null).commit();

                drawerLayout.closeDrawers();

                toolbar.setTitle("Profile");
                closeDrawer();
                break;
            case R.id.nav_home:



                Call<User> call =MainActivity.serviceApi.getAdmin(email);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        String admin_email=response.body().getEmail().toString().trim();
                        String admin_name=response.body().getName().toString().trim();
                        toolbar.setTitle("Welcome "+admin_name);

                    }

                    @Override
                    public void onFailure(Call<User>call, Throwable t) {
                        Log.d("Error", t.getMessage());


                    }
                });

                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id,new HomeFragment()).
                        addToBackStack( null )
                        .commit();

                closeDrawer();
                break;



        }
        return true;
    }





    private void closeDrawer(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private void deSelectCheckedState(){
        int noOfItems = navigationView.getMenu().size();
        for (int i=0; i<noOfItems;i++){
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }
}