package main.java.cz.lukas.mazl.genre.song;

import java.util.List;

public class Track {
    private String name;
    private String mbid;
    private String url;
    private int duration;
    private int listeners;
    private int playcount;

    private Artist artist;
    private Album album;

    private List<Tag> topTags;

    private Wiki wiki;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Tag> getTopTags() {
        return topTags;
    }

    public void setTopTags(List<Tag> topTags) {
        this.topTags = topTags;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", mbid='" + mbid + '\'' +
                ", url='" + url + '\'' +
                ", duration=" + duration +
                ", listeners=" + listeners +
                ", playcount=" + playcount +
                ", artist=" + artist +
                ", album=" + album +
                ", topTags=" + topTags +
                ", wiki=" + wiki +
                '}';
    }
}
