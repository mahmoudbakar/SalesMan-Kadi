package com.undecode.salesman;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.undecode.salesman.models.LoginResponse;
import com.undecode.salesman.utils.network.API;
import com.undecode.salesman.utils.network.OnDataReady;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edID)
    EditText edID;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.txtForgotPassword)
    TextView txtForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @OnClick(R.id.btnLogin)
    public void onBtnLoginClicked()
    {
        if (edID.getText().toString().equals("aglan") && edPassword.getText().toString().equals("1"))
        {
            API.getInstance().login(this, edID.getText().toString(), edPassword.getText().toString(), new OnDataReady.ObjectReady()
            {
                @Override
                public void onObjectReady(Object object)
                {
                    if (object != null)
                    {
                        try
                        {
                            LoginResponse loginResponse = ((LoginResponse) object);
                            if (!loginResponse.getAccessToken().equals(""))
                            {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else
                            {
                                Toast.makeText(LoginActivity.this, getString(R.string.wrong_credentials), Toast.LENGTH_SHORT).show();
                            }
                        }catch (ClassCastException e)
                        {
                            Toast.makeText(LoginActivity.this, getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
                        }
                    }else
                    {
                        Toast.makeText(LoginActivity.this, LoginActivity.this.getString(R.string.wrong_credentials), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Object object)
                {

                }
            }, true);
        }
    }

    @OnClick(R.id.txtForgotPassword)
    public void onTxtForgotPasswordClicked()
    {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialogs);
//        builder.setTitle("Recover password");
//        builder.setMessage("Enter your registered email\nwe will send you a new password");
//
//        RelativeLayout layout = (RelativeLayout) getLayoutInflater().inflate(R.layout.input_dialog, null);
//        // Set up the input
//        final EditText input = layout.findViewById(R.id.edInput);
//        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//        input.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
//        input.setHint("email@anything.com");
//        builder.setView(layout);
//
//        builder.setPositiveButton("Send", new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(final DialogInterface dialog, int which)
//            {
//                Toast.makeText(LoginActivity.this, "Not Activated Yet..", Toast.LENGTH_SHORT).show();
//                dialog.cancel();
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//                dialog.cancel();
//            }
//        });
//
//        builder.show();
    }
}
