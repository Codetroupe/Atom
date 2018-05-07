package com.trevonn.atom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.trevonn.molecule.logger.AndroidLogAdapter;
import com.trevonn.molecule.logger.Logger;

public class MainActivity extends AppCompatActivity {



    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.addLogAdapter(new AndroidLogAdapter());
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                printLog();
            }
        });

        Logger.json("{ \"key\": 3, \"value\": something}");

    }



    private void printLog(){
//        Logger.t("tag").e("Custom tag for only one use");

        Logger.json("{ \"key\": 3, \"value\": something}");

//        Logger.d(Arrays.asList("foo", "bar"));

//        Map<String, String> map = new HashMap<>();
//        map.put("key", "value");
//        map.put("key1", "value2");
//
//        Logger.d(map);

    }


}
