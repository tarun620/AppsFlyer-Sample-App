package com.appsflyer.androidsampleapptgsgkgvg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class bananaActivity extends AppCompatActivity {
    ImageView bananaGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banana);

        bananaGif=findViewById(R.id.bananaGif);

        Glide.with(this)
                .load("https://media.giphy.com/media/10q91EVvNXW0kE/giphy.gif")
                .asGif()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(bananaGif);
    }
}