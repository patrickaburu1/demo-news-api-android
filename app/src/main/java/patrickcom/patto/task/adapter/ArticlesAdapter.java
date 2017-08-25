package patrickcom.patto.task.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import patrickcom.patto.task.MainActivity;
import patrickcom.patto.task.Network.CustomVolleyRequest;
import patrickcom.patto.task.R;
import patrickcom.patto.task.model.Article;

/**
 * Created by patto on 8/22/2017.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    Context context;
    List<Article> article;
    private ImageLoader imageLoader;
    public ArticlesAdapter(List<Article> article, Context context){
        super();
        this.article=article;
        this.context=context;
    }


    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_tech_articles, parent, false);

        ArticlesAdapter.ViewHolder viewHolder = new ArticlesAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ViewHolder holder, int position) {

        Article article1 = article.get(position);

        imageLoader = CustomVolleyRequest.getInstance((context).getApplicationContext())
                .getImageLoader();

        holder.title.setText(article1.getTitle());
       // holder.description.setText(article1.getDescription());
        holder.image.setImageUrl(article1.getImage(),imageLoader);

        final String titlee=article1.getTitle();
        final String imagee=article1.getImage();
        final String description=article1.getDescription();
        final String url=article1.getUrl();

               holder.articleLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity) context).goToViewMore(titlee,imagee,description,url);
                    }
                });
   }

    //get counts
    @Override
    public int getItemCount() {

        return article.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,description;
       public NetworkImageView image;
        public LinearLayout articleLayout;

        public ViewHolder(View itemView) {

            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            articleLayout = (LinearLayout) itemView.findViewById(R.id.articleLayout);
            image = (NetworkImageView) itemView.findViewById(R.id.technologyImage);



        }
    }


}