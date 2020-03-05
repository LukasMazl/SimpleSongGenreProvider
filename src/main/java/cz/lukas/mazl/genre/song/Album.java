package main.java.cz.lukas.mazl.genre.song;

import java.util.List;

public class Album {
    private String artist;
    private String title;
    private String mbid;
    private String url;

    private List<String> images;


    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Album{" +
                "artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", mbid='" + mbid + '\'' +
                ", url='" + url + '\'' +
                ", images=" + images +
                '}';
    }
}
