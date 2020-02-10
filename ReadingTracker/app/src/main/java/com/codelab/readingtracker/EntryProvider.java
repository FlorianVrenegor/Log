package com.codelab.readingtracker;

import java.util.List;

public interface EntryProvider {
    void save(List<Entry> entries);
    List<Entry> load();
}
