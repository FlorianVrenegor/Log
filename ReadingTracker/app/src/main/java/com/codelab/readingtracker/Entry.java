package com.codelab.readingtracker;

import androidx.annotation.NonNull;

public class Entry {
    public String title;
    public int pageNumber;

    public Entry(String title, int pageNumber) {
        this.title = title;
        this.pageNumber = pageNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return title + "," + pageNumber;
    }

    public static Entry fromString(String entry) {
        String[] fields = entry.split(",");
        String title = fields[0];
        int pageNumber = Integer.parseInt(fields[1]);
        return new Entry(title, pageNumber);
    }
}
