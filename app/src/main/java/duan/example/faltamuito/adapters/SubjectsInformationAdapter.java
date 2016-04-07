package duan.example.faltamuito.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import duan.example.faltamuito.DAOs.DAOSubject;
import duan.example.faltamuito.R;
import duan.example.faltamuito.models.Category;
import duan.example.faltamuito.models.Subject;

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
	public View getView(int position, View view, ViewGroup parent) {
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

		double total = (subjects_done * 100) / subjects;

		TextView textViewPercentage = (TextView) view.findViewById(R.id.textViewPercentage);
		TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
		TextView textViewTotal = (TextView) view.findViewById(R.id.textViewTotal);
		TextView textViewTotalDone = (TextView) view.findViewById(R.id.textViewTotalDone);

		textViewPercentage.setText(total + "%");
		textViewName.setText(categoryList.get(position).getName());
		textViewTotal.setText("Total de matérias: "  + subjects);
		textViewTotalDone.setText("Total de matérias cursadas: " + subjects_done);

		return view;
	}

}
