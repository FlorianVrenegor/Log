package com.codelab.readingtracker.reading;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SharedPreferencesEntryProvider implements EntryProvider {

    private SharedPreferences sharedPreferences;

    public SharedPreferencesEntryProvider(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(context));

    }

    @Override
    public void save(List<Entry> entries) {
        List<String> strings = new ArrayList<>();
        entries.forEach((entry -> strings.add(entry.toString())));
        Set<String> set = new HashSet<>(strings);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("Entries");
        editor.putStringSet("Entries", set).apply();
    }

    @Override
    public List<Entry> load() {
        List<Entry> entries = new ArrayList<>();

        new ArrayList<>(sharedPreferences.getStringSet("Entries", new HashSet<>())).forEach((s) -> entries.add(Entry.fromString(s)));

        return entries;
    }
}
