package com.appsflyer.androidsampleapptgsgkgvg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;

public class orangeActivity extends AppCompatActivity {
    ImageView orangeGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orange);

        orangeGif=findViewById(R.id.orangeGif);

        Glide.with(this)
                .load("https://media.giphy.com/media/l378yCCaMVfzb60HS/giphy.gif")
                .asGif()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(orangeGif);
    }
}