package cmsc491.kittykradle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link SearchResultGridView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultGridView extends Fragment{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATID = "catId";

    private String[] mCatId;
    private CatThumbnail[] thumbnails;



    public SearchResultGridView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param catId Parameter 1 array of cat id.
     * @return A new instance of fragment SearchResultGridView.
     */
    public static SearchResultGridView newInstance(String[] catId) {
        SearchResultGridView fragment = new SearchResultGridView();
        Bundle args = new Bundle();
        args.putStringArray(ARG_CATID, catId);
        fragment.setArguments(args);
        return fragment;
    }

    public void setThumbnailFragments(CatThumbnail[] thumbnails){
        this.thumbnails=thumbnails;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCatId = getArguments().getStringArray(ARG_CATID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.thumbnail1, thumbnails[0])
                    .replace(R.id.thumbnail2, thumbnails[1])
                    .replace(R.id.thumbnail3, thumbnails[2])
                    .replace(R.id.thumbnail4, thumbnails[3])
                    .replace(R.id.thumbnail5, thumbnails[4])
                    .replace(R.id.thumbnail6, thumbnails[5])
                    .replace(R.id.thumbnail7, thumbnails[6])
                    .replace(R.id.thumbnail8, thumbnails[7])
                    .addToBackStack(null)
                    .commit();
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
