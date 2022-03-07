package com.example.tutorialtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitOrder(View view)
    {
        CheckBox login=(CheckBox) findViewById(R.id.login);
        boolean hasLogin=login.isChecked();
        CheckBox login1=(CheckBox) findViewById(R.id.login1);
        boolean hasLogin1=login1.isChecked();
        EditText Name_field=(EditText) findViewById(R.id.name);
        String Name=Name_field.getText().toString();
        //Log.v("MainActivity","Has login"+hasLogin);
       // Log.v("MainActivity","Has login"+hasLogin1);
        if(x>=1&&x<=100) {
            int price = check_price(x, hasLogin, hasLogin1);

            String message = display_order(price, hasLogin, hasLogin1, Name);
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for "+Name);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for "+message);
             //   if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
             //   }
            String message1="Thank You!!!! "+"Your Order is being Prepared"+" Price"+" is"+x*5;
            // display(x);

            display_message(message1);
        }
        else{
            Toast.makeText(MainActivity.this,"Please Enter correct Amount",Toast.LENGTH_SHORT).show();
        }
    }
    public void increment(View view)
    {
        ++x;
        display(x);
    }
    public void decrement(View view)
    {
        x--;
        if(x<1)
        {
            Toast.makeText(MainActivity.this,"Please enter correct quantity",Toast.LENGTH_SHORT).show();
        }
        else
        display(x);
    }
    public void display(int number){
        TextView quantity_text_view=(TextView)findViewById(R.id.quantity_text_view);
        quantity_text_view.setText(""+number);
    }
    private void display_message(String message){
        TextView ank=(TextView)findViewById(R.id.Order_Summary_text_view);
        ank.setTextSize(20);
        ank.setText(message);
    }
    private String display_order(int number,boolean check,boolean checkg,String name) {
       String message="Name: "+name;
       message+="\n Add cream "+check;
       message+="\n Add chocalate "+checkg;
       message+="\n Quantity "+x;
       message+="\n Total "+number;
       message+="\n Thank you";
       return message;


    }
    int check_price(int p,boolean check_cream,boolean check_choco){
        int m=5;
        if(check_cream)
            m+=1;
        if(check_choco)
            m+=2;
        int total=p*m;
        return total;
    }
}