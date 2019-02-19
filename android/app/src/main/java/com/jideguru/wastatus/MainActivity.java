package com.jideguru.wastatus;

import android.content.res.AssetManager;
import android.os.Bundle;
import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "samples.flutter.io/files";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);

      new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(
              (call, result) -> {
                  if (call.method.equals("getImages")) {
                      String imgs = getImages();

                      if (imgs != "") {
                          result.success(imgs);
                      } else {
                          result.error("Empty", "No Images.", null);
                      }
                  } else {
                      result.notImplemented();
                  }


//                  if (call.method.equals("getBatteryLevel")) {
//                      int batteryLevel = getBatteryLevel();
//
//                      if (batteryLevel != -1) {
//                          result.success(batteryLevel);
//                      } else {
//                          result.error("UNAVAILABLE", "Battery level not available.", null);
//                      }
//                  } else {
//                      result.notImplemented();
//                  }
              });
  }

    private int getBatteryLevel() {
        int batteryLevel = -1;
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        } else {
            Intent intent = new ContextWrapper(getApplicationContext()).
                    registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
                    intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }

        return batteryLevel;
    }

    private String getImages(){
        String path1 = Environment.getExternalStorageDirectory().toString()+"/GBWhatsApp/Media/.Statuses";
        String path = Environment.getExternalStorageDirectory().toString()+"/Pictures/Twitter";

        String imgs;
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);

        String paths = "";
        String names ="";
        String sizes;
        for (int i = 0; i < files.length; i++) {
            Log.d("Files", "FileName:" + files[i].getName());
            Log.d("Files", "FileName:" + files[i].getAbsolutePath());

            paths = files[i].getAbsolutePath();
            names = files[i].getName();
            sizes = "0mb";
        }

        String json = "["+"'name:'"+names+"]";
        Log.d("Files", json);
        imgs = String.valueOf(files);

        return imgs;
    }

}
