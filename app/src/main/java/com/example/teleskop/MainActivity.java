package com.example.teleskop;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private float telescopePrice = 14000;
     private int account = 1000;
     private float salary = 2500;
     private int percentFree = 100;
     private float percentBank = 5;

     private float[] monthlyPayments = new float[120];

     private float telescopePriceWithContribution() {
         return telescopePrice - account;
     }
     private float mortgageCosts(float amount, int percent){
         return (amount*percent)/100;
     }
     private int countMonth (float total, float mortgageCosts, float BankPercent ) {
         float percentPerMonth = percentBank/12;
         int count = 0;
         while (total>0){
             total = (total + (total*percentPerMonth)/100) - mortgageCosts;

             if (total > mortgageCosts){
                 monthlyPayments[count] = mortgageCosts;
             } else {
                 monthlyPayments[count] = total;
             }
             count++;
         }
         return count;
     }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView countMonth = findViewById(R.id.countMonth);
        TextView payments = findViewById(R.id.payments);

        int count = countMonth(telescopePriceWithContribution(), mortgageCosts(salary, percentFree), percentBank);
        countMonth.setText(String.valueOf(count));
        String s ="";

        for (int i = 0; i < count - 1; i++){
            s += monthlyPayments[i] + " ; " ;
        }
        payments.setText(s);

    }
}