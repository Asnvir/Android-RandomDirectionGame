package com.example.reverseengineering;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class ActivityGame extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_STATE = "EXTRA_STATE";
    private static final String[] DIRECTIONS = {"up", "right", "down", "left"};

    private ImageButton[] arrows;
    private int currentLevel = 0;
    private int[] steps;
    private boolean goodToGo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String id = getIntent().getStringExtra(EXTRA_ID);
        steps = new int[id.length()];

        for (int i = 0; i < id.length(); i++) {
            int step = Character.getNumericValue(id.charAt(i)) % DIRECTIONS.length;
            steps[i] = step;
        }


        Log.d("GAME_LOG", "Win sequence of steps: " + Arrays.toString(getDirectionArray(steps)));

        findViews();
        initViews();
    }

    private void findViews() {
        arrows = new ImageButton[]{findViewById(R.id.game_BTN_up), findViewById(R.id.game_BTN_right), findViewById(R.id.game_BTN_down), findViewById(R.id.game_BTN_left)};
    }

    private void initViews() {
        for (int i = 0; i < arrows.length; i++) {
            ImageButton arrow = arrows[i];
            int finalI = i;
            arrow.setOnClickListener(v -> arrowClicked(finalI));
        }
    }

    private String[] getDirectionArray(int[] steps) {
        String[] directionArray = new String[steps.length];
        for (int i = 0; i < steps.length; i++) {
            directionArray[i] = DIRECTIONS[steps[i]];
        }
        return directionArray;
    }


    private void arrowClicked(int direction) {
        if (goodToGo && direction != steps[currentLevel]) {
            goodToGo = false;
        }

        Log.d("GAME_LOG", "Clicked direction: " + DIRECTIONS[direction] + ", current level: " + currentLevel + ", goodToGo: " + goodToGo);

        currentLevel++;

        if (currentLevel >= steps.length) {
            finishGame();
        }
    }

    private void finishGame() {
        String state = getIntent().getStringExtra(EXTRA_STATE);

        if (goodToGo) {
            Toast.makeText(this, "Survived in " + state, Toast.LENGTH_LONG).show();
            Log.d("GAME_LOG", "Survived in " + state);
        } else {
            Toast.makeText(this, "You Failed ", Toast.LENGTH_LONG).show();
            Log.d("GAME_LOG", "You Failed ");
        }

        finish();
    }

}
