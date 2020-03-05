package main.java.cz.lukas.mazl.genre.parser;


import main.java.cz.lukas.mazl.genre.song.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

public class SimpleGenreParser implements GenreParser {

    @Override
    public Track parser(String content) {
        Track track = new Track();
        Artist artist = new Artist();
        Wiki wiki = new Wiki();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(content)));
            doc.getDocumentElement().normalize();
            XPathFactory xpathfactory = XPathFactory.newInstance();
            XPath xpath = xpathfactory.newXPath();

            boolean songNotFound = songNotFoundInDoc(xpath, doc);
            if(songNotFound) {
                return null;
            }

            readAllValuesForTrack(track, xpath, doc);
            readAllValuesForArtist(artist, xpath, doc);
            readAllValuesForWiki(wiki, xpath, doc);

            List<Tag> tagList = readAllTags(xpath, doc);

            track.setArtist(artist);
            track.setTopTags(tagList);
            track.setWiki(wiki);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return track;
    }

    private boolean songNotFoundInDoc(XPath xpath, Document doc) {
        String countStr = readValue(xpath, doc, "count(/lfm/error)");
        int count = Integer.parseInt(countStr);
        return count != 0;
    }

    private List<Tag> readAllTags(XPath xpath, Document doc) {
        String value = readValue(xpath, doc, "count(//tag)");
        int tagCount = Integer.parseInt(value);
        List<Tag> tags = new LinkedList<>();
        for(int i = 0; i < tagCount; i++) {
            String expression = "/lfm/track/toptags/tag[" + (i + 1) + "]";
            String tagName = readValue(xpath, doc, expression + "/name");
            String tagUrl = readValue(xpath, doc, expression + "/url");

            Tag tag = new Tag();
            tag.setName(tagName);
            tag.setUrl(tagUrl);
            tags.add(tag);
        }
        return tags;
    }

    private void readAllValuesForWiki(Wiki wiki, XPath xpath, Document doc) {
        readStringValue(xpath, doc, wiki::setPublished, "/lfm/track/wiki/published");
        readStringValue(xpath, doc, wiki::setContent, "/lfm/track/wiki/content");
        readStringValue(xpath, doc, wiki::setSummary, "/lfm/track/wiki/summary");
    }

    private void readAllValuesForArtist(Artist artist, XPath xpath, Document doc) {
        readStringValue(xpath, doc, artist::setArtist, "/lfm/track/artist/name");
        readStringValue(xpath, doc, artist::setMbid, "/lfm/track/artist/mbid");
        readStringValue(xpath, doc, artist::setUrl, "/lfm/track/artist/url");
    }

    private void readAllValuesForTrack(Track track, XPath xpath, Document doc) {
        readStringValue(xpath, doc, track::setName, "/lfm/track/name");
        readStringValue(xpath, doc, track::setMbid, "/lfm/track/mbid");
        readStringValue(xpath, doc, track::setUrl, "/lfm/track/url");
        readIntValue(xpath, doc, track::setDuration, "/lfm/track/duration");
        readIntValue(xpath, doc, track::setListeners, "/lfm/track/listeners");
        readIntValue(xpath, doc, track::setPlaycount, "/lfm/track/playcount");
    }

    private void readIntValue(XPath xpath, Document doc, IntegerSetter setter, String expression) {
        String value = readValue(xpath, doc, expression);
        setter.setInteger(Integer.parseInt(value));
    }

    private void readStringValue(XPath xpath, Document doc, StringSetter setter, String expression) {
        String value = readValue(xpath, doc, expression);
        setter.setString(value);
    }

    private String readValue(XPath xpath, Document doc, String expression) {
        XPathExpression expr;
        try {
            expr = xpath.compile(expression);
            String result = (String) expr.evaluate(doc, XPathConstants.STRING);
            return result;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private interface StringSetter {
        void setString(String value);
    }

    private interface IntegerSetter {
        void setInteger(int value);
    }
}
