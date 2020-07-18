package com.ngyb.tankgame.utils;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/18 16:45
 */
public class SoundUtils {
    private static Map<String, Audio> map = new LinkedHashMap<>();

    private SoundUtils() {

    }

    /**
     * 播放音乐，只播放一次
     *
     * @param res 音乐文件路径
     */
    public static void play(String res) {
        play(res, false);
    }

    /**
     * 播放音乐
     *
     * @param res    音乐文件路径
     * @param repeat true一直循环播放音乐，false只播放一次
     */
    public static void play(String res, boolean repeat) {
        String key = getKey(res);
        Audio audio = map.get(key);
        if (audio == null) {
            String format = getFormat(res);
            try {
                audio = AudioLoader.getAudio(format, ResourceLoader.getResourceAsStream(res));
            } catch (IOException e) {
                //资源无法找到时的异常
                e.printStackTrace();
            }
            map.put(key, audio);
        }
        audio.playAsSoundEffect(1.0f, 1.0f, repeat);
    }

    /**
     * 停止播放音乐
     *
     * @param res 音乐文件路径
     */
    public static void stop(String res) {
        String key = getKey(res);
        Audio audio = map.get(key);
        if (audio == null) {
            return;
        }
        if (audio.isPlaying()) {
            audio.stop();
        }
    }

    private static String getKey(String res) {
        return res;
    }

    private static String getFormat(String res) {
        if (res == null) {
            return null;
        }
        int index = res.lastIndexOf(".");
        if (index == -1) {
            return null;
        }
        return res.substring(index + 1).toUpperCase();
    }
}
