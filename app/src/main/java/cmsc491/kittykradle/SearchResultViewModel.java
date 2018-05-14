package cmsc491.kittykradle;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

/**
 * this class hold the data for SearchResultActivity
 */
public class SearchResultViewModel extends ViewModel {
    private MutableLiveData<Cat[]> searchResult = new MutableLiveData<>();
    //cache up to 24 images, change it if need to
    private LruCache<String, LiveData<Bitmap>> imageCache= new LruCache<String, LiveData<Bitmap>>(24) { };


    public void QueryDatabase(String zipcode, String breed, String sex, String age, String size){
        new QueryServerTask().execute(new Query(zipcode, breed, sex, age, size));
    }

    public LiveData<Cat[]> getSearchResult(){
        return searchResult;
    }

    public LiveData<Bitmap> getImage(String url){
        LiveData<Bitmap> cachedImage =imageCache.get(url);
        if(cachedImage!=null){
            return cachedImage;
        }
        final MutableLiveData<Bitmap> image = new MutableLiveData<Bitmap>();
        imageCache.put(url,image);
        new DownloadImageTask().execute(new ImageRequest(url,image));
        return image;
    }

    private final class ImageRequest{
        final String url;
        final MutableLiveData<Bitmap> data;
        public ImageRequest(String url, MutableLiveData<Bitmap> data){
            this.url = url;
            this.data = data;
        }
    }

    private class DownloadImageTask extends AsyncTask<ImageRequest, Void, Bitmap> {
        private ImageRequest request = null;
        protected Bitmap doInBackground(ImageRequest ... imageRequests) {
            this.request = imageRequests[0];
            String url=request.url;
            return download_Image(url);
        }

        protected void onPostExecute(Bitmap result) {
            request.data.postValue(result);
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

    private class Query{
        final String zipcode;
        final String breed;
        final String sex;
        final String age;
        final String size;
        public Query(String zipcode, String breed, String sex, String age, String size){
            this.zipcode=zipcode;
            this.breed=breed;
            this.sex=sex;
            this.age=age;
            this.size=size;
        }
    }

    private class QueryServerTask extends AsyncTask<Query, String, String>{
        HttpsURLConnection conn;
        URL url = null;
        String urlLink = "???? !!!???";

        @Override
        protected String doInBackground(Query... params) {
            Query query=params[0];
            return "[{\"id\":0,\"name\":\"Angel\",\"gender\":\"male\",\"imgUrl\":\"http://oi1126.photobucket.com/albums/l606/kuochine/angel_zpskxewvgnw.jpg\"},{\"id\":1,\"name\":\"Wombat\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/wombat_zpszfcg8lm8.jpg\"},{\"id\":2,\"name\":\"Butter\",\"gender\":\"male\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/butters_zpsr0bl0mev.jpg\"},{\"id\":3,\"name\":\"Joey\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/joey_zpsniyjhxov.jpeg\"},{\"id\":4,\"name\":\"Nando\",\"gender\":\"male\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/nando_zpsqtire7hv.jpg\"},{\"id\":5,\"name\":\"Cucumber\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/cucumber_zpsnepyeosq.jpg\"},{\"id\":6,\"name\":\"Jasmine\",\"gender\":\"male\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/jasmine_zpslwaba5va.jpg\"},{\"id\":7,\"name\":\"Paisley\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/paisley_zpseh1v9saj.jpg\"},{\"id\":1,\"name\":\"Wombat\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/wombat_zpszfcg8lm8.jpg\"},{\"id\":2,\"name\":\"Butter\",\"gender\":\"male\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/butters_zpsr0bl0mev.jpg\"},{\"id\":3,\"name\":\"Joey\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/joey_zpsniyjhxov.jpeg\"},{\"id\":4,\"name\":\"Nando\",\"gender\":\"male\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/nando_zpsqtire7hv.jpg\"},{\"id\":5,\"name\":\"Cucumber\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/cucumber_zpsnepyeosq.jpg\"},{\"id\":6,\"name\":\"Jasmine\",\"gender\":\"male\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/jasmine_zpslwaba5va.jpg\"},{\"id\":7,\"name\":\"Paisley\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/paisley_zpseh1v9saj.jpg\"}]";
            //return "[{\"id\":0,\"name\":\"Angel\",\"gender\":\"male\",\"imgUrl\":\"http://oi1126.photobucket.com/albums/l606/kuochine/angel_zpskxewvgnw.jpg\"},{\"id\":1,\"name\":\"Wombat\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/wombat_zpszfcg8lm8.jpg\"},{\"id\":2,\"name\":\"Butter\",\"gender\":\"male\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/butters_zpsr0bl0mev.jpg\"},{\"id\":3,\"name\":\"Joey\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/joey_zpsniyjhxov.jpeg\"},{\"id\":4,\"name\":\"Nando\",\"gender\":\"male\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/nando_zpsqtire7hv.jpg\"},{\"id\":5,\"name\":\"Cucumber\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/cucumber_zpsnepyeosq.jpg\"},{\"id\":6,\"name\":\"Jasmine\",\"gender\":\"male\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/jasmine_zpslwaba5va.jpg\"},{\"id\":7,\"name\":\"Paisley\",\"gender\":\"female\",\"imgUrl\":\"http://i1126.photobucket.com/albums/l606/kuochine/paisley_zpseh1v9saj.jpg\"}]";
            /*
            try{
                url = new URL(urlLink + "/kittykradle/Search.php");
                conn = (HttpsURLConnection) url.openConnection();
                conn.setReadTimeout(1000);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("zipcode",query.zipcode)
                        .appendQueryParameter("breed",query.breed)
                        .appendQueryParameter("sex",query.sex)
                        .appendQueryParameter("age",query.age)
                        .appendQueryParameter("size",query.size);
                String encodedQuery = builder.build().getEncodedQuery();
                OutputStream outputpost = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputpost, "UTF-8"));
                writer.write(encodedQuery);
                writer.flush();
                writer.close();
                outputpost.close();
                conn.connect();

                int responseCode = conn.getResponseCode();
                if(responseCode == HttpsURLConnection.HTTP_OK){
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    return(result.toString());
                }else{
                    return ("Bad Connection");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
            */
        }

        @Override
        protected void onPostExecute(String result) {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new StringReader(result));
            reader.setLenient(true);
            Cat[] resultCats = gson.fromJson(reader, Cat[].class);
            searchResult.postValue(resultCats);
        }
    }

}
