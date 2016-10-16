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

import semiworld.org.televizyondanevar.Activity.DetailAcActivity;
import semiworld.org.televizyondanevar.Class.Channel;
import semiworld.org.televizyondanevar.R;

/**
 * Author       : l50 - Özcan YARIMDUNYA (@ozcaan11)
 * Created at   : 14.10.2016 - 20:38
 */

public class AllChannelsAdapter extends RecyclerView.Adapter<AllChannelsAdapter.AllChannelsViewHolder> {

    private final Context context;
    private final ArrayList<Channel> channelArrayList;

    public AllChannelsAdapter(Context context, ArrayList<Channel> channelArrayList) {
        this.context = context;
        this.channelArrayList = channelArrayList;
    }

    @Override
    public AllChannelsAdapter.AllChannelsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_all_channels, parent, false);
        return new AllChannelsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AllChannelsAdapter.AllChannelsViewHolder holder, int position) {
        final Channel channel = channelArrayList.get(holder.getAdapterPosition());


        holder.name.setText(channel.getName().replace("Canlı İzle", ""));
        Glide.with(context).load(channel.getLogo_url()).into(holder.logo);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), DetailAcActivity.class);
                intent.putExtra("logo_url", channel.getLogo_url());
                intent.putExtra("link", channel.getLink());
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return channelArrayList.size();
    }

    class AllChannelsViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        private final TextView name;
        private final ImageView logo;

        AllChannelsViewHolder(View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.cv_all_channels);
            name = (TextView) itemView.findViewById(R.id.textView_channel_name_ac);
            logo = (ImageView) itemView.findViewById(R.id.imageView_logo_ac);
        }
    }
}
