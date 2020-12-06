package pooja.borkar.user_fitness.activities;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import  pooja.borkar.user_fitness.R;
import  pooja.borkar.user_fitness.model.ExpertPlan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExpertPlanAdapter extends RecyclerView.Adapter<ExpertPlanAdapter .ViewHolder> {
    private final List<ExpertPlan> professionalsList;
    private final RecyclerAapter.OnItemClickListener mListener;

    public ExpertPlanAdapter(List<ExpertPlan> professionalsList, RecyclerAapter.OnItemClickListener mListener) {
        this.professionalsList = professionalsList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position, mListener);
    }

    @Override
    public int getItemCount() {
        return professionalsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvDuration,tvDetail,tvPrize;
        Button btnsub;
        String professional_id,expert_plans_id,expert_plans_duration;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name_rec);
            //tvDuration= itemView.findViewById(R.id.duration_rec);
            tvDetail= itemView.findViewById(R.id.detail_rec);
            tvPrize= itemView.findViewById(R.id.prize_rec);
              btnsub=itemView.findViewById(R.id.btnsub);
           // btnsub_del=itemView.findViewById(R.id.btnsub_del);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context mContext = view.getContext();






                }
            });

            btnsub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                       String plans_price=tvPrize.getText().toString();

                    String email=MainActivity.appPreferences.getDiaplayEmail();
                    Call<ExpertPlan> call= MainActivity.serviceApi.subscribe_plan(email,professional_id,expert_plans_id,plans_price,expert_plans_duration);
                    call.enqueue(new Callback<ExpertPlan>() {
                        @Override
                        public void onResponse(Call<ExpertPlan> call, Response<ExpertPlan> response) {
                            if (response.body().getResponse().matches("success"))
                            {
                                btnsub.setEnabled(false);
                                Toast.makeText(view.getContext(), "Subscribed Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else if (response.body().getResponse().matches("error"))
                            {
                                Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }

                            else if (response.body().getResponse().matches("exists"))
                            {
                                Toast.makeText(view.getContext(), "Already Subscribed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ExpertPlan> call, Throwable t) {
                            Log.d("Error", t.getMessage());

                        }
                    });

                }
            });




        }

        public void bind(int position, RecyclerAapter.OnItemClickListener mListener) {
            tvName.setText(professionalsList.get(position).getExpertPlansName());
            tvName.setTextColor(Color.parseColor("#33b5e5"));
           expert_plans_duration=professionalsList.get(position).getExpertPlansDuration();
            //tvDuration.setText(professionalsList.get(position).getExpertPlansDuration());
            tvDetail.setText(professionalsList.get(position).getExpertPlansDetails());
            tvPrize.setText("₹ "+professionalsList.get(position).getExpertPlansPrice()+" for "+professionalsList.get(position).getExpertPlansDuration()+" days");
            professional_id=professionalsList.get(position).getProfessional_id().toString();
            expert_plans_id=professionalsList.get(position).getExpert_plans_id().toString();

        }
    }

}
