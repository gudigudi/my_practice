package com.gudigudigudi.mdtemplate.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by gu on 1/21/18.
 */

public class AppUtil {

    public static void goToMarket(Context context, String packageName) {
        String brand = Build.BRAND;

        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent intentGoToMarket = new Intent();

        switch (brand) {
            case "Samsung":
                uri = Uri.parse("http://www.samsungapps.com/appquery/appDetail.as?appId=" + packageName);
                intentGoToMarket.setClassName("com.sec.android.app.samsungapps", "com.sec.android.app.samsungapps.Main");
                intentGoToMarket.setData(uri);
                break;
            case "letv":
                intentGoToMarket.setClassName("com.letv.app.appstore", "com.letv.app.appstore.appmodule.details.DetailsActivity");
                intentGoToMarket.setAction("com.letv.app.appstore.appdetailactivity");
                intentGoToMarket.putExtra("packageName", packageName);
                break;
            default:
                intentGoToMarket = new Intent(Intent.ACTION_VIEW, uri);
        }


        try {
            context.startActivity(intentGoToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap viewScreenshot(View view) {
        view.setDrawingCacheEnabled(true);

        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);

        return bitmap;
    }

    public static Bitmap recyclerViewScreenshot(RecyclerView view) {
        RecyclerView.Adapter adapter = view.getAdapter();

        if (adapter == null) {
            return null;
        }

        Bitmap bitmap = null;

        int size = adapter.getItemCount();
        int height = 0;
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        final int cacheSize = maxMemory / 8;

        LruCache<String, Bitmap> bitmapLruCache = new LruCache<>(cacheSize);

        for (int i = 0; i < size; i++) {
            RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));

            adapter.onBindViewHolder(holder, i);

            // measure.
            holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            // layout.
            holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());

            // draw cache.
            holder.itemView.setDrawingCacheEnabled(true);
            holder.itemView.buildDrawingCache();
            Bitmap drawingCache = holder.itemView.getDrawingCache();
            if (drawingCache != null) {
                bitmapLruCache.put(String.valueOf(i), drawingCache);
            }

            height += holder.itemView.getMeasuredHeight();
        }


        // put cached itemView to bitmap
        bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);

        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) background;
            int color = colorDrawable.getColor();
            canvas.drawColor(color);
        }

        int top = 0;
        Paint paint = new Paint();
        for (int i = 0; i < size; i++) {
            Bitmap bitmap1 = bitmapLruCache.get(String.valueOf(i));
            canvas.drawBitmap(bitmap, 0f, top, paint);
            top += bitmap.getHeight();
        }

        return bitmap;
    }

    public static Bitmap scrollViewScreenshot(ScrollView view) {
        int height = 0;

        for (int i = 0; i < view.getChildCount(); i++) {
            height += view.getChildAt(i).getHeight();
        }

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) background;
            int color = colorDrawable.getColor();
            canvas.drawColor(color);
        }

        view.draw(canvas);
        return bitmap;
    }
}
