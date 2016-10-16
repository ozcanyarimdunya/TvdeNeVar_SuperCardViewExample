package semiworld.org.televizyondanevar.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import semiworld.org.televizyondanevar.R;

public class DetailNitvActivity extends AppCompatActivity {
    private TextView name;
    private TextView channel;
    private TextView day;
    private TextView time;
    private TextView description;
    private ProgressDialog dialog;
    private String n = "";
    private String c = "";
    private String d = "";
    private String t = "";
    private String ds = "";
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nitv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView) findViewById(R.id.textView_nitvd_program_name);
        channel = (TextView) findViewById(R.id.textView_nitvd_channel_name);
        day = (TextView) findViewById(R.id.textView_nitvd_day);
        time = (TextView) findViewById(R.id.textView_nitvd_time);
        description = (TextView) findViewById(R.id.textView_nitvd_description);

        link = getIntent().getStringExtra("link");

        load_data();

    }

    private void load_data() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(DetailNitvActivity.this);
                dialog.setTitle("Loading");
                dialog.setMessage("Please wait ..");
                dialog.setCancelable(false);
                dialog.setIndeterminate(false);
                dialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Document document = Jsoup.connect(link).get();
                    Elements elements = document.select("div.container div.row div[class=col-lg-4 col-md-12 col-sm-12]");

                    for (Element e : elements) {
                        e.select("p b").remove();

                        n = e.select("p").first().text();
                        e.select("p").first().remove();

                        c = e.select("p").first().text();
                        e.select("p").first().remove();

                        d = e.select("p").first().text();
                        e.select("p").first().remove();

                        t = e.select("p").first().text();
                        e.select("p").first().remove();

                        ds = e.select("p").first().text();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                name.setText(n);
                channel.setText(c);
                day.setText(d);
                time.setText(t);
                description.setText(ds);

                dialog.dismiss();
            }
        };

        task.execute();
    }
}
