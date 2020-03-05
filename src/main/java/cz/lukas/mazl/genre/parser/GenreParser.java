package main.java.cz.lukas.mazl.genre.parser;

import main.java.cz.lukas.mazl.genre.song.Track;

public interface GenreParser {
    Track parser(String content);
}
