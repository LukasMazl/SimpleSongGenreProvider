package main.java.cz.lukas.mazl;

import main.java.cz.lukas.mazl.genre.finder.SimpleSongApiProvider;
import main.java.cz.lukas.mazl.genre.finder.SongApiProvider;
import main.java.cz.lukas.mazl.genre.finder.SongDoesNotExistException;
import main.java.cz.lukas.mazl.genre.song.Track;

public class Application {


    public static void findSong(String artist, String song) throws SongDoesNotExistException {
        SongApiProvider songApiProvider = new SimpleSongApiProvider();
        Track track = songApiProvider.findSong(artist, song);
        System.out.println(track.getTopTags());
    }


    public static void main(String[] args) throws SongDoesNotExistException {
        findSong("Rob Thomas & Santana", "Smooth");
        findSong("Christina Aguilera", "What A Girl Wants");
        findSong("Savage Garden", "I Knew I Loved You");
        findSong("Celine Dion", "That''s The Way It Is");
        findSong("Eiffel 65", "Blue (Da Ba Dee)");
        findSong("Brian McKnight", "Back At One");
        findSong("Jennifer Lopez", "Waiting For Tonight");
        findSong("Jessica Simpson", "I Wanna Love You Forever");
    }
}
