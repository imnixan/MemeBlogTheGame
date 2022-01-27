package com.example.MemBlogGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements UpdateCallback{
    Display display;
    ImageView logo;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        display = getWindowManager().getDefaultDisplay();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.pic);
        Glide.with(this).asGif().load(R.drawable.billy).into(logo);
        text = findViewById(R.id.updates);
        new UpdatesChecker(this, this);
        display = getWindowManager().getDefaultDisplay();


    }


    @Override
    public void updateText() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText("Updating...");
                Glide.with(MainActivity.this).asGif().load(R.drawable.updating).into(logo);
            }
        });

    }

    @Override
    public void onSucces() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onError() {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
        finish();
    }
}
