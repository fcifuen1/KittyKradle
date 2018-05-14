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


public class SearchResultActivity extends SidebarActivity implements View.OnClickListener,CatThumbnail.OnFragmentInteractionListener {
    private final int catsPerPage = 8; //don't change this until you implement dynamic layout for result view
    private int currentPage;
    private SearchResultViewModel viewModel;
    private Cat[] catsFound;
    private Button changeViewButton;
    private Button prevPageButton;
    private Button nextPageButton;
    private TextView headerTextView;
    private TextView pageTextView;
    private CatThumbnail selectedThumbnail;
    final String GRID_VIEW = "grid view";
    final String LIST_VIEW = "list view";
    private String currentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        viewModel = ViewModelProviders.of(this).get(SearchResultViewModel.class);
        viewModel.QueryDatabase("00000","vy.neko","female","21","small");
        headerTextView=findViewById(R.id.header_message);
        pageTextView=findViewById(R.id.page_text_view);
        changeViewButton = findViewById(R.id.change_view_button);
        prevPageButton = findViewById(R.id.prev_button);
        nextPageButton =  findViewById(R.id.next_button);
        changeViewButton.setOnClickListener(this);
        prevPageButton.setOnClickListener(this);
        nextPageButton.setOnClickListener(this);
        if(savedInstanceState==null){
            currentView=GRID_VIEW;
            currentPage=1;
        }

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
                changeView(currentView,currentPage);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("VIEW", currentView);
        savedInstanceState.putInt("PAGE",currentPage);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentView = savedInstanceState.getString("VIEW");
        currentPage = savedInstanceState.getInt("PAGE");
    }

    private void changeView(String view, int page){

        int firstCatIndex=catsPerPage*(page-1);
        if(firstCatIndex>=catsFound.length||page<=0){
            return;
        }
        int numberOfCats=(catsFound.length-firstCatIndex)>=catsPerPage?catsPerPage:catsFound.length%catsPerPage;

        CatThumbnail[] thumbnails = new CatThumbnail[numberOfCats];
        for (int i = 0; i < thumbnails.length; i++) {
            Cat cat = catsFound[firstCatIndex+i];
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
        if(page>1)
            prevPageButton.setVisibility(View.VISIBLE);
        else
            prevPageButton.setVisibility(View.INVISIBLE);
        if(page<(catsFound.length+catsPerPage-1)/catsPerPage)
            nextPageButton.setVisibility(View.VISIBLE);
        else
            nextPageButton.setVisibility(View.INVISIBLE);
        currentPage=page;
        pageTextView.setText(Integer.toString(page));
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
            case R.id.prev_button:
                changeView(currentView,Math.max(1,currentPage-1));
                break;
            case R.id.next_button:
                changeView(currentView,Math.min(currentPage+1,(catsFound.length+catsPerPage-1)/catsPerPage));
                break;
            default:
                break;
        }
    }


}


