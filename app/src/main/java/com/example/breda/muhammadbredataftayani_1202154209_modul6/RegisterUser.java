package com.example.breda.muhammadbredataftayani_1202154209_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {
    EditText rgEmail,rgPassword;
    Button rgRegister;
    TextView rgLogin;
    String emails,passwords;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        auth=FirebaseAuth.getInstance();

        if (auth.getCurrentUser()!=null){
            Intent intent=new Intent(this,LoginUser.class);
            startActivity(intent);
        }

        rgEmail=(EditText)findViewById(R.id.rgEmail);
        rgPassword=(EditText)findViewById(R.id.rgPass);
        rgRegister=(Button)findViewById(R.id.rgLogin);
        rgLogin=(TextView)findViewById(R.id.rgMove);

        rgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterUser.this, LoginUser.class);
                startActivity(intent);
            }
        });

        rgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }
    public void register(){
        emails=rgEmail.getText().toString();
        passwords=rgPassword.getText().toString();

        if(TextUtils.isEmpty(emails)){
            Toast.makeText(getApplicationContext(),"input email",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(passwords)){
            Toast.makeText(getApplicationContext(),"input password",Toast.LENGTH_SHORT).show();
        }

        auth.createUserWithEmailAndPassword(emails,passwords)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent=new Intent(RegisterUser.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"gagal daftar",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
