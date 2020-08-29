package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin_adver.R;

import java.util.ArrayList;
import java.util.List;

import adapter.adapter_report;
import classes.report_class_items;

public class reports_fragment extends Fragment {

    List<report_class_items> report_items = new ArrayList<>();

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reports_fragment, container, false);

        RecyclerView recycleListView =view.findViewById(R.id.reports_recyclerview);
        adapter_report adapter=new adapter_report(this.getContext(),report_items);//???
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false);
        recycleListView.setLayoutManager(linearLayoutManager);
        recycleListView.setAdapter(adapter);

        test1();

        return view;
    }

    public void test1()
    {
        addReports("محمد صدیقی","99/5/3","پروزه ی جدید را در هفته آینده تحویل خواهیم داد");
        addReports("علی دهخدایی","99/11/3","پروژه به خطا خورده است.باید با استفاده از یک اندروید کار ماهر رفع خطا شود");
        addReports("علی ابراهیم زاده","99/2/3","پروزه ی جدید را در هفته آینده تحویل خواهیم داد");
        addReports("آقای تجلی","85/5/9","پروزه ی جدید را در هفته آینده تحویل خواهیم داد");
        addReports("اقای امامی","32/4/3","پروزه ی جدید را در هفته آینده تحویل خواهیم داد");
    }

    public void addReports(String name,String date,String preview)
    {
        report_class_items addItem = new report_class_items();
        addItem.setName(name);
        addItem.setDate(date);
        addItem.setPreview(preview);
        report_items.add(addItem);
    }
}