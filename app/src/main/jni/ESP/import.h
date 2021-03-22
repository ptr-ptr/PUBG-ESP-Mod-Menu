#ifndef DESI_ESP_IMPORTANT_IMPORT_H
#define DESI_ESP_IMPORTANT_IMPORT_H

#include <jni.h>
#include <string>
#include <cstdlib>
#include <unistd.h>
#include <sys/mman.h>
#include <android/log.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <cerrno>
#include <sys/un.h>
#include <cstring>
#include <string>
#include <cmath>
#include "struct.h"




bool isESP = false;
bool isPlayerBox = false;
bool isPlayerLine = false;
bool isPlayerDist = false;
bool isPlayerHealth = false;
bool isPlayerName = false;
bool isPlayerHead = false;
bool isr360Alert = false;
bool isSkelton1 = false;

bool isGrenadeWarning = false;
bool isEnemyWeapon= false;

bool isItems = false;
bool isItems2 = false;
bool isVehicles = false;
bool isPlayerename = false;


#endif //DESI_ESP_IMPORTANT_IMPORT_H
