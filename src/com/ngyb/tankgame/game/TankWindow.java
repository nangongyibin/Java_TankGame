package com.ngyb.tankgame.game;

import com.ngyb.tankgame.base.ElementInterface;
import com.ngyb.tankgame.model.Tank;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/18 14:56
 */
public class TankWindow extends Window{
    private List<ElementInterface> lists = new CopyOnWriteArrayList<>();
    private Tank tank;

    public TankWindow(String title, int width, int height, int fps) {
        super(title, width, height, fps);
    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected void onMouseEvent(int key, int x, int y) {

    }

    @Override
    protected void onKeyEvent(int key) {

    }

    @Override
    protected void onDisplayUpdate() {

    }
}
