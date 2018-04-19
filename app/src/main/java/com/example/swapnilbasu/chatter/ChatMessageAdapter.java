package com.example.swapnilbasu.chatter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swapnilbasu.chatter.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import org.achartengine.chart.ScatterChart;
import org.achartengine.model.TimeSeries;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter<com.example.swapnilbasu.chatter.ChatMessage> {
    private static final int MY_MESSAGE = 0, OTHER_MESSAGE = 1, MY_IMAGE = 2, OTHER_IMAGE = 3;
    public ChatMessageAdapter(Context context, List<com.example.swapnilbasu.chatter.ChatMessage> data) {
        super(context, R.layout.item_mine_message, data);
    }
    @Override
    public int getViewTypeCount() {
        // my message, other message, my image, other image
        return 4;
    }
    @Override
    public int getItemViewType(int position) {
        com.example.swapnilbasu.chatter.ChatMessage item = getItem(position);
        if (item.isMine() && !item.isImage()) return MY_MESSAGE;
        else if (!item.isMine() && !item.isImage()) return OTHER_MESSAGE;
        else if (item.isMine() && item.isImage()) return MY_IMAGE;
        else return OTHER_IMAGE;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int viewType = getItemViewType(position);
        if (viewType == MY_MESSAGE) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_mine_message, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText(getItem(position).getContent());
        }else if(viewType == OTHER_IMAGE){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_other_message, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            String url = "http://date.jsontest.com";
            new MyAsyncTask(textView).execute(url);

        }
        else if (viewType == OTHER_MESSAGE) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_other_message, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText(getItem(position).getContent());
        } else if (viewType == MY_IMAGE) {
            com.example.swapnilbasu.chatter.ChatMessage item = getItem(position);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_other_graph, parent, false);
            com.jjoe64.graphview.GraphView graphView = convertView.findViewById(R.id.graph);

            String type = item.getGraphType();


            if (type.matches("bar") || type.matches("Bar")) {

                String data = item.getPairs();

                String[] datapts = data.split(" ");

                DataPoint[] thesePoints = new DataPoint[datapts.length];
                double[] xVals = new double[datapts.length];
                double[] yVals = new double[datapts.length];


                for(int i = 0; i< datapts.length ; i++){

                    String[] coord = datapts[i].split(",");
                    double x = Integer.parseInt(coord[0]);

                    xVals[i] = x;

                    double y = Integer.parseInt(coord[1]);
                    yVals[i] = y;

                    thesePoints[i] = new DataPoint(x,y);

                }

                double xMax = getMax(xVals);
                double xMin = getMin(xVals);
                double yMax = getMax(yVals);
                double yMin = getMin(yVals);


                BarGraphSeries<DataPoint> series = new BarGraphSeries<>( thesePoints);



                graphView.getViewport().setMinX(xMin-1);
                graphView.getViewport().setMaxX(xMax+1);
                graphView.getViewport().setMinY(yMin-1);
                graphView.getViewport().setMaxY(yMax+1);

                graphView.getViewport().setYAxisBoundsManual(true);
                graphView.getViewport().setXAxisBoundsManual(true);

                graphView.getViewport().setScalable(true);
                graphView.getViewport().setScalableY(true);

                graphView.getViewport().setScrollable(true); // enables horizontal scrolling
                graphView.getViewport().setScrollableY(true);

                graphView.addSeries(series);


                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });

                series.setSpacing(50);


                // draw values on top
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);


            } else if (type.matches("line") || type.matches("Line")) {

                String data = item.getPairs();

                String[] datapts = data.split(" ");

                DataPoint[] thesePoints = new DataPoint[datapts.length];
                double[] xVals = new double[datapts.length];
                double[] yVals = new double[datapts.length];


                for(int i = 0; i< datapts.length ; i++){

                    String[] coord = datapts[i].split(",");
                    double x = Integer.parseInt(coord[0]);
                    xVals[i] = x;
                    double y = Integer.parseInt(coord[1]);
                    yVals[i] = y;


                    thesePoints[i] = new DataPoint(x,y);

                }
                double xMax = getMax(xVals);
                double xMin = getMin(xVals);
                double yMax = getMax(yVals);
                double yMin = getMin(yVals);

                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(thesePoints);


                graphView.getViewport().setMinX(xMin-1);
                graphView.getViewport().setMaxX(xMax+1);
                graphView.getViewport().setMinY(yMin-1);
                graphView.getViewport().setMaxY(yMax+1);

                graphView.getViewport().setScalable(true);
                graphView.getViewport().setScalableY(true);

                graphView.getViewport().setScrollable(true); // enables horizontal scrolling
                graphView.getViewport().setScrollableY(true);

                graphView.addSeries(series);


                graphView.setTitle("Sample");

            }



        } else {
            // convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_other_image, parent, false);
        }
        convertView.findViewById(R.id.chatMessageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "onClick", Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }

    public static double getMax(double[] inputArray){
        double maxValue = inputArray[0];
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] > maxValue){
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }

    public static double getMin(double[] inputArray){
        double minValue = inputArray[0];
        for(int i=1;i<inputArray.length;i++){
            if(inputArray[i] < minValue){
                minValue = inputArray[i];
            }
        }
        return minValue;
    }
}
