package fhku.connectfour;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameActivity extends Activity implements View.OnClickListener {

    private LinearLayout[] columns = new LinearLayout[7];
    private Game game;
    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game = new Game();
        gameMode = getIntent().getExtras().getInt("player");

        LinearLayout toolbar = (LinearLayout) findViewById(R.id.game_toolbar);
        for (int i = 0; i < 7; i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f);

            Button button = new Button(this);
            button.setText("" + (i + 1));
            button.setTag(i);
            button.setLayoutParams(layoutParams);
            button.setOnClickListener(this);
            toolbar.addView(button);
        }

        LinearLayout columnsLayout = (LinearLayout) findViewById(R.id.game_columns);
        for (int j = 0; j < columnsLayout.getChildCount(); j++) {
            this.columns[j] = (LinearLayout) columnsLayout.getChildAt(j);
        }
    }

    public ImageView createImageView(int player) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f);

        ImageView iv = new ImageView(this);
        iv.setLayoutParams(layoutParams);

        if (player == Game.RED) {
            iv.setImageResource(R.drawable.red);
        } else {
            iv.setImageResource(R.drawable.yellow);
        }

        return iv;
    }

    @Override
    public void onClick(View view) {
        int columnIndex = Integer.parseInt(view.getTag().toString());
        if (game.move(columnIndex)) {
            this.columns[columnIndex].addView(createImageView(game.getCurrentPlayer()),0);

            if (gameMode == 1) {
                this.columns[game.computerMove()].addView(createImageView(game.getCurrentPlayer()),0);
            }
        }
    }
}
