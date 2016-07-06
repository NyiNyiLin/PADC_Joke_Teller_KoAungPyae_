package xyz.aungpyaephyo.joketeller.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import xyz.aungpyaephyo.joketeller.JokeTellerApp;
import xyz.aungpyaephyo.joketeller.R;
import xyz.aungpyaephyo.joketeller.data.models.EventModel;
import xyz.aungpyaephyo.joketeller.data.vos.EventVO;

public class EventDetailActivity extends AppCompatActivity {

    private static final String IE_EVENT_TITLE = "IE_EVENT_TITLE";

    private ImageView ivEventPhoto;
    private TextView tvEventDesc;
    private TextView tvEventTime;
    private CollapsingToolbarLayout collapsingToolbar;

    public static Intent newIntent(String eventTitle) {
        Intent intent = new Intent(JokeTellerApp.getContext(), EventDetailActivity.class);
        intent.putExtra(IE_EVENT_TITLE, eventTitle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ivEventPhoto = (ImageView) findViewById(R.id.iv_stock_photo);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            String transtionName = JokeTellerApp.getContext().getResources().getString(R.string.event_stock_photo_share_transition);
            ivEventPhoto.setTransitionName(transtionName);
        }

        tvEventDesc = (TextView) findViewById(R.id.tv_event_desc);
        tvEventTime = (TextView) findViewById(R.id.tv_event_time);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }

        String eventTitle = getIntent().getStringExtra(IE_EVENT_TITLE);

        EventVO event = EventModel.getInstance().getEventByTitle(eventTitle);
        if(event == null) {
            throw new RuntimeException("Can't find Event obj with the title : "+eventTitle);
        } else {
            collapsingToolbar.setTitle(event.getEventTitle());
            tvEventDesc.setText(event.getEventDesc() + "\n\n" +
                    event.getEventDesc() + "\n\n" +
                    event.getEventDesc() + "\n\n" +
                    event.getEventDesc() + "\n\n" +
                    event.getEventDesc());
            tvEventTime.setText(event.getEventTime()); //Friday, Feb 26, 1:00pm - 5:00pm

            Glide.with(ivEventPhoto.getContext())
                    .load(event.getStockPhotoPath())
                    .centerCrop()
                    .placeholder(R.drawable.stock_photo_placeholder)
                    .into(ivEventPhoto);
        }
    }

}