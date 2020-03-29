package com.rainbow.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.rainbow.MyApplication;
import com.rainbow.R;
import com.rainbow.okhttp.Okhttps;
import com.rainbow.room.Word;
import com.rainbow.room.WordDao;
import com.rainbow.room.WordDatabases;

public class HomeFragment extends Fragment {
   private EditText editTextinput;
    private Button buttontranslation;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        editTextinput=root.findViewById(R.id.editTextinput);
        buttontranslation=root.findViewById(R.id.buttontranslation);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttontranslation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(v);
                String string = editTextinput.getText().toString();
                if (string.length()==0||string==null){
                    Toast.makeText(MyApplication.getContext(),"内容不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    Okhttps okhttps=new Okhttps();
                    okhttps.execute(string);
                    navController.navigate(R.id.action_navigation_home_to_detailFragment);
                }

            }
        });
    }
}