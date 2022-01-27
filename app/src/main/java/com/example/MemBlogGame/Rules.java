package com.example.MemBlogGame;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Rules extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    boolean isCombat=true;
    String adventure;
    String combat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.rules);
        text = findViewById(R.id.textView);
        combat = "Режим состязания.\n\n" +
                "Начало:\n" +
                "Каждому игроку раздается на руки по пять мемов, мемы на руке держатся в закрытую.\n" +
                "Один из игроков выбирается ведущим.\n\n" +
                "Ход игры:\n" +
                "Ведущий зачитывает ситуацию.\n" +
                "Остальные игроки должны положить по одному мему, который лучше всего подходит к ситуации.\n\n" +
                "Выбор победителя:\n" +
                "Ведущий определяет победителя - у кого мем оказался смешнее.\n" +
                "Победитель забирает на руку все выложенные мемы и становится следущим ведущим.\n" +
                "Те, кому это требуется, добирают руку до пяти мемов.\n\n" +
                "Окончание игры:\n" +
                "Игра заканчивается на усмотрение играющих, победителем признается игрок с наибольшим количеством мемов на руках.\n\n" +
                "Тапни, чтобы посмотреть правила режима приключений";
        adventure = "Режим приключения.\n\n" +
                "Начало:\n" +
                "Каждый игрок выбирает себе персонажа и берет один жетон для игрового поля, и до четырех жетонов, в зависимости от числа игроков, для голосования \n\n" +
                "Ход игры:\n" +
                "Жетоны персонажей выставляются на позицию старта игрового поля.\n" +
                "Колода с мемами перемешивается и каждому игроку выдается на руки по 6 мемов.\n" +
                "Каждый ход один из игроков, по очереди, становится ведущим.\n" +
                "Ведущий загадывает ассоциацию на один из своих мемов, произносит ее в слух и выкладывает на стол загаданный мем рубашкой вверх\n" +
                "Остальные игроки ищут среди своих мемов один, который, по их мнению, наилучшим образом подходит под загаданную фразу, и кладет " +
                "её на стол рубашкой вверх.\n" +
                "Ведущий собирает мемы, перемешивает их и выкладывает на стол в линию в случайном порядке лицевой стороной вверх.\n\n" +
                "Угадывание мема ведущего:\n" +
                "Основная задача игроков — угадать, какой именно из выложенных на столе мемов загадал ведущий, и проголосовать за него.\n" +
                "Каждый игрок кладет цифрой вниз свой жетон персонажа, с цифрой соответствующей мему, за который он голосует. За свой мем голосовать нельзя.\n\n" +
                "Подсчет очков:\n" +
                "Если мем ведущего угадали все игроки, то он идет на 3 хода назад, а остальные стоят на месте.\n" +
                "Если мем ведущего никто не угадал, то ведущий идет на 2 хода назад. Плюс очки получают игроки, чьи карточки угадали.\n" +
                "В любом другом случае по 3 очка получают все игроки, правильно угадавшие мем. Ведущий получает 3 очка плюс по очку за каждого угадавшего его игрока. Все игроки получают по одному очку за каждого игрока, который угадал их мем.\n" +
                "Игроки передвигают свои фишки на игровом поле на количество шагов, соответствующее количеству выигранных очков. Каждый игрок берет по одному мему из колоды. Ведущим становится следующий игрок по порядку.\n\n" +
                "Окончание игры:\n" +
                "Игра заканчивается, когда заканчиваются карты на руках у игроков. Победителем оказывается тот, кто заработал больше всего очков и продвинулся дальше всех.\n\n" +
                "Тапни, чтобы посмотреть правила режима состязания";
        text.setText(combat);
        text.setEnabled(true);
        text.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(text, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(text, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                text.setText(isCombat? adventure : combat);
                isCombat = !isCombat;
                oa2.start();

            }
        });
        oa1.start();
    }
}
