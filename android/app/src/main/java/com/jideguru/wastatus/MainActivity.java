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
import java.util.ArrayList;
import java.util.Arrays;
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
                      List<String> imgs = getImages();
                      if (imgs.size() <= 0) {
                          result.error("Empty", "No Images.", null);
                      } else {
                          result.success(imgs);                      }
                  } else {
                      result.notImplemented();
                  }

              });
  }


    private List<String> getImages(){
        String path1 = Environment.getExternalStorageDirectory().toString()+"/GBWhatsApp/Media/.Statuses";
        String path = Environment.getExternalStorageDirectory().toString()+"/Pictures/Twitter";

        List<String> imgs = new ArrayList<String>();
//        String imgs;
//        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        List<String> files = Arrays.asList(directory.list());
        Log.d("Files", "files: "+ files.toString());


        imgs = files;

        return imgs;
    }

}
