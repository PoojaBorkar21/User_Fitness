package pooja.borkar.user_fitness.activities.ui.home;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import  pooja.borkar.user_fitness.R;
import  pooja.borkar.user_fitness.activities.ExpertsType;
import  pooja.borkar.user_fitness.activities.MainActivity;
import pooja.borkar.user_fitness.activities.MySubscribers.MySubscribers;
import  pooja.borkar.user_fitness.activities.myplans.MyPlansActivity;
import  pooja.borkar.user_fitness.constants.Constant;
import  pooja.borkar.user_fitness.model.MySliderList;
import  pooja.borkar.user_fitness.model.User;
import  pooja.borkar.user_fitness.services.RetrofitClient;
import  pooja.borkar.user_fitness.services.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static  pooja.borkar.user_fitness.activities.MainActivity.appPreferences;


public class HomeFragment extends Fragment {
    ViewPager2 viewPager;
    List<MySliderList> mySliderLists;
    List<User> myUserLists;
    MySliderAdapter adapter;
    private static int currentPage = 0;
    private static int NUM_PAGES = 3;
    LinearLayout indicatorlay;
    ServiceApi serviceApi;
    TextView text_welcome;
    TextView expert;
    ImageView expertImageView,myplans,mysubsplans;

    public ArrayList<String> banner_user = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.viewPager);

        indicatorlay = view.findViewById(R.id.lay_indicator);
        expert=view.findViewById(R.id.expert);
        expertImageView=view.findViewById(R.id.expertImageView);
        myplans=view.findViewById(R.id.myplans);
        mysubsplans=view.findViewById(R.id. mysubsplans);
        expertImageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             // Intent intent=new Intent(getActivity(), YogaProfessional.class);
              //startActivity(intent);
              Intent intent=new Intent(getActivity(), ExpertsType.class);
              startActivity(intent);
          }

      });
        mysubsplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent=new Intent(getActivity(), YogaProfessional.class);
                //startActivity(intent);
                Intent intent=new Intent(getActivity(), MySubscribers.class);
                startActivity(intent);
            }

        });
        myplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent=new Intent(getActivity(), YogaProfessional.class);
                //startActivity(intent);
                Intent intent=new Intent(getActivity(), MyPlansActivity.class);
                startActivity(intent);
            }

        });

        getdata();


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setupCurrentIndicator(position);
            }
        });
        //NUM_PAGES =onBordingLists.size();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        return view;
    }

    public void getdata() {
        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        Call<List<MySliderList>> call=serviceApi.getonbordingdata();
        call.enqueue(new Callback<List<MySliderList>>() {
            @Override
            public void onResponse(@NonNull Call<List<MySliderList>> call, @NonNull Response<List<MySliderList>> response) {
                if(response.isSuccessful())
                {
                    mySliderLists=response.body();
                }

                int slide_size = mySliderLists.size();
                if(slide_size>4)
                {
                    slide_size=4;
                }else
                {
                    slide_size=mySliderLists.size();
                }
                int slide_count=0;

                while (slide_size!=0)
                {
                    banner_user.add(mySliderLists.get(slide_count).getAdminBanerUrl());
                    slide_count++;
                    slide_size--;
                }
                System.out.println(mySliderLists+"++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(banner_user+"+BANNER+++++++++++++++++++++++++++++++++++++++++++");

                adapter=new MySliderAdapter(getActivity(),mySliderLists,viewPager);
                viewPager.setAdapter(adapter);
                setupIndicator();
                setupCurrentIndicator(0);
            }


            @Override
            public void onFailure(Call<List<MySliderList>> call, Throwable t) {
                System.out.println(mySliderLists+"++++++++++++++++++++++++++++++++++++++++++++");
            }
        });
    }


    public void setupIndicator()
    {
        ImageView[] indicator=new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(4,0,4,0);
        for (int i=0; i<indicator.length; i++)
        {
            indicator[i]=new ImageView(getActivity());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.indicator_active));
            indicator[i].setLayoutParams(layoutParams);
            indicatorlay.addView(indicator[i]);
        }
    }

    public void setupCurrentIndicator(int index) {
        int itemcildcount=indicatorlay.getChildCount();
        for (int i=0; i<itemcildcount; i++){
            ImageView imageView=(ImageView)indicatorlay.getChildAt(i);
            if (i==index){
                imageView.setBackgroundResource(R.drawable.indicator_active);
                //imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.indicator_active));
            }else {
                imageView.setBackgroundResource(R.drawable.indicator_inactive);
                //imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.indicator_inactive));
            }
        }
    }
    }
