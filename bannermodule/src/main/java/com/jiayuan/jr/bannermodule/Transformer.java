package com.jiayuan.jr.bannermodule;

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

import androidx.viewpager.widget.ViewPager;


public class Transformer {
    public static Class<? extends ViewPager.PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
