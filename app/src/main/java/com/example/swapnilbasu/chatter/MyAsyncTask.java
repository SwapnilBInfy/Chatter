package com.example.swapnilbasu.chatter;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class MyAsyncTask extends AsyncTask<String,Void,String> {
    TextView tv;

    public MyAsyncTask(TextView textView){
        tv = textView;

    }


    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        String response="";
        try {
            url = new URL(params[0]);

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));

            StringBuffer data= new StringBuffer(1024);
            String tmpdata="";
            while((tmpdata=reader.readLine())!=null) {

                data.append(tmpdata).append("\n");

            }
            reader.close();
            response=data.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();

            }
        }
        return response;
    }
    @Override
    protected void onPostExecute(String jsonObject) {
        tv.setText(jsonObject);
    }

}
