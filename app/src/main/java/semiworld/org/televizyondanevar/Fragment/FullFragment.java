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

import semiworld.org.televizyondanevar.Adapter.FullAdapter;
import semiworld.org.televizyondanevar.Class.Full;
import semiworld.org.televizyondanevar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Full> fullArrayList;
    private FullAdapter adapter;
    private ProgressDialog dialog;
    private String URL = "http://www.hurriyet.com.tr/tv-rehberi/program-akisi/4/film";
    private final String BASE_URL = "http://www.hurriyet.com.tr";
    private String error ="";

    public FullFragment() {
        // Required empty public constructor
    }

    public FullFragment(String URL) {
        this.URL = URL;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_films, container, false);

        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView_films);
        recyclerView.setLayoutManager(glm);

        load_data();

        return view;
    }

    private void load_data() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                fullArrayList = new ArrayList<>();

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
                    Elements elements = document.select("a[href*=/tv-rehberi/program-detay/]");
                    for (Element e : elements) {
                        String photo_url = e.select("div[class^=image] img").attr("src");
                        String logo_url = e.select("div[class^=image] div.logo img").attr("src");
                        String time = e.select("div[class^=image] div.SubTitle div.time").text();
                        String name = e.select("div[class^=image] img").attr("title");
                        String link = BASE_URL + e.attr("href");

                        Full full = new Full(name, time, link, photo_url, logo_url);
                        fullArrayList.add(full);
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
                adapter = new FullAdapter(getContext(), fullArrayList);
                recyclerView.setAdapter(adapter);
                dialog.dismiss();

                if (!String.valueOf(error).equals(String.valueOf("")))
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        };
        task.execute();
    }

}
