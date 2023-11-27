package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;
    private TextView playerResult;
    private TextView computerResult;
    private Button rock;
    private Button paper;
    private Button scissors;
    private int playerScore;
    private int botScore;
    private int playerChoice;
    private int computerChoice;
    private AlertDialog.Builder alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        rock.setOnClickListener(v -> {
            imageView1.setImageResource(R.drawable.rock);
            playerChoice = 0; //rock
            computerChoice = randomChoice();
            checkTheWinner(playerChoice, computerChoice);
            gameOver(playerScore, botScore);
        });
        paper.setOnClickListener(v -> {
            imageView1.setImageResource(R.drawable.paper);
            playerChoice = 1; //paper
            computerChoice = randomChoice();
            checkTheWinner(playerChoice, computerChoice);
            gameOver(playerScore, botScore);
        });
        scissors.setOnClickListener(v -> {
            imageView1.setImageResource(R.drawable.scissors);
            playerChoice = 2; //scissors
            computerChoice = randomChoice();
            checkTheWinner(playerChoice, computerChoice);
            gameOver(playerScore, botScore);
        });
    }

    public int randomChoice() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        switch (randomNumber) {
            case 0:
                imageView2.setImageResource(R.drawable.rock);
                break;
            case 1:
                imageView2.setImageResource(R.drawable.paper);
                break;
            case 2:
                imageView2.setImageResource(R.drawable.scissors);
                break;
        }
        return randomNumber;
    }

    public void checkTheWinner(int playerChoice, int computerChoice) {
        // Lehetőségek ellenőrzése és pontszám növelése
        if (playerChoice == computerChoice) {
            Toast.makeText(MainActivity.this, "Döntetlen!", Toast.LENGTH_SHORT).show();
        } else if ((playerChoice == 0 && computerChoice == 2) ||
                (playerChoice == 1 && computerChoice == 0) ||
                (playerChoice == 2 && computerChoice == 1)) {
            // Player nyer
            playerScore++;
            playerResult.setText(String.valueOf(playerScore));
            Toast.makeText(MainActivity.this, "Gratulálok, te nyertél!", Toast.LENGTH_SHORT).show();
        } else {
            // Bot nyer
            botScore++;
            computerResult.setText(String.valueOf(botScore));
            Toast.makeText(MainActivity.this, "Sajnálom, a gép nyert!", Toast.LENGTH_SHORT).show();
        }
    }

    public void gameOver(int playerScore, int botScore) {
        if (playerScore == 3) {
            alertDialog.setTitle("Gratulálok, nyertél!");
            alertDialog.create();
            alertDialog.show();
        } else if (botScore == 3) {
            alertDialog.setTitle("Sajnálom, vesztettél!");
            alertDialog.create();
            alertDialog.show();
        }
    }

    public void newGame() {
        imageView1.setImageResource(R.drawable.rock);
        imageView2.setImageResource(R.drawable.rock);
        playerResult.setText("0");
        computerResult.setText("0");
        playerScore = 0;
        botScore = 0;
    }


/*
    public void checkTheWinner() {
        //Döntetlen-ek vizsgálata:
        if (imageView1 == findViewById(R.id.rock) && imageView2 == findViewById(R.id.rock)) {
            Toast.makeText(MainActivity.this, "Döntetlen!", Toast.LENGTH_SHORT).show();
        } else if (imageView1 == findViewById(R.id.paper) && imageView2 == findViewById(R.id.paper)) {
            Toast.makeText(MainActivity.this, "Döntetlen!", Toast.LENGTH_SHORT).show();
        } else if (imageView1 == findViewById(R.id.scissors) && imageView2 == findViewById(R.id.scissors)) {
            Toast.makeText(MainActivity.this, "Döntetlen!", Toast.LENGTH_SHORT).show();
        }
        //Lehetséges győzelmek:
        else if (imageView1 == findViewById(R.id.rock) && imageView2 == findViewById(R.id.paper)) {
            botScore++;
            computerResult.setText(botScore);
        } else if (imageView1 == findViewById(R.id.rock) && imageView2 == findViewById(R.id.scissors)) {
            playerScore++;
            playerResult.setText(playerScore);
        } else if (imageView1 == findViewById(R.id.paper) && imageView2 == findViewById(R.id.scissors)) {
            botScore++;
            computerResult.setText(botScore);
        }
    }

 */

    public void init() {
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        playerResult = findViewById(R.id.playerResult);
        computerResult = findViewById(R.id.computerResult);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissors = findViewById(R.id.scissors);
        alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog
                .setMessage("Szeretnél új játékot játszani?")
                .setNegativeButton("Nem", (dialog, i) -> finish())
                .setPositiveButton("Igen", (dialog, i) -> newGame())
                .setCancelable(false)
                .create();
    }
}