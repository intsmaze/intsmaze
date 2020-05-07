package cn.intsmaze.jbdc.mysql;

import cn.intsmaze.jbdc.mysql.utils.JdbcUtils;
import com.google.gson.Gson;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RUD {

    /**
     * @param args
     * @throws SQLException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws SQLException, InterruptedException, ParseException {
//		 update();
//		 delete();
        read();
    }

    static void delete() throws SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = JdbcUtils.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "delete from user where id>4";
            int i = st.executeUpdate(sql);
            System.out.println("i=" + i);
        } finally {
            conn.close();
        }
    }

    static void update() throws SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = JdbcUtils.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "update user set money=money+10 ";
            int i = st.executeUpdate(sql);
            System.out.println("i=" + i);
        } finally {
            conn.close();
        }
    }


    static void read() throws SQLException, InterruptedException, ParseException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        conn = JdbcUtils.getInstance().getConnection();

        Date date1 = null;
        SimpleDateFormat simdate1 = new SimpleDateFormat("yyyy-MM-dd");
        String name = "2020-03-03";

        date1 = simdate1.parse(name);
//        System.out.println(date1);

//        String sql1 = "insert into test(name,time) values(?,?)";
//        PreparedStatement sta = conn.prepareStatement(sql1);
//        sta.setString(1, "12313");
//        sta.setTimestamp(2, new Timestamp(date1.getTime()));
//        int rows = sta.executeUpdate();

        String sql = "select time from kettle where day11=?";
        PreparedStatement  sta = conn.prepareStatement(sql);
        sta.setDate(1, new java.sql.Date(date1.getTime()) );
//        sta.setObject(1, date1);
        rs = sta.executeQuery();
        if (rs.next()) {
            System.out.println(rs.getString("time"));
        }

//			rs = st.executeQuery("select id,name,age,address,sex,phone,email,firstname,lastname,money,city,country,hobby  from test_capability");
//			while (rs.next()) {
//				Thread.sleep(50);
//				System.out.println(rs.getObject("id") + "\t"
//						+ rs.getObject("name") + "\t"
//						+ rs.getObject("age") + "\t"
//						+ rs.getObject("address"));
//			}
    }


}
