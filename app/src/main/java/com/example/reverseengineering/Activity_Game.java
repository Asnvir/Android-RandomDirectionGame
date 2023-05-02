package com.example.reverseengineering;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;


public class Activity_Game extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_STATE = "EXTRA_STATE";
    private ImageButton[] arrows;
    int currentLevel = 0;
    int[] steps = {1, 1, 1, 2, 2, 2, 3, 3, 3};
    private boolean goodToGo = true;
    private final String[] DIRECTIONS = {"left", "right", "up", "down"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        String id = getIntent().getStringExtra(EXTRA_ID);
        if (id.length() == this.steps.length) {
            int i = 0;
            while (true) {
                int[] iArr = this.steps;
                if (i >= iArr.length) {
                    break;
                }
                iArr[i] = Integer.parseInt(String.valueOf(id.charAt(i))) % 4;
                i++;
            }
        }
        Log.d("GAME_LOG", "The correct sequence of arrow clicks is: " + Arrays.toString(getDirectionArray(steps)));

        findViews();
        initViews();
    }

    private String[] getDirectionArray(int[] steps) {
        String[] directionArray = new String[steps.length];
        for (int i = 0; i < steps.length; i++) {
            directionArray[i] = DIRECTIONS[steps[i]];
        }
        return directionArray;
    }

    private void arrowClicked(int direction) {
        if (this.goodToGo && direction != this.steps[this.currentLevel]) {
            this.goodToGo = false;
        }
        Log.d("GAME_LOG", "Clicked direction: " + DIRECTIONS[direction] + ", current level: " + this.currentLevel + ", goodToGo: " + this.goodToGo);
        int i = this.currentLevel + 1;
        this.currentLevel = i;
        if (i >= this.steps.length) {
            finishGame();
        }
    }

    private void finishGame() {
        String state = getIntent().getStringExtra(EXTRA_STATE);
        if (this.goodToGo) {
            Toast.makeText(this, "Survived in " + state, Toast.LENGTH_LONG).show();
            Log.d("GAME_LOG", "Survived in " + state);
        } else {
            Toast.makeText(this, "You Failed ", Toast.LENGTH_LONG).show();
            Log.d("GAME_LOG", "Failed");
        }
        finish();
    }

    private void initViews() {
        int i = 0;
        while (true) {
            ImageButton[] imageButtonArr = this.arrows;
            if (i < imageButtonArr.length) {
                final int finalI = i;
                imageButtonArr[i].setOnClickListener(v -> arrowClicked(finalI));
                i++;
            } else {
                return;
            }
        }
    }

    private void findViews() {
        this.arrows = new ImageButton[]{(ImageButton) findViewById(R.id.game_BTN_left), (ImageButton) findViewById(R.id.game_BTN_right), (ImageButton) findViewById(R.id.game_BTN_up), (ImageButton) findViewById(R.id.game_BTN_down)};
    }
}
