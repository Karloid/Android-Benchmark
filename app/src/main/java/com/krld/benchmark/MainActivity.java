package com.krld.benchmark;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;

import com.krld.benchmark.misc.Benchmark;
import com.krld.benchmark.misc.FieldBenchmark;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Benchmark> benchmarks = new ArrayList<>();
        benchmarks.add(new FieldBenchmark());

        addButtons(benchmarks);


    }

    private void addButtons(List<Benchmark> benchmarks) {
        ViewGroup root = (ViewGroup) findViewById(R.id.content);
        for (final Benchmark benchmark : benchmarks) {
            AppCompatButton button = new AppCompatButton(this);
            button.setText(benchmark.getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String result = benchmark.run();
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Test Result : " + benchmark.getName())
                            .setMessage(result)
                            .show();
                }
            });
            root.addView(button);
        }
    }
}
