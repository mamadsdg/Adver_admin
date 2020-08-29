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

import adapter.adapter_request;
import classes.request_class_item;

public class request_fragment extends Fragment {


    List<request_class_item> requestItems = new ArrayList<>();
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_request_fragment, container, false);

        test2();
        RecyclerView recyclerListView=view.findViewById(R.id.request_recyclerview);
        adapter_request adapter=new adapter_request(getContext(),requestItems);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerListView.setAdapter(adapter);
        recyclerListView.setLayoutManager(linearLayoutManager);


        return view;

    }

    public void test2()
    {
        addrequests("علی مومن زاده");
        addrequests("علی ابراهیم زاده");
        addrequests("محمد صدیقی");
        addrequests("علی دهخدایی");
        addrequests("مرتضی احمد زاده حبیب آبادی");
        addrequests("سینا علیزاده");
        addrequests("خواجه حافظ شیرازی");
    }


    public void addrequests(String name)
    {
        request_class_item addItem = new request_class_item();
        addItem.setName(name);
        requestItems.add(addItem);
    }
}