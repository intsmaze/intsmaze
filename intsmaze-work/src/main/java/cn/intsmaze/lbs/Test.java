//package cn.intsmaze.lbs;
//
//import ch.hsr.geohash.GeoHash;
//import ch.hsr.geohash.WGS84Point;
//import ch.hsr.geohash.queries.GeoHashCircleQuery;
//
///**
// * Created by 刘洋 on 2018/10/19.
// */
//public class Test {
//
//    public static void main(String args[])
//    {
//
//        //GeoHashCircleQueryTest
//        WGS84Point center = new WGS84Point(39.86391280373075, 116.37356590048701);
//        GeoHashCircleQuery query = new GeoHashCircleQuery(center, 589);
//
//        // the distance between center and test1 is about 430 meters
//        WGS84Point test1 = new WGS84Point(39.8648866576058, 116.378465869303);
//        // the distance between center and test2 is about 510 meters
//        WGS84Point test2 = new WGS84Point(39.8664787092599, 116.378552856158);
//
//        query.contains(test1);
//
//
//        GeoHash hash = GeoHash.withCharacterPrecision(-36.919550434870125,174.71024582237604,7);
//
//    }
//}
