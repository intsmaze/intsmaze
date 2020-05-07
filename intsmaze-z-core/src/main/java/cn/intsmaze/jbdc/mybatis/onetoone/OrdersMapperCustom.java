package cn.intsmaze.jbdc.mybatis.onetoone;

import java.util.List;

import cn.intsmaze.jbdc.mybatis.po.OrderCustom;
import cn.intsmaze.jbdc.mybatis.po.Orders;
import cn.intsmaze.jbdc.mybatis.po.User;

public interface OrdersMapperCustom {
	
	// 一对一查询，查询订单关联查询用户，使用resultType
	public List<OrderCustom> findOrderUserList() throws Exception;
	// 一对一查询，使用resultMap
	public List<Orders> findOrderUserListResultMap() throws Exception;
	//一对一查询，延迟加载
	public List<Orders> findOrderUserListLazyLoading() throws Exception;
	
}
