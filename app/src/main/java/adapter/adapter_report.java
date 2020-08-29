package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_adver.R;
import classes.report_class_items;

import java.util.List;

public class adapter_report extends RecyclerView.Adapter<adapter_report.SimpleItemViewHolder>{

    @NonNull
    private Context context;
    private List<report_class_items> reportItems;
    public adapter_report(Context context,List<report_class_items> reportItems)
    {
        this.context = context;
        this.reportItems = reportItems;
    }
    @Override
    public SimpleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.reports_card_view,parent,false);
        return new SimpleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleItemViewHolder holder, int position) {
        holder.bindReports((reportItems.get(position)));
    }

    @Override
    public int getItemCount() {
        return reportItems.size();
    }

    public class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView date;
        private TextView preview;
        public SimpleItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_report);
            date=itemView.findViewById(R.id.date_inf);
            preview=itemView.findViewById(R.id.preview_id);
        }
        public void  bindReports(report_class_items reportItem)
        {
            name.setText(reportItem.getName());
            date.setText(reportItem.getDate());
            preview.setText(reportItem.getPreview());
        }
    }

}
