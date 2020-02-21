package com.codelab.readingtracker;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.codelab.readingtracker.reading.ReadingFragment;
import com.codelab.readingtracker.timer.TimerFragment;
import com.codelab.readingtracker.todo.TodoFragment;

public class MainActivity extends AppCompatActivity {

    FragmentAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new FragmentAdapter(getSupportFragmentManager(), 1);

        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        Button readingButton = findViewById(R.id.reading_button);
        readingButton.setOnClickListener(v -> setTab(0));
        Button timerButton = findViewById(R.id.timer_button);
        timerButton.setOnClickListener(v -> setTab(1));
        Button todoButton = findViewById(R.id.todo_button);
        todoButton.setOnClickListener(v -> setTab(2));
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), 1);
        adapter.addFragment(new ReadingFragment());
        adapter.addFragment(new TimerFragment());
        adapter.addFragment(new TodoFragment());
        viewPager.setAdapter(adapter);
    }

    public void setTab(int index) {
        viewPager.setCurrentItem(index);
    }
}
