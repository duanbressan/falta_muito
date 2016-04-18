package duan.example.faltamuito.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.achartengine.GraphicalView;

import java.util.List;

import duan.example.faltamuito.DAOs.DAOSubject;
import duan.example.faltamuito.MainActivity;
import duan.example.faltamuito.R;
import duan.example.faltamuito.models.Category;
import duan.example.faltamuito.models.Subject;
import duan.example.faltamuito.utilitys.PieChartView;

public class SubjectsInformationAdapter extends BaseAdapter {

	private List<Category> categoryList;
	private Context context;

	public SubjectsInformationAdapter(Context ctx, List<Category> categoryList){
		this.context = ctx;
		this.categoryList = categoryList;
	}

	@Override
	public int getCount() {
		return categoryList.size();
	}

	@Override
	public Object getItem(int position) {
		return categoryList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.iten_percentage_subject, null);
		view.setTag(categoryList.get(position));

		DAOSubject daoSubject = new DAOSubject(context);
		List<Subject> subjectList = daoSubject.findAllSubjects();

		int subjects = 0;
		int subjects_done  = 0;

		for(int i = 0; i < subjectList.size(); i++){
			if(subjectList.get(i).getCategory().getName().equals(categoryList.get(position).getName())){
				subjects ++;
				if(subjectList.get(i).isDone()){
					subjects_done ++;
				}
			}
		}

		int total = (int) ((subjects_done * 100) / subjects);

		TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
		TextView textViewTotal = (TextView) view.findViewById(R.id.textViewTotal);
		TextView textViewTotalDone = (TextView) view.findViewById(R.id.textViewTotalDone);

		textViewName.setText(categoryList.get(position).getName());
		textViewTotal.setText(view.getResources().getString(R.string.total_de_materias) + " " + subjects);
		textViewTotalDone.setText(view.getResources().getString(R.string.total_de_materias_cursadas) + " " + subjects_done);

		addValueToChart(view, total);

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				((MainActivity) context).loadMaterialsActivityInSpecificIten(position);
			}
		});

		return view;
	}

	private void addValueToChart(View view, int valor){

		RelativeLayout chartContainer = (RelativeLayout) view.findViewById(R.id.relativelayoutChart);

		GraphicalView chartView = PieChartView.getNewInstance(context, valor, 100 - valor);
		chartContainer.addView(chartView);

		RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(50,50);
		p.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

		TextView txtPorcentagem = new TextView(context);

		txtPorcentagem.setGravity(Gravity.CENTER);
		txtPorcentagem.setText(valor + "%");
		txtPorcentagem.setTextSize(TypedValue.COMPLEX_UNIT_PX, 22);
		txtPorcentagem.setTextColor(Color.parseColor("#666666"));
		txtPorcentagem.setTypeface(null, Typeface.BOLD);
		txtPorcentagem.setLayoutParams(p);
		txtPorcentagem.setBackgroundResource(R.drawable.background_txtview_redondo);


		chartContainer.addView(txtPorcentagem);
	}

}
