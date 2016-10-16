package semiworld.org.televizyondanevar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import semiworld.org.televizyondanevar.Activity.DetailFullActivity;
import semiworld.org.televizyondanevar.Class.Full;
import semiworld.org.televizyondanevar.R;

/**
 * Author       : l50 - Özcan YARIMDUNYA (@ozcaan11)
 * Created at   : 14.10.2016 - 22:00
 */

public class FullAdapter extends RecyclerView.Adapter<FullAdapter.FilmsViewHolder> {
    private final Context context;
    private final ArrayList<Full> fullArrayList;

    public FullAdapter(Context context, ArrayList<Full> fullArrayList) {
        this.context = context;
        this.fullArrayList = fullArrayList;
    }

    @Override
    public FullAdapter.FilmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_films, parent, false);
        return new FilmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FullAdapter.FilmsViewHolder holder, int position) {
        final Full full = fullArrayList.get(holder.getAdapterPosition());

        holder.name.setText(full.getName());
        holder.time.setText(full.getTime());

        try {
            Glide.with(context).load(full.getPhoto_url()).into(holder.photo);
            Glide.with(context).load(full.getLogo_url()).into(holder.logo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), DetailFullActivity.class);
                intent.putExtra("link", full.getLink());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fullArrayList.size();
    }

    class FilmsViewHolder extends RecyclerView.ViewHolder {
        private final ImageView logo;
        private final ImageView photo;
        private final TextView name;
        private final TextView time;
        private final View view;

        FilmsViewHolder(View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.imageView_film_logo);
            photo = (ImageView) itemView.findViewById(R.id.imageView_film_photo);
            name = (TextView) itemView.findViewById(R.id.textView_film_name);
            time = (TextView) itemView.findViewById(R.id.textView_film_time);
            view = itemView.findViewById(R.id.cv_film);
        }
    }
}
