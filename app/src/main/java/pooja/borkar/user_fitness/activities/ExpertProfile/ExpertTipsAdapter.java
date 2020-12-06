package pooja.borkar.user_fitness.activities.ExpertProfile;

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

import pooja.borkar.user_fitness.R;

import pooja.borkar.user_fitness.activities.MainActivity;


import pooja.borkar.user_fitness.activities.RecyclerAapter;
import pooja.borkar.user_fitness.model.ExpertPlan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpertTipsAdapter extends  RecyclerView.Adapter<ExpertTipsAdapter.ViewHolder> {
    private final List<ExpertPlan> TipsList;
    private final RecyclerAapter.OnItemClickListener mListener;

    public ExpertTipsAdapter(List<ExpertPlan> professionalsList, RecyclerAapter.OnItemClickListener mListener) {
        this.TipsList = professionalsList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ExpertTipsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mytips_layout, parent, false);
        return new ExpertTipsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpertTipsAdapter.ViewHolder holder, int position) {
        holder.bind(position, mListener);
    }

    @Override
    public int getItemCount() {
        return TipsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvtips;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtips = itemView.findViewById(R.id.txt_tips);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context mContext = view.getContext();






                }
            });






        }

        public void bind(int position, RecyclerAapter.OnItemClickListener mListener) {
            tvtips.setText(TipsList.get(position).getExpert_tips_info());

        }
    }

}
