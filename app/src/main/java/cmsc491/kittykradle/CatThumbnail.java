package cmsc491.kittykradle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class CatThumbnail extends Fragment implements View.OnClickListener, View.OnTouchListener{

    private int catId;
    private String catName;
    private String catGender;
    private String imageUrl;
    private Bitmap imgBmp;
    private int textColor;
    private int selectedBg;
    OnFragmentInteractionListener mListener;

    public CatThumbnail() {
        // Required empty public constructor
    }

    public void setCat(int id, String name, String gender, String imageUrl){
        catId = id;
        catName = name;
        catGender=gender;
        this.imageUrl=imageUrl;
        if(gender=="male") {
            textColor = R.color.blue;
            selectedBg=R.drawable.text_border_male;
        }
        else{
            textColor = R.color.pink;
            selectedBg=R.drawable.text_border_female;
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImage(Bitmap bmp){
        imgBmp=bmp;
        ImageView imgview = getView().findViewById(R.id.imageView);
        imgview.setImageBitmap(imgBmp);
    }

    public void select(){
        TextView text = getView().findViewById(R.id.textView);
        text.setBackgroundResource(selectedBg);
        text.setTextColor(ContextCompat.getColor(getContext(),textColor));
    }

    public void deselect(){
        TextView text = getView().findViewById(R.id.textView);
        text.setBackgroundResource(textColor);
        text.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cat_thumbnail, container, false);
        view.setOnTouchListener(this);
        TextView textView = (TextView) (view.findViewById(R.id.textView));
        textView.setText(catName);
        textView.setBackgroundResource(textColor);
        new DownloadCatImageTask().execute(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener=null;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if(action==MotionEvent.ACTION_DOWN||action== MotionEvent.ACTION_POINTER_DOWN){
           mListener.onThumbnailSelected(this);
        }
        return false;
    }

    @Override
    public void onClick(View v) {

    }
    public interface OnFragmentInteractionListener {
        void onThumbnailSelected(CatThumbnail ct);
    }

    private class DownloadCatImageTask extends AsyncTask<CatThumbnail, Void, Bitmap> {
        CatThumbnail ct = null;

        protected Bitmap doInBackground(CatThumbnail ... thumbnails) {
            this.ct=thumbnails[0];
            return download_Image(ct.getImageUrl());
        }

        protected void onPreExecute(){

        }

        protected void onPostExecute(Bitmap result) {
            ct.setImage(result);
        }

        private Bitmap download_Image(String url) {

            Bitmap bmp =null;
            try{
                URL ulrn = new URL(url);
                HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
                InputStream is = con.getInputStream();
                bmp = BitmapFactory.decodeStream(is);
                if (null != bmp)
                    return bmp;

            }catch(Exception e){}
            return bmp;
        }

    }

}
