package cn.intsmaze.lbs;

import java.io.IOException;

public class GaoDePoi {
    public static void main(String[] args) throws IOException {
        GaoDeLbs gaoDeLbs=new GaoDeLbs();
        gaoDeLbs.init();
        gaoDeLbs.getPoi("机场","杭州",20,1,"150100");
    }
}
