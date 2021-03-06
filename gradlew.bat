package com.example.myhero;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaCodec;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    ImageView iv_selected;
    EditText et_guess;
    Button btn_guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_selected = findViewById(R.id.iv_selected);
        et_guess = findViewById(R.id.et_guess);
        btn_guess = findViewById(R.id.btn_guess);

        //화면 로드시, volly 사용 -> 사진, 성함 가져오기
        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();
//        String key = "J1iKzUvTVqcFCVxqpleYhdt1GIsEP40ONa4slM5d1aIgGDV4GU7IXV%2BHQGq4O%2Fm8pFm6GPTRrPXgbNxz4fV3pA%3D%3D";
        String url = "http://apis.data.go.kr/1383000/eyis/womenIndependeceActivistService/getWomenIndependeceActivistList?serviceKey="
                +"J1iKzUvTVqcFCVxqpleYhdt1GIsEP40ONa4slM5d1aIgGDV4GU7IXV%2BHQGq4O%2Fm8pFm6GPTRrPXgbNxz4fV3pA%3D%3D"
                +"&pageNo=1&numOfRows=250&type=json";
        StringRequest request = new StringRequest(url,s->{
            System.out.println("111");

            Map<String,Object> map = gson.fromJson(s, Map.class);
//            System.out.println(map);
//            System.out.println(map);
        },e->{
            System.out.println(e);
        });

        queue.add(request);

        //버튼 클릭
        btn_guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strGuess = et_guess.getText().toString();

            }
        });
        
    }
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      