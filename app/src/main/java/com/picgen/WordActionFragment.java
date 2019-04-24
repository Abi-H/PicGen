package com.picgen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;


public class WordActionFragment extends Fragment {

    private OnWordActionFragmentInteractionListener mListener;
    int maxLines;

    public WordActionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            maxLines = getArguments().getInt("maxLines");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_word_action, container, false);

        final TextView generatedWordTextView = view.findViewById(R.id.generated_word_text_view);
        generatedWordTextView.setText(getWord(maxLines));

        final Button generateWordButton = view.findViewById(R.id.generate_word_button);

        generateWordButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                generatedWordTextView.setText(getWord(maxLines));
            }
        });

        return view;
    }

    private String getWord(int maxLines) {

        int lineCount = 0;

        Random random = new Random();
        int randomInt = random.nextInt(maxLines);

        InputStream in = getContext().getResources().openRawResource(R.raw.words);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));

        String line = "";

        try {

            do {
                line = br.readLine();
                lineCount++;

            } while (line != null && lineCount <= randomInt);

        } catch (IOException e){

            Toast.makeText(getContext(), "Could not read file", Toast.LENGTH_SHORT).show();
        }

        return line;
    }

    public static WordActionFragment newInstance(int maxLines) {
        WordActionFragment fragment = new WordActionFragment();
        Bundle args = new Bundle();
        args.putInt("maxLines", maxLines);
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
