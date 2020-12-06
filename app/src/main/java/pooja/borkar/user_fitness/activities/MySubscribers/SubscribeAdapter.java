package pooja.borkar.user_fitness.activities.MySubscribers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.razorpay.Checkout;

import org.json.JSONObject;

import java.util.List;

import pooja.borkar.user_fitness.R;
import pooja.borkar.user_fitness.activities.MainActivity;
import pooja.borkar.user_fitness.activities.RecyclerAapter;

import pooja.borkar.user_fitness.activities.myplans.MyPlansActivity;
import pooja.borkar.user_fitness.model.ExpertPlan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.ViewHolder>  {
    private static final String TAG = MyPlansActivity.class.getSimpleName();
    private final List<ExpertPlan> myplansList;
    private final RecyclerAapter.OnItemClickListener mListener;
    private static Context context;

    public SubscribeAdapter(List<ExpertPlan> myplansList, RecyclerAapter.OnItemClickListener mListener) {
        this.myplansList = myplansList;
        this.mListener = mListener;

    }

    @NonNull
    @Override
    public SubscribeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mysubplan_layout, parent, false);
        context = parent.getContext();
        return new SubscribeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubscribeAdapter.ViewHolder holder, int position) {
        holder.bind(position, mListener);
    }

    @Override
    public int getItemCount() {
        return myplansList.size();
    }



    public static Context getContext(){
        return context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,exname;
        Button btnsub_delete,btn_payment;
        String plan_sups_id;
        ImageView buttonViewOption;
        String expert_plans_sups_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.planname);
            exname= itemView.findViewById(R.id.exname);

            //btnsub_delete= itemView.findViewById(R.id.btnsub_delete);
            buttonViewOption =  itemView.findViewById(R.id.textViewOptions);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {






                }
            });





            buttonViewOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //creating a popup menu
                    PopupMenu popup = new PopupMenu(view.getContext(), buttonViewOption);
                    //inflating menu from xml resource
                    popup.inflate(R.menu.menu_rec);


                    //adding click listener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu1:
                                    Checkout.preload(view.getContext());
                                    startPayment();



                                    break;
                                case R.id.menu2:

                                    Call<ExpertPlan> call= MainActivity.serviceApi.Del_subscribe_plan(plan_sups_id);
                                    call.enqueue(new Callback<ExpertPlan>() {
                                        @Override
                                        public void onResponse(Call<ExpertPlan> call, Response<ExpertPlan> response) {
                                            if (response.body().getResponse().matches("Deleted"))
                                            {

                                                Toast.makeText(view.getContext(), "UnSubscribed Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                            else if (response.body().getResponse().matches("error"))
                                            {
                                                Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ExpertPlan> call, Throwable t) {
                                            Log.d("Error", t.getMessage());

                                        }
                                    });
                                    break;

                            }
                            return false;
                        }

                        public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
                            final Activity activity = (Activity) context;

                            final Checkout co = new Checkout();

                            try {
                                String email=MainActivity.appPreferences.getDiaplayEmail();
                                JSONObject options = new JSONObject();
                                options.put("name", "FitSpot");
                                //options.put("description", "Demoing Charges");
                                //You can omit the image option to fetch the image from dashboard
                                //options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                                options.put("currency", "INR");
                                int price = (Integer.parseInt(expert_plans_sups_price))*100;
                                options.put("amount",price );

                                JSONObject preFill = new JSONObject();
                                preFill.put("email", email);
                                //preFill.put("contact", "9112740132");

                                options.put("prefill", preFill);

                                co.open(activity, options);
                            } catch (Exception e) {
                                Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                                e.printStackTrace();
                            }
                        }
                    });
                    //displaying the popup
                    popup.show();

                }
            });



        }

        public void bind(int position, RecyclerAapter.OnItemClickListener mListener) {
            tvName.setText(myplansList.get(position).getExpertPlansName());
            tvName.setTextColor(Color.parseColor("#33b5e5"));
            exname.setText(myplansList.get(position).getExpert_name());
            plan_sups_id=myplansList.get(position).getPlan_sups_id().toString();
            expert_plans_sups_price=myplansList.get(position).getExpert_plans_sups_price().toString();

        }
    }

}
