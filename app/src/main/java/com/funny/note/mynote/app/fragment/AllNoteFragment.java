package com.funny.note.mynote.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.funny.note.mynote.R;

/**
 * Created by admin on 15/12/27.
 */
public class AllNoteFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_allnote, null);
        return view;
    }
}
