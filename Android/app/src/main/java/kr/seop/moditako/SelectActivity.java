package kr.seop.moditako;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SelectActivity extends AppCompatActivity {
    private Button seller_button;
    private Button buyer_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        seller_button = findViewById(R.id.seller_button);
        buyer_button = findViewById(R.id.buyer_button);
        buyer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SelectActivity.this, BuyerActivity.class);
                startActivity(it);
                finish();
            }
        });
    }

    public void seller(View v) {
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );
        linearLayout.setLayoutParams(param);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        final EditText id = new EditText(this);
        id.setHint("아이디를 입력해주세요.");
        linearLayout.addView(id);
        final EditText password = new EditText(this);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password.setHint("패스워드를 입력해주세요.");
        linearLayout.addView(password);
        Button login_button = new Button(this);
        login_button.setText("로그인");
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST
                ,"http://172.30.1.42:8080/login"
                ,new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("null")){
                            Toast.makeText(SelectActivity.this, "아이디나 비밀번호가 맞지않습니다.", Toast.LENGTH_LONG).show();
                        }else{
                            if(!response.equals("true")) {
                                CookieManager cookieManager = CookieManager.getInstance();
                                cookieManager.setAcceptCookie(true);
                                cookieManager.setCookie("token", response);
                            }
                            Intent it = new Intent(SelectActivity.this, SellerActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }
                }
                ,new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("userid", id.getText().toString());
                        map.put("userpass", password.getText().toString());
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(SelectActivity.this);
                queue.add(stringRequest);
            }
        });
        linearLayout.addView(login_button);
        TextView textView = new TextView(this);
        textView.setText("회원가입");
        textView.setTextSize(18);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SelectActivity.this, SignupActivity.class);
                startActivity(it);
            }
        });
        textView.setGravity(Gravity.CENTER);
        linearLayout.addView(textView);
        setContentView(linearLayout);
    }

}