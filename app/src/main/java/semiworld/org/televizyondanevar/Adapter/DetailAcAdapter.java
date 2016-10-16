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

import semiworld.org.televizyondanevar.Activity.DetailNitvActivity;
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
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_detail_ac_br, parent, false);
        return new DetailAcViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailAcViewHolder holder, int position) {
        final Program program = programArrayList.get(holder.getAdapterPosition());

        try {
            holder.name.setText(String.valueOf(program.getProgram_name()));
            holder.time.setText(String.valueOf(program.getProgram_start_time()));
            holder.category.setText(String.valueOf(program.getProgram_category()));
            Glide.with(context).load(String.valueOf(program.getLogo_url())).into(holder.imageView);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        Intent intent = new Intent(view.getContext(), DetailNitvActivity.class);
                        intent.putExtra("link", program.getLink());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

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
        final TextView category;
        final View view;

        DetailAcViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_program_name_nitv);
            time = (TextView) itemView.findViewById(R.id.textView_time_nitv);
            category = (TextView) itemView.findViewById(R.id.textView_category_nitv);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_logo_nitv);
            view = itemView.findViewById(R.id.cv_detail_ac_br);
        }
    }
}
