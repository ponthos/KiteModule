package com.jiayuan.jr.bannermodule;

import android.support.v4.view.ViewPager.PageTransformer;

import com.jiayuan.jr.bannermodule.transformer.AccordionTransformer;
import com.jiayuan.jr.bannermodule.transformer.BackgroundToForegroundTransformer;
import com.jiayuan.jr.bannermodule.transformer.CubeInTransformer;
import com.jiayuan.jr.bannermodule.transformer.CubeOutTransformer;
import com.jiayuan.jr.bannermodule.transformer.DefaultTransformer;
import com.jiayuan.jr.bannermodule.transformer.DepthPageTransformer;
import com.jiayuan.jr.bannermodule.transformer.FlipHorizontalTransformer;
import com.jiayuan.jr.bannermodule.transformer.FlipVerticalTransformer;
import com.jiayuan.jr.bannermodule.transformer.ForegroundToBackgroundTransformer;
import com.jiayuan.jr.bannermodule.transformer.RotateDownTransformer;
import com.jiayuan.jr.bannermodule.transformer.RotateUpTransformer;
import com.jiayuan.jr.bannermodule.transformer.ScaleInOutTransformer;
import com.jiayuan.jr.bannermodule.transformer.StackTransformer;
import com.jiayuan.jr.bannermodule.transformer.TabletTransformer;
import com.jiayuan.jr.bannermodule.transformer.ZoomInTransformer;
import com.jiayuan.jr.bannermodule.transformer.ZoomOutSlideTransformer;
import com.jiayuan.jr.bannermodule.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
