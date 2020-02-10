package com.codelab.readingtracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadingFragment extends Fragment {

    private List<Entry> entries = new ArrayList<>();
    private EntryProvider entryProvider = new SharedPreferencesEntryProvider(getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading, container, false);

        load();

        final EditText titleEditText = view.findViewById(R.id.title_editText);
        final EditText pageNumberEditText = view.findViewById(R.id.pagenumber_editText);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String pageNumberString = pageNumberEditText.getText().toString();
            int pageNumber = 0;
            if (!pageNumberString.isEmpty()) {
                pageNumber = Integer.parseInt(pageNumberString);
            }
            Entry entry = new Entry(title, pageNumber);
            entries.add(entry);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    @Override
    public void onPause() {
        super.onPause();
        save();
    }

    @Override
    public void onStop() {
        super.onStop();
        save();
    }

    private void save() {
        entryProvider.save(entries);
        Log.d("ReadingFragment Save - ", "Saved " + Arrays.toString(entries.toArray()));
    }

    private void load() {
        entries = entryProvider.load();
        Log.d("ReadingFragment Load - ", "Loaded " + Arrays.toString(entries.toArray()));
    }
}
