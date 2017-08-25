package patrickcom.patto.task.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import patrickcom.patto.task.MainActivity;
import patrickcom.patto.task.R;
import patrickcom.patto.task.model.Source;

/**
 * Created by patto on 8/25/2017.
 */

public class TechSourcesAdapter extends RecyclerView.Adapter<TechSourcesAdapter.ViewHolder> {
    Context context;
    List<Source> source;

    public TechSourcesAdapter(List<Source> source, Context context){
        super();
        this.source=source;
        this.context=context;
    }


    @Override
    public TechSourcesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_tech_sources, parent, false);

        TechSourcesAdapter.ViewHolder viewHolder = new TechSourcesAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TechSourcesAdapter.ViewHolder holder, int position) {

        Source source1 = source.get(position);


        holder.name.setText(source1.getName());
         holder.description.setText(source1.getSourecDescription());


        final String id=source1.getId();

        holder.sourceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).goToTechArticles(id);
                //Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //get counts
    @Override
    public int getItemCount() {

        return source.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,description;
        public LinearLayout sourceLayout;

        public ViewHolder(View itemView) {

            super(itemView);

            name = (TextView) itemView.findViewById(R.id.sourceName);
            description = (TextView) itemView.findViewById(R.id.sourceDescription);
            sourceLayout= (LinearLayout) itemView.findViewById(R.id.sourcesLayout);


        }
    }




}
