package com.wzy.killyou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.killyou.models.AppInfo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText disable;
    private EditText enable;
    private Button sub_disable;
    private Button sub_enable;
    private static final String ROOTNAME = "su";
    private EditText test;
    private Button sub_test;
    private TextView result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }



    private void initView() {
        disable = (EditText) findViewById(R.id.disable);
        enable = (EditText) findViewById(R.id.enable);
        sub_disable = (Button) findViewById(R.id.sub_disable);
        sub_disable.setOnClickListener(this);
        sub_enable = (Button) findViewById(R.id.sub_enable);
        sub_enable.setOnClickListener(this);
        test = (EditText) findViewById(R.id.test);
        test.setOnClickListener(this);
        sub_test = (Button) findViewById(R.id.sub_test);
        sub_test.setOnClickListener(this);
        result = (TextView) findViewById(R.id.result);
        result.setOnClickListener(this);
    }

    private void submit(Boolean b) {
        // validate
        String cmd;
        if (b) {
            String enableString = enable.getText().toString().trim();
            if (TextUtils.isEmpty(enableString)) {
                Toast.makeText(this, "enableString不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            cmd = "pm enable " + enableString;
            adbprocess(cmd);
        } else {
            String disableString = disable.getText().toString().trim();
            if (TextUtils.isEmpty(disableString)) {
                Toast.makeText(this, "disableString不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            cmd = "pm disable " + disableString;
            adbprocess(cmd);
        }


    }

    /**
     * BufferedReader reader = new BufferedReader(
     * new InputStreamReader(process.getInputStream()));
     * int read;
     * char[] buffer = new char[4096];
     * StringBuffer output = new StringBuffer();
     * while ((read = reader.read(buffer)) > 0) {
     * output.append(buffer, 0, read);
     * }
     * reader.close();
     * process.waitFor();
     *
     * @param s
     */
    private void adbprocess(String s) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");// the phone must be root,it can exctue the adb command
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(s + "\n");
            os.writeBytes("exit\n");
            os.flush();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            result.setText(output.toString());
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sub_disable:
                submit(false);
                break;
            case R.id.sub_enable:
                submit(true);
                break;
            case R.id.sub_test:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String testString = test.getText().toString().trim();
        if (TextUtils.isEmpty(testString)) {
            Toast.makeText(this, "testString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        adbprocess(testString);

    }
}
