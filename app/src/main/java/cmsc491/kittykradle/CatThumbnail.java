package cmsc491.kittykradle;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CatThumbnail extends Fragment implements View.OnClickListener, View.OnTouchListener{

    private int catId;
    private Image catImage;
    private String catName;
    private String catGender;
    private int textColor;

    public CatThumbnail() {
        // Required empty public constructor
    }

    public void setCat(int id){
        catId = id;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat_thumbnail, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        TextView text = getView().findViewById(R.id.textView);
        if(action==MotionEvent.ACTION_DOWN||action== MotionEvent.ACTION_POINTER_DOWN){
            text.setBackgroundResource(R.drawable.text_border_male);
            text.setTextColor(ContextCompat.getColor(getContext(),R.color.blue));
        }
        else if(action==MotionEvent.ACTION_UP||action==MotionEvent.ACTION_POINTER_UP){
            text.setBackgroundResource(R.color.blue);
            text.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
        }
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
