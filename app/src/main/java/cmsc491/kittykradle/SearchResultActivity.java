package cmsc491.kittykradle;


import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.Button;





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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        changeViewButton = findViewById(R.id.view_button);
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

    }

    @Override
    public void onThumbnailSelect(CatThumbnail ct) {
        if(selectedThumbnail!=null){
            selectedThumbnail.unselect();
        }
        ct.select();
        selectedThumbnail=ct;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.view_button:
                changeView();
                break;
            default:
                break;
        }
    }
}


