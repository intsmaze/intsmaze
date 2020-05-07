package cn.intmsaze.project.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author:YangLiu
 * @date:2018年4月17日 下午5:44:43
 * @describe:
 */
public class Dubbo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String seg;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return seg
	 */
	public String getSeg() {
		return seg;
	}

	/**
	 * @param seg
	 */
	public void setSeg(String seg) {
		this.seg = seg;
	}
}
