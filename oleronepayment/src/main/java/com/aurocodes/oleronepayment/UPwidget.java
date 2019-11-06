package com.aurocodes.oleronepayment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class UPwidget extends WebView {
    String trystring ="";
    boolean flag = false;
    boolean dialog_enable = false;
    boolean stop_dotmover = false;
    Handler handler = new Handler();
    String jsonUrl;
    Context ccc;
    String tables = "";
    String up_subject[] = {"Order ID","Amount","Description","Convenience Fee","Currency","Status","Card Holder","PAN","Scheme","Approval Code","Status Description","Transaction Date"};


    public UPwidget(Context context) {
        super(context);
       ccc = context;
    }

    public UPwidget(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }


    public void doit_table(String json_data) {
        UPJsonMapper upJsonMapper = new UPJsonMapper(json_data);
        tables = "<table border=\"0\">"+
                "<tr><th style=\"text-align:left\">"+up_subject[0]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(1)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[1]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(2)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[2]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(3)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[3]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(4)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[4]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(5)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[5]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(6)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[6]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(7)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[7]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(8)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[8]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(9)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[9]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(10)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[10]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(11)+"</th></tr>"+
                "<tr><th style=\"text-align:left\">"+up_subject[11]+"</th><th style=\"text-align:left;color:blue\">"+  upJsonMapper.fetch_order(12)+"</th></tr>"+
                "</table>";

    }
public void init(String merchantId, String publicKey){

    String backEndUrl = "https://test.payarena.com/";
    WebSettings webSetting = getSettings();
    webSetting.setBuiltInZoomControls(true);
    webSetting.setLoadWithOverviewMode(true);
    webSetting.setJavaScriptEnabled(true);
    String htmlstring = "<!DOCTYPE html>" +
            "<html xmlns = \"http://www.w3.org/1999/xhtml\">"
            + "<head><title></title></head>"
            + "<body onload=\"document.frm1.submit()\">"
            + "<form action=\"" + backEndUrl + merchantId + "\" method = \"post\" name =\"frm1\" target = \"_blank\">"
            + "<input type=\"text\" name= \"firstname\" value = \"\"><br>"
            + "<input type=\"text\" name= \"lastname\" value = \"\"><br>"
            + "<input type=\"text\" name= \"amount\" value = \"400\"><br>"
            + "<input type=\"text\" name= \"currency\" value = \"566\"><br>"
            + "<input type=\"text\" name= \"fee\" value = \"0\"><br>"
            + "<input type=\"text\" name= \"email\" value = \"\"><br>"
            + "<input type=\"text\" name= \"description\" value = \"Descriptionproduct\"><br>"
            + "<input type=\"text\" hidden = \"true\" name= \"returnUrl\" value = \"http://oleronesoftwares.com/upjoomla/nice_listener.php\"><br>"
            + "<input type=\"text\" hidden = \"true\" name= \"secretKey\" value = \"" + publicKey + "\"><br>"
            + "<input type=\"text\" hidden = \"true\" name= \"trxId\" value = \"12452\"><br>"
            + "<button type=\"submit\" formmethod= \"post\">Submit using POST</button>" +
            "</form>" +
            "</body>" +
            "</html>";
    loadDataWithBaseURL("http://196.46.20.80:8085",htmlstring, "text/html", "UTF-8", null);
    set_webclient();
}
    public void set_webclient() {
        setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap bt) {
                stop_dotmover = false;

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String as = url;
                if (url.contains("Result")) {
                    flag = true;
                    jsonUrl = as.replace("Result", "Status");
                    UPwidget.MyAsyncKTask mytask = new UPwidget.MyAsyncKTask();
                    mytask.execute();

                }

                stop_dotmover = true;
            }
        });
    }

    public class MyAsyncKTask extends AsyncTask<String, Void, String> {


        MyAsyncKTask() {
            // categorynumber = new String[15];

        }

        @Override

        protected String doInBackground(String... strings) {

            org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();

            HttpGet httpget = new HttpGet(jsonUrl);


            try {
                HttpResponse response = httpclient.execute(httpget, new BasicHttpContext());

                InputStream is = response.getEntity().getContent();

                trystring = inputStreamToString(is);


            } catch (IOException e) {
                e.printStackTrace();
            }
            return trystring;

        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            doit_table(s);
            loadData(tables,"text/html", "UTF-8");
        }

        private String inputStreamToString(InputStream ii) {
            String line = "";
            StringBuilder total = new StringBuilder();

            BufferedReader rd = new BufferedReader(new InputStreamReader(ii));
            try {
                while ((line = rd.readLine()) != null) {
                    total.append(line);
                }
            } catch (IOException e) {

            }
            return total.toString();
        }


    }

    public void setDialogEnabled(boolean b) {
        dialog_enable = b;
    }

}
