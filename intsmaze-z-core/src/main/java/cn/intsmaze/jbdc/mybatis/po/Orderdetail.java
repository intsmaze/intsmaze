package cn.intsmaze.jbdc.mybatis.po;

import java.io.Serializable;

public class Orderdetail implements Serializable {
    private Integer id;

    private Integer ordersId;

    private Integer itemsId;

    private Integer itemsNum;
    
    //商品信息
    private Items items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getItemsId() {
        return itemsId;
    }

    public void setItemsId(Integer itemsId) {
        this.itemsId = itemsId;
    }

    public Integer getItemsNum() {
        return itemsNum;
    }

    public void setItemsNum(Integer itemsNum) {
        this.itemsNum = itemsNum;
    }

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Orderdetail [id=" + id + ", ordersId=" + ordersId
				+ ", itemsId=" + itemsId + ", itemsNum=" + itemsNum + "]";
	}
    
}