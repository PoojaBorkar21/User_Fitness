package pooja.borkar.user_fitness.activities.myplans;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.razorpay.Checkout;

import org.json.JSONObject;

import java.util.List;

import pooja.borkar.user_fitness.R;
import pooja.borkar.user_fitness.activities.ExpertProfile.ExpertProfileActivity;
import pooja.borkar.user_fitness.activities.ExpertsPlans;
import pooja.borkar.user_fitness.activities.ExpertsType;
import pooja.borkar.user_fitness.activities.MainActivity;
import pooja.borkar.user_fitness.activities.RecyclerAapter;
import pooja.borkar.user_fitness.model.ExpertPlan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPlanAdapter extends RecyclerView.Adapter<MyPlanAdapter.ViewHolder>  {
    private static final String TAG = MyPlansActivity.class.getSimpleName();
    private final List<ExpertPlan> myplansList;
    private final RecyclerAapter.OnItemClickListener mListener;
    private static Context context;

    public MyPlanAdapter(List<ExpertPlan> myplansList, RecyclerAapter.OnItemClickListener mListener) {
        this.myplansList = myplansList;
        this.mListener = mListener;

    }

    @NonNull
    @Override
    public MyPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myplans_layout, parent, false);
        context = parent.getContext();
        return new MyPlanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPlanAdapter.ViewHolder holder, int position) {
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
       String plan_sups_id,profession,professional_id,image,expert_plans_sups_price;
     ImageView buttonViewOption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.planname);
            exname= itemView.findViewById(R.id.exname);

           //btnsub_delete= itemView.findViewById(R.id.btnsub_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   String name= exname.getText().toString();
                    Context mContext = view.getContext();

                    Intent intent = new Intent(mContext,ExpertProfileActivity.class);
                    intent.putExtra("plan_sups_id",plan_sups_id);
                    intent.putExtra("profession",profession);
                    intent.putExtra("name",name);
                    intent.putExtra("name",name);
                    intent.putExtra("professional_id",professional_id);
                    intent.putExtra("image",image);
                    mContext.startActivity(intent);



                }
            });









        }

        public void bind(int position, RecyclerAapter.OnItemClickListener mListener) {
            tvName.setText(myplansList.get(position).getExpertPlansName());
            tvName.setTextColor(Color.parseColor("#33b5e5"));
            exname.setText(myplansList.get(position).getExpert_name());
            plan_sups_id=myplansList.get(position).getPlan_sups_id().toString();
            expert_plans_sups_price=myplansList.get(position).getExpert_plans_sups_price().toString();
           profession=myplansList.get(position).getProfession().toString();
            professional_id=myplansList.get(position).getProfessional_id().toString();
            image=myplansList.get(position).getImage().toString();
        }
    }

}
