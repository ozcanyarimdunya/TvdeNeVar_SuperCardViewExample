package semiworld.org.televizyondanevar.Fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import semiworld.org.televizyondanevar.Adapter.AllChannelsAdapter;
import semiworld.org.televizyondanevar.Class.Channel;
import semiworld.org.televizyondanevar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllChannelsFragment extends Fragment {


    private RecyclerView recyclerView;
    private AllChannelsAdapter adapter;
    private ArrayList<Channel> channelArrayList;
    private ProgressDialog dialog;
    private final String URL = "http://www.tivigi.com/";
    public AllChannelsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_channels, container, false);

        GridLayoutManager glm = new GridLayoutManager(getContext(), 4);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView_all_channels);
        recyclerView.setLayoutManager(glm);
        load_data();

        return view;
    }

    private void load_data() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                channelArrayList = new ArrayList<>();

                dialog = new ProgressDialog(getContext());
                dialog.setTitle("Loading");
                dialog.setMessage("Please wait ..");
                dialog.setCancelable(false);
                dialog.setIndeterminate(false);
                dialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Document document = Jsoup.connect(URL + "kanallar").get();

                    Elements elements = document.select("div.kanallar");

                    for (Element e : elements) {
                        String logo_url = URL + e.select("div.thumbnail a img").attr("src");
                        String name = e.select("div.thumbnail a").attr("title");
                        String link = e.select("div.thumbnail a").attr("href");

                        Channel channel = new Channel(name, logo_url, link);
                        channelArrayList.add(channel);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter = new AllChannelsAdapter(getContext(), channelArrayList);
                recyclerView.setAdapter(adapter);
                dialog.dismiss();
            }
        };

        task.execute();

    }

}
