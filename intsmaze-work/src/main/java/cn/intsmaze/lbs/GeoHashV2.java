package cn.intsmaze.lbs;

public class GeoHashV2 {

    public static int BITS[] = {16, 8, 4, 2, 1};

    public static String BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz";

    public static int RIGHT = 0;
    public static int LEFT = 1;
    public static int TOP = 2;
    public static int BOTTOM = 3;

    public static int EVEN = 0;
    public static int ODD = 1;

    public static String[][] NEIGHBORS;
    public static String[][] BORDERS;

    static {
        NEIGHBORS = new String[4][2];
        BORDERS = new String[4][2];

        NEIGHBORS[BOTTOM][EVEN] = "bc01fg45238967deuvhjyznpkmstqrwx";
        NEIGHBORS[TOP][EVEN] = "238967debc01fg45kmstqrwxuvhjyznp";
        NEIGHBORS[LEFT][EVEN] = "p0r21436x8zb9dcf5h7kjnmqesgutwvy";
        NEIGHBORS[RIGHT][EVEN] = "14365h7k9dcfesgujnmqp0r2twvyx8zb";

        BORDERS[BOTTOM][EVEN] = "bcfguvyz";
        BORDERS[TOP][EVEN] = "0145hjnp";
        BORDERS[LEFT][EVEN] = "prxz";
        BORDERS[RIGHT][EVEN] = "028b";

        NEIGHBORS[BOTTOM][ODD] = NEIGHBORS[LEFT][EVEN];
        NEIGHBORS[TOP][ODD] = NEIGHBORS[RIGHT][EVEN];
        NEIGHBORS[LEFT][ODD] = NEIGHBORS[BOTTOM][EVEN];
        NEIGHBORS[RIGHT][ODD] = NEIGHBORS[TOP][EVEN];

        BORDERS[BOTTOM][ODD] = BORDERS[LEFT][EVEN];
        BORDERS[TOP][ODD] = BORDERS[RIGHT][EVEN];
        BORDERS[LEFT][ODD] = BORDERS[BOTTOM][EVEN];
        BORDERS[RIGHT][ODD] = BORDERS[TOP][EVEN];
    }

    private static void refine_interval(double[] interval, int cd, int mask) {
        if ((cd & mask) > 0) {
            interval[0] = (interval[0] + interval[1]) / 2.0;
        } else {
            interval[1] = (interval[0] + interval[1]) / 2.0;
        }
    }

    public static String calculateAdjacent(String srcHash, int dir) {
        srcHash = srcHash.toLowerCase();
        char lastChr = srcHash.charAt(srcHash.length() - 1);
        int type = (srcHash.length() % 2) == 1 ? ODD : EVEN;
        String base = srcHash.substring(0, srcHash.length() - 1);
        if (BORDERS[dir][type].indexOf(lastChr) != -1) {
            base = calculateAdjacent(base, dir);
        }
        return base + BASE32.charAt(NEIGHBORS[dir][type].indexOf(lastChr));
    }


    public static String[] getGeoHashExpand(String geohash) {
        try {
            String geohashTop = calculateAdjacent(geohash, TOP);
            String geohashBottom = calculateAdjacent(geohash, BOTTOM);
            String geohashRight = calculateAdjacent(geohash, RIGHT);
            String geohashLeft = calculateAdjacent(geohash, LEFT);

            String geohashTopLeft = calculateAdjacent(geohashLeft, TOP);
            String geohashTopRight = calculateAdjacent(geohashRight, TOP);
            String geohashBottomRight = calculateAdjacent(geohashRight, BOTTOM);
            String geohashBottomLeft = calculateAdjacent(geohashLeft, BOTTOM);

            String[] expand = {geohash, geohashTop, geohashBottom, geohashRight, geohashLeft, geohashTopLeft,
                    geohashTopRight, geohashBottomRight, geohashBottomLeft};
            return expand;
        } catch (Exception e) {
            //logger.error("GeoHash Error",e);
            return null;
        }
    }


    /*
     * 6个网格为宽1.2KM，高600m,所以不是正方形，要为正方形，所以要返回18个网格
     * @param geohash
     * @return
     */
    public static String[] getGeoHashExpandFor6(String geohash) {
        try {

            String geohashTop1 = calculateAdjacent(geohash, TOP);//与参数网格合并作为一个正方形

            String geohashTop2 = calculateAdjacent(geohashTop1, TOP);//正方形上面的下半个
            String geohashTop3 = calculateAdjacent(geohashTop2, TOP);//正方形上面的上半个   合并为一个正方形，为参数正方形上面

            String geohashBottom = calculateAdjacent(geohash, BOTTOM);//正方形下面上半个
            String geohashBottom1 = calculateAdjacent(geohashBottom, BOTTOM);//正方形下面下半个

            String geohashRight = calculateAdjacent(geohash, RIGHT);
            String geohashLeft = calculateAdjacent(geohash, LEFT);

            String geohashRight1 = calculateAdjacent(geohashTop1, RIGHT);
            String geohashLeft1 = calculateAdjacent(geohashTop1, LEFT);

            String geohashRight2 = calculateAdjacent(geohashTop2, RIGHT);
            String geohashLeft2 = calculateAdjacent(geohashTop2, LEFT);

            String geohashRight3 = calculateAdjacent(geohashTop3, RIGHT);
            String geohashLeft3= calculateAdjacent(geohashTop3, LEFT);

            String geohashRight4 = calculateAdjacent(geohashBottom, RIGHT);
            String geohashLeft4 = calculateAdjacent(geohashBottom, LEFT);

            String geohashRight5 = calculateAdjacent(geohashBottom1, RIGHT);
            String geohashLeft5 = calculateAdjacent(geohashBottom1, LEFT);

            String[] expand = {geohash, geohashTop1, geohashTop2,geohashTop3,geohashBottom, geohashBottom1,geohashRight, geohashLeft,
                    geohashRight1, geohashLeft1,geohashRight2, geohashLeft2,geohashRight3, geohashLeft3,geohashRight4, geohashLeft4,
                    geohashRight5, geohashLeft5};
            return expand;
        } catch (Exception e) {
            //logger.error("GeoHash Error",e);
            return null;
        }
    }


    public static double[][] decode(String geohash) {
        boolean is_even = true;
        double[] lat = new double[3];
        double[] lon = new double[3];

        lat[0] = -90.0;
        lat[1] = 90.0;
        lon[0] = -180.0;
        lon[1] = 180.0;
        double lat_err = 90.0;
        double lon_err = 180.0;

        for (int i = 0; i < geohash.length(); i++) {
            char c = geohash.charAt(i);
            int cd = BASE32.indexOf(c);
            for (int j = 0; j < BITS.length; j++) {
                int mask = BITS[j];
                if (is_even) {
                    lon_err /= 2.0;
                    refine_interval(lon, cd, mask);
                } else {
                    lat_err /= 2.0;
                    refine_interval(lat, cd, mask);
                }
                is_even = !is_even;
            }
        }
        lat[2] = (lat[0] + lat[1]) / 2.0;
        lon[2] = (lon[0] + lon[1]) / 2.0;

        return new double[][]{lat, lon};
    }

    public static String encode(double latitude, double longitude) {
        boolean is_even = true;
        int i = 0;
        double lat[] = new double[3];
        double lon[] = new double[3];
        int bit = 0;
        int ch = 0;
        int precision = 12;
        String geohash = "";

        lat[0] = -90.0;
        lat[1] = 90.0;
        lon[0] = -180.0;
        lon[1] = 180.0;

        while (geohash.length() < precision) {
            if (is_even) {
                double mid = (lon[0] + lon[1]) / 2.0;
                if (longitude > mid) {
                    ch |= BITS[bit];
                    lon[0] = mid;
                } else {
                    lon[1] = mid;
                }
            } else {
                double mid = (lat[0] + lat[1]) / 2.0;
                if (latitude > mid) {
                    ch |= BITS[bit];
                    lat[0] = mid;
                } else {
                    lat[1] = mid;
                }
            }
            is_even = !is_even;
            if (bit < 4) {
                bit++;
            } else {
                geohash += BASE32.charAt(ch);
                bit = 0;
                ch = 0;
            }
        }
        return geohash;
    }



/*    public static void main(String[] args) throws Exception {

        try {
            double lon1=109.0145193757;
            double lat1=34.236080797698;
            //根据地理坐标，生成geohash编码
            // Geohash ghash = new Geohash();
            // String gcode=Geohash.encode(lat1, lon1).substring(0, 4);
            String gcode="wqj6z";

            String[] neargcode=GeoHashV2.getGeoHashExpand(gcode);

            for(int i=0;i<neargcode.length;i++)
            {
                System.out.println(neargcode[i]);
            }

            sql = "select * from retailersinfotable where geohash like "
                    + "'"+neargcode[0]+"%' or '"+neargcode[1]+"%' or '"+neargcode[2]+"%' "
                    + "or '"+neargcode[3]+"%' or '"+neargcode[4]+"%' or '"+neargcode[5]+"%' "
                    + "or '"+neargcode[6]+"%' or '"+neargcode[7]+"%' or '"+neargcode[8]+"%'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值


            System.out.println("当前位置：经度"+lon1+" 维度："+lat1);
            int i=0;
            String[][] array = new String[rowCount][3];
            while (rs.next()){
                //从数据库取出地理坐标
                double lon2=Double.parseDouble(rs.getString("Longitude"));
                double lat2=Double.parseDouble(rs.getString("Latitude"));

                //根据地理坐标，生成geohash编码
                Geohash geohash = new Geohash();
                String geocode=geohash.encode(lat2, lon2).substring(0, 9);

                //计算两点间的距离
                int dist=(int) Test.GetDistance(lon1, lat1, lon2, lat2);

                array[i][0]=String.valueOf(i);
                array[i][1]=geocode;
                array[i][2]=Integer.toString(dist);

                i++;

                //	System.out.println(lon2+"---"+lat2+"---"+geocode+"---"+dist);
            }

            array=sqlTest.getOrder(array); //二维数组排序
            sqlTest.showArray(array);        //打印数组




        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } finally {
            conn.close();
        }

    }
    *//*
     * 二维数组排序，比较array[][2]的值，返回二维数组
     * *//*
    public static String[][] getOrder(String[][] array){
        for (int j = 0; j < array.length ; j++) {
            for (int bb = 0; bb < array.length - 1; bb++) {
                String[] ss;
                int a1=Integer.valueOf(array[bb][2]);  //转化成int型比较大小
                int a2=Integer.valueOf(array[bb+1][2]);
                if (a1>a2) {
                    ss = array[bb];
                    array[bb] = array[bb + 1];
                    array[bb + 1] = ss;

                }
            }
        }
        return array;
    }

    *//*打印数组*//*
    public static void showArray(String[][] array){
        for(int a=0;a<array.length;a++){
            for(int j=0;j<array[0].length;j++)
                System.out.print(array[a][j]+" ");
            System.out.println();
        }
    }*/

    public static void main(String[] args) {
        String gcode = "wtmeb";
        double lon1 = 120.2525711060;
        double lat1 = 30.1893539424;

        System.out.println(GeoHashV2.encode(lat1, lon1).substring(0,9));

        //根据地理坐标，生成geohash编码
        GeoHashV2 geohash = new GeoHashV2();
        String[] str = getGeoHashExpand(gcode);
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }

    }

}
