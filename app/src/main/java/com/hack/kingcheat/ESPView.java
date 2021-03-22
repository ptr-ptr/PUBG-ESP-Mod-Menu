package com.hack.kingcheat;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.BitmapFactory;
import android.util.Log;


public class ESPView extends View implements Runnable {
    Paint mStrokePaint;
    Paint mStrokePaint2;
    Paint mFilledPaint;
    Paint mFilledPaint2;
    Paint mTextPaint;
    Paint mTextPaint2;
    Paint mTextPaint3;
    Thread mThread;
    int FPS = 60;
    static long sleepTime;
    Date time;
    SimpleDateFormat formatter;
    static Context ctx;


    public ESPView(Context context) {
        super(context, null, 0);
        InitializePaints();
        setFocusableInTouchMode(false);
        setBackgroundColor(Color.TRANSPARENT);
        time = new Date();
        formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        sleepTime = 1000 / FPS;
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas != null && getVisibility() == VISIBLE) {
            ClearCanvas(canvas);
            int height = canvas.getHeight();
            float f = (height - 20);
            DrawText3(canvas, 255, 0, 255, 0, 1.0f, "Join Telegram : omar668j", 240.0f, f, 40.0f);
            FloatingModMenuService.DrawOn(this, canvas);
        }
    }
    @Override
    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        while (mThread.isAlive() && !mThread.isInterrupted()) {
            try {
                long t1 = System.currentTimeMillis();
                postInvalidate();
                long td = System.currentTimeMillis() - t1;
                Thread.sleep(Math.max(Math.min(0, sleepTime - td), sleepTime));
            } catch (InterruptedException it) {
                Log.e("OverlayThread", it.getMessage());
            }
        }
    }
    
    
    static boolean getConfig(String key){
        SharedPreferences sp=ctx.getSharedPreferences("espValue",Context.MODE_PRIVATE);
        return  sp.getBoolean(key,false);
        // return !key.equals("");
    }

    public void InitializePaints() {
        mStrokePaint = new Paint();
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setColor(Color.rgb(0, 0, 0));

        mFilledPaint = new Paint();
        mFilledPaint.setStyle(Paint.Style.FILL);
        mFilledPaint.setAntiAlias(true);
        mFilledPaint.setColor(Color.rgb(0, 0, 0));

        mStrokePaint2 = new Paint();
        mStrokePaint2.setStyle(Paint.Style.STROKE);
        mStrokePaint2.setAntiAlias(true);
        mStrokePaint2.setColor(Color.rgb(0, 0, 0));

        mFilledPaint2 = new Paint();
        mFilledPaint2.setStyle(Paint.Style.FILL);
        mFilledPaint2.setAntiAlias(true);
        mFilledPaint2.setColor(Color.rgb(0, 0, 0));

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.rgb(0, 0, 0));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setStrokeWidth(1.1f);

        mTextPaint2 = new Paint();
        mTextPaint2.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint2.setAntiAlias(true);
        mTextPaint2.setColor(Color.rgb(0, 0, 0));
        mTextPaint2.setTextAlign(Paint.Align.CENTER);
        mTextPaint2.setStrokeWidth(1.1f);

        mTextPaint3 = new Paint();
        mTextPaint3.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint3.setAntiAlias(true);
        mTextPaint3.setColor(Color.rgb(0, 0, 0));
        mTextPaint3.setTextAlign(Paint.Align.CENTER);
        mTextPaint3.setStrokeWidth(1.1f);
    }

    public void ClearCanvas(Canvas cvs) {
        cvs.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }
    public void Drawcount(Canvas canvas) {
    DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-80,50,160,40);
    DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-72,50,144,40);
    DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-64,50,128,40);
    DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-60,50,120,40);
    DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-56,50,112,40);
    DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-52,50,104,40);
    DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-48,50,96,40);
    DrawFilledRect(canvas,20,0,255,0,canvas.getWidth()/2-44,50,88,40);}
    
    public void DrawText3(Canvas cvs, int a, int r, int g, int b,float stroke, String txt, float posX, float posY, float size) {
    mTextPaint.setColor(Color.rgb(r, g, b));
    mTextPaint.setAlpha(a);
    mTextPaint.setStrokeWidth(stroke);
    if (getRight() > 1920 || getBottom() > 1920)
    mTextPaint.setTextSize(4 + size);
    else if (getRight() == 1920 || getBottom() == 1920)
    mTextPaint.setTextSize(2 + size);
    else
    mTextPaint.setTextSize(size);

    cvs.drawText(txt, posX, posY, mTextPaint);
    }

    public void DrawLine(Canvas cvs, int a, int r, int g, int b, float lineWidth, float fromX, float fromY, float toX, float toY) {
        mStrokePaint.setColor(Color.rgb(r, g, b));
        mStrokePaint.setAlpha(a);
        mStrokePaint.setStrokeWidth(1.0f);
        cvs.drawLine(fromX, fromY, toX, toY, mStrokePaint);
    }

    public void DrawRect(Canvas cvs, int a, int r, int g, int b, float stroke, float x, float y, float width, float height) {
        mStrokePaint.setStrokeWidth(stroke);
        mStrokePaint.setColor(Color.rgb(r, g, b));
        mStrokePaint.setAlpha(a);
        cvs.drawRect(x, y,  width,  height, mStrokePaint);
    }
    public void DrawFilledRect(Canvas cvs, int a, int r, int g, int b, float x, float y, float width, float height) {
        mFilledPaint.setColor(Color.rgb(r, g, b));
        mFilledPaint.setAlpha(a);
        cvs.drawRect(x, y, width, height, mFilledPaint);
    }

    public void DrawFilledRect3(Canvas cvs, int a, int r, int g, int b, float x, float y, float width, float height) {
        mFilledPaint2.setColor(Color.rgb(r, g, b));
        mFilledPaint2.setAlpha(a);
        cvs.drawRect(x-45, y-5, width+45, (height + 1.0f), mFilledPaint2);
    }

    public void DrawRect2(Canvas cvs, int a, int r, int g, int b, float stroke, float x, float y, float width, float height) {
        mStrokePaint2.setStrokeWidth(stroke);
        mStrokePaint2.setColor(Color.rgb(r, g, b));
        mStrokePaint2.setAlpha(a);
        cvs.drawRect(x-45, y-5,  width+45,  (height + 1.0f), mStrokePaint2);
    }

    public void DebugText(String s) {
        System.out.println(s);
    }

    public void DrawText(Canvas cvs, int a, int r, int g, int b, String txt, float posX, float posY, float size) {
        mTextPaint.setARGB(a,r, g, b);
        mTextPaint.setTextSize(size);
        cvs.drawText(txt, posX, posY, mTextPaint);
    }

    public void DrawTextDistance(Canvas cvs, int a, int r, int g, int b, String txt, float posX, float posY, float size) {
        mTextPaint.setARGB(180,255,255,255);
        mTextPaint.setAlpha(180);
        cvs.drawRect(posX-44,posY-18,posX-79,posY+6,mTextPaint);
        mStrokePaint.setColor(Color.rgb(0, 0, 0));
        mStrokePaint.setStrokeWidth(2.0f);
        cvs.drawRect(posX-44,posY-18,posX-79,posY+6,mStrokePaint);
        mTextPaint.setTextSize(13);
        mTextPaint.setARGB(255,0,0,0);
        cvs.drawText(txt, posX-62, posY, mTextPaint);
    }

    public void DrawTextAltert(Canvas cvs, int a, int r, int g, int b, String txt, float posX, float posY, float size) {
        mTextPaint.setARGB(255,255,255,255);
        mTextPaint.setTextSize(size);
        cvs.drawText(txt, posX, posY, mTextPaint);
    }

    public void DrawWeapon(Canvas cvs, int a, int r, int g, int b, int id,int ammo,int ammo2, float posX, float posY, float size) {
        mTextPaint.setARGB(a,r, g, b);
        String wname=getWeapon(id);
        if(wname!=null)
            mTextPaint2.setAlpha(200);
        cvs.drawRect(posX-100,posY-10,posX+100,posY+18,mTextPaint2);
        mStrokePaint.setColor(Color.rgb(0, 0, 0));
        mStrokePaint.setStrokeWidth(2.0f);
        cvs.drawRect(posX-100,posY-10,posX+100,posY+18,mStrokePaint); 
        mTextPaint.setTextSize(15);
        cvs.drawText(wname+">"+ammo+"/"+ammo2, posX, posY+12, mTextPaint);
    }

    public void DrawName(Canvas cvs, int a, int r, int g, int b, String nametxt,int teamid, float posX, float posY, float size) {
        String[] namesp = nametxt.split(":");
        char[] nameint = new char[namesp.length];
        for (int i = 0; i < namesp.length; i++)
            nameint[i] = (char) Integer.parseInt(namesp[i]);
        String realname = new String(nameint);
        mTextPaint.setARGB(a,r, g, b);
        mTextPaint2.setARGB(255,255,255,255);
        mTextPaint2.setAlpha(150);
        cvs.drawRect(posX - 100, posY-22, posX+100,posY+ 10.0f,mTextPaint2);
        mStrokePaint.setColor(Color.rgb(0, 0, 0));
        mStrokePaint.setStrokeWidth(2.0f);
        cvs.drawRect(posX - 100, posY-22, posX+100,posY+ 10.0f,mStrokePaint); 
        mTextPaint.setARGB(180,255,0,0);
        mTextPaint.setAlpha(200);
        cvs.drawCircle(posX - 100.0f, posY-5, 25.0f,mTextPaint);
        mStrokePaint.setColor(Color.rgb(0, 0, 0));
        mStrokePaint.setStrokeWidth(2.0f);
        cvs.drawCircle(posX - 100.0f, posY-5, 25.0f,mStrokePaint); 
        mTextPaint.setTextSize(15);
        mTextPaint.setARGB(180,0,0,0);
        cvs.drawText("" + realname, posX, posY, mTextPaint);
        mTextPaint.setTextSize(18);
        cvs.drawText("" + teamid, posX - 100.0f, posY, mTextPaint);
    }



    public void DrawFilledCircle(Canvas cvs, int a, int r, int g, int b, float posX, float posY, float radius) {
        mFilledPaint.setARGB(200,255,0,0);
        mFilledPaint.setAlpha(a);
        cvs.drawRect(posX-75,posY-32,posX+75,posY+16,mFilledPaint); 
        mStrokePaint.setColor(Color.rgb(255, 255, 255));
        mStrokePaint.setStrokeWidth(2.0f);
        cvs.drawRect(posX-75,posY-32,posX+75,posY+16,mStrokePaint);
    }

    public void DrawHead(Canvas cvs, int a, int r, int g, int b, float posX, float posY, float radius) {
        mFilledPaint.setColor(Color.rgb(r, g, b));
        mFilledPaint.setAlpha(a);
        mFilledPaint.setARGB(255,255,0,0);
        cvs.drawCircle(posX, posY, radius, mFilledPaint);
    }
    public void DrawItems(Canvas cvs, String itemName,float distance, float posX, float posY, float size) {
        String realItemName = getItemName(itemName);
        mTextPaint.setTextSize(size);
        if(realItemName!=null && !realItemName.equals(""))
            cvs.drawText(realItemName+" ("+Math.round(distance)+"m)", posX, posY, mTextPaint);
    }
    public void DrawVehicles(Canvas cvs, String itemName,float distance, float posX, float posY, float size) {
        String realVehicleName = getVehicleName(itemName);
        mTextPaint.setColor(Color.YELLOW);
        mTextPaint.setAlpha(150);
        mTextPaint.setTextSize(size);
        if(realVehicleName!=null && !realVehicleName.equals(""))
            cvs.drawText(realVehicleName+": "+Math.round(distance)+"m", posX, posY, mTextPaint);
    }
    private String getItemName(String s) {
        //Scopes
        if (s.contains("MZJ_8X")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "8x";
        }

        if (s.contains("MZJ_2X")) { mTextPaint.setARGB(255, 230, 172, 226);
            return "2x";
        }

        if (s.contains("MZJ_HD")) { mTextPaint.setARGB(255, 230, 172, 226);
            return "Red Dot";
        }

        if (s.contains("MZJ_3X")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "3X";
        }

        if (s.contains("MZJ_QX")) { mTextPaint.setARGB(255, 153, 75, 152);
            return "Hollow Sight";
        }

        if (s.contains("MZJ_6X")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "6x";
        }

        if (s.contains("MZJ_4X")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "4x";
        }

        if (s.contains("MZJ_SideRMR")) { mTextPaint.setARGB(255, 153, 75, 152);
            return "Canted Sight";
        }


        //AR and smg
        if (s.contains("AUG")) { mTextPaint.setARGB(255, 52, 224, 63);
            return "AUG";
        }

        if (s.contains("M762")) { mTextPaint.setARGB(255, 43, 26, 28);
            return "M762";
        }

        if (s.contains("SCAR")) { mTextPaint.setARGB(255, 52, 224, 63);
            return "SCAR-L";
        }

        if (s.contains("M416")) { mTextPaint.setARGB(255, 115, 235, 223);
            return "M416";
        }

        if (s.contains("M16A4")) { mTextPaint.setARGB(255, 116, 227, 123);
            return "M16A-4";
        }

        if (s.contains("Mk47")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "Mk47 Mutant";
        }

        if (s.contains("G36")) { mTextPaint.setARGB(255, 116, 227, 123);
            return "G36C";
        }

        if (s.contains("QBZ")) { mTextPaint.setARGB(255, 52, 224, 63);
            return "QBZ";
        }

        if (s.contains("AKM")) { mTextPaint.setARGB(255, 214, 99, 99);
            return "AKM";
        }

        if (s.contains("Groza")) { mTextPaint.setARGB(255, 214, 99, 99);
            return "Groza";
        }

        if (s.contains("PP19")) { mTextPaint.setARGB(255, 255, 246, 0);
            return "Bizon";
        }

        if (s.contains("TommyGun")) { mTextPaint.setARGB(255, 207, 207, 207);
            return "TommyGun";
        }

        if (s.contains("MP5K")) { mTextPaint.setARGB(255, 207, 207, 207);
            return "MP5K";
        }

        if (s.contains("UMP9")) { mTextPaint.setARGB(255, 207, 207, 207);
            return "UMP";
        }

        if (s.contains("Vector")) { mTextPaint.setARGB(255, 255, 246, 0);
            return "Vector";
        }

        if (s.contains("MachineGun_Uzi")) { mTextPaint.setARGB(255, 255, 246, 0);
            return "Uzi";
        }

        if (s.contains("DP28")) { mTextPaint.setARGB(255, 43, 26, 28);
            return "DP28";
        }

        if (s.contains("M249")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "M249";
        }

        //snipers

        if (s.contains("AWM")) { mTextPaint.setColor(Color.BLACK);
            return "AWM";
        }

        if (s.contains("QBU")) { mTextPaint.setARGB(255, 207, 207, 207);
            return "QBU";
        }

        if (s.contains("SLR")) { mTextPaint.setARGB(255, 43, 26, 28);
            return "SLR";
        }

        if (s.contains("SKS")) { mTextPaint.setARGB(255, 43, 26, 28);
            return "SKS";
        }

        if (s.contains("Mini14")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "Mini14";
        }

        if (s.contains("M24")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "M24";
        }

        if (s.contains("Kar98k")) { mTextPaint.setARGB(255, 247, 99, 245);
            return "Kar98k";
        }

        if (s.contains("VSS")) { mTextPaint.setARGB(255, 255, 246, 0);
            return "VSS";
        }

        if (s.contains("Win94")) { mTextPaint.setARGB(255, 207, 207, 207);
            return "Win94";
        }

        if (s.contains("Mk14")) { mTextPaint.setColor(Color.BLACK);
            return "Mk14";
        }

//shotguns and hand weapons
        if (s.contains("S12K")) { mTextPaint.setARGB(255, 153, 109, 109);
            return "S12K";
        }

        if (s.contains("DBS")) { mTextPaint.setARGB(255, 153, 109, 109);
            return "DBS";
        }

        if (s.contains("S686")) { mTextPaint.setARGB(255, 153, 109, 109);
            return "S686";
        }

        if (s.contains("S1897")) { mTextPaint.setARGB(255, 153, 109, 109);
            return "S1897";
        }

        if (s.contains("Sickle")) { mTextPaint.setARGB(255, 102, 74, 74);
            return "Sickle";
        }

        if (s.contains("Machete")) { mTextPaint.setARGB(255, 102, 74, 74);
            return "Machete";
        }

        if (s.contains("Cowbar")) { mTextPaint.setARGB(255, 102, 74, 74);
            return "Cowbar";
        }

        if (s.contains("CrossBow")) { mTextPaint.setARGB(255, 102, 74, 74);
            return "CrossBow";
        }

        if (s.contains("Pan")) { mTextPaint.setARGB(255, 102, 74, 74);
            return "Pan";
        }

        //pistols

        if (s.contains("SawedOff") ) { mTextPaint.setARGB(255, 156, 113, 81);
            return "SawedOff";
        }

        if (s.contains("R1895")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "R1895";
        }

        if (s.contains("Vz61")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "Vz61";
        }

        if (s.contains("P92")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "P92";
        }

        if (s.contains("P18C")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "P18C";
        }

        if (s.contains("R45")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "R45";
        }

        if (s.contains("P1911")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "P1911";
        }

        if (s.contains("DesertEagle")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "DesertEagle";
        }


        //Ammo
        if (s.contains("Ammo_762mm")) { mTextPaint.setARGB(255, 92, 36, 28);
            return "7.62";
        }

        if (s.contains("Ammo_45AC")) { mTextPaint.setColor(Color.LTGRAY);
            return "45ACP";
        }

        if (s.contains("Ammo_556mm")) { mTextPaint.setColor(Color.GREEN);
            return "5.56";
        }

        if (s.contains("Ammo_9mm")) { mTextPaint.setColor(Color.YELLOW);
            return "9mm";
        }

        if (s.contains("Ammo_300Magnum")) { mTextPaint.setColor(Color.BLACK);
            return "300Magnum";
        }

        if (s.contains("Ammo_12Guage")) { mTextPaint.setARGB(255, 156, 91, 81);
            return "12 Guage";
        }

        if (s.contains("Ammo_Bolt")) { mTextPaint.setARGB(255, 156, 113, 81);
            return "Arrow";
        }

        //bag helmet vest
        if (s.contains("Bag_Lv3")) { mTextPaint.setARGB(255, 36, 83, 255);
            return "Bag lvl 3";
        }

        if (s.contains("Bag_Lv1")) { mTextPaint.setARGB(255, 127, 154, 250);
            return "Bag lvl 1";
        }

        if (s.contains("Bag_Lv2")) { mTextPaint.setARGB(255, 77, 115, 255);
            return "Bag lvl 2";
        }

        if (s.contains("Armor_Lv2")) { mTextPaint.setARGB(255, 77, 115, 255);
            return "Vest lvl 2";
        }


        if (s.contains("Armor_Lv1")) { mTextPaint.setARGB(255, 127, 154, 250);
            return "Vest lvl 1";
        }


        if (s.contains("Armor_Lv3")) { mTextPaint.setARGB(255, 36, 83, 255);
            return "Vest lvl 3";
        }


        if (s.contains("Helmet_Lv2")) { mTextPaint.setARGB(255, 77, 115, 255);
            return "Helmet lvl 2";
        }

        if (s.contains("Helmet_Lv1")) { mTextPaint.setARGB(255, 127, 154, 250);
            return "Helmet lvl 1";
        }

        if (s.contains("Helmet_Lv3")) { mTextPaint.setARGB(255, 36, 83, 255);
            return "Helmet lvl 3";
        }

        //Healthkits
        if (s.contains("Pills")) { mTextPaint.setARGB(255, 227, 91, 54);
            return "Painkiller";
        }

        if (s.contains("Injection")) { mTextPaint.setARGB(255,204, 193, 190);
            return "Adrenaline";
        }

        if (s.contains("Drink")) { mTextPaint.setARGB(255, 54, 175, 227);
            return "Energy Drink";
        }

        if (s.contains("Firstaid")) { mTextPaint.setARGB(255, 194, 188, 109);
            return "FirstAidKit";
        }

        if (s.contains("Bandage")) { mTextPaint.setARGB(255, 43, 189, 48);
            return "Bandage";
        }

        if (s.contains("FirstAidbox")) { mTextPaint.setARGB(255, 0, 171, 6);
            return "Medkit";
        }

        //Throwables
        if (s.contains("Grenade_Stun")) { mTextPaint.setARGB(255,204, 193, 190);
            return "Stung";
        }

        if (s.contains("Grenade_Shoulei")) { mTextPaint.setARGB(255, 2, 77, 4);
            return "Grenade";
        }

        if (s.contains("Grenade_Smoke")) { mTextPaint.setColor(Color.WHITE);
            return "Smoke";
        }

        if (s.contains("Grenade_Burn") ) { mTextPaint.setARGB(255, 230, 175, 64);
            return "Molotov";
        }


        //others
        if (s.contains("Large_FlashHider")) { mTextPaint.setARGB(255, 255, 213, 130);
            return "Flash Hider Ar";
        }

        if (s.contains("QK_Large_C")) { mTextPaint.setARGB(255, 255, 213, 130);
            return "Ar Compensator";
        }

        if (s.contains("Mid_FlashHider")) { mTextPaint.setARGB(255, 255, 213, 130);
            return "Flash Hider SMG";
        }

        if (s.contains("QT_A_")) { mTextPaint.setARGB(255, 158, 222, 195);
            return "Tactical Stock";
        }

        if (s.contains("DuckBill")) { mTextPaint.setARGB(255, 158, 222, 195);
            return "DuckBill";
        }

        if (s.contains("Sniper_FlashHider")) { mTextPaint.setARGB(255, 158, 222, 195);
            return "Flash Hider Sniper";
        }

        if (s.contains("Mid_Suppressor")) { mTextPaint.setARGB(255, 158, 222, 195);
            return "Suppressor SMG";
        }

        if (s.contains("HalfGrip")) { mTextPaint.setARGB(255, 155, 189, 222);
            return "Half Grip";
        }


        if (s.contains("Choke")) { mTextPaint.setARGB(255, 155, 189, 222);
            return "Choke";
        }

        if (s.contains("QT_UZI")) { mTextPaint.setARGB(255, 155, 189, 222);
            return "Stock Micro UZI";
        }

        if (s.contains("QK_Sniper")) { mTextPaint.setARGB(255, 60, 127, 194);
            return "Sniper Compensator";
        }

        if (s.contains("Sniper_Suppressor")) { mTextPaint.setARGB(255, 60, 127, 194);
            return "Suppressor Sniper";
        }

        if (s.contains("Large_Suppressor")) { mTextPaint.setARGB(255, 60, 127, 194);
            return "Suppressor Ar";
        }


        if (s.contains("Sniper_EQ_")) { mTextPaint.setARGB(255, 193, 140, 222);
            return "Ex.Qd.Sniper";
        }

        if (s.contains("Mid_Q_")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Qd.SMG";
        }

        if (s.contains("Mid_E_")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Ex.SMG";
        }

        if (s.contains("Sniper_Q_")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Qd.Sniper";
        }

        if (s.contains("Sniper_E_")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Ex.Sniper";
        }

        if (s.contains("Large_E_")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Ex.Ar";
        }

        if (s.contains("Large_EQ_")) { mTextPaint.setARGB(255, 193, 140, 222);
            return "Ex.Qd.Ar";
        }

        if (s.contains("Large_Q_")) { mTextPaint.setARGB(255, 193, 163, 209);
            return "Qd.Ar";
        }

        if (s.contains("Mid_EQ_")) { mTextPaint.setARGB(255, 193, 140, 222);
            return "Ex.Qd.SMG";
        }

        if (s.contains("Crossbow_Q")) { mTextPaint.setARGB(255, 148, 121, 163);
            return "Quiver CrossBow";
        }

        if (s.contains("ZDD_Sniper")) { mTextPaint.setARGB(255, 148, 121, 163);
            return "Bullet Loop";
        }


        if (s.contains("ThumbGrip")) { mTextPaint.setARGB(255, 148, 121, 163);
            return "Thumb Grip";
        }

        if (s.contains("Lasersight")) { mTextPaint.setARGB(255, 148, 121, 163);
            return "Laser Sight";
        }

        if (s.contains("Angled")) { mTextPaint.setARGB(255, 219, 219, 219);
            return "Angled Grip";
        }

        if (s.contains("LightGrip")) { mTextPaint.setARGB(255, 219, 219, 219);
            return "Light Grip";
        }

        if (s.contains("Vertical")) { mTextPaint.setARGB(255, 219, 219, 219);
            return "Vertical Grip";
        }

        if (s.contains("GasCan")) { mTextPaint.setARGB(255, 255, 143, 203);
            return "Gas Can";
        }

        if (s.contains("Mid_Compensator")) { mTextPaint.setARGB(255, 219, 219, 219);
            return "Compensator SMG";
        }


        //special
        if (s.contains("Flare")) { mTextPaint.setARGB(255, 242, 63, 159);
            return "Flare Gun";
        }

        if (s.contains("Ghillie")) { mTextPaint.setARGB(255, 139, 247, 67);
            return "Ghillie Suit";
        }
        if (s.contains("CheekPad")) { mTextPaint.setARGB(255, 112, 55, 55);
            return "CheekPad";
        }
        if ( s.contains("PickUpListWrapperActor")) { mTextPaint.setARGB(255, 132, 201, 66);
            return "Crate";
        }
        if ((s.contains("AirDropPlane"))) { mTextPaint.setARGB(255, 224, 177, 224);
            return "DropPlane";
        }
        if ((s.contains("AirDrop"))){ mTextPaint.setARGB(255, 255, 10, 255);
            return "AirDrop";
        }
        //return s;
        return null;

    }
    private String getWeapon(int id){
        if(id==101006)
            return "AUG";

        if(id==101008)
            return "M762" ;

        if(id==101003)
            return "SCAR-L";

        if(id==101004)
            return "M416";

        if(id==101002)
            return "M16A-4";

        if(id==101009)
            return "Mk47 Mutant";

        if(id==101010)
            return "G36C";

        if(id==101007)
            return "QBZ";

        if(id==101001)
            return "AKM";

        if(id==101005)
            return "Groza";

        if(id==102005)
            return "Bizon";

        if(id==102004)
            return "TommyGun";

        if(id==102007)
            return "MP5K";

        if(id==102002)
            return "UMP";

        if(id==102003)
            return "Vector";

        if(id==102001)
            return "Uzi";

        if(id==105002)
            return "DP28";

        if(id==105001)
            return "M249";

        //snipers

        if(id==103003)
            return "AWM";

        if(id==103010)
            return "QBU";

        if(id==103009)
            return "SLR";

        if(id==103004)
            return "SKS";

        if(id==103006)
            return "Mini14";

        if(id==103002)
            return "M24";

        if(id==103001)
            return "Kar98k";

        if(id==103005)
            return "VSS";

        if(id==103008)
            return "Win94";

        if(id==103007)
            return "Mk14";

//shotguns and hand weapons
        if(id==104003)
            return "S12K";

        if(id==104004)
            return "DBS";

        if(id==104001)
            return "S686";

        if(id==104002)
            return "S1897";

        if(id==108003)
            return "Sickle";

        if(id==108001)
            return "Machete";

        if(id==108002)
            return "Crowbar";

        if(id==107001)
            return "CrossBow";

        if(id==108004)
            return "Pan";

        //pistols

        if(id==106006)
            return "SawedOff";

        if(id==106003)
            return "R1895";

        if(id==106008)
            return "Vz61";

        if(id==106001)
            return "P92";

        if(id==106004)
            return "P18C";

        if(id==106005)
            return "R45";

        if(id==106002)
            return "P1911";

        if(id==106010)
            return "DesertEagle";

        return null;

    }
    private String getVehicleName(String s){
        if(s.contains("Buggy"))
            return "Buggy";

        if(s.contains("UAZ"))
            return "UAZ";

        if(s.contains("MotorcycleC"))
            return "Trike";

        if(s.contains("Motorcycle"))
            return "Bike";

        if(s.contains("Dacia"))
            return "Dacia";

        if(s.contains("AquaRail"))
            return "Jet";

        if(s.contains("PG117"))
            return "Boat";

        if(s.contains("MiniBus"))
            return "Bus";

        if(s.contains("Mirado"))
            return "Mirado";

        if(s.contains("Scooter"))
            return "Scooter";

        if(s.contains("Rony"))
            return "Rony";

        if(s.contains("Snowbike"))
            return "Snowbike";

        if(s.contains("Snowmobile"))
            return "Snowmobile";

        if(s.contains("Tuk"))
            return "Tempo";

        if(s.contains("PickUp"))
            return "Truck";

        if(s.contains("BRDM"))
            return "BRDM";

        if(s.contains("LadaNiva"))
            return "LadaNiva";

        if(s.contains("Bigfoot"))
            return "Monster Truck";

        return "";
    }

    public static Bitmap scale(Bitmap bitmap, int maxWidth, int maxHeight) {
        // Determine the constrained dimension, which determines both dimensions.
        int width;
        int height;
        float widthRatio = (float)bitmap.getWidth() / maxWidth;
        float heightRatio = (float)bitmap.getHeight() / maxHeight;
        // Width constrained.
        if (widthRatio >= heightRatio) {
            width = maxWidth;
            height = (int)(((float)width / bitmap.getWidth()) * bitmap.getHeight());
        }
        // Height constrained.
        else {
            height = maxHeight;
            width = (int)(((float)height / bitmap.getHeight()) * bitmap.getWidth());
        }
        Bitmap scaledBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        float ratioX = (float)width / bitmap.getWidth();
        float ratioY = (float)height / bitmap.getHeight();
        float middleX = width / 2.0f;
        float middleY = height / 2.0f;
        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        return scaledBitmap;
    }
}

