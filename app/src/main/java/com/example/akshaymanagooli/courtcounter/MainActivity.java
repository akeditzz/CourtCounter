package com.example.akshaymanagooli.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_point_team_a, tv_point_team_b;
    int scoreTeamA = 0, scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_point_team_a = findViewById(R.id.tv_teama_score);
        tv_point_team_b = findViewById(R.id.tv_teamb_score);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_plus3_A:
                IncreasePointsByThree("A");
                break;
            case R.id.btn_plus3_B:
                IncreasePointsByThree("B");
                break;
            case R.id.btn_plus2_A:
                IncreasePointsByTwo("A");
                break;
            case R.id.btn_plus2_B:
                IncreasePointsByTwo("B");
                break;
            case R.id.btn_freethrow_A:
                FreeThrow("A");
                break;
            case R.id.btn_freethrow_B:
                FreeThrow("B");
                break;
            case R.id.btn_reset:
                ResetPoints();
                break;
        }
    }

    private void IncreasePointsByThree(String team) {

        switch (team) {
            case "A":
                scoreTeamA += 3;
                DisplayScore("A");
                break;
            case "B":
                scoreTeamB += 3;
                DisplayScore("B");
                break;
        }

    }

    private void IncreasePointsByTwo(String team) {

        switch (team) {
            case "A":
                scoreTeamA += 2;
                DisplayScore("A");
                break;
            case "B":
                scoreTeamB += 2;
                DisplayScore("B");
                break;
        }

    }

    private void FreeThrow(String team) {

        switch (team) {
            case "A":
                scoreTeamA += 1;
                DisplayScore("A");
                break;
            case "B":
                scoreTeamB += 1;
                DisplayScore("B");
                break;
        }

    }

    private void ResetPoints() {
        scoreTeamA = 0;
        scoreTeamB = 0;
        DisplayScore("A");
        DisplayScore("B");
    }

    private void DisplayScore(String team) {
        switch (team) {
            case "A":
                tv_point_team_a.setText(String.valueOf(scoreTeamA));
                break;
            case "B":
                tv_point_team_b.setText(String.valueOf(scoreTeamB));
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("TeamA", scoreTeamA);
        outState.putInt("TeamB", scoreTeamB);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreTeamA = savedInstanceState.getInt("TeamA");
        scoreTeamB = savedInstanceState.getInt("TeamB");
        DisplayScore("A");
        DisplayScore("B");
    }
}
