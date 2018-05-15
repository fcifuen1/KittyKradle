package cmsc491.kittykradle;

import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CatProfileActivity extends SidebarActivity
{
    private ImageView img;
    private String imageUrl,catName, catId, textColor, selectedBg;
    private Handler handler;
    private TextView catNameText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_profile);

        // Initialize
        img = (ImageView) findViewById(R.id.catImage);
        handler = new Handler();
        catNameText = findViewById(R.id.catName);

        // Get Information from other activity
        imageUrl = getIntent().getExtras().getString("imageUrl");
        catName = getIntent().getExtras().getString("catName");
        catId = getIntent().getExtras().getString("catId");
        textColor = getIntent().getExtras().getString("textColor");
        selectedBg = getIntent().getExtras().getString("selectedBg");

        // Set Cat Name
        handler.post(new Runnable(){
            @Override
            public void run() {
                catNameText.setText(catName);
                catNameText.setBackgroundColor(Integer.parseInt(textColor));
            }
        });

        // Set image
        new DownloadImageTask().execute(imageUrl);

    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... imageUrl) {
            String url=imageUrl[0];
            return download_Image(url);
        }

        protected void onPostExecute(Bitmap result) {
            img.setImageBitmap(result);
        }

        private Bitmap download_Image(String url) {
            Bitmap bmp =null;
            try{
                URL ulrn = new URL(url);
                HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
                InputStream is = con.getInputStream();
                bmp = BitmapFactory.decodeStream(is);
                if (null != bmp)
                    return bmp;

            }catch(Exception e){}
            return bmp;
        }
    }
}
