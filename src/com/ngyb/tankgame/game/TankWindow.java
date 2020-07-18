package com.ngyb.tankgame.game;

import com.ngyb.tankgame.base.ElementInterface;
import com.ngyb.tankgame.model.*;
import com.ngyb.tankgame.utils.SoundUtils;
import org.lwjgl.input.Keyboard;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/18 14:56
 */
public class TankWindow extends Window {
    private List<ElementInterface> lists = new CopyOnWriteArrayList<>();
    private Tank tank;

    public TankWindow(String title, int width, int height, int fps) {
        super(title, width, height, fps);
    }

    @Override
    protected void onCreate() {
        SoundUtils.play("res/snd/start.wav", false);
        for (int i = 0; i < 14; i++) {
            lists.add(new WaterWay(i * 64, 64 * 3));
        }
        for (int i = 0; i < 14; i++) {
            lists.add(new BrickWall(i * 64 + 64, 64 * 5));
        }
        for (int i = 0; i < 14; i++) {
            lists.add(new SteelPlate(i * 64, 64 * 7));
        }
        tank = new Tank(Config.width / 2 - 32, Config.height - 64);
        lists.add(tank);
        for (int i = 0; i < 14; i++) {
            lists.add(new Lawn(i * 64 + 64, 64));
        }
    }

    @Override
    protected void onMouseEvent(int key, int x, int y) {

    }

    @Override
    protected void onKeyEvent(int key) {
        switch (key) {
            case Keyboard.KEY_UP:
            case Keyboard.KEY_W:
                tank.Move(Direction.TOP);
                break;
            case Keyboard.KEY_DOWN:
            case Keyboard.KEY_S:
                tank.Move(Direction.BOTTOM);
                break;
            case Keyboard.KEY_LEFT:
            case Keyboard.KEY_A:
                tank.Move(Direction.LEFT);
                break;
            case Keyboard.KEY_RIGHT:
            case Keyboard.KEY_D:
                tank.Move(Direction.RIGHT);
                break;
            case Keyboard.KEY_RETURN:
            case Keyboard.KEY_SPACE:
                Bullet zd = tank.firing();
                if (zd != null) {
                    lists.add(zd);
                }
                break;
        }
    }

    @Override
    protected void onDisplayUpdate() {
        try {
            for (int i = 0; i < lists.size(); i++) {
                lists.get(i).onDraw();
            }
            for (ElementInterface element : lists) {
                if (element instanceof Bullet) {
                    boolean isTransgression = ((Bullet) element).isTransgression();
                    if (isTransgression) {
                        lists.remove(element);
                    }
                }
            }
            for (ElementInterface elementInterface : lists) {
                if (elementInterface instanceof SteelPlate) {
                    boolean isBump = tank.isBump((SteelPlate) elementInterface);
                    if (isBump) {

                    }
                }
            }
        } catch (Exception e) {
            System.out.println("======"+e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
