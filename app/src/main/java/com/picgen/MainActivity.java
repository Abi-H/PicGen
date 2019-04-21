package com.picgen;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements GeneratedWordFragment.OnGeneratedWordFragmentInteractionListener {

    int maxLines;
    GeneratedWordFragment generatedWordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        maxLines = countLines();

        Button button = findViewById(R.id.generate_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(generatedWordFragment == null) {
                    generatedWordFragment = GeneratedWordFragment.newInstance(getWord(maxLines));
                    startFragment(generatedWordFragment, R.id.generated_word_fragment_place, "generatedWordFragment");

                } else {
                    generatedWordFragment.changeText(getWord(maxLines));
                }

            }
        });
    }

    private int countLines() {

        int lines = 0;

        InputStream in = getBaseContext().getResources().openRawResource(R.raw.words);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));

        String line = "";

        try {

            do {
                line = br.readLine();
                lines++;

            } while (line != null);

        } catch (IOException e){

            Toast.makeText(getBaseContext(), "Could not read line count", Toast.LENGTH_SHORT).show();
        }

        return lines;
    }

    private String getWord(int totalLines) {

        int lineCount = 0;

        Random random = new Random();
        int randomInt = random.nextInt(totalLines);

        InputStream in = getBaseContext().getResources().openRawResource(R.raw.words);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));

        String line = "";

        try {

            do {
                line = br.readLine();
                lineCount++;

            } while (line != null && lineCount <= randomInt);

        } catch (IOException e){

            Toast.makeText(getBaseContext(), "Could not read file", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(getBaseContext(), line, Toast.LENGTH_SHORT).show();

        return line;
    }

    public void startFragment(Fragment fragment, int id, String tag){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    public void OnGeneratedGameFragmentInteraction() {

    }
}
