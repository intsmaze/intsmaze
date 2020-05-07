package cn.intsmaze.lbs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by 刘洋 on 2018/9/17.
 */
public class BaiDuLbs extends LbsUtils{

    /**
     * 通过经纬度再去查一遍得到详细的物理地址层级
     * @param pathNode
     * @param lineTxt
     * @throws IOException
     */
    public void writeJsonNode(JsonNode pathNode, String lineTxt) throws IOException {
        String[] fileStr = lineTxt.split("\\t");
        String lng=pathNode.path("lng").toString();
        String lat=pathNode.path("lat").toString();
        JsonNode rootNode =  sendRequestForCity(lat+","+lng,null);
        JsonNode it = rootNode.path("result").path("addressComponent");

        String province = it.path("province").textValue();//省
        String city = it.path("city").textValue();//市
        if (city == null) {
            city = province;
        }
        String district = it.path("district").textValue();//区
        String street= it.path("street").textValue();
        String adcode= it.path("adcode").textValue();

        //返回数据 第一个是lat,第二个是lng
        double[] latlng=GPSUtil.baidu_to_gps84(Double.valueOf(lat),Double.valueOf(lng));

        String latD=String.valueOf(latlng[0]);
        String[] latArray=latD.split("\\.");
        String demoLat=latArray[1].substring(0, 5);
        String baiduLat=latArray[0]+"."+demoLat;

        String lngD=String.valueOf(latlng[1]);
        String[] lngArray=lngD.split("\\.");
        String demoLng=lngArray[1].substring(0, 5);
        String baiduLng=lngArray[0]+"."+demoLng;

        GeoHash g = new GeoHash(Double.valueOf(baiduLat), Double.valueOf(baiduLng));
        g.sethashLength(9);
        String geohash= g.getGeoHashBase32();

        out.write(StringUtils.join(geohash, "\t",province, "\t", city, "\t", district,"\t",street,
                "\t",baiduLng , "\t",baiduLat ,"\t",fileStr[0] ,"\t",fileStr[1] ,"\t",fileStr[2] ,"\t",fileStr[3] ,"\t",adcode,"\r\n"));
    }

    public JsonNode sendRequestForCity(String param,String gpsType) throws IOException {
        String walkingUrl = "http://api.map.baidu.com/geocoder/v2/?location={location}&output=json&pois=1&ak={key}";
        if(gpsType==null)
        {
             walkingUrl = "http://api.map.baidu.com/geocoder/v2/?location={location}&output=json&pois=1&ak={key}";
        }
        else
        {
             walkingUrl = "http://api.map.baidu.com/geocoder/v2/?location={location}&output=json&pois=1&ak={key}&ret_coordtype="+gpsType;
        }

        HashMap<String, Object> urlVariables = new HashMap();
        urlVariables.put("key", BaiDukey);
        System.out.println(param);
        urlVariables.put("location", param);
        String walkJson="";
        try {
            walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
        }catch (Exception e)
        {
            try {
                Thread.sleep(1000);
                walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        ObjectMapper m = new ObjectMapper();
        JsonNode rootNode = m.readTree(walkJson);
        System.out.println(walkJson);
        return rootNode;
    }

    /**
     * 通过物理地址得到他的baidu经纬度，
     * @param param
     * @return
     * @throws IOException
     */
    public JsonNode sendRequestForLngLat(String param) throws IOException {
//      String walkingUrl = "http://api.map.baidu.com/geocoder/v2/?address={destination}&output=json&ak={key}&ret_coordtype=gcj02ll";
        String walkingUrl = "http://api.map.baidu.com/geocoder/v2/?address={destination}&output=json&ak={key}";
        HashMap<String, Object> urlVariables = new HashMap();
        urlVariables.put("key", BaiDukey);
        urlVariables.put("destination", param);
        String walkJson="";
        try {
            walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
        }catch (Exception e)
        {
            try {
                Thread.sleep(1000);
                walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        ObjectMapper m = new ObjectMapper();
        JsonNode rootNode = m.readTree(walkJson);
        System.out.println(walkJson);
        return rootNode;
    }

    /**
     * @param  lineTxt 通过物理位置返回gcj02ll的坐标
     * @param firstIndex
     * @param lastIndex
     * @throws IOException
     */
    @Override
    public void getLocation(String lineTxt, int firstIndex, int lastIndex) throws IOException {
        String[] fileStr = lineTxt.split("\\t");
        JsonNode rootNode = sendRequestForLngLat(fileStr[lastIndex]);//根据下标四物理位置
        JsonNode it = rootNode.path("result").path("location");
        String lng=it.path("lng").toString();
        String lat=it.path("lat").toString();
        if(lng==null||lng.equals(""))
        {
            rootNode = sendRequestForLngLat(fileStr[firstIndex]);//根据下标四物理位置
            it = rootNode.path("result").path("location");
        }

        writeJsonNode(it,lineTxt);
        System.out.println("------------------------------------------------------------------------");
        out.flush();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getAddress(String lineTxt) throws Exception {
        String[] fileStr = lineTxt.split("\t");
        double[] a=GPSUtil.gps84_To_bd09(Double.valueOf(fileStr[3]),Double.valueOf(fileStr[2]));
        //经度，纬度 gps角度坐标转百度坐标
//        JsonNode rootNode =  sendRequestSwitchLatLong(fileStr[2] + "," + fileStr[3]);
//        JsonNode it = rootNode.path("result");
//        String x="";//经度
//        String y="";//纬度
//        Iterator<JsonNode> iterator=it.iterator();
//            if (iterator.hasNext()) {
//                JsonNode jsonNode=iterator.next();
//                x=jsonNode.path("x").toString();
//                y=jsonNode.path("y").toString();
//            }


        //lat<纬度>,lng<经度> 通过百度经纬度得到物理位置   试过通过apg的经纬度在接口指明类型但是查询的位置有偏差，所以还是用百度经纬度查询吧
        JsonNode rootNode =  sendRequestForCity(a[0]+","+a[1],null);
        JsonNode it = rootNode.path("result").path("addressComponent");

        String province = it.path("province").textValue();//省
        String city = it.path("city").textValue();//市
        if (city == null) {
            city = province;
        }
        String district = it.path("district").textValue();//区
        String street= it.path("street").textValue();

        GeoHash g = new GeoHash(Double.valueOf(fileStr[2]),Double.valueOf(fileStr[3]));
        g.sethashLength(9);
        String geohash=g.getGeoHashBase32();

       //写回的坐标为百度角度坐标
//     out.write(StringUtils.join(fileStr[0] ,"\t", geohash,"\t", x ,"\t", y,"\t",fileStr[4] ,"\t", fileStr[5],"\t",province, "\t", city, "\t", district, "\t", street, "\r\n"));

        out.write(StringUtils.join(lineTxt,"\t",province, "\t", city, "\t", district, "\t", street, "\r\n"));
        out.flush();
        Thread.sleep(20);
    }



    /**
     * @param param 将gps角度坐标转百度坐标
     * @return
     * @throws IOException
     */
    public JsonNode sendRequestSwitchLatLong(String param) throws IOException {
        String walkingUrl = "http://api.map.baidu.com/geoconv/v1/?coords={location}&from=1&to=5&ak={key}";
        HashMap<String, Object> urlVariables = new HashMap();
        urlVariables.put("key", BaiDukey);
        System.out.println(param);
        urlVariables.put("location", param);
        String walkJson="";
        try {
            walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
        }catch (Exception e)
        {
            try {
                Thread.sleep(1000);
                walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        ObjectMapper m = new ObjectMapper();
        JsonNode rootNode = m.readTree(walkJson);
        System.out.println(walkJson);
        return rootNode;
    }

    public JsonNode sendRequestSwitchLatLongForGps(String param) throws IOException {
        String walkingUrl = "http://api.map.baidu.com/geoconv/v1/?coords={location}&from=5&to=3&ak={key}";
        HashMap<String, Object> urlVariables = new HashMap();
        urlVariables.put("key", BaiDukey);
        System.out.println(param);
        urlVariables.put("location", param);
        String walkJson="";
        try {
            walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
        }catch (Exception e)
        {
            try {
                Thread.sleep(1000);
                walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        ObjectMapper m = new ObjectMapper();
        JsonNode rootNode = m.readTree(walkJson);
        System.out.println(walkJson);
        return rootNode;
    }
    public static void main(String[] args)throws IOException
    {
        BaiDuLbs b=new BaiDuLbs();
        b.init();
//        b.sendRequestSwitchLatLongForGps("116.9751447205871,33.62768977746834");

//        JsonNode rootNode = b.sendRequestForLngLat("苍南县灵溪镇人民大道425号 ");
//        JsonNode it = rootNode.path("result").path("location");
//        String lng=it.path("lng").toString();
//        String lat=it.path("lat").toString();
//        System.out.println(lat+","+lng);
//        double[] latlng=GPSUtil.baidu_to_gps84(Double.valueOf(lat),Double.valueOf(lng));
//        System.out.println(latlng[0]+","+latlng[1]);

        double[] a=GPSUtil.gps84_To_bd09(Double.valueOf("27.92179"),Double.valueOf("119.9582"));
        b.sendRequestForCity(a[0]+","+a[1],null);
    }
}
