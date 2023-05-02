package com.example.reverseengineering;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;


public class ActivityMenu extends AppCompatActivity {
    private MaterialButton startButton;
    private TextInputEditText idEditText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        initViews();
    }

    private void findViews() {
        startButton = findViewById(R.id.menu_BTN_start);
        idEditText = findViewById(R.id.menu_EDT_id);
    }

    private void initViews() {
        startButton.setOnClickListener(v -> makeServerCall());
    }

    private void makeServerCall() {
        Thread thread = new Thread(() -> {
            String url = getString(R.string.url);
            //Log.d("pttt", "URL: " + url);
            String data = getJSON(url);
            //Log.d("pttt", "Data: " + data);
            if (data != null) {
                runOnUiThread(() -> startGame(Objects.requireNonNull(idEditText.getText()).toString(), data));
            }
        });
        thread.start();
    }


    public void startGame(String id, String data) {
        String[] splits = data.split(",");
        String state;
        if (id.length() > 0) {
            int stateIndex = new Random().nextInt(id.length());
            state = splits[stateIndex];
            Intent intent = new Intent(getBaseContext(), ActivityGame.class);
            intent.putExtra(ActivityGame.EXTRA_ID, id);
            intent.putExtra(ActivityGame.EXTRA_STATE, state);
            startActivity(intent);
        } else {
            Toast.makeText(ActivityMenu.this, "ID should be at least 1 digit", Toast.LENGTH_LONG).show();
        }
    }


    public static String getJSON(String urlString) {
        String data = "";
        HttpsURLConnection connection = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            data = scanner.hasNext() ? scanner.next() : "";
        } catch (IOException ex2) {
            ex2.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return data;
    }
}
