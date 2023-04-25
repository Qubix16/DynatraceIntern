package com.example.demo;

public class ApiURL {
    static String BankApiURL = "http://api.nbp.pl/api/exchangerates/rates";

    static public String getTopCountURL(String table, String code, String date)
    {
        String tempURL = ApiURL.BankApiURL;
        tempURL += "/" + table + "/" + code + "/" + date;
        return tempURL;
    }

    static public String getDateURL(String table, String code, String topCount)
    {
        String tempURL = ApiURL.BankApiURL;
        tempURL += "/" + table + "/" + code + "/last/" + topCount;
        return tempURL;
    }
}
