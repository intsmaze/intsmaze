package cn.intsmaze.lbs;

/**
 * Created by 刘洋 on 2018/10/17.
 */
public class OrgServer {

    public static void main(String[] args) throws Exception {
        String inputPath=args[0];
        String outPath=args[1];
        LbsUtils lbs = new BaiDuLbs();
        lbs.init();
        /**
         * 先根据物理位置得到百度的经纬度，然后用百度的经纬度查询物理详细位置，然后将百度的经纬度转换为gps经纬度
         * 2060条数据有问题
         */
        lbs.cleanOrg(inputPath,outPath);
    }
}
