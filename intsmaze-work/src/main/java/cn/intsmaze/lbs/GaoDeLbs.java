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
public class GaoDeLbs extends LbsUtils {


    //    public String GaoDekey = "c403bba913ece562f4f28587dcbc93f5";
//    public String GaoDekey = "3f6f17ebb188401525a529cecb9e7cbc";
    public String GaoDekey = "47ea635a161638932fcadf9c37db3fb9";


    //    /**
//     * 根据经纬度得到具体的位置信息
//     * @param location
//     * @throws IOException
//     */
//    private void getAddressByLocation(String location) throws IOException {
//        String walkingUrl = "https://restapi.amap.com/v3/geocode/regeo?key={key}&location={destination}&poitype=&radius=&extensions=all&batch=false&roadlevel=0";
//        HashMap<String, Object> urlVariables = new HashMap();
//        urlVariables.put("key", key);
//        urlVariables.put("destination", location);
//        String walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
//        ObjectMapper m = new ObjectMapper();
//        JsonNode rootNode = m.readTree(walkJson);
//        JsonNode jsonNode = rootNode.path("regeocode").path("addressComponent");
//        String country = jsonNode.path("country").textValue();//国家
//        String province = jsonNode.path("province").textValue();//省
//        String city = jsonNode.path("city").textValue();//市
//        if (city == null) {
//            city = province;
//        }
//        String cityCode = jsonNode.path("citycode").textValue();//市编号
//        String district = jsonNode.path("district").textValue();//区
//        String adcode = jsonNode.path("adcode").textValue();//区编号
//        String township = jsonNode.path("township").textValue();//街道
//        String towncode = jsonNode.path("towncode").textValue();//街道编号
//        System.out.println(walkJson);
//    }
//


    @Override
    public void getAddress(String lineTxt) throws Exception {
        String[] fileStr = lineTxt.split(",");

        String walkingUrl = "https://restapi.amap.com/v3/geocode/regeo?key={key}&location={destination}";
        HashMap<String, Object> urlVariables = new HashMap();
        urlVariables.put("key", GaoDekey);
        urlVariables.put("destination", fileStr[2] + "," + fileStr[3]);
        String walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
        ObjectMapper m = new ObjectMapper();
        JsonNode rootNode = m.readTree(walkJson);
        JsonNode jsonNode = rootNode.path("regeocode").path("addressComponent");

        String country = jsonNode.path("country").textValue();//国家
        String province = jsonNode.path("province").textValue();//省
        String city = jsonNode.path("city").textValue();//市
        if (city == null) {
            city = province;
        }
        String cityCode = jsonNode.path("citycode").textValue();//市编号
        String district = jsonNode.path("district").textValue();//区
        String adcode = jsonNode.path("adcode").textValue();//区编号
        String township = jsonNode.path("township").textValue();//街道
        String towncode = jsonNode.path("towncode").textValue();//街道编号
        System.out.println(country + ":" + province + ":" + city + ":" + cityCode + ":" + district + ":" + adcode + ":" + township + ":" + towncode);
        out.write(StringUtils.join(lineTxt, ",", province, ",", city, ",", district, ",", township, "\r\n"));
        out.flush();
        Thread.sleep(20);
    }
//
//
//    /**
//     * 根据位置信息得到详细的位置信息以及经纬度
//     * @param address
//     * @throws IOException
//     */
//    private void getLocationByAddress(String address) throws IOException {
//        String walkingUrl = "https://restapi.amap.com/v3/geocode/geo?key={key}&address={destination}";
//        HashMap<String, Object> urlVariables = new HashMap();
//        urlVariables.put("key", key);
//        urlVariables.put("destination", address);
//        String walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
//        ObjectMapper m = new ObjectMapper();
//        JsonNode rootNode = m.readTree(walkJson);
//        Iterator<JsonNode> it = rootNode.path("geocodes").elements();
//        if (it.hasNext()) {
//            JsonNode pathNode = it.next();
//            String distance = pathNode.path("formatted_address").textValue();
//            String province = pathNode.path("province").textValue();//省
//            String city = pathNode.path("city").textValue();//市
//            String district = pathNode.path("district").textValue();//区
//            String township = pathNode.path("township").textValue();//街道
//            String location = pathNode.path("location").textValue();
//            System.out.println(location + ":" + province + ":" + city + ":" + district + ":" + township);
//        }
//
//        System.out.println(walkJson);
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


    public JsonNode sendRequestForLngLat(String param) throws IOException {
        String walkingUrl = "https://restapi.amap.com/v3/geocode/geo?key={key}&address={destination}";
        HashMap<String, Object> urlVariables = new HashMap();
        urlVariables.put("key", GaoDekey);
        System.out.println(param);
        urlVariables.put("destination", param);
        String walkJson = "";
        try {
            walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
        } catch (Exception e) {
            try {
                Thread.sleep(2000);
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

    public void writeJsonNode(JsonNode pathNode, String address) throws IOException {
        String distance = pathNode.path("formatted_address").textValue();
        String province = pathNode.path("province").textValue();//省
        String city = pathNode.path("city").textValue();//市
        if (city == null) {
            city = province;
        }
        String district = pathNode.path("district").textValue();//区
        String location = pathNode.path("location").textValue();
        out.write(StringUtils.join(province, "\t", city, "\t", district, "\t", location.split(",")[0], "\t", location.split(",")[1], "\t", address, "\r\n"));
    }


    @Override
    public void getLocation(String address, int firstIndex, int lastIndex) throws IOException {
        String[] fileStr = address.split("\\t");
        JsonNode rootNode = sendRequestForLngLat(fileStr[lastIndex]);

        Iterator<JsonNode> it = rootNode.path("geocodes").elements();
        if (it.hasNext()) {
            JsonNode pathNode = it.next();
            writeJsonNode(pathNode, address);
        } else {
            rootNode = sendRequestForLngLat(fileStr[firstIndex]);
            it = rootNode.path("geocodes").elements();
            if (it.hasNext()) {
                JsonNode pathNode = it.next();
                writeJsonNode(pathNode, address);
            } else {
                out.write(StringUtils.join("", "\t", "", "\t", "", "\t", "", "\t", "", address, "\r\n"));
            }
        }
        System.out.println("------------------------------------------------------------------------");
        out.flush();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void getPoi(String keywords, String city, int offset, int page, String types) throws IOException {
        out = FileWR.writeFile("C:\\汉得工作/浙江农信/"+city+keywords+".txt");

        int count=wirteInfo(keywords,city,offset,page,types);
        int sendPage=count/offset;
        int n=count%offset;
        if(n!=0)
        {
            sendPage++;
        }

        int pageTemp=page;
        for(int i=1;i<sendPage;i++)
        {
            pageTemp++;
            count=wirteInfo(keywords,city,offset,pageTemp,types);
        }

        System.out.println("------------------------------------------------------------------------");
        out.flush();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int wirteInfo(String keywords, String city, int offset, int page, String types) throws IOException {
        JsonNode rootNode = sendRequestForPoi(keywords, city, offset, page, types);
        String count=rootNode.path("count").textValue();

        Iterator<JsonNode> it = rootNode.path("pois").elements();
        while (it.hasNext()) {
            JsonNode pathNode = it.next();
            String name = pathNode.path("name").textValue();
            String alias = pathNode.path("alias").textValue();
            String type = pathNode.path("type").textValue();
            String address = pathNode.path("address").textValue();
            String location = pathNode.path("location").textValue();
            String adname = pathNode.path("adname").textValue();
            out.write(StringUtils.join(name, ",", alias, ",", type, ",", address, ",", location, ",",adname, "\r\n"));
            Iterator<JsonNode> children = pathNode.path("children").elements();
            while (children.hasNext()) {
                JsonNode childrenNode = children.next();
                String childrenName = childrenNode.path("name").textValue();
                String childrenAlias = childrenNode.path("sname").textValue();
                String childrentType = childrenNode.path("subtype").textValue();
                String childrenAddress = childrenNode.path("address").textValue();
                String childrenLocation = childrenNode.path("location").textValue();
                String childrenAdname = adname;
                out.write(StringUtils.join(childrenName, ",", childrenAlias, ",", childrentType, ",", childrenAddress, ",", childrenLocation, ",",childrenAdname, "\r\n"));
            }
        }
        return Integer.valueOf(count);
    }


    public JsonNode sendRequestForPoi(String keywords, String city, int offset, int page, String types) throws IOException {
        String walkingUrl = "https://restapi.amap.com/v3/place/text?key={key}&keywords={keywords}&city={city}&children=1&offset={offset}&page={page}&extensions=all";
        HashMap<String, Object> urlVariables = new HashMap();
        urlVariables.put("key", GaoDekey);
        urlVariables.put("keywords", keywords);
        urlVariables.put("city", city);
        urlVariables.put("offset", offset);
        urlVariables.put("page", page);
//        urlVariables.put("types", types);
        String walkJson = "";
        try {
            walkJson = restTemplate.getForObject(walkingUrl, String.class, urlVariables);
        } catch (Exception e) {
            try {
                Thread.sleep(2000);
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
}
