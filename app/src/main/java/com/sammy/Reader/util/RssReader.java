package com.sammy.Reader.util;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.sammy.Reader.data.RssItem;

import org.xml.sax.XMLReader;

/**
 * Class reads RSS data.
 * 
 *
 *
 */
public class RssReader {
	
	private String rssUrl;

	/**
	 * Constructor
	 * 
	 * @param rssUrl
	 */
	public RssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	/**
	 * Get RSS items.
	 * 
	 * @return
	 */
	public List<RssItem> getItems() throws Exception {
		// SAX parse RSS data


		RssParseHandler handler = new RssParseHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLReader xmlReader= saxParser.getXMLReader();
        xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler",
                handler);

		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

}
