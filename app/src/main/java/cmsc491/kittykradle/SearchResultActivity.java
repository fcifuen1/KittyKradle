package cmsc491.kittykradle;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class SearchResultActivity extends FragmentActivity implements View.OnClickListener,CatThumbnail.OnFragmentInteractionListener {
    private final int catPerPage = 8;
    private int currentPage = 1;
    private SearchResultViewModel viewModel;
    private Cat[] catsFound;
    private Button changeViewButton;
    private Button prevPageButton;
    private Button nextPageButton;
    private TextView headerTextView;
    private TextView pageTextView;
    private CatThumbnail selectedThumbnail;
    private String currentView;
    final String GRID_VIEW = "grid view";
    final String LIST_VIEW = "list view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        viewModel = ViewModelProviders.of(this).get(SearchResultViewModel.class);
        viewModel.QueryDatabase("00000","vyneko","female","21","small");
        headerTextView=findViewById(R.id.header_message);
        pageTextView=findViewById(R.id.page_text_view);
        changeViewButton = findViewById(R.id.change_view_button);
        prevPageButton = findViewById(R.id.prev_button);
        nextPageButton =  findViewById(R.id.next_button);
        changeViewButton.setOnClickListener(this);
        prevPageButton.setOnClickListener(this);
        nextPageButton.setOnClickListener(this);


        viewModel.getSearchResult().observe(this, new Observer<Cat[]>() {
            @Override
            public void onChanged(@Nullable Cat[] cats) {
                catsFound=cats;
                if (cats == null) {
                    //waiting for cats
                    headerTextView.setText("Looking for cat...");
                    return;
                }
                if(cats.length==0){
                    //no cat found
                    headerTextView.setText("No cat found ):");
                    return;
                }
                //cats are ready
                headerTextView.setText("We found...");
                changeViewButton.setVisibility(View.VISIBLE);
                pageTextView.setVisibility(View.VISIBLE);
                changeView(GRID_VIEW,1);
            }
        });


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

    private void changeView(String view, int page){
        prevPageButton.setVisibility(View.VISIBLE);
        nextPageButton.setVisibility(View.VISIBLE);
        CatThumbnail[] thumbnails = new CatThumbnail[8];
        for (int i = 0; i < thumbnails.length; i++) {
            Cat cat = catsFound[i];
            final CatThumbnail catThumbnail = new CatThumbnail();
            catThumbnail.setCat(cat.getId(),cat.getName(),cat.getGender(),cat.getImgUrl());
            viewModel.getImage(cat.getImgUrl()).observe(catThumbnail, new Observer<Bitmap>() {
                @Override
                public void onChanged(@Nullable Bitmap bitmap) {
                    catThumbnail.setImage(bitmap);
                }
            });
            thumbnails[i]=catThumbnail;
        }

        switch (view){
            case LIST_VIEW:
                changeViewButton.setText(GRID_VIEW);
                SearchResultListView listView = new SearchResultListView();
                listView.setThumbnailFragments(thumbnails);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, listView).commit();
                currentView=LIST_VIEW;
                break;
            case GRID_VIEW:
                changeViewButton.setText(LIST_VIEW);
                SearchResultGridView gridView = new SearchResultGridView();
                gridView.setThumbnailFragments(thumbnails);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, gridView).commit();
                currentView = GRID_VIEW;
                break;
            default:
                break;
        }
        selectedThumbnail=null;
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
                if(currentView==GRID_VIEW){
                    changeView(LIST_VIEW,currentPage);
                }
                else{
                    changeView(GRID_VIEW,currentPage);
                }
                break;
            default:
                break;
        }
    }


}


