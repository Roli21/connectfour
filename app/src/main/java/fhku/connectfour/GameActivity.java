package fhku.connectfour;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout[] columns = new LinearLayout[7];
    private Game game;
    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game = new Game();
        gameMode = getIntent().getExtras().getInt("player");
        //int player = getIntent().getExtras().getInt("player");

        LinearLayout toolbar = (LinearLayout) findViewById(R.id.game_toolbar);                      //Instanz..
        for(int i = 0; i < 7; i++){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f); //f fuer Float

            Button button = new Button(this);
            button.setText("" + (i + 1));
            button.setTag(i);
            button.setLayoutParams(layoutParams);
            button.setOnClickListener(this);
            toolbar.addView(button);
        }

        LinearLayout columnsLayout = (LinearLayout) findViewById(R.id.game_columns);
        for (int j = 0; j < columnsLayout.getChildCount(); j++){
            columns[j] = (LinearLayout) columnsLayout.getChildAt(j);
        }

    }

    public ImageView createImageView(int player){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f);
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.red);
        iv.setLayoutParams(layoutParams);

        if (player == Game.RED){
            iv.setImageResource(R.drawable.red);
        } else {
            iv.setImageResource(R.drawable.yellow);
        }

        return iv;
    }

    public void showFinishedScreen(int gameOver){
        Intent winIntent = new Intent(this, FinishActivity.class);
        winIntent.putExtra("game_finished", gameOver);
        startActivity(winIntent);
    }

    @Override
    public void onClick(View v) {
        int columnIndex = Integer.parseInt(v.getTag().toString());
        if (game.move(columnIndex)){
            this.columns[columnIndex].addView(createImageView(game.getCurrentPlayer()), 0);

            if(this.game.checkGameState() >= 1 || this.game.checkEmpty()){
                showFinishedScreen(this.game.checkGameState());
            } else if(gameMode == 1){
                // Execute code after 1 seconds have passed
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        columns[game.computerMove()].addView(createImageView(game.getCurrentPlayer()), 0);
                        if(game.checkGameState() >= 1 || game.checkEmpty()){
                            showFinishedScreen(3);
                        }
                    }
                }, 1000);
            }
        }

    }
}
