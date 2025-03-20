package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private TextView winnerMessage;
    private boolean playerTurn = true; // true = X, false = O
    private int turnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);

        // Initialize winner message TextView
        winnerMessage = findViewById(R.id.winnerMessage);
        winnerMessage.setText("Player X's turn");
    }

    // Called when a cell is clicked
    public void onCellClicked(View view) {
        Button buttonClicked = (Button) view;

        // If button already has text (X or O), do nothing
        if (!buttonClicked.getText().toString().equals("")) {
            return;
        }

        // Set X or O based on whose turn it is
        if (playerTurn) {
            buttonClicked.setText("X");
        } else {
            buttonClicked.setText("O");
        }

        turnCount++;

        // Check for a winner
        if (checkForWinner()) {
            String winner = playerTurn ? "X" : "O";
            winnerMessage.setText("Player " + winner + " wins!");
            Toast.makeText(this, "Player " + winner + " wins!", Toast.LENGTH_SHORT).show();
            disableBoard();
        } else if (turnCount == 9) {
            // It's a draw
            winnerMessage.setText("It's a draw!");
            Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
            disableBoard();
        } else {
            // Switch turns
            playerTurn = !playerTurn;
            winnerMessage.setText("Player " + (playerTurn ? "X" : "O") + "'s turn");
        }
    }

    // Check for winner (rows, columns, diagonals)
    private boolean checkForWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i * 3].getText().toString().equals("X") &&
                    buttons[i * 3 + 1].getText().toString().equals("X") &&
                    buttons[i * 3 + 2].getText().toString().equals("X")) {
                return true;
            }
            if (buttons[i * 3].getText().toString().equals("O") &&
                    buttons[i * 3 + 1].getText().toString().equals("O") &&
                    buttons[i * 3 + 2].getText().toString().equals("O")) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().toString().equals("X") &&
                    buttons[i + 3].getText().toString().equals("X") &&
                    buttons[i + 6].getText().toString().equals("X")) {
                return true;
            }
            if (buttons[i].getText().toString().equals("O") &&
                    buttons[i + 3].getText().toString().equals("O") &&
                    buttons[i + 6].getText().toString().equals("O")) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0].getText().toString().equals("X") &&
                buttons[4].getText().toString().equals("X") &&
                buttons[8].getText().toString().equals("X")) {
            return true;
        }
        if (buttons[2].getText().toString().equals("X") &&
                buttons[4].getText().toString().equals("X") &&
                buttons[6].getText().toString().equals("X")) {
            return true;
        }

        if (buttons[0].getText().toString().equals("O") &&
                buttons[4].getText().toString().equals("O") &&
                buttons[8].getText().toString().equals("O")) {
            return true;
        }
        if (buttons[2].getText().toString().equals("O") &&
                buttons[4].getText().toString().equals("O") &&
                buttons[6].getText().toString().equals("O")) {
            return true;
        }

        return false;
    }

    // Disable all buttons after the game is finished
    private void disableBoard() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

    // Reset the game when reset button is clicked
    public void onResetClicked(View view) {
        turnCount = 0;
        playerTurn = true;
        winnerMessage.setText("Player X's turn");

        // Clear all buttons and enable them
        for (Button button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
    }
}
