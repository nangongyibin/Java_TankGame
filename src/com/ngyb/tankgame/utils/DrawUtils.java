package com.ngyb.tankgame.utils;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：南宫燚滨
 * 描述：显示图片工具类
 * draw绘制的意思
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/18 15:47
 */
public class DrawUtils {
    private static Map<String, Texture> map = new LinkedHashMap<>();

    private DrawUtils() {
    }

    /**
     * 获得图片的大小
     *
     * @param imagePath 图片路径
     * @return 返回长度为2的数组，0为宽，1为高
     */
    public static int[] getSize(String imagePath) {
        String key = getKey(imagePath);
        Texture texture = map.get(key);
        if (texture == null) {
            String format = getFormat(imagePath);
            try {
                texture = TextureLoader.getTexture(format, new FileInputStream(new File(imagePath)));
            } catch (IOException e) {
                //图片不存在时的异常
                e.printStackTrace();
            }
            map.put(key, texture);
        }
        int width = (int) (texture.getImageWidth() + 0.5f);
        int height = (int) (texture.getImageHeight() + 0.5f);
        return new int[]{width, height};
    }

    public static void draw(String imagePath, int x, int y) {
        String key = getKey(imagePath);
        Texture texture = map.get(key);
        if (texture == null) {
            String format = getFormat(imagePath);
            try {
                texture = TextureLoader.getTexture(format, new FileInputStream(new File(imagePath)));
            } catch (IOException e) {
                //图片不存在时的异常
                e.printStackTrace();
            }
            map.put(key, texture);
        }
        int width = texture.getImageWidth();
        int height = texture.getImageHeight();
        width = texture.getTextureWidth();
        height = texture.getTextureHeight();
        Color.white.bind();
        texture.bind();// or GL11.glBind(texture.getTextureID());
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(0f, 0f);
            GL11.glVertex2f(x, y);
            GL11.glTexCoord2f(1f, 0f);
            GL11.glVertex2f(x + width, y);
            GL11.glTexCoord2f(1f, 1f);
            GL11.glVertex2f(x + width, y + height);
            GL11.glTexCoord2f(0f, 1f);
            GL11.glVertex2f(x, y + height);
        }
        GL11.glEnd();
    }

    private static String getKey(String imagePath) {
        return imagePath;
    }

    private static String getFormat(String imagePath) {
        if (imagePath == null) {
            return null;
        }
        int index = imagePath.lastIndexOf(".");
        if (index == -1) {
            return null;
        }
        return imagePath.substring(index + 1);
    }
}
