package com.sammy.Reader;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/**
 * Main activity. It displays tabs.
 * 
 *
 */
@SuppressWarnings("deprecation") 
// @SuppressWarnings annotation is here since we are using TabActivity which is deprecated in Android 4+
// Alternative way of constructing Tab Layout is to use ActionBar API
public class RssTabsActivity extends TabActivity {




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		// First, set the content view
		setContentView(R.layout.activity_rss_tabs);


		// Then get the TabHost
		TabHost tabHost = getTabHost();
		

		Intent artIntent = new Intent().setClass(this, RssChannelActivity.class);

		artIntent.putExtra("rss-url", "http://www.myjoyonline.com/pages/rss/site_politics.xml");
		
		// The name of the art tab taken from the String resources
		String artTabName = getResources().getString(R.string.tab_joy);
		TabSpec artTabSpec = tabHost.newTabSpec(artTabName)
									.setIndicator(artTabName, getResources().getDrawable(R.drawable.rss_tab_art))
									.setContent(artIntent);

		tabHost.addTab(artTabSpec);
	

		Intent techIntent = new Intent().setClass(this, RssChannelActivity.class);


		techIntent.putExtra("rss-url", "http://www.myjoyonline.com/pages/rss/site_sports.xml");
		
		// Tech tab name taken from the string resources
		String techTabName = getResources().getString(R.string.tab_gimpa);
		TabSpec techTabSpec = tabHost.newTabSpec(techTabName)
									 .setIndicator(techTabName, getResources().getDrawable(R.drawable.rss_tab_tech))
									 .setContent(techIntent);
		// Add tech tab to the TabHost
		tabHost.addTab(techTabSpec);
		
		
		/* *****************
		 * Sports tab
		 */
		Intent sportsIntent = new Intent().setClass(this, RssChannelActivity.class);
		// Set Sports category RSS URL
		sportsIntent.putExtra("rss-url", "http://www.myjoyonline.com/pages/rss/site_business.xml");
		
		// Sports tab name - string resources
		String sportsTabName = getResources().getString(R.string.tab_sports);
		TabSpec sportsTabSpec = tabHost.newTabSpec(sportsTabName)
									   .setIndicator(sportsTabName, getResources().getDrawable(R.drawable.rss_tab_sports))
				  					   .setContent(sportsIntent);
		// Add sports tab to the TabHost
		tabHost.addTab(sportsTabSpec);
		
		
		// Set current tab to Technology
		tabHost.setCurrentTab(1);

	}

}
