package xyz.aungpyaephyo.joketeller.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.URL;

import xyz.aungpyaephyo.joketeller.JokeTellerApp;
import xyz.aungpyaephyo.joketeller.R;

public class ImageLoadingActivity extends AppCompatActivity {

    private static final String DUMMY_IMAGE_URL = "http://1.bp.blogspot.com/-ARWd4lfx6eY/VHiKpMbkjqI/AAAAAAAAVF4/x86xYtfIDNA/s640/5.jpg";

    private ImageView ivWithAsync;
    private ImageView ivWithGlide;

    public static Intent newIntent() {
        Intent intent = new Intent(JokeTellerApp.getContext(), ImageLoadingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loading);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ivWithAsync = (ImageView) findViewById(R.id.iv_with_async);
        ivWithGlide = (ImageView) findViewById(R.id.iv_with_glide);

        Button btnLoadImageAsync = (Button) findViewById(R.id.btn_load_image_async);
        btnLoadImageAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadImageTask().execute(DUMMY_IMAGE_URL);
            }
        });

        Button btnLoadImageGlide = (Button) findViewById(R.id.btn_load_image_glide);
        btnLoadImageGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(ivWithAsync.getContext())
                        .load(DUMMY_IMAGE_URL)
                        .centerCrop()
                        .placeholder(R.drawable.stock_photo_placeholder)
                        .into(ivWithGlide);
            }
        });
    }

    private class LoadImageTask extends AsyncTask<String, String, Bitmap> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ImageLoadingActivity.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }

        @Override
        protected Bitmap doInBackground(String... args) {
            Bitmap bitmap = null;
            try {
                String imageUrl = args[0];
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap image) {
            if (image != null) {
                ivWithAsync.setImageBitmap(image);
                pDialog.dismiss();

            } else {

                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
