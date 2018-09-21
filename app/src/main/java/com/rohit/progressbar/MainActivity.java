package com.rohit.progressbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar mProgressbar;
    Button mButtonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressbar =findViewById(R.id.progressBar1);
        mButtonStart = findViewById(R.id.startBtn);

        // Button click handling
        mButtonStart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // calling the asynck task inner class.
                new Downloader().execute();
            }
        });

    }

    // inner class
    class Downloader extends AsyncTask<Void, Integer, Integer>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //set progress bar maximum value
            mProgressbar.setMax(100);

        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //update progress bar value
            mProgressbar.setProgress(values[0]);

        }

        @Override
        protected Integer doInBackground(Void... arg0) {

            //this method is used to perform heavy task
            for(int i=0;i<100;i++)
            {
                publishProgress(i);

                try
                {
                    Thread.sleep(100);
                }catch(InterruptedException ie)
                {
                    ie.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            // printing the toast on progressbar value reached to 100.
            Toast.makeText(getApplicationContext(), "Download Finished !!", Toast.LENGTH_LONG).show();
        }

    }

}