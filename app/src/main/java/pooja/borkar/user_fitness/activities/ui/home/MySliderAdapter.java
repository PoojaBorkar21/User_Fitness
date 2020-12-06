package pooja.borkar.user_fitness.activities.ui.home;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;

import java.util.List;

import  pooja.borkar.user_fitness.R;
import  pooja.borkar.user_fitness.model.MySliderList;


public class MySliderAdapter  extends RecyclerView.Adapter<MySliderAdapter.ViewHolder> {
    private List<MySliderList> mySliderLists;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager;
    Context context;

    public MySliderAdapter(Context context, List<MySliderList> mySliderLists, ViewPager2 viewPager) {
        this.mInflater = LayoutInflater.from(context);
        this.mySliderLists = mySliderLists;
        this.viewPager = viewPager;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.slider_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MySliderList ob= mySliderLists.get(position);
        Glide.with(context).load(ob.getAdminBanerUrl()).into(holder.myimage);
        holder.myimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(viewPager.getContext(), R.style.MyDialogTheme);
                final View customLayout = mInflater.inflate(R.layout.banner_dialog, null);
                ImageView imageView =  customLayout.findViewById(R.id.my_image);
                Glide.with(context).load(ob.getAdminBanerUrl()).into(imageView);

                builder.setCancelable(true);
                builder.setView(customLayout)

                        .create().show();

            }
        });
//
    }

    @Override
    public int getItemCount() {
        return mySliderLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView myimage;
        RelativeLayout relativeLayout;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myimage = itemView.findViewById(R.id.myimage);
            relativeLayout = itemView.findViewById(R.id.container);

        }
    }
}
