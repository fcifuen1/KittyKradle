package cmsc491.kittykradle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

class Cat{
    String id;
    String name;
    String image; //link? or actual image?
    String gender;
}

public class SearchResultActivity extends AppCompatActivity {
    private final int catPerPage=8;
    private Cat[] catList;
    private int currentPage=1;
    private Button changeViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
    }

    private void showResultGridView(){

    }

    private void showResultListView(){

    }

    private void changeResultPage(int page){

    }

    private void startCatInfoActivity(String catId){

    }
}
