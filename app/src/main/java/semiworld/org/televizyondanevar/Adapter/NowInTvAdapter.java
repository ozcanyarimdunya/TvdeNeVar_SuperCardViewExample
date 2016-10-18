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

import semiworld.org.televizyondanevar.Class.Program;
import semiworld.org.televizyondanevar.R;

/**
 * Author       : l50 - Özcan YARIMDUNYA (@ozcaan11)
 * Created at   : 14.10.2016 - 17:33
 */

public class NowInTvAdapter extends RecyclerView.Adapter<NowInTvAdapter.NowInTvViewHolder> {

    private final Context context;
    private final ArrayList<Program> programArrayList;

    public NowInTvAdapter(Context context, ArrayList<Program> programArrayList) {
        this.programArrayList = programArrayList;
        this.context = context;
    }

    @Override
    public NowInTvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_now_in_tv, parent, false);
        return new NowInTvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NowInTvViewHolder holder, int position) {
        final Program program = programArrayList.get(holder.getAdapterPosition());

        try {
            holder.name.setText(String.valueOf(program.getProgram_name()));
            holder.time.setText(String.valueOf(program.getProgram_start_time()));
            holder.category.setText(String.valueOf(program.getProgram_category()));
            //holder.channel_name.setText(String.valueOf(program.getChannel_name()));
            Glide.with(context).load(String.valueOf(program.getPhoto())).into(holder.imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return programArrayList.size();
    }

    class NowInTvViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        final TextView name;
        final TextView time;
        final TextView category;
        final TextView channel_name;
        final View view;

        NowInTvViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.textView_program_name_nitv);
            time = (TextView) itemView.findViewById(R.id.textView_time_nitv);
            category = (TextView) itemView.findViewById(R.id.textView_category_nitv);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_logo_nitv);
            channel_name = (TextView) itemView.findViewById(R.id.textView_channel_name_nitv);
            view = itemView.findViewById(R.id.cv_now_in_tv);
        }
    }

}
