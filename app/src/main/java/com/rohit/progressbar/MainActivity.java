package com.rohit.progressbar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    ProgressBar pb;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb=(ProgressBar) findViewById(R.id.progressBar1);
        startBtn=(Button) findViewById(R.id.startBtn);

        //ONCLICK
        startBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                new Downloader().execute();
            }
        });

    }

    class Downloader extends AsyncTask<Void, Integer, Integer>
    {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            //SET PB PROIPERTIES
            pb.setMax(100);

        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);

            //UPDATE PROGRESSBAR
            pb.setProgress(values[0]);

        }

        @Override
        protected Integer doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            //DO HEAVY JOB
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
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            Toast.makeText(getApplicationContext(), "Download Finished !!", Toast.LENGTH_LONG).show();
        }

    }

}