package com.codelab.readingtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ReadingFragment extends Fragment {

    private List<Entry> entries = new ArrayList<>();
    private TextView entriesTextView = null;
    private SharedPreferences sharedPreferences = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getContext()));

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
        List<String> strings = new ArrayList<>();
        entries.forEach((entry -> strings.add(entry.toString())));
        Set<String> set = new HashSet<>(strings);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("Entries");
        editor.putStringSet("Entries", set).apply();

        Toast.makeText(getContext(), "Saved " + Arrays.toString(entries.toArray()), Toast.LENGTH_SHORT).show();
    }

    private void load() {
        entries.clear();
        Set<String> stringSet = sharedPreferences.getStringSet("Entries", new HashSet<>());
        List<String> strings = new ArrayList<>(stringSet);
        strings.forEach((s) -> entries.add(Entry.fromString(s)));

        if(entriesTextView != null) {
            entriesTextView.setText(entries.toString());
        }

        Toast.makeText(getContext(), "Loaded " + Arrays.toString(entries.toArray()), Toast.LENGTH_SHORT).show();
    }
}
