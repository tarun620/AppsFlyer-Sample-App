package com.appsflyer.androidsampleapptgsgkgvg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class appleActivity extends AppCompatActivity {
    ImageView appleGif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);
        appleGif=findViewById(R.id.appleGif);

        Glide.with(this)
                .load("https://media.giphy.com/media/9xu5xmkt5IbXW/giphy.gif")
                .asGif()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(appleGif);
    }
}