package com.kingja.customcheckcodeview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Shinelon on 2015/12/13.
 */
public class CustomCheckCodeView extends View {
    private static final String TAG = "CustomCheckCodeView";
    private Context context;

    private Paint mPaint;
    private int textNumber = 4;
    private int pointNumber = 40;
    private int mTitleTextSize = 40;
    private Rect mBound = new Rect();
    private String single = "0";
    private float textSize;
    private int textColor;
    private int bgColor;
    private int type;

    public CustomCheckCodeView(Context context) {
        this(context, null);
    }

    public CustomCheckCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomCheckCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCheckCodeView);
        textSize = typedArray.getDimension(R.styleable.CustomCheckCodeView_textSize, 32);
        textColor = typedArray.getColor(R.styleable.CustomCheckCodeView_textColor, Color.RED);
        bgColor = typedArray.getColor(R.styleable.CustomCheckCodeView_bgColor, Color.YELLOW);
        textNumber = typedArray.getInteger(R.styleable.CustomCheckCodeView_textNumber, 4);
        type = typedArray.getInteger(R.styleable.CustomCheckCodeView_type, 0);
        typedArray.recycle();
        this.context = context;
        mPaint = new Paint();
        mPaint.setTextSize(textSize);
        mPaint.setColor(textColor);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(single, 0, single.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + (textWidth + CheckCodeUtil.dip2px(context, 8) * 2) * textNumber + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(single, 0, single.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + CheckCodeUtil.dip2px(context, 8) * 2 + getPaddingBottom());
            height = desired;
        }


        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        String s = "0";
        canvas.drawColor(bgColor);
        Rect rect = new Rect();
        mPaint.getTextBounds(s, 0, s.length(), rect);
        //绘制随机数字
        String[] checkCode = type==0?CheckCodeUtil.getNumber(textNumber):CheckCodeUtil.getChinese(textNumber);
        for (int i = 0; i < textNumber; i++) {
            drawText(canvas, checkCode[i], i * getWidth() / textNumber + getWidth() / textNumber / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, mPaint, CheckCodeUtil.getRandomDegrees());
        }
        //绘制随机点
        int[] point = null;
        for (int i = 0; i < pointNumber; i++) {
            point = CheckCodeUtil.getPoint(width, height);
            canvas.drawPoint(point[0], point[1], mPaint);
        }


    }



    private void drawText(Canvas canvas, String text, float x, float y, Paint paint, float angle) {
        if (angle != 0) {
            canvas.rotate(angle, x, y);
        }
        canvas.drawText(text, x, y, paint);
        if (angle != 0) {
            canvas.rotate(-angle, x, y);
        }
    }



    /**
     * 生成新的验证码
     */
    public void invaliChenkCode() {
        invalidate();
    }
}
