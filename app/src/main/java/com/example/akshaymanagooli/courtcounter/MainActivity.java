package com.example.akshaymanagooli.courtcounter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AlphabetIndexer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Constants {

    //views and variables declaration
    private TextView tv_point_team_a, tv_point_team_b, tv_fauls_team_a, tv_fauls_team_b;
    private int scoreTeamA = 0, scoreTeamB = 0;
    private int faulsTeamA = 0, faulsTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * @author Akshay
     * method to initialize view
     */
    private void initView() {
        tv_point_team_a = findViewById(R.id.tv_teama_score);
        tv_point_team_b = findViewById(R.id.tv_teamb_score);
        tv_fauls_team_a = findViewById(R.id.tv_fauls_A);
        tv_fauls_team_b = findViewById(R.id.tv_fauls_B);
    }

    /**
     * @param v gets the view on which clicked
     * @author Akshay
     * method to perform action on button click
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_plus15_A:
                increasePointsByFifteen(LABEL_A);
                break;
            case R.id.btn_plus15_B:
                increasePointsByFifteen(LABEL_B);
                break;
            case R.id.btn_plus10_A:
                increasePointsByTen(LABEL_A);
                break;
            case R.id.btn_plus10_B:
                increasePointsByTen(LABEL_B);
                break;
            case R.id.btn_faul_A:
                faul(LABEL_A);
                break;
            case R.id.btn_faul_B:
                faul(LABEL_B);
                break;
            case R.id.btn_reset:
                resetPoints();
                break;
        }
    }

    /**
     * @param team string A/B
     * @author Akshay
     * method to increase score by 15
     */
    private void increasePointsByFifteen(String team) {

        switch (team) {
            case LABEL_A:
                scoreTeamA += 15;
                displayScore(LABEL_A);
                break;
            case LABEL_B:
                scoreTeamB += 15;
                displayScore(LABEL_B);
                break;
        }

    }

    /**
     * @param team string A/B
     * @author Akshay
     * method to increase score by 10
     */
    private void increasePointsByTen(String team) {

        switch (team) {
            case LABEL_A:
                scoreTeamA += 10;
                displayScore(LABEL_A);
                break;
            case LABEL_B:
                scoreTeamB += 10;
                displayScore(LABEL_B);
                break;
        }

    }

    /**
     * @param team string A/B
     * @author Akshay
     * method to update fauls and scores according to fauls
     */
    private void faul(String team) {

        switch (team) {
            case LABEL_A:
                faulsTeamA += 1;
                if (faulsTeamA % 2 == 0) {
                    scoreTeamB += 10;
                    displayScore(LABEL_B);
                }
                displayFauls(LABEL_A);
                break;
            case LABEL_B:
                faulsTeamB += 1;
                if (faulsTeamB % 2 == 0) {
                    scoreTeamA += 10;
                    displayScore(LABEL_A);
                }
                displayFauls(LABEL_B);
                break;
        }

    }

    /**
     * @author Akshay
     * method to reset all points/fauls
     */
    private void resetPoints() {
        scoreTeamA = 0;
        scoreTeamB = 0;
        faulsTeamA = 0;
        faulsTeamB = 0;
        displayScore(LABEL_A);
        displayScore(LABEL_B);
        displayFauls(LABEL_A);
        displayFauls(LABEL_B);
    }

    /**
     * @param team string A/B
     * @author Akshay
     * method to display score
     */
    private void displayScore(String team) {
        switch (team) {
            case LABEL_A:
                if (scoreTeamA == 50) {
                    tv_point_team_a.setText(LABEL_AD);
                    if (tv_point_team_b.getText().toString().equals(LABEL_AD)) {
                        scoreTeamB = 40;
                        tv_point_team_b.setText(String.valueOf(scoreTeamB));
                    }
                } else if (scoreTeamA == 60) {
                    showAlertDialog(LABEL_A);
                } else {
                    tv_point_team_a.setText(String.valueOf(scoreTeamA));
                }
                break;
            case LABEL_B:
                if (scoreTeamB == 50) {
                    tv_point_team_b.setText(LABEL_AD);
                    if (tv_point_team_a.getText().toString().equals(LABEL_AD)) {
                        scoreTeamA = 40;
                        tv_point_team_a.setText(String.valueOf(scoreTeamA));
                    }
                } else if (scoreTeamB == 60) {
                    showAlertDialog(LABEL_B);
                } else {
                    tv_point_team_b.setText(String.valueOf(scoreTeamB));
                }
                break;
        }
    }

    /**
     * @param team string A/B
     * @author Akshay
     * method to display fauls
     */
    private void displayFauls(String team) {
        switch (team) {
            case LABEL_A:
                tv_fauls_team_a.setText(String.format(getString(R.string.format_fauls), faulsTeamA));
                break;
            case LABEL_B:
                tv_fauls_team_b.setText(String.format(getString(R.string.format_fauls), faulsTeamB));
                break;
        }
    }

    /**
     * @param team string A/B
     * @author Akshay
     * method to show alert dialog
     */
    private void showAlertDialog(String team) {
        resetPoints();
        new AlertDialog.Builder(this)
                .setTitle(R.string.label_winner)
                .setMessage(String.format(getString(R.string.format_congrats), team))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

}
