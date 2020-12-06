package pooja.borkar.user_fitness.activities.ExpertProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.hosseiniseyro.apprating.AppRatingDialog;
import com.hosseiniseyro.apprating.listener.RatingDialogListener;

import java.util.Arrays;
import java.util.List;

import pooja.borkar.user_fitness.R;

import pooja.borkar.user_fitness.activities.ExpertPlanAdapter;
import pooja.borkar.user_fitness.activities.MainActivity;
import pooja.borkar.user_fitness.activities.RecyclerAapter;
import pooja.borkar.user_fitness.constants.Constant;
import pooja.borkar.user_fitness.model.ExpertPlan;
import pooja.borkar.user_fitness.model.User;
import pooja.borkar.user_fitness.services.RetrofitClient;
import pooja.borkar.user_fitness.services.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpertProfileActivity extends AppCompatActivity  implements RatingDialogListener {
TextView expert_profile_name,expert_profile_short_bio;
LinearLayout rate;
ImageView ex_image;
    ExpertTipsAdapter recyclerAapter;
    ServiceApi serviceApi;
    RecyclerView profRecyclerView;
    String plan_sups_id;
    RecyclerView.LayoutManager layoutManager;
    String  professional_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("My Plan");
        rate=findViewById(R.id.rate);
        ex_image=findViewById(R.id.ex_image);
        expert_profile_name=findViewById(R.id.expert_profile_name);
        profRecyclerView =findViewById(R.id.recyclertips);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        profRecyclerView.setLayoutManager(layoutManager);
        expert_profile_short_bio=findViewById(R.id. expert_profile_short_bio);
        Intent intent = getIntent();
      plan_sups_id= intent.getStringExtra("plan_sups_id");
        String profession = intent.getStringExtra("profession");
        String name= intent.getStringExtra("name");
        String image= intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(image).into(ex_image);
       professional_id=intent.getStringExtra("professional_id");
        expert_profile_name.setText(name);
        rate.startAnimation((Animation) AnimationUtils.loadAnimation(ExpertProfileActivity.this,R.anim.transition));
        expert_profile_short_bio.setText(profession);
            getData();
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void getData() {

        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        Call<List<ExpertPlan>> call= serviceApi.getExpertTips(plan_sups_id);
        call.enqueue(new Callback<List<ExpertPlan>>() {
            @Override
            public void onResponse(Call<List<ExpertPlan>> call, Response<List<ExpertPlan>> response) {
                if(response.isSuccessful()) {
                    List<ExpertPlan> users = response.body();
                    System.out.println(users + "++++++++++++++++++++++++++++++++++++");

                    profRecyclerView.setAdapter(new ExpertTipsAdapter(users, new RecyclerAapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {

                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<List<ExpertPlan>> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }
        });

    }

    private void showDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNeutralButtonText("Later")
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(2)
               .setThreshold(5)
                .setTitle("Rate my Coaching")
                .setDescription("Please select some stars and give your feedback")
                .setCommentInputEnabled(true)
               .setDefaultComment("Pretty cool !")
                .setStarColor(R.color.starColor)
                .setNoteDescriptionTextColor(R.color.noteDescriptionTextColor)
                .setTitleTextColor(R.color.titleTextColor)
                .setDescriptionTextColor(R.color.commentTextColor)
                .setHint("Please write your comment here ...")
                .setHintTextColor(R.color.hintTextColor)
                .setCommentTextColor(R.color.commentTextColor)
                .setCommentBackgroundColor(R.color.commentText)
                .setDialogBackgroundColor(R.color.whiteCardColor)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .create(ExpertProfileActivity.this)

                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }

    @Override
    public void onPositiveButtonClickedWithComment(int rate, String comment) {
        Toast.makeText(this, "Rating "+rate+"\ncomment "+comment, Toast.LENGTH_SHORT).show();
String email=MainActivity.appPreferences.getDiaplayEmail();
        System.out.println("+++++++++++++++++++++++++++++++"+professional_id+"professional_id"+rate+"rate"+comment+email);
        Call<ExpertPlan> call = MainActivity.serviceApi.doRating(email,professional_id,rate,comment);
        call.enqueue(new Callback<ExpertPlan>() {
            @Override
            public void onResponse(Call<ExpertPlan> call, Response<ExpertPlan> response) {
                       if(response.body().getResponse().equals("Added"))
                       {
                           Toast.makeText(ExpertProfileActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           Toast.makeText(ExpertProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                       }

            }

            @Override
            public void onFailure(Call<ExpertPlan>call, Throwable t) {
                Log.d("Error", t.getMessage());


            }
        });

    }

    @Override
    public void onPositiveButtonClickedWithoutComment(int i) {

    }
}