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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import patrickcom.patto.task.R;
import patrickcom.patto.task.adapter.TechSourcesAdapter;
import patrickcom.patto.task.model.Source;

import static com.android.volley.VolleyLog.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechSources extends Fragment {

    List<Source> source;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    public TechSources() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_tech_sources, container, false);
        source = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.techSourcesRecycler);


        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        sources();
        return view;
    }
// method to get all technology related sorces
    private void sources() {
        final ProgressDialog progress = ProgressDialog.show(getActivity(),"Loading", "Please wait..");
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "https://newsapi.org/v1/sources?category=technology", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progress.dismiss();
                        try {
                            JSONArray arr = response.getJSONArray("sources");
                            for (int i = 0; i < response.length(); i++) {
                                Source source1 = new Source();
                                try {

                                    JSONObject obj = (JSONObject) arr.get(i);
                                    source1.setId(obj.getString("id"));
                                    source1.setName(obj.getString("name"));
                                    source1.setSourecDescription(obj.getString("description"));
                                    //Toast.makeText(getActivity(), "Success    " + response, Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                source.add(source1);
                                recyclerViewadapter = new TechSourcesAdapter(source, getActivity());

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
