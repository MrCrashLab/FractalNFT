package ru.MrCrashLab;

public class Main {
    public static void main(String[] args) {
//        ComplexNum num1 = new ComplexNum(1,1);
//        ComplexNum num2 = new ComplexNum(2,2);
        GradientCreator gradientCreator = new GradientCreator();
        gradientCreator.createGradient();
        FractalCreator fractalCreator = new FractalCreator();
        fractalCreator.createFractal();

    }
}
