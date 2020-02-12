package com.codelab.readingtracker;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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
        readingButton.setOnClickListener(v -> {
            setTab(0);
        });
        Button timerButton = findViewById(R.id.timer_button);
        timerButton.setOnClickListener(v -> {
            setTab(1);
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), 1);
        adapter.addFragment(new ReadingFragment());
        adapter.addFragment(new TimerFragment());
        viewPager.setAdapter(adapter);
    }

    public void setTab(int index) {
        viewPager.setCurrentItem(index);
    }
}
