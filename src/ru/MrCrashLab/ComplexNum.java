package ru.MrCrashLab;

public class ComplexNum {
    private double real;
    private double complex;

    public ComplexNum(double real, double complex) {
        this.real = real;
        this.complex = complex;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getComplex() {
        return complex;
    }

    public void setComplex(double complex) {
        this.complex = complex;
    }
}
