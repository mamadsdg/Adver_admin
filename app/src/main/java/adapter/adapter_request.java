package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_adver.R;
import classes.request_class_item;

import java.util.List;

public class adapter_request extends RecyclerView.Adapter<adapter_request.simpleItemViewHolder> {

    private Context context;
    private List<request_class_item> requestItems;
    public adapter_request(Context context,List<request_class_item> items)
    {
        this.context=context;
        this.requestItems=items;
    }

    @NonNull
    @Override
    public simpleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.requests_card_view,parent,false);
        return new simpleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull simpleItemViewHolder holder, int position) {
        holder.bindRequests(requestItems.get(position));
    }

    @Override
    public int getItemCount() {
        return requestItems.size();
    }

    public class simpleItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        public simpleItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_request);
        }
        public void bindRequests(request_class_item requestitem)
        {
            name.setText(requestitem.getName());
        }
    }
}
