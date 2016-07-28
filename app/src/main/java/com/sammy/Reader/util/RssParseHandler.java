package com.sammy.Reader.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

import com.sammy.Reader.data.RssItem;

/**
 * SAX tag handler
 * 
 *
 *
 */
public class RssParseHandler extends DefaultHandler implements LexicalHandler {

    private List<RssItem> rssItems;

    private String image;


    private RssItem currentItem;


    private boolean parsingTitle;


    private StringBuffer currentTitleSb;



    // Parsing link indicator
    private boolean parsingLink;

    //Parsing description
    private boolean parsingCdata;

    private boolean parsingDate;

    public RssParseHandler() {
        rssItems = new ArrayList<RssItem>();
    }

    public List<RssItem> getItems() {
        return rssItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            currentItem = new RssItem();
        } else if ("title".equals(qName)) {
            parsingTitle = true;

            currentTitleSb = new StringBuffer();
        } else if ("link".equals(qName)) {
            parsingLink = true;
        }else if("pubDate".equals(qName)){

            parsingDate=true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            rssItems.add(currentItem);
            currentItem = null;
        } else if ("title".equals(qName)) {

            parsingTitle = false;

            // Set item's title when we parse item->title tag not the channel title tag
            if (currentItem != null) {
                // Set item's title here
                currentItem.setTitle(currentTitleSb.toString());
//               currentItem.setImageurl("http://photos.myjoyonline.com/photos/news/201409/5449974794665_2026561818602.jpg");
            }

        } else if ("link".equals(qName)) {
            parsingLink = false;
        }else if("pubDate".equals(qName)){
            parsingDate=false;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (parsingTitle) {
            if (currentItem != null) {
                // Here we append the title to the buffer due to network issues.
                // Sometimes this characters method is called multiple times for a tag contents.
                currentTitleSb.append(new String(ch, start, length));
            }
        } else if (parsingLink) {
            if (currentItem != null) {
                currentItem.setLink(new String(ch, start, length));
                parsingLink = false;
            }

        }else if (parsingDate) {
            if (currentItem != null) {
                currentItem.setDate(new String(ch, start, length));
                parsingDate= false;
            }

        }
        else if (parsingCdata) {

                currentItem.setImageurl(new String(ch, start, length));
                parsingCdata = false;


        }
//
    }


    @Override
    public void startDTD(String s, String s2, String s3) throws SAXException {

    }

    @Override
    public void endDTD() throws SAXException {

    }

    @Override
    public void startEntity(String s) throws SAXException {

    }

    @Override
    public void endEntity(String s) throws SAXException {

    }

    @Override
    public void startCDATA() throws SAXException {
parsingCdata=true;
    }

    @Override
    public void endCDATA() throws SAXException {
        parsingCdata=false;

    }

    @Override
    public void comment(char[] chars, int i, int i2) throws SAXException {

    }
}
