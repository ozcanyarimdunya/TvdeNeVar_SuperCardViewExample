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

public class DetailAcActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DetailAcAdapter adapter;
    private ArrayList<Program> programArrayList;
    private ProgressDialog dialog;
    private final String URL = "http://www.tivigi.com/";
    private String link_url;
    private ImageView imageView;
    private TextView textView;
    private String description, image_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ac);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.imageView_detail_ac_logo);
        textView = (TextView) findViewById(R.id.textView_detail_ac_description);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView_detail_ac);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);

        link_url = getIntent().getStringExtra("link");
        image_url = getIntent().getStringExtra("logo_url");

        load_data();

    }

    private void load_data() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                programArrayList = new ArrayList<>();

                dialog = new ProgressDialog(DetailAcActivity.this);
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
                    Document document = Jsoup.connect(link_url).get();

                    Elements elements = document.select("div[class^=row cyya margin-0]");

                    for (Element e : elements) {
                        String logo_url = URL + e.select("div.col-md-2 div.cell div.channel a img").attr("src");
                        String category = e.select("div.col-md-2 div.cell div.type code a").text();
                        String time = e.select("div.col-md-2 div.cell div.time").text();
                        String name = e.select("div.col-md-4 div.cell div.programname a").text();
                        String link = URL + e.select("div.col-md-4 div.cell div.programname a").attr("href");

                        Program program = new Program();
                        program.setLogo_url(logo_url);
                        program.setProgram_start_time(time);
                        program.setProgram_category(category);
                        program.setProgram_name(name);
                        program.setLink(link);

                        programArrayList.add(program);
                    }


                    description = document.select("div[class=col-lg-4 col-md-12 col-sm-12] p").first().text();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                textView.setText(description);
                Glide.with(getApplicationContext()).load(image_url).into(imageView);

                adapter = new DetailAcAdapter(getApplicationContext(), programArrayList);
                recyclerView.setAdapter(adapter);
                dialog.dismiss();
            }
        };
        task.execute();
    }
}
