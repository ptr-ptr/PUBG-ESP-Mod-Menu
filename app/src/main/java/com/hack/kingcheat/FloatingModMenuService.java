package com.hack.kingcheat;


import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import java.util.List;
import java.util.Random;
import android.view.Gravity;
import android.graphics.PixelFormat;
import android.view.ViewConfiguration;

public class FloatingModMenuService
extends Service {
    public static String TITLE;
    int GLITCH_DELAY = 175;
    int GLITCH_LEN = 2;
    float density;
    int dpi;
    WindowManager.LayoutParams g_layoutParams;
    int height=30;
    RelativeLayout iconLayout;
    LinearLayout itemsLayout;
    RelativeLayout relativeLayout;
    ScrollView scrollView;
    TextView textTitle;
    int type;
    int width=50;
    WindowManager windowManager;
    public native void stringFromJNI();
    ESPView espLayout;
    static Context ctx;
    private native String Icon();

    static {
        TITLE = "هكر السوراقي";
    }


    private void AddButton(String string, View.OnClickListener onClickListener) {
        Button button = new Button((Context)this);
        button.setText((CharSequence)string);
        button.setX((float)this.convertSizeToDp(2.0f));
        button.setY(10.0f + button.getY());
        button.setTextSize(1, 12.5f);
        button.setOnClickListener(onClickListener);
        button.setBackgroundColor(Color.argb((int)255, (int)230, (int)0, (int)0));
        button.setTextColor(-1);
        button.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(this.g_layoutParams.width - 2* this.convertSizeToDp(2.0f), -2));
        this.itemsLayout.addView((View)button);
    }
    private void AddSeekbar(final String string, int n3,final SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        final TextView textView = new TextView((Context)this);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append(": ");
        stringBuilder.append(n3);
        textView.setText((CharSequence)stringBuilder.toString());
        textView.setTextSize(1, 12.5f);
        textView.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
        textView.setTextColor(-1);
        textView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -2));
        SeekBar seekBar = new SeekBar((Context)this);
        seekBar.setMax(n3);
        seekBar.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

                public void onProgressChanged(SeekBar seekBar, int n, boolean bl) {
                    TextView textView2 = textView;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(string);
                    stringBuilder.append(": ");
                    stringBuilder.append(n);
                    textView2.setText((CharSequence)stringBuilder.toString());
                    onSeekBarChangeListener.onProgressChanged(seekBar, n, bl);
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        this.itemsLayout.addView((View)textView);
        this.itemsLayout.addView((View)seekBar);
    }

    private void AddText(String string) {
        TextView textView = new TextView((Context)this);
        textView.setText((CharSequence)string);
        textView.setTextSize(1, 12.5f);
        textView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-2, -2));
        textView.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
        textView.setTextColor(-1);
        this.itemsLayout.addView((View)textView);
    }

    private void AddTextDivide(String string) {
        TextView textView = new TextView((Context)this);
        textView.setText((CharSequence)string);
        textView.setTextSize(1, 15.0f);
        textView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-2, -2));
        textView.setTypeface(null, 1);
        textView.setPadding(this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(5.0f));
        textView.setTextColor(Color.argb((int)255, (int)230, (int)0, (int)0));
        this.itemsLayout.addView((View)textView);
    }

    private void AddToggle(String string, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        Switch switch_ = new Switch((Context)this);
        switch_.setText((CharSequence)string);
        switch_.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
        switch_.setTextSize(1, 12.5f);
        switch_.setTextColor(-1);
        switch_.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                }
            });
        ColorStateList colorStateList = new ColorStateList((int[][])new int[][]{{-16842910}, {16842912}, new int[0]}, new int[]{-16776961, -16711936, -65536});
        ColorStateList colorStateList2 = new ColorStateList((int[][])new int[][]{{-16842910}, new int[0]}, new int[]{-7829368, -3355444});
        if (Build.VERSION.SDK_INT >= 23) {
            if (Build.VERSION.SDK_INT >= 24) {
                switch_.setTrackTintList(colorStateList2);
                switch_.setTrackTintMode(PorterDuff.Mode.OVERLAY);
            }
            switch_.setThumbTintList(colorStateList);
        } else if (Build.VERSION.SDK_INT >= 21) {
            switch_.getThumbDrawable().setTintList(colorStateList);
            switch_.getTrackDrawable().setTintList(colorStateList2);
        }
        switch_.setOnCheckedChangeListener(onCheckedChangeListener);
        switch_.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -2));
        this.itemsLayout.addView((View)switch_);
    }

    private void AddToggleDefTrue(String string, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        Switch switch_ = new Switch((Context)this);
        switch_.setText((CharSequence)string);
        switch_.setChecked(true);
        switch_.setPadding(this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f), this.convertSizeToDp(10.0f), this.convertSizeToDp(5.0f));
        switch_.setTextSize(1, 12.5f);
        switch_.setTextColor(-1);
        switch_.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                }
            });
        ColorStateList colorStateList = new ColorStateList((int[][])new int[][]{{-16842910}, {16842912}, new int[0]}, new int[]{-16776961, -16711936, -65536});
        ColorStateList colorStateList2 = new ColorStateList((int[][])new int[][]{{-16842910}, new int[0]}, new int[]{-7829368, -3355444});
        if (Build.VERSION.SDK_INT >= 23) {
            if (Build.VERSION.SDK_INT >= 24) {
                switch_.setTrackTintList(colorStateList2);
                switch_.setTrackTintMode(PorterDuff.Mode.OVERLAY);
            }
            switch_.setThumbTintList(colorStateList);
        } else if (Build.VERSION.SDK_INT >= 21) {
            switch_.getThumbDrawable().setTintList(colorStateList);
            switch_.getTrackDrawable().setTintList(colorStateList2);
        }
        switch_.setOnCheckedChangeListener(onCheckedChangeListener);
        switch_.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -2));
        this.itemsLayout.addView((View)switch_);
    }
    static native void Switch(int i, boolean k);

    static native void Switch2(int i);
    static native void TextSize(int var0);

    private int getLayoutType() {
        if (Build.VERSION.SDK_INT >= 26) {
            return 2038;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return 2002;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return 2005;
        }
        return 2003;
    }
    public static native void DrawOn(ESPView espView, Canvas canvas);
    public void CreateCanvas() {
        ESPView eSPView;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, this.type, 56, -3);
        if (Build.VERSION.SDK_INT >= 28) {
            layoutParams.layoutInDisplayCutoutMode = 1;
        }
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.gravity = 51;
        this.espLayout = eSPView = new ESPView((Context)this);
        this.windowManager.addView((View)eSPView, (ViewGroup.LayoutParams)layoutParams);
    }
    public int convertSizeToDp(float f) {
        return Math.round((float)TypedValue.applyDimension((int)1, (float)f, (DisplayMetrics)this.getResources().getDisplayMetrics()));
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        final RelativeLayout relativeLayout;
        TextView textView;
        ScrollView scrollView;
        super.onCreate();
        System.loadLibrary("CMODS");
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        this.relativeLayout = new RelativeLayout((Context)this);
        this.type = this.getLayoutType();
        Display display = this.windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        this.width = point.x;
        this.height = point.y;
        this.dpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        this.density = Resources.getSystem().getDisplayMetrics().density;
        this.itemsLayout = new LinearLayout((Context)this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.itemsLayout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        this.relativeLayout.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
        this.scrollView = scrollView = new ScrollView((Context)this);
        scrollView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -2));
        this.scrollView.setPadding(0, (int)((float)this.dpi / 5.5f), 0, 0);
        int n = this.convertSizeToDp(250.0f);
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(n, this.convertSizeToDp(300.0f), this.type, 8, -3);
        this.relativeLayout.setBackgroundColor(Color.argb((int)500, (int)0, (int)1, (int)1));
        layoutParams2.x = 0;
        layoutParams2.y = 0;
        layoutParams2.gravity = 51;
        this.g_layoutParams = layoutParams2;
        FrameLayout frameLayout = new FrameLayout((Context)this);
        frameLayout.setClickable(true);
        frameLayout.setFocusable(true);
        frameLayout.setFocusableInTouchMode(true);
        frameLayout.setBackgroundColor(Color.argb((int)200, (int)0, (int)210, (int)0));
        frameLayout.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(n, (int)((float)this.dpi / 5.5f)));
        Button button = new Button((Context)this);
        button.setText("x");
        button.setTextColor(-1);
        button.setTextSize(1, 8.0f);
        button.setBackgroundColor(Color.argb((int)255, (int)230, (int)0, (int)0));
        button.setX((float)(n - (int)((float)this.dpi / 5.5f)));
        frameLayout.addView((View)button);
        this.textTitle = textView = new TextView((Context)this);
        textView.setText((CharSequence)TITLE);
        this.textTitle.setGravity(19);
        this.textTitle.setTextColor(-1);
        this.textTitle.setTypeface(null, 1);
        this.textTitle.setPadding(this.convertSizeToDp(8.0f), 0, 0, 0);
        this.textTitle.setTextSize(1, 15.0f);
        frameLayout.addView((View)this.textTitle);
          this.relativeLayout.addView((View)this.scrollView);
        this.relativeLayout.addView((View)frameLayout);
        this.relativeLayout.setAlpha(0.7f);
        this.itemsLayout.setOrientation(1);
        this.scrollView.addView((View)this.itemsLayout);
        this.iconLayout = relativeLayout = new RelativeLayout((Context)this);
        relativeLayout.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-2, -2));
        ImageView imageView = new ImageView((Context)this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(this.convertSizeToDp(60.0f), this.convertSizeToDp(60.0f)));
        relativeLayout.addView((View)imageView);
        byte[] arrby = Base64.decode(Icon(), 0);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray((byte[])arrby, (int)0, (int)arrby.length));
        final WindowManager.LayoutParams layoutParams4 = new WindowManager.LayoutParams(-2, -2, this.type, 8, -3);
        layoutParams4.gravity = 51;
        layoutParams4.x = 0;
        layoutParams4.y = 0;
        this.windowManager.addView((View)relativeLayout, (ViewGroup.LayoutParams)layoutParams4);
        this.windowManager.addView((View)this.relativeLayout, (ViewGroup.LayoutParams)layoutParams2);
        this.relativeLayout.setVisibility(8);
        frameLayout.setOnTouchListener(new View.OnTouchListener(){
                float deltaX;
                float deltaY;
                float maxX;
                float maxY;
                float newX;
                float newY;
                float pressedX;
                float pressedY;

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int n = motionEvent.getActionMasked();
                    if (n == 0) {
                        this.deltaX = (float)FloatingModMenuService.this.g_layoutParams.x - motionEvent.getRawX();
                        this.deltaY = (float)FloatingModMenuService.this.g_layoutParams.y - motionEvent.getRawY();
                        this.pressedX = motionEvent.getRawX();
                        this.pressedY = motionEvent.getRawY();
                        FloatingModMenuService.this.scrollView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    }
                    if (n == 1) {
                        float f;
                        float f2;
                        float f3;
                        float f4;
                        this.maxX = FloatingModMenuService.this.width - FloatingModMenuService.this.relativeLayout.getWidth();
                        this.maxY = FloatingModMenuService.this.height - FloatingModMenuService.this.relativeLayout.getHeight();
                        if (this.newX < 0.0f) {
                            this.newX = 0.0f;
                        }
                        if ((f = this.newX) > (f3 = this.maxX)) {
                            this.newX = (int)f3;
                        }
                        if (this.newY < 0.0f) {
                            this.newY = 0.0f;
                        }
                        if ((f4 = this.newY) > (f2 = this.maxY)) {
                            this.newY = (int)f2;
                        }
                        FloatingModMenuService.this.g_layoutParams.x = (int)this.newX;
                        FloatingModMenuService.this.g_layoutParams.y = (int)this.newY;
                        FloatingModMenuService.this.windowManager.updateViewLayout((View)FloatingModMenuService.this.relativeLayout, (ViewGroup.LayoutParams)FloatingModMenuService.this.g_layoutParams);
                        FloatingModMenuService.this.relativeLayout.setAlpha(0.7f);
                        FloatingModMenuService.this.scrollView.requestDisallowInterceptTouchEvent(false);
                        return true;
                    }
                    if (n == 2) {
                        float f;
                        float f5;
                        this.newX = motionEvent.getRawX() + this.deltaX;
                        this.newY = motionEvent.getRawY() + this.deltaY;
                        this.maxX = FloatingModMenuService.this.width - FloatingModMenuService.this.relativeLayout.getWidth();
                        this.maxY = f5 = (float)(FloatingModMenuService.this.height - FloatingModMenuService.this.relativeLayout.getHeight());
                        float f6 = this.newX;
                        if (f6 >= 0.0f && f6 <= this.maxX && (f = this.newY) >= 0.0f && f <= f5) {
                            FloatingModMenuService.this.relativeLayout.setAlpha(0.7f);
                            FloatingModMenuService.this.g_layoutParams.x = (int)this.newX;
                            FloatingModMenuService.this.g_layoutParams.y = (int)this.newY;
                            FloatingModMenuService.this.windowManager.updateViewLayout((View)FloatingModMenuService.this.relativeLayout, (ViewGroup.LayoutParams)FloatingModMenuService.this.g_layoutParams);
                        }
                        FloatingModMenuService.this.relativeLayout.setAlpha(0.5f);
                        FloatingModMenuService.this.g_layoutParams.x = (int)this.newX;
                        FloatingModMenuService.this.g_layoutParams.y = (int)this.newY;
                        FloatingModMenuService.this.windowManager.updateViewLayout((View)FloatingModMenuService.this.relativeLayout, (ViewGroup.LayoutParams)FloatingModMenuService.this.g_layoutParams);
                    }
                    return false;
                }
            });
        relativeLayout.setOnTouchListener(new View.OnTouchListener(){
                float deltaX;
                float deltaY;
                float newX;
                float newY;
                float pressedX;
                float pressedY;

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int n = motionEvent.getActionMasked();
                    if (n == 0) {
                        this.deltaX = (float)layoutParams4.x - motionEvent.getRawX();
                        this.deltaY = (float)layoutParams4.y - motionEvent.getRawY();
                        this.pressedX = motionEvent.getRawX();
                        this.pressedY = motionEvent.getRawY();
                        return false;
                    }
                    if (n == 1) {
                        int n2 = (int)(motionEvent.getRawX() - this.pressedX);
                        int n3 = (int)(motionEvent.getRawY() - this.pressedY);
                        if (n2 == 0 && n3 == 0) {
                            FloatingModMenuService.this.relativeLayout.setVisibility(0);
                            relativeLayout.setVisibility(8);
                        }
                        return true;
                    }
                    if (n == 2) {
                        this.newX = motionEvent.getRawX() + this.deltaX;
                        this.newY = motionEvent.getRawY() + this.deltaY;
                        float f = FloatingModMenuService.this.width - view.getWidth();
                        float f2 = FloatingModMenuService.this.height - view.getHeight();
                        if (this.newX < 0.0f) {
                            this.newX = 0.0f;
                        }
                        if (this.newX > f) {
                            this.newX = (int)f;
                        }
                        if (this.newY < 0.0f) {
                            this.newY = 0.0f;
                        }
                        if (this.newY > f2) {
                            this.newY = (int)f2;
                        }
                        layoutParams4.x = (int)this.newX;
                        layoutParams4.y = (int)this.newY;
                        FloatingModMenuService.this.windowManager.updateViewLayout((View)relativeLayout, (ViewGroup.LayoutParams)layoutParams4);
                    }
                    return false;
                }
            });
        button.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FloatingModMenuService.this.relativeLayout.setVisibility(8);
                    relativeLayout.setVisibility(0);
                }
            });
        CreateCanvas();
        this.AddTextDivide("𝙴𝚂𝙿 𝙼𝙴𝙽𝚄");
        AddToggleDefTrue("Active Esp", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    ESPView eSPView = espLayout;
                    int n = bl ? 0 : 4;
                    eSPView.setVisibility(n);
                    }
            });
        AddToggle("Esp Box", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(1, bl);
                }
            });
        AddToggle("Esp Line", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(2, bl);
                }
            });
        AddToggle("Esp Distance", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(3, bl);
                }
            });
        AddToggle("Esp Health", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(4, bl);
                }
            });
        AddToggle("Player Name", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(5, bl);
                }
            });
        AddToggle("Esp Head", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(6, bl);
                }
            });
        AddToggle("Esp 360°", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(7, bl);
                }
            });
        this.AddToggle("Skeleton", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(8, bl);
                }
            });
        AddToggle("Grenade Warning", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(9, bl);
                }
            });
        this.AddToggle("Enemy Count", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(14, bl);
                }
            });
        this.AddToggle("Vehicle", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(13, bl);
                }
            });
        this.AddToggle("Loot Box", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(11, bl);
                }
            });
        AddToggle("Items", new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    Switch(12, bl);
                }
            });
        AddButton("Close Menu", new View.OnClickListener(){
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder((Context)FloatingModMenuService.this, 5);
                    builder.setMessage("You will have to reopen the game to show the menu and esp again press the x in the top corner to zoom out instead. Are you sure you want to close the list completely?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialogInterface, int n) {
                                stopSelf();
                            }
                        });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialogInterface, int n) {
                                dialogInterface.dismiss();
                            }
                        });
                    AlertDialog alertDialog = builder.create();
                    if (Build.VERSION.SDK_INT >= 26) {
                        Window window = alertDialog.getWindow();
                        if (window != null) {
                            window.setType(2038);
                        }
                    } else {
                        Window window = alertDialog.getWindow();
                        if (window != null) {
                            window.setType(2002);
                        }
                    }
                    alertDialog.show();
                }

            });
        AddText("Telegram : omar668j");
    }

    public void onDestroy() {
        this.windowManager.removeViewImmediate((View)this.espLayout);
        this.windowManager.removeViewImmediate((View)this.iconLayout);
        this.windowManager.removeViewImmediate((View)this.relativeLayout);
        super.onDestroy();
    }
    public static interface OnListChoosedListener {
        public void onChoosed(int var1);
    }

}



