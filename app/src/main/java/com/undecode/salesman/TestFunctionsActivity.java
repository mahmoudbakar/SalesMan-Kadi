package com.undecode.salesman;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.undecode.salesman.models.local.Visit;
import com.undecode.salesman.utils.Sender;
import com.undecode.salesman.utils.mail.Mailer;
import com.undecode.salesman.utils.mail.Template;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestFunctionsActivity extends AppCompatActivity
{
    @BindView(R.id.edPhone)
    EditText edPhone;
    @BindView(R.id.edMessage)
    EditText edMessage;
    @BindView(R.id.btnSendSms)
    Button btnSendSms;
    Sender sender;
    Mailer mailer;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edSubject)
    EditText edSubject;
    @BindView(R.id.edMail)
    EditText edMail;
    @BindView(R.id.btnSendEmail)
    Button btnSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_functions);
        ButterKnife.bind(this);
        sender = new Sender(this, this);
        mailer = new Mailer("undecode.eg@gmail.com", "Qwerty@12++");
    }

    @OnClick(R.id.btnSendSms)
    public void onSendSms()
    {
        sender.sendSms(edPhone.getText().toString(), edMessage.getText().toString());
    }

    @SuppressLint("StaticFieldLeak")
    @OnClick(R.id.btnSendEmail)
    public void onSendEmail()
    {
            new AsyncTask<String, String, String>() {
                @Override
                protected String doInBackground(String... strings) {
                    try {
//                        List<Visit> visits = new ArrayList<>();
//                        visits.add(new Visit(1, "Mahmoud Emad", "18-12 01:55PM", "Sales Order"));
//                        visits.add(new Visit(1, "Akram", "18-12 02:35PM", "Sales Order"));
//                        visits.add(new Visit(1, "Ehab Mostafa", "18-12 03:12PM", "Unsuccessful"));
//                        visits.add(new Visit(1, "Mahmoud Emad", "18-12 03:43AM", "Payment"));
//                        String message = new Template().summaryReport(visits);
//                        mailer.sendMail(edSubject.getText().toString(), message, "undecode.eg@gmail.com", edEmail.getText().toString(), Mailer.textHTML);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "";
                }
            }.execute();
    }
}
