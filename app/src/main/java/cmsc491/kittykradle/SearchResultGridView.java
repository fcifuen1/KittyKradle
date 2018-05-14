package cmsc491.kittykradle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.TextView;


/**
 * Fragment for grid view in search result
 */
public class SearchResultGridView extends Fragment{



    private CatThumbnail[] thumbnails;
    private int[] slots={
            R.id.thumbnail1,
            R.id.thumbnail2,
            R.id.thumbnail3,
            R.id.thumbnail4,
            R.id.thumbnail5,
            R.id.thumbnail6,
            R.id.thumbnail7,
            R.id.thumbnail8};



    public SearchResultGridView() {
        // Required empty public constructor
    }



    public void setThumbnailFragments(CatThumbnail[] thumbnails){
        this.thumbnails=thumbnails;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            FragmentTransaction ft=getChildFragmentManager().beginTransaction();
            for(int i=0;i<thumbnails.length;i++) {
                ft.replace(slots[i], thumbnails[i]);
            }
            ft.addToBackStack(null).commit();
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result_grid_view, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }







}
