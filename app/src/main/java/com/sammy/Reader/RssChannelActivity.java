package com.sammy.Reader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.sammy.Reader.data.RssItem;
import com.sammy.Reader.listeners.ListListener;
import com.sammy.Reader.util.NewsAdapter;
import com.sammy.Reader.util.RssReader;

import java.util.List;

/**
 * Each category tab activity.
 *
 *
 */
public class RssChannelActivity extends Activity implements SensorEventListener {
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {

                    GetRSSDataTask task = new GetRSSDataTask();
                    Toast.makeText(getApplicationContext(),"the device Speed : " + speed, Toast.LENGTH_LONG).show();
                    // Start process RSS task
                    task.execute(rssUrl);

                }

                last_x = x;
                last_y = y;
                last_z = z;
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


private String rssUrl;
    // A reference to this activity
    private RssChannelActivity local;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_rss_channel);


		rssUrl = (String)getIntent().getExtras().get("rss-url");
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);



        local = this;

        GetRSSDataTask task = new GetRSSDataTask();


        task.execute(rssUrl);

	}
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }


    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }







    /**
	 * This class downloads and parses RSS Channel feed.
	 *
	 *
	 *
	 */
	private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem> > {
       private  ProgressDialog toload;
        List<RssItem> listItems;
        @Override
        protected List<RssItem> doInBackground(String... urls) {
            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e("RssChannelActivity", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             toload= ProgressDialog.show(RssChannelActivity.this,"Loading Feed",null,true,true);
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {
             toload.dismiss();


            ListView itcItems = (ListView) findViewById(R.id.rssChannelListView);

//            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local,android.R.layout.simple_list_item_1, result);
            NewsAdapter adapter = new NewsAdapter(local, android.R.layout.simple_list_item_1, result);
            // Set list adapter for the ListView
            itcItems.setAdapter(adapter);
                         
            // Set list view item click listener

          itcItems.setOnItemClickListener(new ListListener(result, local));


        }



    }

}
