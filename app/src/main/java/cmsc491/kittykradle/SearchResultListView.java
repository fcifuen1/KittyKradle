package cmsc491.kittykradle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * fragment for list view in search result
 */
public class SearchResultListView extends Fragment {

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

    public SearchResultListView() {
        // Required empty public constructor
    }


    public static SearchResultListView newInstance(String param1, String param2) {
        SearchResultListView fragment = new SearchResultListView();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_search_result_list_view, container, false);
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
