package fhku.connectfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class StartActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ViewGroup vg = (ViewGroup) findViewById(R.id.game_options);

        for (int i = 0; i < vg.getChildCount(); i++) {
            vg.getChildAt(i).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        int player = Integer.parseInt(view.getTag().toString());

        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("player",player);

        startActivity(intent);
    }
}
