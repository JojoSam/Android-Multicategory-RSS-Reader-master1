package com.sammy.Reader.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sammy.Reader.R;
import com.sammy.Reader.data.RssItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


import java.util.List;

/**
 * Created by Sammy on 10/29/2015.
 */
public class NewsAdapter extends ArrayAdapter<RssItem> {

    private int lastPosition = -1;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    private Activity myContext;
    private List<RssItem> datas;

    /*public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }*/


    public NewsAdapter(Context context, int textViewResourceId, List<RssItem> objects) {

        super(context, textViewResourceId, objects);
        myContext = (Activity) context;
        datas = objects;

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);


        options = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.postitem, null);
        WebView webView = (WebView) rowView.findViewById(R.id.webView);
        ImageView thumbImageView = (ImageView) rowView

                .findViewById(R.id.postThumb);


        String SplitString = datas.get(position).getImageUrl();
        String result="";
        try {
            result = SplitString.substring(SplitString.indexOf("src=") + 5, SplitString.indexOf("jpg") + 3);
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        if (datas.get(position).getImageUrl() == null || result == null) {

            thumbImageView.setImageResource(R.drawable.thumb);
        }else {
            //should be change to make the image specific to the item
            // thumbImageView.setImageResource(R.drawable.test);


            // imageLoader.displayImage(result,thumbImageView,options);
            imageLoader.displayImage(result, thumbImageView, options);
        }
        // Bitmap bitmap=getBitmapFromURL(result);

        // thumbImageView.setImageBitmap(bitmap);

//thumbImageView.setImageBitmap(getBitmap(result));

        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        rowView.startAnimation(animation);
        lastPosition = position;



         TextView postlink=(TextView) rowView.findViewById(R.id.link);
        postlink.setText(datas.get(position).getLink());
        TextView postTitleView = (TextView) rowView

                .findViewById(R.id.postTitleLabel);
        postTitleView.setText(datas.get(position).getTitle());

        TextView postDateView = (TextView) rowView

                .findViewById(R.id.postDateLabel);
        postDateView.setText(datas.get(position).getDate()
        );

        return rowView;

    }
}
