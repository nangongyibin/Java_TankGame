package com.ngyb.tankgame.model;

import com.ngyb.tankgame.base.ElementInterface;
import com.ngyb.tankgame.game.Config;
import com.ngyb.tankgame.game.Direction;
import com.ngyb.tankgame.utils.DrawUtils;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/18 16:15
 */
public class Bullet implements ElementInterface {
    protected int x;
    protected int y;
    private Direction dt;
    private Tank k;

    public Bullet(Tank k) {
        this.k = k;
        this.dt = k.fdt;
        this.x = k.getX();
        this.y = k.getY();
        switch (dt) {
            case TOP:
                x = x + k.getW() / 2 - 8;
                y -= 32;
                break;
            case BOTTOM:
                x = x + k.getW() / 2 - 8;
                y += 64;
                break;
            case LEFT:
                x -= 16;
                y = y + k.getH() / 2 - 8;
                break;
            case RIGHT:
                x += 64;
                y = y + k.getH() / 2 - 8;
                break;
        }
    }

    @Override
    public void onDraw() {
        String res = "";
        switch (dt) {
            case TOP:
                res = "res/img/bullet_u.gif";
                y -= Config.z_rate;
                break;
            case BOTTOM:
                res = "res/img/bullet_d.gif";
                y += Config.z_rate;
                break;
            case LEFT:
                res = "res/img/bullet_l.gif";
                x -= Config.z_rate;
                break;
            case RIGHT:
                res = "res/img/bullet_r.gif";
                x += Config.z_rate;
                break;
            default:
                break;
        }
        DrawUtils.draw(res, x, y);
    }

    public boolean isTransgression() {
        if (y < 0 || y > Config.height - 32 || x < 0 || x > Config.width - 32) {
            return true;
        }
        return false;
    }
}
