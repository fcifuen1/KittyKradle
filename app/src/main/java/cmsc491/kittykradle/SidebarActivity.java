package cmsc491.kittykradle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Set;

public class SidebarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawerLayout;
    private FragmentManager fm;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onCreateDrawer();
    }

    protected void onCreateDrawer()
    {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sidebar);

        // toolbar is the menu button at the top left of the screen
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Set up menu bar at top corner
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.hamburger_white);

        drawerLayout = findViewById(R.id.drawer_layout);
        fm = getSupportFragmentManager();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sidebar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle sidebar navigation items
        switch (item.getItemId())
        {
            case R.id.home:
                // Load Home fragment
                homepage();
                break;

            case R.id.search:
                // Load search activity
                search();
                break;

            case R.id.favorites:
                // Load favorites fragment
                favorites();
                break;

            case R.id.settings:
                settings();
                break;

            case R.id.about:
                // Load about fragment -- mission, faq, cat care
                goToFaq();
                break;

            case R.id.logout:
                logout();
                break;

            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void homepage() {
        if(this.getClass() != Homepage.class) {
            Intent i = new Intent(this, Homepage.class);
            startActivity(i);
        }
    }

    private void goToFaq()
    {
        if(this.getClass() != FAQActivity.class) {
            Intent i = new Intent(this, FAQActivity.class);
            startActivity(i);
        }
    }

    // Goes to the search screen
    private void search()
    {
        if(this.getClass() != SearchActivity.class) {
            Intent i = new Intent(this, SearchActivity.class);
            startActivity(i);
        }
    }

    // Go to favorites screen
    private void favorites() {
        if(this.getClass() != FavoritesActivity.class) {
            Intent i = new Intent(this, FavoritesActivity.class);
            startActivity(i);
        }
    }

    // Go to settings
    private void settings(){
        if(this.getClass() != Settings.class) {
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
        }
    }

    // Clears the activity stack and goes back to the Login page
    private void logout()
    {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}