package duan.example.faltamuito.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import duan.example.faltamuito.R;
import duan.example.faltamuito.models.Subject;

public class SubjectsAdapter extends BaseAdapter {

	private List<Subject> subjectList;
	private Context context;

	public SubjectsAdapter(Context ctx, List<Subject> subjectList){
		this.context = ctx;
		this.subjectList = subjectList;
	}
	
	@Override
	public int getCount() {
		return subjectList.size();
	}

	@Override
	public Object getItem(int position) {
		return subjectList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.iten_materials, null);
		view.setTag(subjectList.get(position));

		TextView textViewHalfIntegral = (TextView) view.findViewById(R.id.textViewHalfIntegral);
		TextView textViewHalfNight = (TextView) view.findViewById(R.id.textViewHalfNight);
		CheckBox textViewName = (CheckBox) view.findViewById(R.id.checkBoxName);

		Subject subject = subjectList.get(position);

		textViewHalfIntegral.setText(subject.getHalf_integral());
		textViewHalfNight.setText(subject.getHalf_night());
		textViewName.setText(subject.getName());

		return view;
	}

}
