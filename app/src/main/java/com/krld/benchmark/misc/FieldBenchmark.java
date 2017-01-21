package com.krld.benchmark.misc;


import android.os.SystemClock;
import android.support.annotation.NonNull;

public class FieldBenchmark implements Benchmark {

    public static final int MODELS_COUNT = 1_000_000;

    @Override
    public String getName() {
        return "Field Benchmark";
    }

    @Override
    public String run() {
        String result = "";

        result += testSetters("\n1. Get\\Set benchmark: ");
        result += testDirect("\n2. Direct benchmark: ");
        result += testSetters("\n3. Get\\Set benchmark: ");
        result += testDirect("\n4. Direct benchmark: ");
        result += testSetters("\n5. Get\\Set benchmark: ");
        result += testDirect("\n6. Direct benchmark: ");
        result += testSetters("\n7. Get\\Set benchmark: ");

        return result;
    }

    private String testDirect(String name) {
        Model[] models = generateModels();
        int length = models.length;

        Model prevModel = models[0];
        Model currentModel;
        long startTime = SystemClock.elapsedRealtime();
        for (int i = 1; i < length; i++) {

            currentModel = models[i];
            currentModel.a = (prevModel.a);
            currentModel.b = (currentModel.b + currentModel.a);
            currentModel.c = (currentModel.c + currentModel.b);
            currentModel.d = (currentModel.d + currentModel.c);
            currentModel.r = (currentModel.r + currentModel.d);
        }
        long delta = SystemClock.elapsedRealtime() - startTime;

        return name + delta + "ms\n";
    }

    @NonNull
    private String testSetters(String name) {
        Model[] models = generateModels();
        int length = models.length;

        Model prevModel = models[0];
        Model currentModel;
        long startTime = SystemClock.elapsedRealtime();
        for (int i = 1; i < length; i++) {

            currentModel = models[i];
            currentModel.setA(prevModel.getA());
            currentModel.setB(currentModel.getB() + currentModel.getA());
            currentModel.setC(currentModel.getC() + currentModel.getB());
            currentModel.setD(currentModel.getD() + currentModel.getC());
            currentModel.setR(currentModel.getR() + currentModel.getD());
        }
        long delta = SystemClock.elapsedRealtime() - startTime;

        return name + delta + "ms\n";
    }

    private Model[] generateModels() {
        Model[] models = new Model[MODELS_COUNT];
        for (int i = 0; i < MODELS_COUNT; i++) {
            Model model = new Model();
            model.setA(1);
            model.setB(1);
            model.setC(1);
            model.setD(1);
            model.setR(1);
            models[i] = model;
        }
        return models;
    }
}
