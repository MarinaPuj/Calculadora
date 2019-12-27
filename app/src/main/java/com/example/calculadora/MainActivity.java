package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

     String op = "";
     double numfinal=0;
     String ops = "";
     String tri1 = "";
     String tri2="";
     int trigo=0;
     int numops=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        op="";
        trigo=0;
    }
    public void clearButton(View view){
        numfinal=0;
        ops="";
        trigo=0;
        tri2="";
        tri1="";
        op="";
        numops=0;
        refreshBox();
        refreshResult();
    }
    public void refreshBox(){
        TextView txt = (TextView) findViewById(R.id.operacion);
        txt.setText(tri1+op+tri2);
    }
    public void refreshResult(){
        //Fer display del resultat final
        TextView res = (TextView) findViewById(R.id.result);
        res.setText(String.valueOf(numfinal));
    }
    public void clickButton(View view){
        Button boto = (Button) view;
        String number = boto.getText().toString();
        op = op + number;
       // double numberdouble = Double.parseDouble(number);
       // num = num + numberdouble;
        refreshBox();
    }

    public void signButton(View view){
        Button boto = (Button) view;
        String sign = boto.getText().toString();
        op = op + sign ;
        ops+=sign;
        numops = numops +1;
        //refreshBox();
    }

    public void trigoButton (View view){
        Button boto = (Button) view;
        tri1 = boto.getText().toString() +"(";
        tri2=")";
        trigo=1;
        refreshBox();
    }

    public double result (View view) {
        double numFinal=0;
        double[] nums = new double[numops + 1];
        int a=0;
        int b=0;
        String lletra="";

        for(int i=0; i<op.length();i++){
            //llegir les operacions i guardar a nums[] els numeros.
            lletra=op.substring(i,i+1);
            if((lletra.equals("+")) ||(lletra.equals("-")) ||(lletra.equals("*")) ||(lletra.equals("/")))
            {
                nums[a] = Double.parseDouble(op.substring(b, i));
                a++;
                b=i+1;
            }
        }
        nums[a]=Double.parseDouble((op.substring(b,op.length())));

        //Fer les operacions d'esquerra a dreta
        for(int i=0;i<ops.length();i++){
            switch (ops.substring(i,i+1)){
                case "+":
                    numFinal=nums[0]+nums[1];
                    break;
                case "-":
                    numFinal=nums[0]-nums[1];
                    break;
                case "*":
                    numFinal=nums[0]*nums[1];
                    break;
                case "/":
                    numFinal=nums[0]/nums[1];
                    break;
            }
            nums[0]=numFinal;
            for(int ii=1; ii<nums.length-1;ii++){
                nums[ii]=nums[ii+1];
            }
        }
        if(numops == 0){
            numFinal=Double.parseDouble(op);
        }
        return numFinal;
    }

    public void resultButton(View view){

        numfinal = result(view);
        if (trigo==1) {
            String rad="";
            Switch s1 = (Switch) findViewById(R.id.radians);
            if (s1.isChecked()) {
                rad = s1.getTextOn().toString();
            }
            else {
                rad = s1.getTextOff().toString();
                numfinal = Math.toRadians(numfinal);
            }
         //   Log.i("rad: (1 si 0 no)", rad);
         //   Log.i("numfabanstri:",String.valueOf(numfinal));
            switch (tri1.substring(0,3)){
                case "sin":
                    numfinal=Math.round(Math.sin(numfinal)*100);
                    numfinal=numfinal/100;
                    break;
                case "cos":
                    numfinal=Math.round(Math.cos(numfinal)*100);
                    numfinal=numfinal/100;
                    break;
                case "tan":
                    numfinal=Math.round(Math.tan(numfinal)*100);
                    numfinal=numfinal/100;
                    break;
            }
        }
        refreshResult();
        }

    }