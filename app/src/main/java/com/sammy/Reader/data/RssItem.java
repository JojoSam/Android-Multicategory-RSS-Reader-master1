package com.sammy.Reader.data;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 *
 *
 *
 */
public class RssItem {
	
	// item title
	private String title;
	// item link
	private String link;

    public String getImageGet() {
        return imageGet;
    }

    public void setImageGet(String imageGet) {
        this.imageGet = imageGet;
    }

    private String imageGet;

private  String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;

    }

    public void setImageurl(String imageurl) {
        this.imageUrl = imageurl;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
}
