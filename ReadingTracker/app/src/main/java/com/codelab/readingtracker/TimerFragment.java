package com.codelab.readingtracker;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimerFragment extends Fragment {

    private TextView timerTextView;
    private Button startButton;
    private Button stopButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        timerTextView = view.findViewById(R.id.timer_textView);
        startButton = view.findViewById(R.id.start_btn);
        stopButton = view.findViewById(R.id.stop_btn);

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

        stopButton.setOnClickListener(v -> {

        });

        return view;
    }
}
