package pooja.borkar.user_fitness.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import  pooja.borkar.user_fitness.R;
import  pooja.borkar.user_fitness.model.Expart_name;

public class RecyclerAapter extends RecyclerView.Adapter<RecyclerAapter .ViewHolder>  {
    private final List<Expart_name> professionalsList;
    private final OnItemClickListener mListener;

    public RecyclerAapter(List<Expart_name> professionals, OnItemClickListener listener) {
        professionalsList = professionals;
        mListener = listener;
    }



    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.experts_types, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position, mListener);

    }


    @Override
    public int getItemCount()
    {
        return professionalsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        String expertname;
        TextView tvName;




        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name_rec);
           // itemView.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context mContext = view.getContext();

                    final Expart_name expertsType= professionalsList.get(getAdapterPosition());
                    expertname= expertsType.getExperties_type_name().toString();

                    Intent intent = new Intent(mContext, ExpertsList.class);
                    intent.putExtra("type",expertname);

                    mContext.startActivity(intent);


                }
            });
        }

        public void bind(final int position, final OnItemClickListener listener) {
            tvName.setText(professionalsList.get(position).getExperties_type_name());

        }



    }
}
