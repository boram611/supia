package com.example.supia.Activities.Payment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.supia.NetworkTask.DeliveryAddressNetWorkTask;
import com.example.supia.R;
import com.example.supia.ShareVar.ShareVar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PaymentBankActivity extends Activity {

    TextView bankItem;
    EditText bankNumber, bankUserName;
    Button btnNext;
    String urlAddr = null;
    String urlIp = null;
    String strOrderDate;
    String strOrderQuantity;
    String strOrderAddr;
    String strOrderAddrDetail;
    String strOrderPayment;
    String strOrderTotalPrice;
    String userId;
    String productId;
    String strItem;
    String strOrderTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_bank);

        Intent intent = getIntent();
        strItem = intent.getStringExtra("ITEM");
        userId = intent.getStringExtra("userId");
        strOrderAddr = intent.getStringExtra("orderAddr");
        strOrderAddrDetail = intent.getStringExtra("orderAddrDetail");
        strOrderQuantity = "1";//intent.getStringExtra("orderQuantity");
        strOrderTotalPrice = "1"; // intent.getStringExtra("orderTotalPrice");
        productId  = "1"; //intent.getStringExtra("productId");
        strOrderTel = intent.getStringExtra("orderTel");
        bankUserName = findViewById(R.id.bankNameEditText);
        bankNumber = findViewById(R.id.bankNumberEditText);

        btnNext = findViewById(R.id.btn_paymentNext_Bankpayment);
        btnNext.setOnClickListener(mOnclickListener);

        bankItem = findViewById(R.id.tv_itemselected_paymentBank);
        bankItem.setText(strItem);


    }


    View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.btn_paymentNext_Bankpayment:

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String today = df.format(calendar.getTime());//파일에도 날짜를 넣기위한 메소드


                    urlIp = ShareVar.urlIp;
                    urlAddr = "http://" + urlIp + ":8080/test/supiaUserOrderInsert.jsp?";//바로 삭제 할 수 있도록 한다.(따로 페이지 필요 X)
                    if (bankItem.getText().toString().trim().length() >= 10) {//임의로 10자리만큼 큰걸로
                        new AlertDialog.Builder(PaymentBankActivity.this)
                                .setTitle("구매")
                                .setMessage("진짜로 구매 하시겠습니까?")
                                .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                                .setPositiveButton("아니오", null)
                                .setNegativeButton("예", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        urlAddr = urlAddr + "orderDate=" + today + "&orderQuantity=" + strOrderQuantity + "&orderAddr=" + strOrderAddr + "&orderAddrDetail=" + strOrderAddrDetail
                                                + "&orderPayment=" + "은행" + "&orderTotalPrice=" + strOrderTotalPrice + "&userId=" + userId + "&productId=" + productId+"&orderTel="+strOrderTel;
                                        connectInsertData();

                                        new AlertDialog.Builder(PaymentBankActivity.this)
                                                .setTitle("완료")
                                                .setMessage("구매가 완료 되었습니다.")
                                                .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                                                .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Intent intent = new Intent(PaymentBankActivity.this, PaymentConfirmActivity.class);//
                                                        startActivity(intent);
                                                    }
                                                })
                                                .show();
                                    }
                                }).show();


                        Log.d("수정및삭", "들어가나?");
                        break;
                    } else {
                        new AlertDialog.Builder(PaymentBankActivity.this)
                                .setTitle("계좌번호 오류")
                                .setMessage("유효한 계좌번호를 입력해주세요.")
                                .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                                .setPositiveButton("닫기", null)
                                .show();

                    }
            }
        }


    };

    private void connectInsertData() {
        try {
            Log.d("확인", "connectInsertData");
            DeliveryAddressNetWorkTask insertworkTask = new DeliveryAddressNetWorkTask(PaymentBankActivity.this, urlAddr, "insert");
            insertworkTask.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}