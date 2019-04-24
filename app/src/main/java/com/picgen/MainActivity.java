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

public class MainActivity extends AppCompatActivity implements GeneratedWordFragment.OnGeneratedWordFragmentInteractionListener,
        GenerateButtonFragment.OnGenerateButtonFragmentInteractionListener, WordActionFragment.OnWordActionFragmentInteractionListener {

    //int maxLines;
    //GeneratedWordFragment generatedWordFragment;
    //GenerateButtonFragment generateButtonFragment;
    WordActionFragment wordActionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int maxLines = countLines();

        wordActionFragment = WordActionFragment.newInstance(maxLines);
        //Toast.makeText(getBaseContext(), "maxLines is "+countLines(), Toast.LENGTH_SHORT).show();
        startFragment(wordActionFragment,R.id.word_action_fragment_place, "wordActionFragment");

        /*generateButtonFragment = GenerateButtonFragment.newInstance();
        startFragment(generateButtonFragment, R.id.generate_button_fragment_place, "generateButtonFragment");*/

        /*Button button = findViewById(R.id.generate_button);

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
        });*/
    }

    private int countLines() {

        int lineCount = 0;

        InputStream in = getBaseContext().getResources().openRawResource(R.raw.words);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));

        String line = "";

        try {

            do {
                line = br.readLine();
                lineCount++;

            } while (line != null);

        } catch (IOException e){

            Toast.makeText(getBaseContext(), "Could not read line count", Toast.LENGTH_SHORT).show();
        }

        return lineCount;
    }

    /*private String getWord(int totalLines) {

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

        return line;
    }*/

    public void startFragment(Fragment fragment, int id, String tag){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    public void OnGeneratedWordFragmentInteraction() {

    }

    @Override
    public void onClickGenerateWordButton() {

    }

    @Override
    public void onWordActionFragmentInteraction() {

    }


   /* @Override
    public void onClickGenerateWordButton() {

        Toast.makeText(getBaseContext(), "Clicked Generate Word Button", Toast.LENGTH_SHORT).show();

        if(generatedWordFragment == null) {
            generatedWordFragment = GeneratedWordFragment.newInstance(getWord(maxLines));
            startFragment(generatedWordFragment, R.id.generated_word_fragment_place, "generatedWordFragment");

        } else {
            generatedWordFragment.changeText(getWord(maxLines));
        }
    }*/
}
