#ifndef DESI_ESP_IMPORTANT_STRUCT_H
#define DESI_ESP_IMPORTANT_STRUCT_H

#include <string>
#define maxplayerCount 100
#define maxvehicleCount 50
#define maxitemsCount 400
#define maxgrenadeCount 10


class Color {
public:
    float r;
    float g;
    float b;
    float a;

    Color() {
        this->r = 0;
        this->g = 0;
        this->b = 0;
        this->a = 0;
    }

    Color(float r, float g, float b, float a) {
        this->r = r;
        this->g = g;
        this->b = b;
        this->a = a;
    }

    Color(float r, float g, float b) {
        this->r = r;
        this->g = g;
        this->b = b;
        this->a = 255;
    }

    static Color White(){
        return Color(255,255,255);
    }

    static Color Green(){
        return Color(0,255,0);
    }
    static Color Y(){
        return Color(255,255,0);
    }
};

class Rect {
public:
    float x;
    float y;
    float width;
    float height;

    Rect() {
        this->x = 0;
        this->y = 0;
        this->width = 0;
        this->height = 0;
    }

    Rect(float x, float y, float width, float height) {
        this->x = x;
        this->y = y;
        this->width = width;
        this->height = height;
    }

    bool operator==(const Rect &src) const {
        return (src.x == this->x && src.y == this->y && src.height == this->height &&
                src.width == this->width);
    }

    bool operator!=(const Rect &src) const {
        return (src.x != this->x && src.y != this->y && src.height != this->height &&
                src.width != this->width);
    }
};

struct Vec33 {
    float x, y, z;
};
class Vec22 {
        public:
        float x;
        float y;

        Vec22() {
            this->x = 0;
            this->y = 0;
        }

        Vec22(float x, float y) {
            this->x = x;
            this->y = y;
        }

        static Vec22 Zero() {
            return Vec22(0.0f, 0.0f);
        }

        bool operator!=(const Vec22 &src) const {
            return (src.x != x) || (src.y != y);
        }

        Vec22 &operator+=(const Vec22 &v) {
            x += v.x;
            y += v.y;
            return *this;
        }

        Vec22 &operator-=(const Vec22 &v) {
            x -= v.x;
            y -= v.y;
            return *this;
        }
};


#endif //DESI_ESP_IMPORTANT_STRUCT_H
