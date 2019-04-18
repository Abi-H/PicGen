package com.picgen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    String word, filename;
    File file;
    FileInputStream in;
    int maxLines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        word = "";
        filename = "words.txt";
        maxLines = countLines(filename);

        button = findViewById(R.id.generate_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        textView = findViewById(R.id.generated_word_text_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setText(String word){
        this.word = word;

        textView.setText(word);

    }

    private String getText(){
        return word;
    }

    private void updateWord(){

    }

    private int countLines(String filename) {

        int lines = 0;
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

             do {
                 line = br.readLine();

            } while (line != null);

        } catch (IOException e) {

            Toast.makeText(getBaseContext(), "Lines could not be read", Toast.LENGTH_SHORT).show();

        }

        return lines;
    }

    private String getWord(String file){

        return "";
    }
}
