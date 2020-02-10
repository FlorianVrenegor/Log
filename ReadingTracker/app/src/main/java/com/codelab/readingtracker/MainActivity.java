package com.codelab.readingtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    List<Entry> entries = new ArrayList<>();

    TextView entriesTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entriesTextView = findViewById(R.id.entries_textView);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        load();

        final TextView textView = findViewById(R.id.textView);

        final EditText titleEditText = findViewById(R.id.title_editText);
        final EditText pageNumberEditText = findViewById(R.id.pagenumber_editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String pageNumberString = pageNumberEditText.getText().toString();
                int pageNumber = 0;
                if (!pageNumberString.isEmpty()) {
                    pageNumber = Integer.parseInt(pageNumberString);
                }
                Entry entry = new Entry(title, pageNumber);
                entries.add(entry);
                textView.setText("Added " + entry.toString() + "!");
            }
        });

        TextView saveTextView = findViewById(R.id.saved_textView);

        Button saveButton = findViewById(R.id.save_btn);
        saveButton.setOnClickListener(v -> {
            save();
            saveTextView.setText("Saved " + entries.toString() + "!");
        });

        final TextView loadTextView = findViewById(R.id.loaded_textView);

        Button loadButton = findViewById(R.id.load_btn);
        loadButton.setOnClickListener(v -> {
            load();
            loadTextView.setText("Loaded " + Arrays.toString(entries.toArray()) +"!");
        });

        TextView timerTextView = findViewById(R.id.timer_textView);
        Button startButton = findViewById(R.id.start_btn);
        startButton.setOnClickListener(v -> {
            CountDownTimer timer = new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText("Remaining: " + millisUntilFinished / 1000 + " sec");
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("You Win!");
                }
            }.start();
        });

        Button stopButton = findViewById(R.id.stop_btn);
        stopButton.setOnClickListener(v -> {

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }

    SharedPreferences sharedPreferences = null;

    private void save() {
        List<String> strings = new ArrayList<>();
        entries.forEach((entry -> strings.add(entry.toString())));
        Set<String> set = new HashSet<>(strings);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("Entries");
        editor.putStringSet("Entries", set).apply();
    }

    private void load() {
        entries.clear();
        Set<String> stringSet = sharedPreferences.getStringSet("Entries", new HashSet<>());
        Map<String, ?> temp = sharedPreferences.getAll();
        List<String> strings = new ArrayList<>(stringSet);
        strings.forEach((s) -> entries.add(Entry.fromString(s)));

        if(entriesTextView != null) {
            entriesTextView.setText(entries.toString());
        }
    }
}
