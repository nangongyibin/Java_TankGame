package com.ngyb.tankgame.model;

import com.ngyb.tankgame.base.Element;
import com.ngyb.tankgame.game.Config;
import com.ngyb.tankgame.game.Direction;
import com.ngyb.tankgame.utils.CollsionUtils;
import com.ngyb.tankgame.utils.DrawUtils;
import com.sun.corba.se.spi.ior.iiop.IIOPFactories;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/18 15:29
 */
public class Tank extends Element {
    public Direction dt = Direction.TOP;
    public Direction fdt;
    public int rate = Config.t_rate;
    public long lastTime;
    public int gap;
    public Direction bdt;

    public Tank(int x, int y) {
        super(x, y);
        int[] arr = DrawUtils.getSize("res/img/tank_u.gif");
        w = arr[0];
        h = arr[1];
    }

    public void Move(Direction dt) {
        if (bdt != null && bdt == dt) {
            switch (dt) {
                case TOP:
                    y -= gap;
                    break;
                case BOTTOM:
                    y += gap;
                    break;
                case LEFT:
                    x -= gap;
                    break;
                case RIGHT:
                    x += gap;
                    break;
            }
            bdt = null;
            gap = 0;
            return;
        }
        if (this.dt != dt) {
            this.dt = dt;
            return;
        }
        switch (dt) {
            case TOP:
                y -= rate;
                break;
            case BOTTOM:
                y += rate;
                break;
            case LEFT:
                x -= rate;
                break;
            case RIGHT:
                x += rate;
                break;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > Config.height - 64) {
            y = Config.height - 64;
        }
        if (x < 0) {
            x = 0;
        }
        if (x > Config.width - 64) {
            x = Config.width - 64;
        }
    }

    @Override
    public void onDraw() {
        String res = "";
        switch (dt) {
            case TOP:
                res = "res/img/tank_u.gif";
                break;
            case BOTTOM:
                res = "res/img/tank_d.gif";
                break;
            case LEFT:
                res = "res/img/tank_l.gif";
                break;
            case RIGHT:
                res = "res/img/tank_r.gif";
                break;
            default:
                break;
        }
        DrawUtils.draw(res, x, y);
    }

    public Bullet firing() {
        this.fdt = dt;
        long time = System.currentTimeMillis();
        if (time - lastTime > 400) {
            lastTime = time;
            Bullet zd = new Bullet(this);
            return zd;
        }
        return null;
    }

    public boolean isBump(SteelPlate gb) {
        int x1 = gb.getX();
        int y1 = gb.getY();
        int h1 = gb.getH();
        int w1 = gb.getW();
        int x2 = getX();
        int y2 = getY();
        int h2 = getH();
        int w2 = getW();
        switch (dt) {
            case TOP:
                y2 -= rate;
                break;
            case BOTTOM:
                y2 += rate;
                break;
            case LEFT:
                x2 -= rate;
                break;
            case RIGHT:
                x2 += rate;
                break;
        }
        boolean b = CollsionUtils.isCollsionWidthRect(x1, y1, w1, h1, x2, y2, w2, h2);
        if (b) {
            bdt = dt;
            int x3 = getX();
            int y3 = getY();
            int w3 = getW();
            int h3 = getH();
            switch (dt) {
                case TOP:
                    gap = y3 - y1 - h1;
                    break;
                case BOTTOM:
                    gap = y1 - y3 - h3;
                    break;
                case LEFT:
                    gap = x3 - x1 - w1;
                    break;
                case RIGHT:
                    gap = x1 - x3 - w3;
                    break;
            }
        }
        return b;
    }
}
