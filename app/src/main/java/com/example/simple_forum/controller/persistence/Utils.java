package com.example.simple_forum.controller.persistence;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.simple_forum.controller.application.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils{

    private static Context context = null;

    public Utils(Context context){
        this.context = context;
    }

    public Utils(){}

    public boolean has_context(){ return context != null; }

    private void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = context.getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    public boolean copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = context.getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPath(dataDirectory.toString() + "/" + Main.getDBName());
            Log.i("TOPIC LIST DB CREATE", "copyDatabaseToDevice Succeeded: " + Main.getDBPath() );
            return true;

        } catch (final IOException ioe) {
            Log.e("TOPIC LIST DB CREATE", "copyDatabaseToDevice Failed: something has gone wrong", ioe);
        }
        return false;
    }
}
