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
import android.widget.Toast;

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
    private final String URL = "http://www.hurriyet.com.tr/tv-rehberi/tum-programlar";
    private final String BASE_URL = "http://www.hurriyet.com.tr";
    private String error="";

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
                    Document document = Jsoup.connect(URL).get();

                    Elements elements = document.select("div[class=ChannelList FL]");

                    for (Element e : elements) {
                        String logo_url = e.select("a img").attr("src");
                        String name = e.select("a img").attr("alt");
                        String link = BASE_URL+e.select("a").attr("href");

                        Channel channel = new Channel(name, logo_url, link);
                        channelArrayList.add(channel);

                    }

                } catch (IOException e) {
                    error = "İnternet bağlantınızı kontrol edin!";
                    return null;
                }catch (Exception e){
                    error = "Hata oluştu!";
                    return null;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter = new AllChannelsAdapter(getContext(), channelArrayList);
                recyclerView.setAdapter(adapter);
                dialog.dismiss();

                if (!String.valueOf(error).equals(String.valueOf("")))
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        };

        task.execute();

    }

}
