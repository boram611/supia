package com.example.supia.Activities.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.supia.Activities.Calendar.MainCalendar;
import com.example.supia.Activities.Product.ProductMainActivity;
import com.example.supia.Dto.CalendarDTO;
import com.example.supia.Dto.UserDto;
import com.example.supia.NetworkTask.CalendarNetworkTask;
import com.example.supia.NetworkTask.CalendarNetworkTaskForCheck;
import com.example.supia.NetworkTask.UserInfoNetworkTask;
import com.example.supia.R;
import com.example.supia.ShareVar.ShareVar;
import com.kakao.sdk.user.model.User;

import java.util.ArrayList;
import java.util.HashSet;


public class UserDataQuestion1 extends Activity {

    final static String TAG = "첫번째 질문";
    Button buttonNext;
    DatePicker datePicker;
    TextView tvSkip;
    String userId;
    private String urlAddr;
    ArrayList<CalendarDTO> calendarDtos;
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_question1);

        Intent intent = getIntent();
        userId = ShareVar.sharvarUserId;





        tvSkip = findViewById(R.id.tv_skip_question1);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserDataQuestion1.this,MainCalendar.class);
                startActivity(intent1);

            }
        });

        datePicker = findViewById(R.id.dp_question1);
        insertCheck();//달력 수정했다면 다음으로 넘기


        buttonNext = findViewById(R.id.btn_next_question1);



        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                date = Integer.toString(datePicker.getYear()) + "-" + Integer.toString(datePicker.getMonth() + 1) + "-" + Integer.toString(datePicker.getDayOfMonth());
                Log.d(TAG, date);
                Intent intent1 = new Intent(UserDataQuestion1.this, UserDataQuestion2.class);
                intent1.putExtra("menstruationStart", date);
                intent1.putExtra("userId", userId);
                startActivity(intent1);

            }
        });
    }


    public void insertCheck() {

        Log.v(TAG, "connectGetData()");
        try {
            urlAddr = "http://" + ShareVar.urlIp + ":8080/test/supiaCalendarSelectMens.jsp?"; //localhost나  127.0.0.1을 넣을경우 LOOP가 생길 수 있으므로 할당된 IP 주소를 사용할것

            urlAddr = urlAddr + "userId=" + ShareVar.sharvarUserId;//jsp에 ID값 Request할 수 있게 페이지 설정.
            Log.v(TAG, urlAddr);
            CalendarNetworkTaskForCheck networkTask = new CalendarNetworkTaskForCheck(UserDataQuestion1.this, urlAddr, "select");
            Object obj = networkTask.execute().get(); //obj를 받아들여서
            calendarDtos = (ArrayList<CalendarDTO>) obj; //userInfoDtos 다시 풀기
            String check =  calendarDtos.get(0).getCalendarStartDate();
            Log.d(TAG,check);
            if (!check.equals("null")) {
                Intent intent = new Intent(UserDataQuestion1.this, MainCalendar.class);
                startActivity(intent);

            }else {

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}



