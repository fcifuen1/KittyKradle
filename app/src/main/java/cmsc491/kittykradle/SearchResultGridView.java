package cmsc491.kittykradle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * {@link SearchResultGridView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchResultGridView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultGridView extends Fragment implements View.OnClickListener, View.OnTouchListener{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATID = "catId";

    private String[] mCatId;

    private OnFragmentInteractionListener mListener;



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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result_grid_view, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onCatPressed(Uri uri) {
        //Intent catInfoIntent =  new Intent(getActivity());
        //startActivity(catInfoIntent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.thumbnail1:

                break;
            case R.id.thumbnail2:

                break;
            case R.id.thumbnail3:

                break;
            case R.id.thumbnail4:

                break;
            case R.id.thumbnail5:

                break;
            case R.id.thumbnail6:

                break;
            case R.id.thumbnail7:

                break;
            case R.id.thumbnail8:

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        switch(v.getId()){
            case R.id.thumbnail1:
                TextView text = getView().findViewById(R.id.textView1);
                if(action==MotionEvent.ACTION_DOWN||action== MotionEvent.ACTION_POINTER_DOWN){
                    text.setBackgroundResource(R.drawable.text_border_male);
                    text.setTextColor(ContextCompat.getColor(getContext(),R.color.blue));
                }
                else if(action==MotionEvent.ACTION_UP||action==MotionEvent.ACTION_POINTER_UP){
                    text.setBackgroundResource(R.color.blue);
                    text.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
                }
                break;
            case R.id.thumbnail2:

                break;
            case R.id.thumbnail3:

                break;
            case R.id.thumbnail4:

                break;
            case R.id.thumbnail5:

                break;
            case R.id.thumbnail6:

                break;
            case R.id.thumbnail7:

                break;
            case R.id.thumbnail8:

                break;
            default:
                break;
        }
        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
