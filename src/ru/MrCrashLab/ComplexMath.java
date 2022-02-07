package ru.MrCrashLab;

public class ComplexMath {
    public static ComplexNum sum(ComplexNum num1, ComplexNum num2) {
        double real = num1.getReal() + num2.getReal();
        double complex = num1.getComplex() + num2.getComplex();
        return new ComplexNum(real, complex);
    }

    public static ComplexNum minus(ComplexNum num1, ComplexNum num2) {
        double real = num1.getReal() - num2.getReal();
        double complex = num1.getComplex() - num2.getComplex();
        return new ComplexNum(real, complex);
    }

    public static ComplexNum multi(ComplexNum num1, ComplexNum num2) {
        double real = num1.getReal() * num2.getReal() - num1.getComplex() * num2.getComplex();
        double complex = num1.getReal() * num2.getComplex() + num2.getReal() * num1.getComplex();
        return new ComplexNum(real, complex);
    }

    public static ComplexNum div(ComplexNum num1, ComplexNum num2) {
        double real = (num1.getReal() * num2.getReal() + num1.getComplex() * num2.getComplex()) / Math.pow(modulus(num2), 2);
        double complex = (num2.getReal() * num1.getComplex() - num1.getReal() * num2.getComplex()) / Math.pow(modulus(num2), 2);
        return new ComplexNum(real, complex);
    }

    public static ComplexNum pow(ComplexNum num, int exp) {
        ComplexNum newNum = num;
        for (int i = 0; i < exp - 1; i++) {
            newNum = multi(num, newNum);
        }
        return new ComplexNum(newNum.getReal(), newNum.getComplex());
    }

    public static double modulus(ComplexNum num) {
        return Math.sqrt(Math.pow(num.getReal(), 2) + Math.pow(num.getComplex(), 2));
    }

    public static ComplexNum sin(ComplexNum num) {
        double real = Math.sin(num.getReal()) * Math.cosh(num.getComplex());
        double complex = Math.cos(num.getReal()) * Math.sinh(num.getComplex());
        return new ComplexNum(real, complex);
    }

    public static ComplexNum cos(ComplexNum num) {
        double real = Math.cos(num.getReal()) * Math.cosh(num.getComplex());
        double complex = -Math.sin(num.getReal()) * Math.sinh(num.getComplex());
        return new ComplexNum(real, complex);
    }

    public static ComplexNum tan(ComplexNum num) {
        ComplexNum sinZ = sin(num);
        ComplexNum cosZ = cos(num);
        return div(sinZ, cosZ);
    }

}
