package com.rainbow.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainbow.R;
import com.rainbow.room.Word;
import com.rainbow.room.WordDatabases;

public class DashboardFragment extends Fragment {
       LiveData<PagedList<Word>> liveData;
       HistoryAdapter historyAdapter;
       RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        liveData=new LivePagedListBuilder<>(WordDatabases.getWordDatabases().getDao().getList(),10).build();
        historyAdapter=new HistoryAdapter();
        recyclerView=root.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(historyAdapter);
        liveData.observe(getActivity(), new Observer<PagedList<Word>>() {
            @Override
            public void onChanged(PagedList<Word> words) {
                historyAdapter.submitList(words);
            }
        });
        return root;
    }
}