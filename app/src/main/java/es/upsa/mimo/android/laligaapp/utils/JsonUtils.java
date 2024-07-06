package es.upsa.mimo.android.laligaapp.utils;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonUtils {

    public static String readJsonFromAssets(Context context, String fileName){
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            InputStreamReader reader = new InputStreamReader(inputStream);
            char[] buffer = new char[1024];
            StringBuilder builder = new StringBuilder();
            int numRead;
            while ((numRead = reader.read(buffer)) != -1) {
                builder.append(buffer, 0, numRead);
            }
            reader.close();
            return builder.toString();
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found)
            return null;
        }
    }

    public static <T> T parseJsonToModel(Context context, String fileName, Class<T> modelClass) {
        String jsonString = readJsonFromAssets(context, fileName);
        if (jsonString != null) {
            return new Gson().fromJson(jsonString, modelClass);
        } else {
            return null;
        }
    }
}
