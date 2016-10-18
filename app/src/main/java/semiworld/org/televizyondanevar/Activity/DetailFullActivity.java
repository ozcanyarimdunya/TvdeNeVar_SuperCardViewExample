package semiworld.org.televizyondanevar.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import semiworld.org.televizyondanevar.R;

public class DetailFullActivity extends AppCompatActivity {

    private TextView time, long_time, name, category, artists, description;
    private ImageView photo, logo;
    private String url, t, lt, n, c, a, d, lu, pu;
    private ProgressDialog dialog;
    private String error="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_full);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        time = (TextView) findViewById(R.id.textView_detail_full_time);
        long_time = (TextView) findViewById(R.id.textView_detail_full_long_time);
        name = (TextView) findViewById(R.id.textView_detail_full_name);
        category = (TextView) findViewById(R.id.textView_detail_full_category);
        artists = (TextView) findViewById(R.id.textView_detail_full_artists);
        description = (TextView) findViewById(R.id.textView_detail_full_description);
        photo = (ImageView) findViewById(R.id.imageView_detail_full_photo);
        logo = (ImageView) findViewById(R.id.imageView_detail_full_channel_logo);

        url = getIntent().getStringExtra("link");
        load_data();
    }

    private void load_data() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(DetailFullActivity.this);
                dialog.setTitle("Loading");
                dialog.setMessage("Please wait ..");
                dialog.setCancelable(false);
                dialog.setIndeterminate(false);
                dialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Document document = Jsoup.connect(url).get();

                    document.select("strong").remove();

                    pu = document.select("div.TVDetail div[class=image FL] img").attr("src");
                    lu = document.select("div.TVDetail div.FL div[class=logo FL] img").attr("src");
                    t = document.select("div.TVDetail div.FL div[class=title FL] div[class=time FL]").text();
                    n = document.select("div.TVDetail div.FL div[class=title FL] div.name").text();
                    lt = document.select("div.TVDetail div.FL div[class=title-2 clear] div.duration ").text();
                    c = document.select("div.TVDetail div.FL div[class=title-2 clear] div.genre ").first().text();

                    document.select("div.TVDetail div.FL div[class=title-2 clear] div.genre ").first().remove();

                    a = document.select("div.TVDetail div.FL div[class=title-2 clear] div.genre ").first().text();
                    d = document.select("div.TVDetail div[class=content clear]").text();

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
                Glide.with(getApplicationContext()).load(pu).into(photo);
                Glide.with(getApplicationContext()).load(lu).into(logo);

                time.setText(t);
                name.setText(n);
                long_time.setText(lt);
                category.setText(c);
                artists.setText(a);
                description.setText(d);

                dialog.dismiss();

                if (!String.valueOf(error).equals(String.valueOf("")))
                    Toast.makeText(DetailFullActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        };
        task.execute();
    }

}
