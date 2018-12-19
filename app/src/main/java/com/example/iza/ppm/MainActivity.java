package com.example.iza.ppm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.text.DecimalFormat;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        TextView wynik = (TextView) findViewById(R.id.ppm_wynik);

        Spinner spinnerPlec = (Spinner) findViewById(R.id.opcjaPlci);
        String plec = String.valueOf(spinnerPlec.getSelectedItem());

        Spinner spinnerWzrost = (Spinner) findViewById(R.id.opcjaWzrostu);
        String jednostkaWzrostu = String.valueOf(spinnerWzrost.getSelectedItem());

        DatePicker dataUrodzenia = (DatePicker) findViewById(R.id.dataUrodzenia);

        Spinner spinnerSposob = (Spinner) findViewById(R.id.opcjaSposob);
        String sposobObliczen = String.valueOf(spinnerSposob.getSelectedItem());

        EditText poleWzrost = (EditText) findViewById(R.id.wzrost);
        EditText poleMasa = (EditText) findViewById(R.id.masa);

        try {

            double wzrost = Double.valueOf(poleWzrost.getText().toString());
            double masa = Double.valueOf(poleMasa.getText().toString());

            LocalDate data = new LocalDate(dataUrodzenia.getYear(), dataUrodzenia.getMonth(), dataUrodzenia.getDayOfMonth());
            int wiek = new Period(data, LocalDate.now()).getYears();

            if (wzrost <= 0 || masa <= 0 || wiek < 0) throw new NumberFormatException();

            double PPM;
            if (jednostkaWzrostu.equals("m")) wzrost = wzrost * 100;

            PPM ppm = new PPM(wzrost, masa, wiek, plec);
            if (sposobObliczen.equals("wzor Benedicta-Harrisa")) {
                PPM = ppm.benedictHarris();
            } else {
                PPM = ppm.mifflin();
            }

            DecimalFormat twoDFFormat = new DecimalFormat("#.##");
            wynik.setText("PPM: " + String.valueOf(twoDFFormat.format(PPM) + "\nwiek: " + wiek));


        } catch (NumberFormatException e) {
            wynik.setText("Nieprawidlowe dane");
        }

    }
}
