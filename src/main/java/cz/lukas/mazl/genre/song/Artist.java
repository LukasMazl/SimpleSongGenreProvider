package main.java.cz.lukas.mazl.genre.song;

public class Artist {
    private String artist;
    private String mbid;
    private String url;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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

    @Override
    public String toString() {
        return "Artist{" +
                "artist='" + artist + '\'' +
                ", mbid='" + mbid + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
