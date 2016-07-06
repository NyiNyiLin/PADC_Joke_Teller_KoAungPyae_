package xyz.aungpyaephyo.joketeller.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import xyz.aungpyaephyo.joketeller.R;
import xyz.aungpyaephyo.joketeller.data.models.JokeModel;
import xyz.aungpyaephyo.joketeller.data.vos.JokeVO;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeFragment extends Fragment {

    private static final String BARG_JOKE_INDEX = "BARG_JOKE_INDEX";

    private int jokeIndex;
    private JokeVO joke;

    private ShareActionProvider mShareActionProvider;
    private View mView;
    private MenuItemCompat.OnActionExpandListener mOnActionExpandListener;

    public static JokeFragment newInstance(int jokeIndex) {
        JokeFragment fragment = new JokeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BARG_JOKE_INDEX, jokeIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

    public JokeFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnActionExpandListener = (MenuItemCompat.OnActionExpandListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            jokeIndex = bundle.getInt(BARG_JOKE_INDEX);
            joke = JokeModel.getInstance().getJoke(jokeIndex);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvJokeTitle = (TextView) mView.findViewById(R.id.tv_joke_title);
        tvJokeTitle.setText(joke.getJokeTitle());

        TextView tvJokeContent = (TextView) mView.findViewById(R.id.tv_joke_content);
        tvJokeContent.setText(joke.getJokeContent());

        ImageView ivJoke = (ImageView) mView.findViewById(R.id.iv_joke);
        ivJoke.setImageResource(joke.getJokeImage());

        return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_joke_screen, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, mOnActionExpandListener);

        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareIntent());
        } else {
            Snackbar.make(mView, "ShareActionProvider is being null. Why ?", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_love:
                Toast.makeText(getContext(), getString(R.string.lbl_love_the_joke), Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static Intent createShareIntent(){
        Intent myShareIntent = new Intent(Intent.ACTION_SEND);
        myShareIntent.setType("text/*");
        myShareIntent.putExtra(Intent.EXTRA_TEXT, "Hello Share Action Provider!");
        return myShareIntent;
    }
}
