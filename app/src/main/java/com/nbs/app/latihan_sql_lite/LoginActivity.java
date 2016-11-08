package com.nbs.app.latihan_sql_lite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Cursor cursor;
    String[] daftar;
    DataHelper dblogin;

    //Email, Password editText
    EditText txtUsername, txtPassword;

    // isi Shared Preferences
    private final String NAMA = "Admin";
    private final String EMAIL = "mohamaddenisjs@gmail.com";

    // Username dan password
    private final String USERNAME = "denis";
    private final String PASSWORD = "denis";

    // login button
    Button btnLogin;

    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager
        session = new SessionManager(getApplicationContext());
        // Email, Password input text
        txtUsername = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
        // Menampilkan Status Login dengan Toast
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        // Login button
        btnLogin = (Button) findViewById(R.id.email_sign_in_button);
        // Login button onClick event
        dblogin = new DataHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                SQLiteDatabase db = dblogin.getReadableDatabase();
                cursor = db.rawQuery("SELECT * FROM login",null);
                daftar = new String[cursor.getCount()];
                cursor.moveToFirst();

                for (int cc=0; cc < cursor.getCount(); cc++){
                    cursor.moveToPosition(cc);
                    daftar[cc] = cursor.getString(1).toString() ;
                }

                // cek jika username dan password terisi
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    // jika username dan password sesuai dengan data
                    if(username.equals(cursor.getString(1).toString()) && password.equals(cursor.getString(2).toString())){
                        // membuat user login session                   // menyimpan data nama dan email
                        session.createLoginSession(cursor.getString(1).toString(), cursor.getString(2).toString());
                        // Mengarahkan ke MainActivity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);                   finish();

                    }else{
                        // username / password tidak sesuai
                        Toast.makeText(getApplicationContext(), "Login Gagal..\n"
                                + "Username/Password anda salah", Toast.LENGTH_LONG).show();
                    }
                }else{
                    // user tidak memasukan username atau password
                    Toast.makeText(getApplicationContext(), "Login Gagal..\n" +
                            "Silahlkan masukan username dan password", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
