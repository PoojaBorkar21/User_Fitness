package pooja.borkar.user_fitness.activities.myplans;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.razorpay.PaymentResultListener;

import java.util.List;

import  pooja.borkar.user_fitness.R;
import  pooja.borkar.user_fitness.activities.MainActivity;
import  pooja.borkar.user_fitness.activities.RecyclerAapter;
import  pooja.borkar.user_fitness.constants.Constant;
import  pooja.borkar.user_fitness.model.ExpertPlan;
import  pooja.borkar.user_fitness.services.RetrofitClient;
import pooja.borkar.user_fitness.services.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPlansActivity extends AppCompatActivity  {
    MyPlanAdapter recyclerAapter;
    ServiceApi serviceApi;

    private static final String TAG = MyPlansActivity.class.getSimpleName();
    RecyclerView profRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plans);

        profRecyclerView =findViewById(R.id.recyclerprof);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        profRecyclerView.setLayoutManager(layoutManager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("My Plans");
        getData();
    }

    private void getData() {
       String email= MainActivity.appPreferences.getDiaplayEmail();
        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        Call<List<ExpertPlan>> call= serviceApi.getMyPlan(email);
        call.enqueue(new Callback<List<ExpertPlan>>() {
            @Override
            public void onResponse(Call<List<ExpertPlan>> call, Response<List<ExpertPlan>> response) {
                if(response.isSuccessful()) {
                    List<ExpertPlan> myplans = response.body();


                    profRecyclerView.setAdapter(new MyPlanAdapter(myplans, new RecyclerAapter.OnItemClickListener() {
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





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}