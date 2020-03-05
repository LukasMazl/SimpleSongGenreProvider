package main.java.cz.lukas.mazl.genre.finder;

import main.java.cz.lukas.mazl.genre.song.Track;

public interface SongApiProvider {
    Track findSong(String artist, String song) throws SongDoesNotExistException;
}
