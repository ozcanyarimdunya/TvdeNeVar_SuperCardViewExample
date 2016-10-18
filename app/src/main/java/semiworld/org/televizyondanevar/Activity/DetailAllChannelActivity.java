package semiworld.org.televizyondanevar.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import semiworld.org.televizyondanevar.Adapter.DetailAcAdapter;
import semiworld.org.televizyondanevar.Class.Program;
import semiworld.org.televizyondanevar.R;

public class DetailAllChannelActivity extends AppCompatActivity {

    private TextView channel_name;
    private ImageView channel_logo;
    private RecyclerView recyclerView;
    private ArrayList<Program> programArrayList;
    private ProgressDialog dialog;
    private String URL;
    private String BASE_URL="http://www.hurriyet.com.tr/";
    private String error="",logo_url, ch_name;
    private DetailAcAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_all_channel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView_detail_all_channels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);

        channel_name = (TextView) findViewById(R.id.textView_detail_all_channels_name);
        channel_logo = (ImageView) findViewById(R.id.imageView_detail_all_channels_logo);

        URL = getIntent().getStringExtra("link");

        load_data();
    }

    private void load_data() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                programArrayList = new ArrayList<>();

                dialog = new ProgressDialog(DetailAllChannelActivity.this);
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

                    ch_name = document.select("div.TVCategory div[class=Broadcast FL]").text();
                    logo_url = document.select("div.TVCategory div.FL img").attr("src");

                    Elements elements = document.select("div[class=TVProgram FL] ul[class=content-expanded clr] li[class=ProgramFilmExpanded FL]");
                    for (Element e:elements){
                        String l = BASE_URL+e.select("a.ProgramDetailLink").attr("href");
                        String t = e.select("div").first().text();
                        String pu = e.select("div[class=image FL] img").attr("src");
                        String n = e.select("div.FL div[class=TimelineTitle FL] div.TimelineName").text();

                        e.select("strong").remove();
                        String lt = e.select("div[class=TimelineTitle-2 clear] div.TimelineDuration").text();
                        String c = e.select("div[class=TimelineTitle-2 clear] div.TimelineGenre").text();
                        String s = e.select("div[class=TimelineTitle-2 clear] div.TimelineSummary").text();

                        Program program = new Program(pu,n,c,lt,l,t,s);
                        programArrayList.add(program);

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

                channel_name.setText(ch_name);
                Glide.with(getApplicationContext()).load(logo_url).into(channel_logo);

                adapter = new DetailAcAdapter(getApplicationContext(), programArrayList);
                recyclerView.setAdapter(adapter);
                dialog.dismiss();

                if (!String.valueOf(error).equals(String.valueOf("")))
                    Toast.makeText(DetailAllChannelActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        };
        task.execute();
    }
}
