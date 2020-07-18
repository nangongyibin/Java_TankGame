package com.ngyb.tankgame.tank;

import com.ngyb.tankgame.game.Config;
import com.ngyb.tankgame.game.TankWindow;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 作者：南宫燚滨
 * 描述：程序入口 绝对不要用System.out.println();
 * -Dfile.encoding=utf-8 -Djava.library.path=libs/natives/windows
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/18 14:50
 */
public class Game {

    public static void main(String[] args){
        TankWindow tw = new TankWindow(
                Config.title,
                Config.width,
                Config.height,
                Config.fps
        );
        tw.start();
    }
}
