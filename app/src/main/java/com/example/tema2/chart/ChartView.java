package com.example.tema2.chart;

import static com.example.tema2.chart.ChartActivity.PACIENT_NEGATIV;
import static com.example.tema2.chart.ChartActivity.PACIENT_POZITIV;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import com.example.tema2.R;

import java.util.Map;
import java.util.Random;

public class ChartView extends View {

    private final Context context;
    private final Map<String, Integer> source;
    private final Paint paint;
    private final Random random;
    private RectF rectF = new RectF();
    int total = 0;


    public ChartView(Context context, Map<String, Integer> source) {
        super(context);
        this.context = context;
        this.source = source;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setColor(Color.BLACK);
        this.random = new Random();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(source.isEmpty()){
            return;
        }
        total += source.get(PACIENT_POZITIV)+source.get(PACIENT_NEGATIV);

        canvas.drawColor(Color.WHITE);
        float percentage =(((float)(source.get(PACIENT_NEGATIV))/total))*100;
        paint.setTextSize(40);
        System.out.println(percentage);
        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF, 0, 360 * percentage / 100, true, paint);
        canvas.drawText(context.getString(R.string.main_message_neg,percentage), rectF.left + 10, rectF.bottom + 60, paint);

        paint.setColor(Color.RED);
        paint.setTextSize(40);
        canvas.drawArc(rectF, 360 * percentage / 100, 360 - 360 * percentage / 100, true, paint);
        canvas.drawText(context.getString(R.string.main_message_poz, 100-percentage), rectF.left + 10, rectF.bottom + 100, paint);
    }

    @Override
    protected void onSizeChanged(int weight, int height, int oldw, int oldh)
    {
        super.onSizeChanged(weight, height, oldw, oldh);
        if (weight > height)
        {
            rectF.set(weight / 2 - height / 2, 0, weight / 2 + height / 2, height);
        }
        else
        {
            rectF.set(0, height / 2 - weight / 2, weight, height / 2 + weight / 2);
        }
    }
}
