package com.example.am_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;
    public static final String[] urls = {
            "https://i1.kwejk.pl/k/obrazki/2020/10/L8m1W1dtHvLiQK4X.jpg",
            "https://i1.kwejk.pl/k/obrazki/2020/10/mDe9bqW8P0Hl0iU3.jpg",
            "https://i1.kwejk.pl/k/obrazki/2020/10/KRT8KZ2k7FQX7JZY.jpg"};
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("tag", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = random.nextInt(3);
                Glide
                        .with(getApplicationContext())
                        .load(urls[index])
                        .into(imageView);
                textView.setText(String.valueOf(index));
            }
        });
    }

    @Override
    protected void onResume() {
        Log.i("tag", "onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.i("tag", "onStop");
        super.onStop();
    }

}