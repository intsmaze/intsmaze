//package cn.intsmaze.jbdc.mysql;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import cn.intsmaze.jbdc.mysql.utils.DataSourceUtils;
//
//
///**
// */
//public class ORMTest {
//
//	/**
//	 * @param args
//	 * @throws Exception
//	 * @throws InvocationTargetException
//	 * @throws IllegalAccessException
//	 * @throws SQLException
//	 */
//	public static void main(String[] args) throws SQLException,
//			IllegalAccessException, InvocationTargetException, Exception {
//		User user = (User) getObject(
//				"select id as Id, name as Name, birthday as Birthday, money as Money  from user where id=1",
//				User.class);
//		System.out.println(user);
//
//		Bean b = (Bean) getObject(
//				"select id as Id, name as Name, birthday as Birthday, money as Money from user where id=1",
//				Bean.class);
//		System.out.println(b);
//	}
//
//	static List<Object> getObjects(String sql, Class clazz)
//			throws SQLException, Exception, IllegalAccessException,
//			InvocationTargetException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = DataSourceUtils.getConnection();
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//			String[] colNames = getColNames(rs);
//
//			List<Object> objects = new ArrayList<Object>();
//			Method[] ms = clazz.getMethods();
//			while (rs.next()) {
//				Object object = clazz.newInstance();
//				for (int i = 0; i < colNames.length; i++) {
//					String colName = colNames[i];
//					String methodName = "set" + colName;
//					// Object value = rs.getObject(colName);
//					// try {
//					// Method m = clazz
//					// .getMethod(methodName, value.getClass());
//					// if (m != null)
//					// m.invoke(object, value);
//					// } catch (NoSuchMethodException e) {
//					// e.printStackTrace();
//					// //
//					// }
//					for (Method m : ms) {
//						if (methodName.equals(m.getName())) {
//							m.invoke(object, rs.getObject(colName));
//							break;
//						}
//					}
//					objects.add(object);
//				}
//			}
//			return objects;
//		} finally {
//			DataSourceUtils.free(rs, ps, conn);
//		}
//	}
//
//	private static String[] getColNames(ResultSet rs) throws SQLException {
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int count = rsmd.getColumnCount();
//		String[] colNames = new String[count];
//		for (int i = 1; i <= count; i++) {
//			colNames[i - 1] = rsmd.getColumnLabel(i);
//		}
//		return colNames;
//	}
//
//	static Object getObject(String sql, Class clazz) throws SQLException,
//			Exception, IllegalAccessException, InvocationTargetException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = DataSourceUtils.getConnection();
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//			String[] colNames = getColNames(rs);
//
//			Object object = null;
//			Method[] ms = clazz.getMethods();
//			if (rs.next()) {
//				object = clazz.newInstance();
//				for (int i = 0; i < colNames.length; i++) {
//					String colName = colNames[i];
//					String methodName = "set" + colName;
//					// Object value = rs.getObject(colName);
//					// try {
//					// Method m = clazz
//					// .getMethod(methodName, value.getClass());
//					// if (m != null)
//					// m.invoke(object, value);
//					// } catch (NoSuchMethodException e) {
//					// e.printStackTrace();
//					// //
//					// }
//					for (Method m : ms) {
//						if (methodName.equals(m.getName())) {
//							m.invoke(object, rs.getObject(colName));
//							break;
//						}
//					}
//				}
//			}
//			return object;
//		} finally {
//			DataSourceUtils.free(rs, ps, conn);
//		}
//	}
//}
