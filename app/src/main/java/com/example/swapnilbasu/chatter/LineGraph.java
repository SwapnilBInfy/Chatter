package com.example.swapnilbasu.chatter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class LineGraph {
    public Intent getIntent(Context context){

        int [] x = {1,2,3,4,5,6,7,8,9,10};
        int [] y = {30,34, 45, 57,77,89,100,111,123,145};

        TimeSeries series = new TimeSeries("Line1");
        for(int i=0; i<x.length; i++){
            series.add(x[i], y[i]);
        }

        int [] x2 = {1,2,3,4,5,6,7,8,9,10};
        int [] y2 = {145,123,111,100,89,77,57,45,34,30};

        TimeSeries series2 = new TimeSeries("Line2");
        for(int i=0; i<x2.length; i++){
            series.add(x2[i], y2[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series);
        dataset.addSeries(series2);

        //customization for line 1
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setColor(Color.BLACK);
        renderer.setLineWidth(15);
        renderer.setPointStyle(PointStyle.SQUARE);
        renderer.setFillPoints(true);

        //customization for line 2
        XYSeriesRenderer renderer2 = new XYSeriesRenderer();
        renderer2.setColor(Color.BLUE);
        renderer2.setLineWidth(15);
        renderer2.setPointStyle(PointStyle.SQUARE);
        renderer2.setFillPoints(true);


        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.addSeriesRenderer(renderer2);
        mRenderer.setChartTitle("Infy-Graph");



        Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer,"Line Graph Title");
        return intent;

    }
}
