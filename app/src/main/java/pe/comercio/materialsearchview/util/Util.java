package pe.comercio.materialsearchview.util;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pe.comercio.materialsearchview.R;

/**
 * Created by Carlos Vargas on 12/13/16.
 * CarlitosDroid
 */

public class Util {

    public static void animTranslationHide(Context context, View linMenu){
        Animator animLinearMenu = AnimatorInflater.loadAnimator(context, R.animator.animator_translation_hide);
        animLinearMenu.setTarget(linMenu);
        animLinearMenu.start();
    }

    public static void animTranslationShow(Context context, View linMenu){
        Animator animLinearMenu = AnimatorInflater.loadAnimator(context, R.animator.animator_translation_show);
        animLinearMenu.setTarget(linMenu);
        animLinearMenu.start();
    }


    public static String getFormatDate(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

//    public static Date getFormatDate(){
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String stringDate = sdf.format(new Date());
//        try {
//            return sdf.parse(stringDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
