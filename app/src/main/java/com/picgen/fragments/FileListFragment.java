package com.picgen.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.picgen.R;

public class FileListFragment extends Fragment {

    private OnFileListFragmentInteractionListener mListener;
    RadioGroup radioGroup;
    int file;
    int[] files;

    public FileListFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            files = getArguments().getIntArray("files");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_file_list, container, false);

        radioGroup = (RadioGroup) view.findViewById(R.id.file_selector_radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId) {

                    case R.id.file_selector_radio_button_1:
                        file = R.raw.words;
                        mListener.getFileClick(file);
                        break;

                    case R.id.file_selector_radio_button_2:
                        file = R.raw.words_alpha;
                        mListener.getFileClick(file);
                        break;

                    case R.id.file_selector_radio_button_3:
                        file = R.raw.nouns;
                        mListener.getFileClick(file);
                        break;

                    case R.id.file_selector_radio_button_4:
                        file = R.raw.common_words;
                        mListener.getFileClick(file);
                        break;

                }
            }
        });

        return view;
    }

    public static FileListFragment newInstance(int[] files) {
        FileListFragment fragment = new FileListFragment();
        Bundle args = new Bundle();
        args.putIntArray("files", files);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFileListFragmentInteractionListener) {
            mListener = (OnFileListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFileListFragmentInteractionListener {
        void getFileClick(int file);
    }
}
