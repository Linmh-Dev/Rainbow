package com.rainbow.ui.home;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rainbow.MyApplication;
import com.rainbow.R;
import com.rainbow.room.Word;
import com.rainbow.viewmodel.WordViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }

    Button back, copy;
    TextView textView;
    WordViewModel wordViewModel;

    @Override
    public void onResume() {
        super.onResume();
        textView.setHint("正在查询...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        textView = view.findViewById(R.id.textViewdetail);
        back = view.findViewById(R.id.buttonback);
        copy = view.findViewById(R.id.buttoncopy);
        wordViewModel = new ViewModelProvider(getActivity()).get(WordViewModel.class);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wordViewModel.getWordMutableLiveData().observe(getActivity(), new Observer<Word>() {
            @Override
            public void onChanged(Word word) {
                textView.setText(String.valueOf(word.getChinaese()));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_detailFragment_to_navigation_home);
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText("Result",textView.getText().toString()));
                Toast.makeText(MyApplication.getContext(),"已复制到剪切板！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
