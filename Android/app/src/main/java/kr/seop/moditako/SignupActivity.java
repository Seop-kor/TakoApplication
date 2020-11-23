package kr.seop.moditako;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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

public class SignupActivity extends AppCompatActivity {
    private LinearLayout ll_signuplayout
            ,ll_idlayout
            ,ll_passlayout
            ,ll_passchecklayout
            ,ll_namelayout
            ,ll_phonelayout
            ,ll_maillayout;
    private TextView tv_checkstat, tv_idcheckstat;
    private EditText et_id
            ,et_pass
            ,et_passcheck
            ,et_name
            ,et_phone
            ,et_mail;
    private Button bt_signup;

    private boolean passwordstat = false;
    private boolean idcheckstat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_passcheck = findViewById(R.id.et_passcheck);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_mail = findViewById(R.id.et_mail);
        tv_checkstat = findViewById(R.id.tv_checkstat);
        tv_idcheckstat = findViewById(R.id.tv_idcheckstat);
        bt_signup = findViewById(R.id.bt_signup);

        et_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //http통신
                StringRequest stringRequest = new StringRequest(Request.Method.GET
                        , "http://172.30.1.42:8080/idcheck/" + editable.toString()
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            tv_idcheckstat.setText("아이디가 중복됩니다.");
                            tv_idcheckstat.setTextColor(Color.parseColor("#FF0000"));
                        }else{
                            idcheckstat = true;
                            tv_idcheckstat.setText("아이디가 중복되지 않습니다.");
                            tv_idcheckstat.setTextColor(Color.parseColor("#1DDB16"));
                        }
                        tv_idcheckstat.setVisibility(View.VISIBLE);
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error",error.getMessage());
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                queue.add(stringRequest);
            }
        });

        et_passcheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(et_pass.getText().toString().equals(editable.toString())){
                    tv_checkstat.setText("비밀번호가 일치합니다.");
                    tv_checkstat.setTextColor(Color.parseColor("#1DDB16"));
                    passwordstat = true;
                }else{
                    tv_checkstat.setText("비밀번호가 일치하지 않습니다.");
                    tv_checkstat.setTextColor(Color.parseColor("#FF0000"));
                }
                tv_checkstat.setVisibility(View.VISIBLE);
            }
        });

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST
                        , "http://172.30.1.42:8080/register"
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignupActivity.this, "가입이 완료 되었습니다.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getMessage());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("userid", et_id.getText().toString());
                        map.put("userpass", et_pass.getText().toString());
                        map.put("username", et_name.getText().toString());
                        map.put("phone", et_phone.getText().toString());
                        map.put("mail", et_mail.getText().toString());
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                if (!passwordstat){
                    Toast.makeText(SignupActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }else if(!idcheckstat){
                    Toast.makeText(SignupActivity.this, "아이디 중복을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    queue.add(request);
                }
            }
        });
    }
}