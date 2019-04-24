package com.picgen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GeneratedWordFragment extends Fragment {

    String word;
    OnGeneratedWordFragmentInteractionListener listener;
    TextView generatedWordTextView;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_generated_word, container, false);

        if(getArguments() != null){
            word = getArguments().getString("word");
        }

        generatedWordTextView = view.findViewById(R.id.generated_word_text_view);
        changeText(word);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnGeneratedWordFragmentInteractionListener) {
            listener = (OnGeneratedWordFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGeneratedWordFragmentInteractionListener");
        }
    }

    public static GeneratedWordFragment newInstance(String word) {
        GeneratedWordFragment fragment = new GeneratedWordFragment();
        Bundle args = new Bundle();
        args.putString("word", word);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void changeText(String updatedWord){
        generatedWordTextView.setText(updatedWord);
    }

    public interface OnGeneratedWordFragmentInteractionListener {
        void OnGeneratedWordFragmentInteraction();
    }
}
