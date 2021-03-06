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
public void init(String merchantId, String publicKey, String backEndUrl){


    WebSettings webSetting = getSettings();
    webSetting.setBuiltInZoomControls(true);
    webSetting.setLoadWithOverviewMode(true);
    webSetting.setJavaScriptEnabled(true);
    String htmlstring = "<!DOCTYPE html>\n" +
            "<html xmlns = \"http://www.w3.org/1999/xhtml\"><head><style>\n" +
            "body {background-color: lightblue;}\n" +
            "\n" +
            "h1 {font-family: verdana;\n" +
            "  color: orange;\n" +
            "  text-align: center;}\n" +
            "\n" +
            "p {font-family: verdana;\n" +
            "  font-size: 20px;}\n" +
            "\n" +
            "\n" +
            "input[type=text], select {width: 100%;\n" +
            "  padding: 20px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  display: inline-block;\n" +
            "  border: 1px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "  box-sizing: border-box;}\n" +
            "\n" +
            "button[type=submit] {\n" +
            "  width: 100%;\n" +
            "  background-color: #4CAF50;\n" +
            "  color: white;\n" +
            "  padding: 14px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  border: none;\n" +
            "  border-radius: 4px;\n" +
            "  cursor: pointer;}\n" +
            "\n" +
            "input[type=submit]:hover {\n" +
            "  background-color: #45a049;}\n" +
            "\n" +
            "div {border-radius: 5px;\n" +
            "  background-color: #f2f2f2;\n" +
            "  padding: 20px;}\n" +
            "</style></head><body onload=\"document.frm1.submit()\">\n" +
            "        <h1>UNIFIED PAYMENT</h1>\n" +
            "        <form action=\""+backEndUrl + "/" + merchantId +"\" method = \"post\" name =\"frm1\" target = \"_blank\">\n" +
            "        <input type=\"text\" name= \"firstname\" value = \"\" placeholder=\"Your first name..\"><br>\n" +
            "        <input type=\"text\" name= \"lastname\" value = \"\"placeholder=\"Your Surname name..\"><br>\n" +
            "        <input type=\"text\" name= \"amount\" value = \"\" placeholder=\"Amount in Naira..\"><br>\n" +
            "        <input type=\"text\" name= \"currency\" value = \"566\"><br>\n" +
            "        <input type=\"text\" name= \"fee\" value = \"0\"><br>\n" +
            "        <input type=\"text\" name= \"email\" value = \"\"placeholder=\"E-mail..\"><br>\n" +
            "       \n" +
            "        <input type=\"text\" name= \"description\" value = \"\" placeholder=\"Description..\"><br>\n" +
            "        \n" +
            "        <input type=\"hidden\" name= \"returnUrl\" value = \"http://oleronesoftwares.com/upjoomla/nice_listener.php\"><br>\n" +
            "        \n" +
            "        <input type=\"hidden\" name= \"secretKey\" value = \"" + publicKey + "\"><br>\n" +
            "         \n" +
            "        <input type=\"hidden\" name= \"trxId\" value = \"12452\"><br>\n" +
            "        <button type=\"submit\" formmethod= \"post\">Pay with Unified Payment</button>\n" +
            "        </form>\n" +
            "        </body>\n" +
            "        </html>\n" +
            "\n" +
            "\n";
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
