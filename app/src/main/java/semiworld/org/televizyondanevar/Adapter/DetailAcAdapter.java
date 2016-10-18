package semiworld.org.televizyondanevar.Adapter;

import android.content.Context;
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
 * Created at   : 16.10.2016 - 03:39
 */

public class DetailAcAdapter extends RecyclerView.Adapter<DetailAcAdapter.DetailAcViewHolder> {

    private final Context context;
    private final ArrayList<Program> programArrayList;

    public DetailAcAdapter(Context context, ArrayList<Program> programArrayList) {
        this.context = context;
        this.programArrayList = programArrayList;
    }

    @Override
    public DetailAcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_detail_all_channel_broadcast, parent, false);
        return new DetailAcViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailAcViewHolder holder, int position) {
        final Program program = programArrayList.get(holder.getAdapterPosition());

        try {
            holder.name.setText(String.valueOf(program.getProgram_name()));
            holder.time.setText(String.valueOf(program.getProgram_time()));
            holder.long_time.setText(String.valueOf(program.getProgram_start_time()));
            holder.category.setText(String.valueOf(program.getProgram_category()));
            Glide.with(context).load(String.valueOf(program.getPhoto())).into(holder.imageView);
            holder.summary.setText(program.getProgram_summary());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return programArrayList.size();
    }

    class DetailAcViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView name;
        final TextView time;
        final TextView long_time;
        final TextView category;
        final TextView summary;

        DetailAcViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_dac_name);
            time = (TextView) itemView.findViewById(R.id.textView_dac_time);
            long_time = (TextView) itemView.findViewById(R.id.textView_dac_long_time);
            category = (TextView) itemView.findViewById(R.id.textView_dac_category);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_dac_photo);
            summary = (TextView) itemView.findViewById(R.id.textView_dac_summary);
        }
    }
}
