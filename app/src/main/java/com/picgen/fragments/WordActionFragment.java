package com.picgen.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.picgen.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Random;


public class WordActionFragment extends Fragment {

    private OnWordActionFragmentInteractionListener mListener;
    int maxLines, file;
    TextView generatedWordTextView;
    int[] files;

    public WordActionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            file = getArguments().getInt("file");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_word_action, container, false);

        maxLines = countLines(file);

        generatedWordTextView = view.findViewById(R.id.generated_word_text_view);
       // generatedWordTextView.setText(getWord(maxLines, file));

        if(file == -1) {

            setText("Select a file");
        }

        final Button generateWordButton = view.findViewById(R.id.generate_word_button);

        generateWordButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //generatedWordTextView.setText(getWord(maxLines, file));
                if(file != -1) {
                    setText(getWord(maxLines, file));
                }
            }
        });

        return view;
    }

    public void setText(String text){
        generatedWordTextView.setText(getWord(maxLines, file));
    }

    public void setFile(int file){
        this.file = file;
        maxLines = countLines(file);
    }

    private int countLines(int file) {

        int lineCount = 0;

        //InputStream in = getBaseContext().getResources().openRawResource(R.raw.words_alpha);
        InputStream in = getContext().getResources().openRawResource(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));

        String line;

        try {

            while((line = br.readLine()) != null){
                lineCount++;
            }

        } catch (IOException e){

            Toast.makeText(getContext(), "Could not read line count", Toast.LENGTH_SHORT).show();
        }

        return lineCount;
    }

    private void getAllRawResources(){

        Field[] fields = R.raw.class.getFields();

        for(int i = 0; i < fields.length; i++){
            files[i] = Integer.parseInt(fields[i].getName());
        }

    }

    private String getWord(int maxLines, int file) {

        int lineCount = 0;

        Random random = new Random();
        int randomInt = random.nextInt(maxLines);

        InputStream in = getContext().getResources().openRawResource(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));

        String line = "";

        try {

            while((line = br.readLine()) != null && lineCount <= randomInt){
                lineCount++;
            }

        } catch (IOException e){

            Toast.makeText(getContext(), "Could not read file", Toast.LENGTH_SHORT).show();
        }

        return line;
    }

    public static WordActionFragment newInstance(int file) {
        WordActionFragment fragment = new WordActionFragment();
        Bundle args = new Bundle();
        args.putInt("file", file);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnWordActionFragmentInteractionListener) {
            mListener = (OnWordActionFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnWordActionFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnWordActionFragmentInteractionListener {
        void onWordActionFragmentInteraction();
    }
}
