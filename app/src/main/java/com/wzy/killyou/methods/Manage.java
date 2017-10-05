package com.wzy.killyou.methods;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

/**
 * Created by zy on 2017/3/15.
 */

public class Manage {
    private static final String ROOTNAME = "su";
    private static String cmd;

    public static String Disable(String s) {
        cmd = "pm disable " + s;
        return adbprocess(cmd);
    }

    public static String Enable(String s) {
        cmd = "pm enable " + s;
        return adbprocess(cmd);
    }

    private static String adbprocess(String s) {
        Process process = null;
        String out = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec(ROOTNAME);// the phone must be root,it can exctue the adb command
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
            out = output.toString();
            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();

            } catch (Exception e) {
            }
            return out;
        }
    }


}
