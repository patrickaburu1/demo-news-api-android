package patrickcom.patto.task.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import patrickcom.patto.task.Network.AppStatus;
import patrickcom.patto.task.R;
import patrickcom.patto.task.adapter.ArticlesAdapter;
import patrickcom.patto.task.model.Article;

import static com.android.volley.VolleyLog.TAG;
import static patrickcom.patto.task.MainActivity.id;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechArticles extends Fragment {
    List<Article> article1;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_articles, container, false);

        article1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.techArticlesRecycler);


        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        if(AppStatus.getInstance(getActivity()).isOnline()) {
            articles();
        }
        else {
            Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

//get all technology articles of a specific source
    private void articles(){
        final ProgressDialog progress = ProgressDialog.show(getActivity(),"Loading", "Please wait..");
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "https://newsapi.org/v1/articles?source="+id+"&apiKey=cdb1b3bad0414a5eaf8acff169222b27", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progress.dismiss();
                        try {
                            JSONArray arr = response.getJSONArray("articles");
                            for (int i = 0; i < response.length(); i++) {
                                Article article = new Article();
                                try {

                                    JSONObject obj = (JSONObject) arr.get(i);
                                    article.setTitle(obj.getString("title"));
                                    article.setDescription(obj.getString("description"));
                                    article.setImage(obj.getString("urlToImage"));
                                    article.setAuthor(obj.getString("author"));
                                    article.setDate(obj.getString("publishedAt"));
                                    article.setUrl(obj.getString("url"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                article1.add(article);
                                recyclerViewadapter = new ArticlesAdapter(article1, getActivity());

                                recyclerView.setAdapter(recyclerViewadapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }},new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                Log.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
            }

        });

        RequestQueue request= Volley.newRequestQueue(getActivity());
        request.add(req);
    }
}
