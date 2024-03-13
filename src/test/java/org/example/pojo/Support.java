package org.example.pojo;

public class Support {
    private String url;
    private String text;

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Support{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
