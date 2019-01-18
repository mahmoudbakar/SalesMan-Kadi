package com.undecode.salesman;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.undecode.salesman.utils.Sender;
import com.undecode.salesman.utils.mail.Mailer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends AppCompatActivity {

    @BindView(R.id.edSubject)
    EditText edSubject;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.message)
    EditText message;
    @BindView(R.id.btnSend)
    Button btnSend;
    Sender sender;
    Mailer mailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        setTitle(getString(R.string.feedback));
        sender = new Sender(this, this);
        mailer = new Mailer("undecode.eg@gmail.com", "Qwerty@12++");
        try {
            edSubject.setText(getIntent().getStringExtra("subject"));
            if (getIntent().getStringExtra("subject").length() > 0) {
                setTitle(getIntent().getStringExtra("subject"));
            }
        } catch (Exception e) {
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("StaticFieldLeak")
    @OnClick(R.id.btnSend)
    public void onViewClicked()
    {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    mailer.sendMail(edSubject.getText().toString(), email.getText().toString()+"\n"+message.getText().toString(), "undecode.eg@gmail.com", "akramaly88@gmail.com", Mailer.textPlain);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }
        }.execute();
    }
}
