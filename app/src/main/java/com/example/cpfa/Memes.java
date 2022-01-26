package com.example.cpfa;

import static com.example.cpfa.UpdatesChecker.mems;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Collections;

public class Memes extends AppCompatActivity implements View.OnClickListener {
    int count = 0;
    TextView mem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Collections.shuffle(mems);
        Collections.shuffle(mems);
        Collections.shuffle(mems);
        Collections.shuffle(mems);
        setContentView(R.layout.memes);
        mem = findViewById(R.id.textView);
        mem.setText(mems.get(count));
        Button button = new Button(this);
        ConstraintLayout.LayoutParams param = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        button.setX(0);
        button.setY(0);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setOnClickListener(this);
        addContentView(button, param);
        System.out.println(mems);
    }


    @Override
    public void onClick(View view) {
            count++;
            if(count == mems.size()){
                Collections.shuffle(mems);
                Collections.shuffle(mems);
                Collections.shuffle(mems);
                Collections.shuffle(mems);
                Collections.shuffle(mems);
                count = 0;
                System.out.println(mems);
            }
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(mem, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(mem, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
               mem.setText(mems.get(count));
                oa2.start();

            }
        });
        oa1.start();

    }
}
