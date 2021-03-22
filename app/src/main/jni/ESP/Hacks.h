#ifndef DESI_ESP_IMPORTANT_HACKS_H
#define DESI_ESP_IMPORTANT_HACKS_H
#include "support.h"
#include "init.h"
#include "import.h"
#include "socket.h"
#include "Memory.h"
#include "Process.h"
#include <thread>
#include <sys/uio.h>
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/syscall.h>
#include <unistd.h>
#include <sys/uio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <errno.h>
#include <stdio.h>
#include <sys/un.h>
#include <time.h>
#include <ctype.h>
#include <iostream>
kaddr pBase;
int myTeamID = 9999;
int myplayeradd = 0;
char VehicleName[50];
float Distance;
Vec3 Location;
char ItemName[50];
char PlayerNameByte[100];
int TeamID;
float Health;
bool isBot;
Vec3 HeadLocation;
PlayerWeapon Weapon;
PlayerBone Bone;
kaddr vworld; 
kaddr CM; 
kaddr JZDZ;
float x,y;
char extra[30];
int botCount,playerCount;
Color clr,clrHealth;
int cfd;
int recbytes;
int sin_size;
Request request{};
Response response{};
struct sockaddr_in s_add, c_add;
unsigned short portnum = 443;
#define PI 3.141592653589793238
float px;
float py;


struct D3DXVECTOR4
{
    float X;
    float Y;
    float Z;
    float W;
};
struct D3DXMATRIX
{
    float _11;
    float _12;
    float _13;
    float _14;
    float _21;
    float _22;
    float _23;
    float _24;
    float _31;
    float _32;
    float _33;
    float _34;
    float _41;
    float _42;
    float _43;
    float _44;
};
struct FTransform1
{
    D3DXVECTOR4 Rotation;
    Vec3 Translation;
    Vec3 Scale3D;
};
float get2dDistance(float x, float y, float x1, float y1)
{
    float xx1 = x - x1;
    float yy2 = y - y1;
    return sqrt(xx1 * xx1 + yy2 * yy2);
}
Vec2 rotateCoord(float angle, float objectRadar_x, float objectRadar_y)
{
    Vec2 radarCoordinate;
    float s = sin(angle * PI / 180);
    float c = cos(angle * PI / 180);
    radarCoordinate.X = objectRadar_x * c + objectRadar_y * s;
    radarCoordinate.Y = -objectRadar_x * s + objectRadar_y * c;
    return radarCoordinate;
}

float get_3D_Distance(float Self_x, float Self_y, float Self_z, float Object_x, float Object_y, float Object_z)
{
    float x, y, z;
    x = Self_x - Object_x;
    y = Self_y - Object_y;
    z = Self_z - Object_z;
    return (float)(sqrt(x * x + y * y + z * z));
}

D3DXMATRIX ToMatrixWithScale(D3DXVECTOR4 Rotation, Vec3 Translation, Vec3 Scale3D)
{
    D3DXMATRIX M;
    float X2, Y2, Z2, xX2, Yy2, Zz2, Zy2, Wx2, Xy2, Wz2, Zx2, Wy2;
    M._41 = Translation.X;
    M._42 = Translation.Y;
    M._43 = Translation.Z;
    X2 = Rotation.X + Rotation.X;
    Y2 = Rotation.Y + Rotation.Y;
    Z2 = Rotation.Z + Rotation.Z;
    xX2 = Rotation.X * X2;
    Yy2 = Rotation.Y * Y2;
    Zz2 = Rotation.Z * Z2;
    M._11 = (1 - (Yy2 + Zz2)) * Scale3D.X;
    M._22 = (1 - (xX2 + Zz2)) * Scale3D.Y;
    M._33 = (1 - (xX2 + Yy2)) * Scale3D.Z;
    Zy2 = Rotation.Y * Z2;
    Wx2 = Rotation.W * X2;
    M._32 = (Zy2 - Wx2) * Scale3D.Z;
    M._23 = (Zy2 + Wx2) * Scale3D.Y;
    Xy2 = Rotation.X * Y2;
    Wz2 = Rotation.W * Z2;
    M._21 = (Xy2 - Wz2) * Scale3D.Y;
    M._12 = (Xy2 + Wz2) * Scale3D.X;
    Zx2 = Rotation.X * Z2;
    Wy2 = Rotation.W * Y2;
    M._31 = (Zx2 + Wy2) * Scale3D.Z;
    M._13 = (Zx2 - Wy2) * Scale3D.X;
    M._14 = 0;
    M._24 = 0;
    M._34 = 0;
    M._44 = 1;
    return M;
}

        // BF开547087912

D3DXMATRIX D3DXMatrixMultiply(D3DXMATRIX Pm1, D3DXMATRIX Pm2)
{
    D3DXMATRIX Pout;
    Pout._11 = Pm1._11 * Pm2._11 + Pm1._12 * Pm2._21 + Pm1._13 * Pm2._31 + Pm1._14 * Pm2._41;
    Pout._12 = Pm1._11 * Pm2._12 + Pm1._12 * Pm2._22 + Pm1._13 * Pm2._32 + Pm1._14 * Pm2._42;
    Pout._13 = Pm1._11 * Pm2._13 + Pm1._12 * Pm2._23 + Pm1._13 * Pm2._33 + Pm1._14 * Pm2._43;
    Pout._14 = Pm1._11 * Pm2._14 + Pm1._12 * Pm2._24 + Pm1._13 * Pm2._34 + Pm1._14 * Pm2._44;
    Pout._21 = Pm1._21 * Pm2._11 + Pm1._22 * Pm2._21 + Pm1._23 * Pm2._31 + Pm1._24 * Pm2._41;
    Pout._22 = Pm1._21 * Pm2._12 + Pm1._22 * Pm2._22 + Pm1._23 * Pm2._32 + Pm1._24 * Pm2._42;
    Pout._23 = Pm1._21 * Pm2._13 + Pm1._22 * Pm2._23 + Pm1._23 * Pm2._33 + Pm1._24 * Pm2._43;
    Pout._24 = Pm1._21 * Pm2._14 + Pm1._22 * Pm2._24 + Pm1._23 * Pm2._34 + Pm1._24 * Pm2._44;
    Pout._31 = Pm1._31 * Pm2._11 + Pm1._32 * Pm2._21 + Pm1._33 * Pm2._31 + Pm1._34 * Pm2._41;
    Pout._32 = Pm1._31 * Pm2._12 + Pm1._32 * Pm2._22 + Pm1._33 * Pm2._32 + Pm1._34 * Pm2._42;
    Pout._33 = Pm1._31 * Pm2._13 + Pm1._32 * Pm2._23 + Pm1._33 * Pm2._33 + Pm1._34 * Pm2._43;
    Pout._34 = Pm1._31 * Pm2._14 + Pm1._32 * Pm2._24 + Pm1._33 * Pm2._34 + Pm1._34 * Pm2._44;
    Pout._41 = Pm1._41 * Pm2._11 + Pm1._42 * Pm2._21 + Pm1._43 * Pm2._31 + Pm1._44 * Pm2._41;
    Pout._42 = Pm1._41 * Pm2._12 + Pm1._42 * Pm2._22 + Pm1._43 * Pm2._32 + Pm1._44 * Pm2._42;
    Pout._43 = Pm1._41 * Pm2._13 + Pm1._42 * Pm2._23 + Pm1._43 * Pm2._33 + Pm1._44 * Pm2._43;
    Pout._44 = Pm1._41 * Pm2._14 + Pm1._42 * Pm2._24 + Pm1._43 * Pm2._34 + Pm1._44 * Pm2._44;
    return Pout;
}
FTransform1 ReadFTransform(kaddr address)
{
    FTransform1 Result;
    Result.Rotation.X = Read<float>(address);    // Rotation_X 
    Result.Rotation.Y = Read<float>(address + 4);    // Rotation_y
    Result.Rotation.Z = Read<float>(address + 8);    // Rotation_z
    Result.Rotation.W = Read<float>(address + 12);   // Rotation_w
    Result.Translation.X = Read<float>(address + 16);    // /Translation_X
    Result.Translation.Y = Read<float>(address + 20);    // Translation_y
    Result.Translation.Z = Read<float>(address + 24);    // Translation_z
    Result.Scale3D.X = Read<float>(address + 32);;   // Scale_X
    Result.Scale3D.Y = Read<float>(address + 36);;   // Scale_y
    Result.Scale3D.Z = Read<float>(address + 40);;   // Scale_z
    return Result;
}
Vec3 D3dMatrixMultiply(D3DXMATRIX bonematrix, D3DXMATRIX actormatrix)
{
    Vec3 result;
    result.X =
        bonematrix._41 * actormatrix._11 + bonematrix._42 * actormatrix._21 +
        bonematrix._43 * actormatrix._31 + bonematrix._44 * actormatrix._41;
    result.Y =
        bonematrix._41 * actormatrix._12 + bonematrix._42 * actormatrix._22 +
        bonematrix._43 * actormatrix._32 + bonematrix._44 * actormatrix._42;
    result.Z =
        bonematrix._41 * actormatrix._13 + bonematrix._42 * actormatrix._23 +
        bonematrix._43 * actormatrix._33 + bonematrix._44 * actormatrix._43;
    return result;
}
Vec3 getBoneXYZ(kaddr humanAddr, kaddr boneAddr, int Part)
{
    FTransform1 Bone = ReadFTransform(boneAddr + Part * 48);
    FTransform1 Actor = ReadFTransform(humanAddr);
    D3DXMATRIX Bone_Matrix = ToMatrixWithScale(Bone.Rotation, Bone.Translation, Bone.Scale3D);
    D3DXMATRIX Component_ToWorld_Matrix =ToMatrixWithScale(Actor.Rotation, Actor.Translation, Actor.Scale3D);
    Vec3 result = D3dMatrixMultiply(Bone_Matrix, Component_ToWorld_Matrix);
    return result;
}

char* getNameByte(kaddr address)
{
    char static lj[64];
    memset(lj, 0, 64);
    unsigned short int nameI[32];
    pvm(address, nameI, sizeof(nameI));
    char s[10];
    int i;
    for (i = 0; i < 32; i++)
    {
        if (nameI[i] == 0)
            break;
        sprintf(s, "%d:", nameI[i]);
        strcat(lj, s);
    }
    lj[63] = '\0';

    return lj;
}
PlayerBone getPlayerBone(uintptr_t pBase, struct D3DMatrix viewMatrix)
{
    PlayerBone b;
    b.isBone = true;
    struct D3DMatrix oMatrix;
    kaddr boneAddr = getPtr(pBase + 0x308);
    struct D3DMatrix baseMatrix = getOMatrix(boneAddr + 0x140);
    int bones[] = { 6, 5, 2,12, 33, 13, 34, 64, 63, 53, 57, 54, 58, 55, 59 };
    boneAddr = getPtr(boneAddr + 0x590) + 0x30;
    oMatrix = getOMatrix(boneAddr + (bones[0]) * 48);
    b.neck = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //cheast 1
    oMatrix = getOMatrix(boneAddr + (bones[1]) * 48);
    b.cheast = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //pelvis 2
    oMatrix = getOMatrix(boneAddr + (bones[2]) * 48);
    b.pelvis = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lSh 3
    oMatrix = getOMatrix(boneAddr + (bones[3]) * 48);
    b.lSh = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rSh 4
    oMatrix = getOMatrix(boneAddr + (bones[4]) * 48);
    b.rSh = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lElb 5
    oMatrix = getOMatrix(boneAddr + (bones[5]) * 48);
    b.lElb = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rElb 6
    oMatrix = getOMatrix(boneAddr + (bones[6]) * 48);
    b.rElb = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lWr 7
    oMatrix = getOMatrix(boneAddr + (bones[7]) * 48);
    b.lWr = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rWr 8
    oMatrix = getOMatrix(boneAddr + (bones[8]) * 48);
    b.rWr = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lTh 9
    oMatrix = getOMatrix(boneAddr + (bones[9]) * 48);
    b.lTh = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rTh 10
    oMatrix = getOMatrix(boneAddr + (bones[10]) * 48);
    b.rTh = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lKn 11
    oMatrix = getOMatrix(boneAddr + (bones[11]) * 48);
    b.lKn = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rKn 12
    oMatrix = getOMatrix(boneAddr + (bones[12]) * 48);
    b.rKn = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lAn 13 
    oMatrix = getOMatrix(boneAddr + (bones[13]) * 48);
    b.lAn = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rAn 14
    oMatrix = getOMatrix(boneAddr + (bones[14]) * 48);
    b.rAn = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));

    return b;
}
void getplayername(int addr,char * bbb) {
    kaddr temp;
    int classname;
    char name[32];
    int m = 0;
    int namepy = getPtr(addr + 0x5C8);
    int i = 0;
    for (i; i < 8; i++)
    {
        classname = getPtr(namepy + i * 4);
        memcpy(&bbb[m],&classname,4);
        m +=4;
    }
}
void getname(int id,char * bbb,kaddr Gname)
{
    kaddr temp;
    int classname;
    char name[32];
    int m = 0;
    if ( id  >  0 && id  <  2000000)
    {
        int ye = id / 16384;
        int xu = id % 16384;
        int ddz = getPtr(Gname + ye * 4);
        int namedz = getPtr(ddz + xu * 4);
        int i = 0;
        for (i; i < 8; i++)
        {
            classname = getPtr(namedz + 8 + i * 4);
            memcpy(&bbb[m],&classname,4);
            m +=4;
        }
    }
}
char* getText(kaddr addr) {
    static char txt[42];
    memset(txt, 0, 42);
    char buff[41];
    pvm(addr + 4+SIZE, &buff, 41);
    int i;
    for (i = 0; i < 41; i++) {
        if (buff[i] == 0)
            break;

        txt[i] = buff[i];

        if (buff[i] == 67 && i > 10)
            break;

    }
    txt[i + 1] = '\0';
    return txt;
}

kaddr getbsaddr(int pid){
    FILE *fp;
    uintptr_t addr = 0;
    char filename[40], buffer[1024];
    snprintf(filename, sizeof(filename), "/proc/%d/maps", pid);
    fp = fopen(filename, "rt");
    if (fp != NULL) {
        while (fgets(buffer, sizeof(buffer), fp)) {
            if (strstr(buffer, "libUE4.so")) {
                addr = (uintptr_t)strtoul(buffer, NULL, 16);
                break;
            }
        }
        fclose(fp);
    }
    return addr;
}
void DrawESP(ESP esp, int screenWidth, int screenHeight) {
    botCount=0;playerCount=0;
   target_pid = find_pid("com.pubg.krmobile");
if (target_pid == 0){return;}
libbase = getbsaddr(target_pid);
vworld = getPtr(libbase + 0x7111E80);
CM = getPtr(vworld + 32);
JZDZ = CM + 512;
struct Vec3 exyz;
struct D3DMatrix vMat;
char loaded[1000], loadedpn[20];
                int type = 69;
float textsize=screenHeight/50;
height = screenHeight; width = screenWidth;
response.Success = false;
        response.PlayerCount = 0;
        response.VehicleCount = 0;
        response.ItemsCount = 0;
        response.GrenadeCount = 0;
           pvm(JZDZ, &vMat, sizeof(vMat));
           kaddr uWorlds = getPtr(libbase +0x6CB087C);
           kaddr uLevel = getPtr(uWorlds + 32);
           kaddr gameInstance = getPtr(uWorlds + 36);
           kaddr playerController = getPtr(gameInstance + 96);
           kaddr playerCarry = getPtr(playerController + 0x20);
           kaddr uMyObject = getPtr(playerCarry + 792);
           kaddr entityEntry = getPtr(uLevel + 112);
           kaddr entityCount = getPtr(uLevel + 116);
           if (gameInstance == 0)
                return;
            if (entityCount < 0) {
                entityCount = 0;
            }
            else if (entityCount > 1024) {
                entityCount = 1024;
            }
     for (int i = 0; i < entityCount; i++) {
       pBase = getPtr(entityEntry + i * 4);
       kaddr ID = getPtr(pBase + 16);
       kaddr gname_buff[30];
       kaddr gname = getPtr(libbase + 0x6EADC60);
       pvm(gname, &gname_buff, sizeof(gname_buff));
       char name[100];
            int ids = Read<int>(pBase + 8 + 2 * SIZE);
            int page = ids / 0x4000;
            int index = ids % 0x4000;
            if (page < 1 || page>30)
                continue;
            if (gname_buff[page] == 0)
                gname_buff[page] = getPtr(gname + page * 4);
            strcpy(name, getText(getPtr(gname_buff[page] + index * 4)));
            if (strlen(name) < 5)
                continue;
          if (strstr(name, "BP_AirDropPlane_C") || strstr(name, "PlayerDeadInventoryBox_C") || strstr(name, "BP_AirDropBox_C")) {//Items
               char ItemName[50];
                pvm(getPtr(pBase + 0x138) + 0x150, &exyz, sizeof(exyz));
                Location = World2Screen(vMat, exyz);
                if (Location.Z == 1.0f || Location.X > width + 100 || Location.X < -50)
                    continue;
                float screenW = (vMat._14 * exyz.X) + (vMat._24 * exyz.Y) + (vMat._34 * exyz.Z + vMat._44);
                Distance= (screenW / 100);
                strcpy(ItemName, name);
                if(isItems)
                if(Location.Z!=1.0f) {
                   esp.DrawItems(ItemName,Distance,Vec22(Location.X,Location.Y),10.0f);
               }}
         if (strstr(name, "Pickup_C") || strstr(name, "PickUp") || strstr(name, "BP_Ammo") || strstr(name, "BP_QK") || strstr(name, "Wrapper")) {//Items
               char ItemName[50];
               pvm(getPtr(pBase + 0x138) + 0x150, &exyz, sizeof(exyz));
                Location = World2Screen(vMat, exyz);
                if (Location.Z == 1.0f || Location.X > width + 100 || Location.X < -50)
                    continue;
                float screenW = (vMat._14 * exyz.X) + (vMat._24 * exyz.Y) + (vMat._34 * exyz.Z + vMat._44);
                Distance= (screenW / 100);
                if (Distance > 200)
                        continue;
                strcpy(ItemName, name);
                if(isItems2)
                 if(Location.Z!=1.0f) {
                   esp.DrawItems(ItemName,Distance,Vec22(Location.X,Location.Y),10.0f);
               }}
             if (strstr(name, "VH") || (strstr(name, "PickUp_") && !strstr(name, "BP")) || strstr(name, "Rony") || strstr(name, "Mirado") || strstr(name, "LadaNiva") || strstr(name, "AquaRail")) {//Vehicle
                char VehicleName[50];
                pvm(getPtr(pBase + 0x138) + 0x150, &exyz, sizeof(exyz));
                Location = World2Screen(vMat, exyz);
                if (Location.Z == 1.0f || Location.X > width + 200 || Location.X < -200)
                    continue;
                float screenW = (vMat._14 * exyz.X) + (vMat._24 * exyz.Y) + (vMat._34 * exyz.Z + vMat._44);
                Distance= (screenW / 100);
                strcpy(VehicleName, name);
                if(isVehicles)
             if(Location.Z!=1.0f) {
                esp.DrawVehicles(VehicleName,Distance,Vec22(Location.X,Location.Y),textsize);
                       }}
          if (strstr(name, "BP_Grenade_Shoulei_C") || strstr(name, "BP_Grenade_Burn_C")) {//Grenade Warning
                pvm(getPtr(pBase + 0x138) + 0x150, &exyz, sizeof(exyz));
                Location = World2Screen(vMat, exyz);
                float screenW = (vMat._14 * exyz.X) + (vMat._24 * exyz.Y) + (vMat._34 * exyz.Z + vMat._44);
                Distance= (screenW / 100);
                if (Distance > 150)
                        continue;
                if (strstr(name, "Shoulei"))
                    type = 1;
               else
                    type = 2;
              if(!isGrenadeWarning)
                    continue;
            esp.DrawText(Color(255, 200, 200),"Warning Granat",Vec22(screenWidth/2,screenHeight/8),textsize);
            if(Location.Z!=1.0f){
                if(type==1 )
                    esp.DrawText(Color(255,0,0),"Awas Granat Cok",Vec22(Location.X,Location.Y),textsize);
                else
                    esp.DrawText(Color(255, 158, 89),"Molotov",Vec22(Location.X,Location.Y),textsize);
            }}
        if (strstr(name, "BP_PlayerPawn")){
                pvm(pBase + 0x880, healthbuff, sizeof(healthbuff));
                if (healthbuff[1] > 200.0f || healthbuff[1] < 50.0f || healthbuff[0]>healthbuff[1] || healthbuff[0] < 0.0f)
                    continue;              
              Health = healthbuff[0] / healthbuff[1] * 100;     
              int TeamID = Read<int>(pBase + 0x620);
                    if (pBase == uMyObject) {
                        myTeamID = TeamID;
                        myplayeradd = pBase;
                        continue; }
                    if (TeamID == myTeamID) {
                        myplayeradd = pBase;
                        continue; }
                pvm(getPtr(pBase + 0x2650) + 0xfc, &exyz, sizeof(exyz));
                HeadLocation = World2Screen(vMat, exyz);
                float screenW = (vMat._14 * exyz.X) + (vMat._24 * exyz.Y) + (vMat._34 * exyz.Z + vMat._44);
                Distance= (screenW / 100); 
                if (Distance > 600)
                     continue;
                pvm(pBase + 0x694, &isBot, sizeof(isBot));
                strcpy(PlayerNameByte, "66:111:116:");
                  strcpy(PlayerNameByte, getNameByte(getPtr(pBase + 0x5f8)));
                        if (strlen(PlayerNameByte) < 4)
                            continue;
            PlayerWeapon p;
            p.id = Read<int>(getPtr(getPtr(getPtr(pBase + 0x12C) + 0x0) + 0x4E4) + 0xCC);
            p.ammo = Read<int>(getPtr(getPtr(pBase + 0x12C) + 0x0) + 0x8C8);
            p.ammo2 = Read<int>(getPtr(getPtr(pBase + 0x12C) + 0x0) + 0x8DC);
            kaddr viewingCoordinates =getPtr(getPtr(getPtr(getPtr(uWorlds + 36) + 96) + 32) +784);                
            float viewx = Read<float>(viewingCoordinates + 0x4c);
            float viewy = Read<float>(viewingCoordinates + 0x4c +4);
            float viewz = Read<float>(viewingCoordinates + 0x4c +8 );
            kaddr qunViewAngle_x =getPtr(getPtr (getPtr(getPtr(uWorlds + 36) + 96) + 32) + 808) + 0x358;           
            kaddr zmAddr = getPtr(uMyObject + 740); 
           kaddr zmAddrx = zmAddr + 740; 
           kaddr zmAddry = zmAddr + 736; 
            float angle = Read<float>(getPtr(getPtr(uLevel + 124) + 36) + 0x13b8) - 90;
            float Self_x = Read<float>(uMyObject + 0x90);
            float Self_y = Read<float>(uMyObject + 0x90 + 4);
            float Self_z = Read<float>(uMyObject + 0x90 + 8);               
            kaddr objectCoordinatePointer = getPtr(pBase + 0x138);
            float object_x = Read<float>(objectCoordinatePointer + 0xFC);
            float object_y = Read<float>(objectCoordinatePointer + 0xFC + 4);
            float object_z = Read<float>(objectCoordinatePointer + 0xFC + 8);
            int state = Read<int>(pBase + 0x9b4);
            Vec2 angleJudgement =  rotateCoord(angle, (Self_x - object_x) / 200, (Self_y - object_y) / 200);
            float wzzb = Read<float>(pBase + 0x16ac);
            if (wzzb != 479.5)
            {
                continue;
            }
             x = HeadLocation.X; y = HeadLocation.Y;
          float magic_number = (Distance);
                float mx = (screenWidth / 4) / magic_number;
                float my = (screenWidth / 1.38) / magic_number;
                float top = y - my + (screenWidth / 1.7) / magic_number;
                float bottom = y + my + screenHeight / 4 / magic_number;
                long int object = getPtr(pBase + 0x138);
            float d_x = Read<float>(object + 0xFC);
            float d_y = Read<float>(object + 0x100);
            float d_z = Read<float>(object + 0x104);
    float matrix[50];
    for (int i = 0; i < 16; i++)
        {
            float matrixaddr = Read<float>(JZDZ + i * 4);
            matrix[i] = matrixaddr;
        }

            float camear_r = matrix[3] * d_x + matrix[7] * d_y + matrix[11] * d_z + matrix[15];
            float r_x =
                screenWidth + (matrix[0] * d_x + matrix[4] * d_y + matrix[8] * d_z +
                      matrix[12]) / camear_r * screenWidth;
            float r_y =
                screenHeight - (matrix[1] * d_x + matrix[5] * d_y + matrix[9] * (d_z - 5) +
                      matrix[13]) / camear_r * screenHeight;
            float r_w =
                screenHeight - (matrix[1] * d_x + matrix[5] * d_y + matrix[9] * (d_z + 205) +
                      matrix[13]) / camear_r * screenHeight;
                  float x2=r_x - (r_y - r_w) / 4;
                  float y2=r_y;
                   float w=(r_y - r_w) / 2;    // 3.w
                    float h=r_y - r_w;// 4.h
                   float w_2=y2-w;
          if (y2>screenHeight-w-20 && y2<screenHeight+w+20 && x2>screenWidth-w_2-20 && x2<screenWidth+w_2+20)
          {
                clr.r = 0;
                    clr.g = 232;
                    clr.b = 0;
                    clr.a = 255;
               } else{
            if (isBot) {
                    clr.r = 30;
                    clr.g = 232;
                    clr.b = 222;
                    clr.a = 255;
                } else {
                    clr.r = 255;
                    clr.g = 0;
                    clr.b = 0;
                    clr.a = 255;
                }}
                if (isBot) {
                    botCount++;
                } else {
                    playerCount++;
                }
                  if (HeadLocation.Z != 1) {
              if (x > -50 && x < screenWidth + 50) {//onScreen
                    if (isSkelton1) {  //Skelton
                            Bone = getPlayerBone(pBase, vMat);
                            esp.DrawLine(Color().Y(), 1, Vec22(x, y),Vec22(Bone.neck.X,Bone.neck.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.neck.X,Bone.neck.Y),Vec22(Bone.cheast.X,Bone.cheast.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.cheast.X,Bone.cheast.Y),Vec22(Bone.pelvis.X,Bone.pelvis.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.neck.X,Bone.neck.Y),Vec22(Bone.lSh.X,Bone.lSh.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.neck.X,Bone.neck.Y),Vec22(Bone.rSh.X,Bone.rSh.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.lSh.X,Bone.lSh.Y),Vec22(Bone.lElb.X,Bone.lElb.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.rSh.X,Bone.rSh.Y),Vec22(Bone.rElb.X,Bone.rElb.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.lElb.X,Bone.lElb.Y),Vec22(Bone.lWr.X,Bone.lWr.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.rElb.X,Bone.rElb.Y),Vec22(Bone.rWr.X,Bone.rWr.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.pelvis.X,Bone.pelvis.Y),Vec22(Bone.lTh.X,Bone.lTh.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.pelvis.X,Bone.pelvis.Y),Vec22(Bone.rTh.X,Bone.rTh.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.lTh.X,Bone.lTh.Y),Vec22(Bone.lKn.X,Bone.lKn.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.rTh.X,Bone.rTh.Y),Vec22(Bone.rKn.X,Bone.rKn.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.lKn.X,Bone.lKn.Y),Vec22(Bone.lAn.X,Bone.lAn.Y));
                            esp.DrawLine(Color().Y(), 1, Vec22(Bone.rKn.X,Bone.rKn.Y),Vec22(Bone.rAn.X,Bone.rAn.Y));
                        }
                    //Box
                    if (isPlayerBox)
                            esp.DrawRect(clr, screenHeight / 500, Vec22(x - mx, top),
                                         Vec22(x + mx, bottom));
                    //Health
                    if (isPlayerHealth) {
                        float healthLength = screenWidth / 60;
                        if (healthLength < mx)
                            healthLength = mx;
                        if (Health < 25)
                            clrHealth = Color(255, 0, 0);
                        else if (Health < 50)
                            clrHealth = Color(255, 165, 0);
                        else if (Health < 75)
                            clrHealth = Color(255, 255, 0);
                        else
                            clrHealth = Color(0, 255, 0);
                        if (Health == 0)
                            esp.DrawText(Color(255, 0, 0), "وقع نوك",Vec22(x, top - screenHeight / 225), textsize);
                        else {
                            esp.DrawFilledRect3(clrHealth,
                                                   Vec22(x - healthLength, top - screenHeight / 34),
                                                   Vec22(x - healthLength +
                                                        (2 * healthLength) *
                                                        Health / 100, top - screenHeight / 225));
                                esp.DrawRect(Color(0, 0, 0), screenHeight / 660,
                                             Vec22(x - healthLength, top - screenHeight / 34),
                                             Vec22(x + healthLength, top - screenHeight / 225));
                        }
                    }
                    if (isEnemyWeapon)
                        if (true) {
                            esp.DrawWeapon(Color(0, 0, 190), p.id,p.ammo,p.ammo2,
                                Vec22(x, top - 62), textsize);
                        }
                    //Head
                    if (isPlayerHead)
                        esp.DrawHead(Color(255, 0, 0, 255),
                                     Vec22(HeadLocation.X,HeadLocation.Y),screenHeight / 12 / magic_number);

                    //Name and distance
                    if (isPlayerName)
                        if (true) {
                            esp.DrawName(Color().White(), PlayerNameByte,TeamID,
                                Vec22(x, top - 23), textsize);}
                    if (isPlayerDist) {
                        sprintf(extra, "%0.0fm", Distance);
                        esp.DrawTextDistance(Color(0, 0, 255), extra,
                            Vec22(x + 140, top - 23),textsize);
                    }}}
            //360 Alert
            if (HeadLocation.Z == 1.0f) {
                if(!isr360Alert)
                    continue;
                if (y > screenHeight - screenHeight / 12)
                    y = screenHeight - screenHeight / 12;
                else if (y < screenHeight / 12)
                    y = screenHeight / 12;
                if (x < screenWidth / 2) {
                    esp.DrawFilledCircle(Color(255, 0, 0, 80),
                        Vec22(screenWidth, y),screenHeight / 18);
                    sprintf(extra, "%0.0f m", Distance);
                    esp.DrawTextAltert(Color(180, 250, 181, 200), extra,
                        Vec22(screenWidth - screenWidth / 80, y), textsize);
                } else {
                    esp.DrawFilledCircle(Color(255, 0, 0, 80),
                        Vec22(0, y),screenHeight / 18);
                    sprintf(extra, "%0.0f m", Distance);
                    esp.DrawTextAltert(Color(180, 250, 181, 200), extra,
                        Vec22(screenWidth / 80, y), textsize);
                }}
            else if (x < -screenWidth / 10 || x > screenWidth + screenWidth / 10) {
                if(!isr360Alert)
                    continue;
                if (y > screenHeight - screenHeight / 12)
                    y = screenHeight - screenHeight / 12;
                else if (y < screenHeight / 12)
                    y = screenHeight / 12;
                if (x > screenWidth / 2) {
                    esp.DrawFilledCircle(Color(255, 0, 0, 80),
                        Vec22(screenWidth, y),screenHeight / 18);
                    sprintf(extra, "%0.0f m", Distance);
                    esp.DrawTextAltert(Color(180, 250, 181, 200), extra,
                        Vec22(screenWidth - screenWidth / 80, y), textsize);
                } else {
                    esp.DrawFilledCircle(Color(255, 0, 0, 80),
                        Vec22(0, y),screenHeight / 18);
                    sprintf(extra, "%0.0f m", Distance);
                    esp.DrawTextAltert(Color(180, 250, 181, 200), extra,
                        Vec22(screenWidth / 80, y), textsize);
                }
            }
            else if(isPlayerLine)
                    esp.DrawLine(clr, screenHeight / 500,
                        Vec22(screenWidth / 2, screenHeight / 10.5 + 8), Vec22(x, top));
                }}
           if(isPlayerename)
          if (botCount + playerCount > 0)
       {
                esp.DrawFilledRect(Color(255, 169, 0, 170),
                                   Vec22(screenWidth / 2 - screenHeight / 15, screenHeight / 18),
                                   Vec22(screenWidth / 2 + screenHeight / 15, 90));
     sprintf(extra, "%d", playerCount);
         esp.DrawText(Color(255, 0, 0),extra,
                             Vec22(screenWidth / 2 - screenHeight / 25, 80),
                             30);
                sprintf(extra, "%d", botCount);
                esp.DrawText(Color(30, 232, 222), extra,
                             Vec22(screenWidth / 2 + screenHeight / 40, 80),
                             30);
                esp.DrawLine(Color(0, 0, 0), 3, Vec22(screenWidth / 2, screenHeight / 18),
                             Vec22(screenWidth / 2, 90));

           }
     else
     {esp.DrawFilledRect(Color(0, 255, 0, 170),
                                   Vec22(screenWidth / 2 - screenHeight / 15, screenHeight / 18),
                                   Vec22(screenWidth / 2 + screenHeight / 15, 90));
   
         esp.DrawText(Color(0,0,0,255),"Rambk Madarchod", Vec22(
                                screenWidth/2, 80), 30);
   

     }              }

#endif //KINGCHAET

