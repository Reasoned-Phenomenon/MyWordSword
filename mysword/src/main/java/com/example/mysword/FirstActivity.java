package com.example.mysword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    Button btn_start;
    EditText et_name;
    RecyclerView rv_list;
    SeekBar sb_time;
    TextView tv_set_time;
    int int_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        DBHelper dbHelper = new DBHelper(this);

        //선언
        btn_start = findViewById(R.id.btn_start);
        et_name = findViewById(R.id.et_name);
        rv_list = findViewById(R.id.rv_list);
        sb_time = findViewById(R.id.sb_time);
        tv_set_time = findViewById(R.id.tv_set_time);

        //리스트
        RecordDAO dao = new RecordDAO();
        ArrayList<RecordVO> list = dao.rankList(dbHelper);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(layoutManager);
        ListAdapter adapter = new ListAdapter(this,list);
        rv_list.setAdapter(adapter);

//        tv_list.setText("등수   이름   점수   등록일자 \n");
//        for(int i =0 ; i < list.size(); i ++) {
//            String str_rank = "" + (i+1) + "   " + list.get(i).name + "   " + list.get(i).score + "   " + list.get(i).time +"\n";
//            tv_list.append(str_rank);
//        }

        sb_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int_time = seekBar.getProgress();
                tv_set_time.setText(String.format("%d 초로 시간 설정",int_time));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int_time = seekBar.getProgress();
                tv_set_time.setText(String.format("%d 초로 시간 설정",int_time));
            }
        });

        //게임 시작 버튼
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //이름 null체크
                if (et_name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"이름을 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //이름 넘기기
                intent.putExtra("name",et_name.getText().toString());
                //TODO 난이도 설정
                intent.putExtra("time",int_time);
                //이름칸 초기화
                et_name.setText("");
                
                startActivity(intent);
            }
        });
    }
}