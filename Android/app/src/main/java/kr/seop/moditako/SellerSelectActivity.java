package kr.seop.moditako;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SellerSelectActivity extends AppCompatActivity {
    private Button btAuto, btManual;

    private final int AUTO = 1;
    private final int MANUAL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_seller_select);
        btAuto = findViewById(R.id.btAuto);
        btManual = findViewById(R.id.btManual);

        btAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(AUTO);
                finish();
            }
        });

        btManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(MANUAL);
                finish();
            }
        });
    }
}