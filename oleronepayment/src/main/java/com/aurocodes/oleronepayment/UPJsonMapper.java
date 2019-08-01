package com.aurocodes.oleronepayment;

import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class UPJsonMapper {
    private ListView mlistview;
    List<Object> list;
    Gson gson;
    Thread ti;
    Map<String,Object> blankmapPost;


   String orderId;
   String amount;
   String description;
   String convenienceFee;
    String currency;
    String status;
    String cardHolder;
    String pan;
    String scheme;
    String aprovalCode;
    String statusDescription;
    String tranDate;

    public UPJsonMapper(String jsonString){
        gson = new Gson();
        try { blankmapPost = (Map) gson.fromJson( jsonString , Map.class);

                orderId = (String) blankmapPost.get("Order Id");
                amount = (String) blankmapPost.get("Amount");
                description = (String) blankmapPost.get("Description");
                convenienceFee = (String) blankmapPost.get("Convenience Fee");
            currency = (String) blankmapPost.get("Currency");
            status = (String) blankmapPost.get("Status");
            cardHolder = (String) blankmapPost.get("Card Holder");
            pan = (String) blankmapPost.get("PAN");
            scheme = (String) blankmapPost.get("Scheme");
            aprovalCode = (String) blankmapPost.get("Approval Code");
            statusDescription = (String) blankmapPost.get("StatusDescription");
            tranDate = (String) blankmapPost.get("TranDateTime");

        }
        catch(Exception e){
            orderId = "not seen";
            }
        }

        public String fetch_order(int index){
        String as = "";
        switch(index){
            case 1: as = orderId;break;
            case 2: as = amount ;break;
            case 3: as = description;break;
            case 4: as = convenienceFee;break;
            case 5: as = currency;break;
            case 6: as = status ;break;
            case 7: as = cardHolder;break;
            case 8: as = pan;break;
            case 9: as = scheme;break;
            case 11: as = aprovalCode ;break;
            case 10: as = statusDescription;break;
            case 12: as = tranDate;break;
        }
            return as;
        }

    }



