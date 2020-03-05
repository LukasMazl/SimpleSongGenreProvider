package main.java.cz.lukas.mazl.genre.finder;

import main.java.cz.lukas.mazl.genre.api.GenreSongApiCaller;
import main.java.cz.lukas.mazl.genre.parser.GenreParser;
import main.java.cz.lukas.mazl.genre.parser.SimpleGenreParser;
import main.java.cz.lukas.mazl.genre.song.Track;

public class SimpleSongApiProvider implements SongApiProvider {

    private GenreParser parser;

    public SimpleSongApiProvider() {
        init();
    }

    private void init() {
        parser = new SimpleGenreParser();
    }

    @Override
    public Track findSong(String artist, String song) throws SongDoesNotExistException {
        String urlContent = GenreSongApiCaller.getContent(song.replace("\'\'", "\'"), artist.replace("\'\'", "\'"));
        Track track;
        if (urlContent.isEmpty()) {
            throw new SongDoesNotExistException();
        } else {
             track = getParser().parser(urlContent);
            if (track == null) {
                throw new SongDoesNotExistException();
            }
        }
        return track;
    }

    public GenreParser getParser() {
        return parser;
    }

    public void setParser(GenreParser parser) {
        this.parser = parser;
    }
}
