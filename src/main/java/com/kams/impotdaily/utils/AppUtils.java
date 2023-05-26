/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author macbook
 */
public class AppUtils {
    
    public static String formatDate(Date date){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
    
    public static Date getDateFrom(String dateString, String format) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(/*"dd/MM/yyyy"*/ format);
        return sdf.parse(dateString);
    }
    
}
