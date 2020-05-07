package cn.intsmaze.jbdc.mybatis.onetomany;

import java.util.List;

import cn.intsmaze.jbdc.mybatis.po.OrderCustom;
import cn.intsmaze.jbdc.mybatis.po.Orders;
import cn.intsmaze.jbdc.mybatis.po.User;

public interface OrdersMapperCustom {
	

	// 一对多查询，使用resultMap
	public List<Orders> findOrderAndOrderDetails() throws Exception;
	
	// 一对多查询，使用resultMap
	public List<User> findUserOrderDetail() throws Exception;
	
}
