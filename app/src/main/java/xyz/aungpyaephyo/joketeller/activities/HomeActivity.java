package xyz.aungpyaephyo.joketeller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import xyz.aungpyaephyo.joketeller.R;
import xyz.aungpyaephyo.joketeller.fragments.EventFragment;
import xyz.aungpyaephyo.joketeller.fragments.JokeFragment;
import xyz.aungpyaephyo.joketeller.utils.JokeTellerConstants;
import xyz.aungpyaephyo.joketeller.utils.MMFontUtils;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MenuItemCompat.OnActionExpandListener {

    private int jokeIndex = -1;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView tvSearchGuide;
    private FrameLayout flContainer;
    private Button btnNextJoke;
    private Button btnPreviousJoke;

    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Menu leftMenu = navigationView.getMenu();
        MMFontUtils.applyMMFontToMenu(leftMenu);
        navigationView.setNavigationItemSelectedListener(this);

        tvSearchGuide = (TextView) findViewById(R.id.tv_search_jokes);
        flContainer = (FrameLayout) findViewById(R.id.fl_container);

        btnNextJoke = (Button) findViewById(R.id.btn_next_joke);
        btnNextJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jokeIndex++;
                if (jokeIndex < JokeTellerConstants.TOTAL_JOKES) {
                    navigateToJoke(jokeIndex);
                } else {
                    jokeIndex = JokeTellerConstants.TOTAL_JOKES - 1;
                    Toast.makeText(getApplicationContext(), R.string.msg_no_more_joke, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPreviousJoke = (Button) findViewById(R.id.btn_previous_joke);
        btnPreviousJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jokeIndex--;
                if (jokeIndex >= 0) {
                    navigateToJoke(jokeIndex);
                } else {
                    jokeIndex = 0;
                    Toast.makeText(getApplicationContext(), R.string.msg_no_more_joke, Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState == null) {
            jokeIndex++;
            navigateToJoke(jokeIndex);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigateToJoke(int jokeIndex) {
        JokeFragment fragment = JokeFragment.newInstance(jokeIndex);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit();
    }

    private void navigateToPhandeeyar() {
        Intent intent = EventActivity.newIntent();
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (menuItem.getItemId()) {
                    case R.id.left_menu_har_tha_pa_day_thar:
                        //navigateToJoke(jokeIndex);
                        break;
                    case R.id.left_menu_phandeeyar:
                        navigateToPhandeeyar();
                        break;
                    case R.id.drawer_image_laoding:
                        navigateToImageLoaingActiity();
                        break;
                }
            }
        }, 100); //to close drawer smoothly.

        return true;
    }

    private void navigateToImageLoaingActiity() {
        Intent in = new Intent(this, ImageLoadingActivity.class);
        startActivity(in);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        tvSearchGuide.setVisibility(View.VISIBLE);
        flContainer.setVisibility(View.INVISIBLE);
        btnNextJoke.setVisibility(View.INVISIBLE);
        btnPreviousJoke.setVisibility(View.INVISIBLE);
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        tvSearchGuide.setVisibility(View.INVISIBLE);
        flContainer.setVisibility(View.VISIBLE);
        btnNextJoke.setVisibility(View.VISIBLE);
        btnPreviousJoke.setVisibility(View.VISIBLE);
        return true;
    }

    private static Intent createShareIntent(){
        Intent myShareIntent = new Intent(Intent.ACTION_SEND);
        myShareIntent.setType("text/*");
        myShareIntent.putExtra(Intent.EXTRA_TEXT, "Hello Share Action Provider!");
        return myShareIntent;
    }
}
