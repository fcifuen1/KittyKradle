package cmsc491.kittykradle;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class SearchResultActivity extends FragmentActivity implements View.OnClickListener,CatThumbnail.OnFragmentInteractionListener{
    private final int catPerPage=8;
    private int currentPage=1;
    private LruCache<String, Bitmap> mMemoryCache;
    private Button changeViewButton;
    private CatThumbnail[] thumbnails = new CatThumbnail[8];
    private CatThumbnail selectedThumbnail;
    private SearchResultGridView gridView;
    private SearchResultListView listView;
    private String currentView;
    final String GRID_VIEW="grid view";
    final String LIST_VIEW="list view";
//test data
    private String[] imgUrls={
            "http://oi1126.photobucket.com/albums/l606/kuochine/angel_zpskxewvgnw.jpg",
            "http://i1126.photobucket.com/albums/l606/kuochine/wombat_zpszfcg8lm8.jpg",
            "http://i1126.photobucket.com/albums/l606/kuochine/butters_zpsr0bl0mev.jpg",
            "http://i1126.photobucket.com/albums/l606/kuochine/joey_zpsniyjhxov.jpeg",
            "http://i1126.photobucket.com/albums/l606/kuochine/nando_zpsqtire7hv.jpg",
            "http://i1126.photobucket.com/albums/l606/kuochine/cucumber_zpsnepyeosq.jpg",
            "http://i1126.photobucket.com/albums/l606/kuochine/jasmine_zpslwaba5va.jpg",
            "http://i1126.photobucket.com/albums/l606/kuochine/paisley_zpseh1v9saj.jpg"};
    private String[] names={"angel","wombat","butter","joey","nando","cucumber","jasmine","paisley"};
    private int[] ids={1,2,3,4,5,6,7,8};
    private String[] genders={"male","female","male","female","male","female","male","female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        changeViewButton = findViewById(R.id.change_view_button);
        changeViewButton.setOnClickListener(this);

        RetainFragment retainFragment =
                RetainFragment.findOrCreateRetainFragment(getSupportFragmentManager());
        mMemoryCache = retainFragment.mRetainedCache;
        if (mMemoryCache == null) {
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;
            mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    // The cache size will be measured in kilobytes rather than
                    // number of items.
                    return bitmap.getByteCount() / 1024;
                }
            };
            retainFragment.mRetainedCache = mMemoryCache;
        }
        for (int i = 0; i < thumbnails.length; i++) {
            thumbnails[i] = new CatThumbnail();
            thumbnails[i].setCat(ids[i],names[i],genders[i],imgUrls[i]);
        }

        gridView = new SearchResultGridView();
        gridView.setThumbnailFragments(thumbnails);
        listView = new SearchResultListView();
        listView.setThumbnailFragments(thumbnails);

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, gridView).commit();
            currentView = "grid view";
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("VIEW", currentView);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        currentView = savedInstanceState.getString("VIEW");

    }

    private void changeView(){
        switch (currentView){
            case GRID_VIEW:
                changeViewButton.setText(currentView);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, listView).commit();
                currentView=LIST_VIEW;
                break;
            case LIST_VIEW:
                changeViewButton.setText(currentView);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, gridView).commit();
                currentView = GRID_VIEW;
                break;
            default:
                break;
        }
        selectedThumbnail=null;
    }

    private void changeResultPage(int page){

    }

    private void startCatInfoActivity(String catId){
        Intent intent = new Intent(getApplicationContext(), CatProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onThumbnailSelected(CatThumbnail ct) {
        if(selectedThumbnail!=null){
            selectedThumbnail.deselect();
        }
        ct.select();
        selectedThumbnail=ct;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.change_view_button:
                changeView();
                break;
            default:
                break;
        }
    }


}


