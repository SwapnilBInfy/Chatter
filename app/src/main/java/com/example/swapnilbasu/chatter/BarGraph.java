package com.example.swapnilbasu.chatter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.chart.BarChart.Type;

public class BarGraph {

    public Intent getIntent(Context context){
        //data 1
        int[] y = {124,135,443,456,234,123,342,134,123,643, 234, 274};

        CategorySeries series = new CategorySeries("Demo Bar Graph 1");

        for (int i=0; i<y.length; i++){
            series.add("Bar "+(i+1), y[i]);
        }
        //Data 2
        int[] y2 = {224, 235,243,256, 234,223,242,234,223,243,234,275,35,36,34, 36};

        CategorySeries series2 = new CategorySeries("Demo Bar Graph 2");

        for (int i=0; i<y2.length; i++){
            series2.add("Bar "+(i+1), y[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series.toXYSeries());
        dataset.addSeries(series2.toXYSeries());

        //Customize Bar 1
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing((float) 0.5);
        renderer.setColor(Color.BLUE);

        renderer.setChartValuesTextAlign(Paint.Align.CENTER);

        //Customize Bar 2
        XYSeriesRenderer renderer2 = new XYSeriesRenderer();
        renderer2.setDisplayChartValues(true);
        renderer2.setChartValuesSpacing((float) 0.5);
        renderer2.setColor(Color.CYAN);

        renderer2.setChartValuesTextAlign(Paint.Align.CENTER);

        //Customization for the graph itself
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.addSeriesRenderer(renderer2);
        mRenderer.setChartTitle("Infy-Bar Graph");
        mRenderer.setXTitle("X Values");
        mRenderer.setYTitle("Y Values");
        mRenderer.setZoomEnabled(true);
        mRenderer.setZoomButtonsVisible(true);

        Intent intent = ChartFactory.getBarChartIntent(context, dataset, mRenderer, Type.DEFAULT);


        return intent;
    }
}
