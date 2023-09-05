package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txt_name,txt_age,txt_uage,txt_uname,txt_dname,txt_sname,txt_sage,txt_count,txt_user;
    Button btn_insert,btn_update,btn_delete,btn_first,btn_last,btn_next,btn_prev;

    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_name = findViewById(R.id.txt_name);
        txt_age = findViewById(R.id.txt_age);
        txt_uname = findViewById(R.id.txt_uname);
        txt_uage = findViewById(R.id.txt_uage);
        txt_dname = findViewById(R.id.txt_dname);
        txt_sname = findViewById(R.id.txt_sname);
        txt_sage = findViewById(R.id.txt_sage);
        txt_count = findViewById(R.id.txt_count);
        txt_user = findViewById(R.id.txt_user);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);
        btn_first = findViewById(R.id.btn_first);
        btn_last = findViewById(R.id.btn_last);
        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);
        btn_insert = findViewById(R.id.btn_insert);

        sqLiteOpenHelper = new OPenHelperDB(this);
        sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();


        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txt_name.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "mull Name", Toast.LENGTH_SHORT).show();
                }
                else if(txt_age.getText().length()== 0){
                    Toast.makeText(MainActivity.this, "null Age", Toast.LENGTH_SHORT).show();
                }
                else{
                    int a = Integer.parseInt(txt_age.getText().toString());
                    String b =txt_name.getText().toString();

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Name",b);
                    contentValues.put("Age",a);
                    sqLiteDatabase.insert("USER",null,contentValues);
                    Toast.makeText(MainActivity.this, "Insert success", Toast.LENGTH_SHORT).show();
                    txt_name.setText("");
                    txt_age.setText("");
                    cursor = sqLiteDatabase.query("USER", new String[]{"Name", "Age"}, null, null, null, null, null);
                    int c = cursor.getCount();
                    if(c == 0 || c == 1){
                       txt_user.setText("USER");
                    }
                    else {
                        txt_user.setText("USERS");
                        txt_count.setText(String.valueOf(c));
                    }

                }

            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_uname.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "null Name", Toast.LENGTH_SHORT).show();
                }
                if(txt_uage.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "null age", Toast.LENGTH_SHORT).show();
                }
                else{
                    int a = Integer.parseInt(txt_uage.getText().toString());
                    String b =txt_uname.getText().toString();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Age",a);
                    sqLiteDatabase.update("USER",contentValues,"Name=?",new String[]{b});
                    Toast.makeText(MainActivity.this, "Update success", Toast.LENGTH_SHORT).show();
                    txt_uname.setText("");
                    txt_uage.setText("");
                    cursor = sqLiteDatabase.query("USER", new String[]{"Name", "Age"}, null, null, null, null, null);
                    int c = cursor.getCount();
                    if(c == 0 || c == 1){
                        txt_user.setText("USER");
                    }
                    else {
                        txt_user.setText("USERS");
                        txt_count.setText(String.valueOf(c));
                    }

                }

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("DELETE User").setMessage("Are you sure delete user ? ").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(txt_dname.getText().length() == 0){
                            Toast.makeText(MainActivity.this, "null name", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String b =txt_dname.getText().toString();
                            sqLiteDatabase.delete("USER","Name=?",new String[]{b});
                            Toast.makeText(MainActivity.this, "Delete success", Toast.LENGTH_SHORT).show();
                            txt_dname.setText("");
                            cursor = sqLiteDatabase.query("USER", new String[]{"Name", "Age"}, null, null, null, null, null);
                            int c = cursor.getCount();
                            if(c == 0 || c == 1){
                                txt_user.setText("USER");
                            }
                            else {
                                txt_user.setText("USERS");
                                txt_count.setText(String.valueOf(c));
                            }
                        }
                    }
                }).setNegativeButton("No",null).show();



            }
        });

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cursor = sqLiteDatabase.query("USER", new String[]{"Name", "Age"}, null, null, null, null, null);
                    cursor.moveToFirst();
                    int c = cursor.getCount();
                    if(c == 0 || c == 1){
                        txt_user.setText("USER");
                    }
                    else {
                        txt_user.setText("USERS");
                        txt_count.setText(String.valueOf(c));
                    }
                    txt_sname.setText(String.valueOf(cursor.getString(0)));
                    txt_sage.setText(String.valueOf(cursor.getString(1)));
                    Toast.makeText(MainActivity.this, "Move to first", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "First Insert values", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cursor = sqLiteDatabase.query("USER", new String[]{"Name,Age"}, null, null, null, null, null);
                    cursor.moveToLast();
                    int c = cursor.getCount();
                    if(c == 0 || c == 1){
                        txt_user.setText("USER");
                    }
                    else {
                        txt_user.setText("USERS");
                        txt_count.setText(String.valueOf(c));
                    }
                    txt_sname.setText(String.valueOf(cursor.getString(0)));
                    txt_sage.setText(String.valueOf(cursor.getString(1)));
                    Toast.makeText(MainActivity.this, "Move to Last", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "First Insert values", Toast.LENGTH_SHORT).show();
                }




            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    cursor = sqLiteDatabase.query("USER", new String[]{"Name,Age"}, null, null, null, null, null);
                    cursor.moveToLast();
                    int c = cursor.getCount();
                    if(c == 0 || c == 1){
                        txt_user.setText("USER");
                    }
                    else {
                        txt_user.setText("USERS");
                        txt_count.setText(String.valueOf(c));
                    }
                    txt_sname.setText(String.valueOf(cursor.getString(0)));
                    txt_sage.setText(String.valueOf(cursor.getString(1)));
                    Toast.makeText(MainActivity.this, "Move to previews", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "First Insert values", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    cursor = sqLiteDatabase.query("USER", new String[]{"Name,Age"}, null, null, null, null, null);
                    cursor.moveToNext();
                    int c = cursor.getCount();
                    if(c == 0 || c == 1){
                        txt_user.setText("USER");
                    }
                    else {
                        txt_user.setText("USERS");
                        txt_count.setText(String.valueOf(c));
                    }
                    txt_sname.setText(String.valueOf(cursor.getString(0)));
                    txt_sage.setText(String.valueOf(cursor.getString(1)));
                    Toast.makeText(MainActivity.this, "Move yo next", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "First Insert values", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override

    public  void  onBackPressed(){

        new AlertDialog.Builder(this).setMessage("Do you want to exit ? ").setTitle("EXIT").setCancelable(false).setPositiveButton("Yes"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No",null).show();




    }


}