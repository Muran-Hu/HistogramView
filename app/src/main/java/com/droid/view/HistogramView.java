package com.droid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.droid.model.Histogram;

import java.util.ArrayList;
import java.util.List;

public class HistogramView extends View {
    static final String title = "消费分析";
    int x, y;

    List<Histogram> histograms = new ArrayList<>();
    {
        histograms.add(new Histogram(500, "通讯"));
        histograms.add(new Histogram(800, "小买卖"));
        histograms.add(new Histogram(300, "饮食"));
        histograms.add(new Histogram(100, "交通出行"));
        histograms.add(new Histogram(50, "生活日用"));
        histograms.add(new Histogram(150, "理财"));
        histograms.add(new Histogram(650, "提现"));
        histograms.add(new Histogram(350, "人情往来"));
        histograms.add(new Histogram(400, "其他"));
    }

    public HistogramView(Context context) {
        super(context);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 绘制调度方法
     *
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    /**
     * 主体绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        x = getWidth();
        y = getHeight();

        drawHistogramView(canvas);
    }

    /**
     * 绘制子view
     *
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    /**
     * 绘制前景色
     *
     * 注意：背景色不能重写，只能在layout文件中设置
     * android:background="@android:color/darker_gray"
     *
     * @param canvas
     */
    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
    }

    private void drawHistogramView(Canvas canvas) {
        // 绘制横纵坐标
        drawXYLines(canvas);

        // 绘制每一个立柱及下面的文字说明
        drawHistograms(canvas);

        // 绘制title
        drawTitle(canvas);
    }

    private void drawXYLines(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(100, 500);
        path.lineTo(100, y-400);
        path.lineTo(x-100, y-400);

        canvas.drawPath(path, paint);
    }

    private void drawHistograms(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TextPaint textPaint = new TextPaint();

        int histogramWidth = (x-100-100-20-20)/histograms.size()*5/6;
        int xOffset = 100 + 20;
        for (Histogram histogram: histograms) {
            // 绘制Column
            paint.setColor(Color.GREEN);
            canvas.drawRect(xOffset, y-400-histogram.getData(), xOffset+histogramWidth, y-400, paint);

            // 绘制文字

            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(40);
            StaticLayout staticLayout = new StaticLayout(
                    histogram.getLabel(),
                    textPaint,
                    histogramWidth,
                    Layout.Alignment.ALIGN_NORMAL,
                    1,
                    0,
                    true);
            canvas.save();
            canvas.translate(xOffset+5, y-360);
            staticLayout.draw(canvas);
            canvas.restore();
            xOffset += histogramWidth+20;
        }
    }

    private void drawTitle(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(66);

        canvas.drawText(title, x/2-100, y-100, paint);
    }
}
