package kr.seop.moditako;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ManualAddressActivity extends AppCompatActivity {
    private Button btManualOk, btManualCancle;
    private EditText etManual;

    private final int OK = 3;
    private final int CANCLE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_manual_address);

        btManualOk = findViewById(R.id.btManualOk);
        btManualCancle = findViewById(R.id.btManualCancle);

        etManual = findViewById(R.id.etManual);

        btManualOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent();
                it.putExtra("address", etManual.getText().toString());
                setResult(OK, it);
                finish();
            }
        });

        btManualCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(CANCLE);
                finish();
            }
        });
    }
}