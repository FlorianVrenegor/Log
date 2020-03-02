package com.codelab.readingtracker.storage;

import com.codelab.readingtracker.reading.Entry;

import java.util.List;

public interface EntryProvider {
    void save(List<Entry> entries);
    List<Entry> load();
}
