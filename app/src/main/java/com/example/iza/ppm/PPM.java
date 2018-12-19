package com.example.iza.ppm;

public class PPM {

    private double wzrost;
    private double masa;
    private int wiek;
    private String plec;

    public PPM(double wzrost, double masa, int wiek, String plec) {
        this.wzrost = wzrost;
        this.masa = masa;
        this.wiek = wiek;
        this.plec = plec;
    }

    public double benedictHarris() {
        double PPM = 0;
        if (plec.equals("kobieta"))
            PPM = 655.1 + 9.563 * masa + 1.85 * wzrost - 4.676 * wiek;
        else PPM = 66.5 + 13.75 * masa + 5.003 * wzrost - 6.775 * wiek;
        return PPM;
    }

    public double mifflin() {
        double PPM = 0;
        if (plec.equals("kobieta"))
            PPM = 10 * masa + 6.25 * wzrost - 5 * wiek - 161;
        else PPM = 10 * masa + 6.25 * wzrost - 5 * wiek + 5;
        return PPM;
    }


}
