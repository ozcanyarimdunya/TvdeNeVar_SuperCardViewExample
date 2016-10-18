package semiworld.org.televizyondanevar.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

import semiworld.org.televizyondanevar.Adapter.NowInTvAdapter;
import semiworld.org.televizyondanevar.Class.Program;
import semiworld.org.televizyondanevar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowInTvFragment extends Fragment {

    private RecyclerView recyclerView;
    private NowInTvAdapter adapter;
    private ArrayList<Program> programArrayList;
    private ProgressDialog dialog;
    private final String URL = "http://www.tivigi.com/";
    private String error="";

    public NowInTvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_now_in_tv, container, false);

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView_now_in_tv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);

        //load_data();

        return view;
    }

    private void load_data() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                programArrayList = new ArrayList<>();
                dialog = new ProgressDialog(getContext());
                dialog.setTitle("Loading");
                dialog.setMessage("Please wait ..");
                dialog.setCancelable(false);
                dialog.setIndeterminate(false);
                dialog.show();
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Document document = Jsoup.connect(URL).get();

                    Elements elements = document.select("div[class=row cyya margin-0]");

                    for (Element e : elements) {
                        String logo_url = URL + e.select("div.col-md-2 div.cell div.channel a img").attr("src");
                        String category = e.select("div.col-md-2 div.cell div.type code a").text();
                        String time = e.select("div.col-md-2 div.cell div.time").text();
                        String name = e.select("div.col-md-4 div.cell div.programname a").text();
                        String channel_name = e.select("div.col-md-2 div.cell div.channel a").attr("title");
                        String link = URL + e.select("div.col-md-4 div.cell div.programname a").attr("href");

                        //Program program = new Program(logo_url, name, category, time, channel_name, link);
                        //programArrayList.add(program);
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

                adapter = new NowInTvAdapter(getContext(), programArrayList);
                recyclerView.setAdapter(adapter);
                dialog.dismiss();

                if (!String.valueOf(error).equals(String.valueOf("")))
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        };
        task.execute();
    }
}
