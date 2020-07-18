package com.ngyb.tankgame.model;

import com.ngyb.tankgame.base.Element;
import com.ngyb.tankgame.utils.DrawUtils;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/18 16:14
 */
public class BrickWall extends Element {
    public BrickWall(int x, int y) {
        super(x, y);
    }

    @Override
    public void onDraw() {
        DrawUtils.draw("res/img/wall.gif", x, y);
    }
}
