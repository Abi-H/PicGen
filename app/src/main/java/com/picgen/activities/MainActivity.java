package com.picgen.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.picgen.R;
import com.picgen.fragments.FileListFragment;
import com.picgen.fragments.WordActionFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity implements WordActionFragment.OnWordActionFragmentInteractionListener, FileListFragment.OnFileListFragmentInteractionListener {

    WordActionFragment wordActionFragment;
    FileListFragment fileListFragment;
    int file;
    int[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        file = R.raw.words;

        wordActionFragment = WordActionFragment.newInstance(file);
        startFragment(wordActionFragment, R.id.word_action_fragment_place, "wordActionFragment");

        fileListFragment = FileListFragment.newInstance(files);
        startFragment(fileListFragment, R.id.file_list_fragment_place, "fileListFragment");

    }

    public void startFragment(Fragment fragment, int id, String tag){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }


    @Override
    public void onWordActionFragmentInteraction() {

    }

    @Override
    public void getFileClick(int file) {
        this.file = file;
        wordActionFragment.setFile(file);
    }
}
