package me.pawanjeswani.redditapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class FullscreenActivity extends AppCompatActivity {
    private ImageView ivFullScreen;
    private String url="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        ivFullScreen = findViewById(R.id.ivFullScreen);

        Intent intent = getIntent();
        url = intent.getStringExtra("uri");
        Log.d("urlfull for imgs",url);
        if(url != null)
            setImagePic();

    }

    private void setImagePic() {
        Picasso.with(getApplicationContext()).load(url).placeholder(R.drawable.placeholder).error(R.drawable.accden).into(ivFullScreen);
    }
}
