package fhku.connectfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Bundle extras = getIntent().getExtras();
        if(extras.containsKey("game_finished")){
            TextView text = (TextView) findViewById(R.id.text_gameFinished);
            int gameFinished = extras.getInt("game_finished");
            if(gameFinished == 1){
                text.setText("GAME OVER: YELLOW WINS");
            } else if (gameFinished == 2){
                text.setText("GAME OVER: RED WINS");
            } else if (gameFinished == 3) {
                text.setText("GAME OVER: COMPUTER WINS");
            } else {
                text.setText("GAME OVER: DRAW");
            }
        }
    }
}
