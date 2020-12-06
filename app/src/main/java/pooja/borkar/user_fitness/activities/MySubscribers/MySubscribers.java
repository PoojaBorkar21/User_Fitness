package pooja.borkar.user_fitness.activities.MySubscribers;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.razorpay.PaymentResultListener;

import java.util.ArrayList;
import java.util.List;

import pooja.borkar.user_fitness.R;
import pooja.borkar.user_fitness.activities.MainActivity;
import pooja.borkar.user_fitness.activities.RecyclerAapter;
import pooja.borkar.user_fitness.activities.myplans.MyPlanAdapter;
import pooja.borkar.user_fitness.activities.myplans.MyPlansActivity;
import pooja.borkar.user_fitness.constants.Constant;
import pooja.borkar.user_fitness.model.ExpertPlan;
import pooja.borkar.user_fitness.services.RetrofitClient;
import pooja.borkar.user_fitness.services.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySubscribers extends AppCompatActivity implements PaymentResultListener {
    MyPlanAdapter recyclerAapter;
    ServiceApi serviceApi;

    private static final String TAG = MyPlansActivity.class.getSimpleName();
    RecyclerView profRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysubplans);

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
        Call<List<ExpertPlan>> call= serviceApi.getSubMyPlan(email);
        call.enqueue(new Callback<List<ExpertPlan>>() {
            @Override
            public void onResponse(Call<List<ExpertPlan>> call, Response<List<ExpertPlan>> response) {
                if(response.isSuccessful()) {
                    List<ExpertPlan> myplans = response.body();


                    profRecyclerView.setAdapter(new SubscribeAdapter(myplans, new RecyclerAapter.OnItemClickListener() {
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
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {

            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Toast.makeText(MyPlansActivity.getContext(), "Exception in onPaymentError: " + code + " " + response, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}