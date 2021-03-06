package com.example.usphuong.thenewyorktimes.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class Article implements Serializable{
    String webUrl;
    String articleTitle;
    String articleSnippet;

    public String getWebUrl() {
        return webUrl;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getArticleSnippet(){return articleSnippet;}

    String thumbnail;

    public Article(JSONObject jsonObject) {
        try {
            this.webUrl = jsonObject.getString("web_url");
//            this.headline = jsonObject.getString("headline");
            JSONObject headline = jsonObject.getJSONObject("headline");
            articleTitle = headline.getString("main");

            articleSnippet = jsonObject.getString("snippet");
            JSONArray multimedia = jsonObject.getJSONArray("multimedia");
            if (multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                this.thumbnail = "http://www.nytimes.com/" + multimediaJson.getString("url");
            } else {
                this.thumbnail = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<Article> fromJsonArray(JSONArray array) {
        ArrayList<Article> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++) {
            try {
                results.add(new Article(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;


    }

}