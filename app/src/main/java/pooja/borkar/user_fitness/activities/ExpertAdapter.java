package pooja.borkar.user_fitness.activities;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pooja.borkar.user_fitness.R;
import  pooja.borkar.user_fitness.model.Experts;


public class ExpertAdapter extends RecyclerView.Adapter<ExpertAdapter.ExpertViewHolder> implements Filterable {

    Context context;
    List<Experts> expertsList;
    //list of filtered search
    List<Experts> filteredExpertList;
    private ExpertAdapterListener listener;

    public ExpertAdapter(Context context, List expertsList, ExpertAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.expertsList = expertsList;
        this.filteredExpertList = expertsList;

    }

    @Override
    public ExpertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.recyclerproflist,null);
        return new ExpertViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(ExpertViewHolder holder, int position) {
        Experts experts = filteredExpertList.get(position);
        holder.fName.setText(experts.getName());
      //  holder.phone.setText(experts.getPhone());
       // holder.phone.setTextColor(Color.parseColor("#000000"));
        holder.fName.setTextColor(Color.parseColor("#33b5e5"));
        //load image
        holder.expert_id=experts.getProfessional_id().toString();
       float  d = Float.parseFloat(experts.getRating().toString());
       holder.rb.setRating(d);

        Glide.with(context)
                .load(experts.getImage())
                .into(holder.fImage);
    }
    @Override
    public int getItemCount() {
        return filteredExpertList.size();
    }
    //filter method
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredExpertList = expertsList;

                } else {
                    List filteredList = new ArrayList<>();
                    for (Experts row : expertsList) {


                        //change this to filter according to your case
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filteredExpertList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredExpertList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredExpertList = (ArrayList) filterResults.values;
                notifyDataSetChanged();

            }
        };

    }

    public class ExpertViewHolder extends RecyclerView.ViewHolder {
        TextView fName,phone;
        ImageView fImage;
        String expert_id;
        RatingBar rb;


        public ExpertViewHolder(View itemView) {
            super(itemView);
            fName = itemView.findViewById(R.id.name_rec);
            fImage = itemView.findViewById(R.id.image_rec);
           // phone = itemView.findViewById(R.id.phone_rec);
           rb = itemView. findViewById(R.id.ratingBar1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context mContext = view.getContext();
                    Intent intent = new Intent(mContext,ExpertsPlans.class);
                    intent.putExtra("expert_id",expert_id);
                    mContext.startActivity(intent);

                }
            });

        }
    }

    public interface ExpertAdapterListener {
        void onExpertSelected(Experts expert);
    }
}