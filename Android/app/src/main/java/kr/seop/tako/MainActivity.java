package kr.seop.tako;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent it = new Intent(MainActivity.this, SelectActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(it);
                finish();
            }
        }, 1000);
    }
}