package cn.intsmaze.lbs;

/**
 * Created by 刘洋 on 2018/10/17.
 */
public class AtmServer {
    /**
     * d:\位置数据/atm_position_20181022.txt d:\位置数据/atm_position_result_v3.txt
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String inputPath=args[0];
        String outPath=args[1];
        LbsUtils lbs = new BaiDuLbs();
        lbs.init();
        lbs.cleanLatLon(inputPath,outPath);
    }
}
