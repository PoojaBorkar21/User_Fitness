package pooja.borkar.user_fitness.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pooja.borkar.user_fitness.R;
import  pooja.borkar.user_fitness.constants.Constant;
import  pooja.borkar.user_fitness.model.Expart_name;
import  pooja.borkar.user_fitness.services.RetrofitClient;
import  pooja.borkar.user_fitness.services.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpertsType extends AppCompatActivity {
    RecyclerAapter recyclerAapter;
    ServiceApi serviceApi;
    RecyclerView profRecyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experts_type);

        profRecyclerView =findViewById(R.id.recyclerprof);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        profRecyclerView.setLayoutManager(layoutManager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Experts");

        getData();
    }


    private void getData() {
        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        Call<List<Expart_name>> call= serviceApi.getExpertList();
        call.enqueue(new Callback<List<Expart_name>>() {
            @Override
            public void onResponse(Call<List<Expart_name>> call, Response<List<Expart_name>> response) {

                if(response.isSuccessful()) {
                    List<Expart_name> users = response.body();
                    System.out.println(users + "++++++++++++++++++++++++++++++++++++");

                    profRecyclerView.setAdapter(new RecyclerAapter(users, new RecyclerAapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {


                        }


                    }));
                }
            }


            @Override
            public void onFailure(Call<List<Expart_name>> call, Throwable t) {
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