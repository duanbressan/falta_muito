package duan.example.faltamuito.utilitys;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class PieChartView {

	public static final int COLOR_PRENCHIDO = Color.parseColor("#e2e2e6");
	public static final int COLOR_VAZIO = Color.parseColor("#aeaeae");

	public static GraphicalView getNewInstance(Context context, int income, int costs){
		return ChartFactory.getPieChartView(context, getDataSet(context, income, costs), getRenderer());
	}
 
	private static DefaultRenderer getRenderer(){
		int[] colors = new int[] { COLOR_PRENCHIDO, COLOR_VAZIO};
 
		DefaultRenderer defaultRenderer = new DefaultRenderer();
		for (int color : colors)
		{
			SimpleSeriesRenderer simpleRenderer = new SimpleSeriesRenderer();
			simpleRenderer.setColor(color);
			defaultRenderer.addSeriesRenderer(simpleRenderer);			
		}
		defaultRenderer.setShowLabels(false);
		defaultRenderer.setShowLegend(false);
		return defaultRenderer;
	}

	private static CategorySeries getDataSet(Context context, int income, int costs){
		CategorySeries series = new CategorySeries("Chart");
		series.add("vazio", costs);
		series.add("completo", income);
		return series;
	}
}