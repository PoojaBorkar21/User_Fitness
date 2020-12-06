package pooja.borkar.user_fitness.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import  pooja.borkar.user_fitness.R;
import  pooja.borkar.user_fitness.model.Experts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpertsList extends AppCompatActivity implements ExpertAdapter.ExpertAdapterListener{

    RecyclerView recyclerView;
    List expertsList;
    ExpertAdapter adapter;
    SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experts_list);

        //finding views
        recyclerView = findViewById(R.id.recyclerprof);
        recyclerView.setHasFixedSize(true);





        //item decorator to separate the items
       // recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        //setting layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // toolbar fancy stuff
        getSupportActionBar().setTitle("Search Here");

        // white background notification bar
        whiteNotificationBar(recyclerView);

        //initialize fruits list
        expertsList = new ArrayList<>();



        adapter = new ExpertAdapter(this,expertsList,this);

        //method to load fruits
        getData();
        //onItemClickListener


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        if(item.getItemId()== android.R.id.home) {

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }




    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setSystemUiVisibility(0);
        }
    }



    //loading data from database.
    private void getData() {
        Intent intent = getIntent();

        String experties_type_name = intent.getStringExtra("type");

        Call<List<Experts>> call = MainActivity.serviceApi.expertlist(experties_type_name);
        call.enqueue(new Callback<List<Experts>>() {
            @Override
            public void onResponse(Call<List<Experts>> call, Response<List<Experts>> response) {
                if(response.isSuccessful()) {
                    List<Experts> expertList = response.body();


                    //looping through all the heroes and inserting the names inside the string array
                    for (int i = 0; i < expertList.size(); i++) {
                        String professional_id = expertList.get(i).getProfessional_id();
                        String name = expertList.get(i).getName();
                        String image = expertList.get(i).getImage();
                        String phone = expertList.get(i).getPhone();
                        String rating = expertList.get(i).getRating();


                        expertsList.add(new Experts(professional_id, name, phone, image,rating));
                        System.out.println(expertsList + "+++++++++++++++++++++++++$expertList???????");
                    }
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Experts>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public void onExpertSelected(Experts expert) {

    }
}