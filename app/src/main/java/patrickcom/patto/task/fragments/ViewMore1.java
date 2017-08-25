package patrickcom.patto.task.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import patrickcom.patto.task.Network.AppStatus;
import patrickcom.patto.task.R;

import static patrickcom.patto.task.MainActivity.link;
import static patrickcom.patto.task.R.id.webView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewMore1 extends Fragment {

    WebView mWebview;

    public ViewMore1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_view_more1, container, false);
        mWebview= (WebView) view.findViewById(webView);

       if(AppStatus.getInstance(getActivity()).isOnline()) {

           mWebview.setWebViewClient(new CustomWebViewClient());
           WebSettings webSetting = mWebview.getSettings();
           webSetting.setJavaScriptEnabled(true);
           webSetting.setDisplayZoomControls(true);
           mWebview.loadUrl(link);

           final ProgressDialog progress = new ProgressDialog(getActivity());
           progress.setTitle("Loading");
           progress.setMessage("Please wait ...");
           progress.show();


           Runnable progressRunnable = new Runnable() {

               @Override
               public void run() {
                   progress.cancel();
               }
           };

           Handler pdCanceller = new Handler();
           pdCanceller.postDelayed(progressRunnable, 3000);
       }else {
           Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_SHORT).show();
       }

        return view;
    }
    private class CustomWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
