package cmsc491.kittykradle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link SearchResultListView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultListView extends Fragment {

    private CatThumbnail[] thumbnails;
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
