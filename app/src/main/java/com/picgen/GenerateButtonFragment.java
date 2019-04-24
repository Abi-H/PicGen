package com.picgen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class GenerateButtonFragment extends Fragment {

    private OnGenerateButtonFragmentInteractionListener mListener;

    public GenerateButtonFragment() {

    }

    public static GenerateButtonFragment newInstance() {
        GenerateButtonFragment fragment = new GenerateButtonFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_generate_button, container, false);

        Button generateButton = (Button) view.findViewById(R.id.generate_word_button);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return inflater.inflate(R.layout.fragment_generate_button, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onClickGenerateWordButton();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGenerateButtonFragmentInteractionListener) {
            mListener = (OnGenerateButtonFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGenerateButtonFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnGenerateButtonFragmentInteractionListener {
        void onClickGenerateWordButton();
    }
}
