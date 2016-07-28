package com.sammy.Reader.listeners;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.sammy.Reader.data.RssItem;
import com.sammy.Reader.postView;

/**
 * Class implements a list listener
 * 
 *
 *
 */
public class ListListener implements OnItemClickListener {

	// List item's reference
	List<RssItem> listItems;
	// Calling activity reference
	Activity activity;
	
	public ListListener(List<RssItem> aListItems, Activity anActivity) {
		listItems = aListItems;
		activity  = anActivity;
	}
	
	/**
	 * Start a browser with url from the rss item.
	 */
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		//Intent i = new Intent(Intent.ACTION_VIEW);
		//i.setData(Uri.parse(listItems.get(pos).getLink()));
	
		//activity.startActivity(i);

        Bundle postInfo = new Bundle();




      postInfo.putString("content",listItems.get(pos).getLink() );
      postInfo.putString("title",listItems.get(pos).getTitle());



        Intent PostViewIntent=  new Intent(this.activity, postView.class);

        PostViewIntent.putExtras(postInfo);

        activity.startActivity(PostViewIntent);




    }
	
}
