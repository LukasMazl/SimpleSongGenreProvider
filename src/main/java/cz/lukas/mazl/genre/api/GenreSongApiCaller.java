package main.java.cz.lukas.mazl.genre.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GenreSongApiCaller {

    private static Map<String, String> prepareParameters(String song, String artist) {
        Map<String, String> params = new HashMap<>();
        params.put(GenreSongApiCallerConst.API_KEY_NAME, GenreSongApiCallerConst.API_KEY_VALUE);
        params.put(GenreSongApiCallerConst.METHOD_NAME, GenreSongApiCallerConst.METHOD_VALUE);
        params.put(GenreSongApiCallerConst.ARTIST_NAME, artist);
        params.put(GenreSongApiCallerConst.TRACK_NAME, song);
        return params;
    }

    private static String prepareUrlParameters(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder("?");
        try {
            for (Map.Entry<String, String> param : params.entrySet()) {
                stringBuilder.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(param.getValue(), "UTF-8"));
                stringBuilder.append("&");
            }
        } catch (UnsupportedEncodingException uEE) {
            uEE.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String getContent(String song, String artist) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Map<String, String> preparedParams = prepareParameters(song, artist);
            String preparedURLParameters = prepareUrlParameters(preparedParams);
            URL apiUrl = new URL(GenreSongApiCallerConst.URL_VALUE + preparedURLParameters);
            HttpURLConnection httpURLConnection = (HttpURLConnection) apiUrl.openConnection();
            httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            Scanner scanner = new Scanner(apiUrl.openStream());
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
