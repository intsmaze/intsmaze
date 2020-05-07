package cn.intsmaze.proxy.customImpl;
public class TankTimeProxy implements Moveable {
    public TankTimeProxy(Moveable t) {
        super();
        this.t = t;
    }
    Moveable t;
@Override
public void move() {
   long start = System.currentTimeMillis();
   System.out.println("starttime:" + start);
   t.move();
   long end = System.currentTimeMillis();
   System.out.println("time:" + (end-start));
}}