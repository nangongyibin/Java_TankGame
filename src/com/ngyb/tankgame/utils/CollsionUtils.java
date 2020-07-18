package com.ngyb.tankgame.utils;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/18 16:26
 */
public class CollsionUtils {
    public CollsionUtils() {
    }

    /**
     * 判断两个矩形是否碰撞
     *
     * @param x1 第一个矩形的x坐标
     * @param y1 第一个矩形的y坐标
     * @param w1 第一个矩形的宽度
     * @param h1 第一个矩形的高度
     * @param x2 第二个矩形的x坐标
     * @param y2 第二个矩形的y坐标
     * @param w2 第二个矩形的宽度
     * @param h2 第二个矩形的高度
     * @return
     */
    public static boolean isCollsionWidthRect(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        if (x1 >= x2 && x1 >= x2 + w2) {
            return false;
        } else if (x1 <= x2 && x1 + w1 <= x2) {
            return false;
        } else if (y1 >= y2 && y1 >= y2 + h2) {
            return false;
        } else if (y1 <= y2 && y1 + h1 <= y2) {
            return false;
        }
        return true;
    }
}
