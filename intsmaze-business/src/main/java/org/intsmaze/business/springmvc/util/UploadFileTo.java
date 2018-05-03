package org.intsmaze.business.springmvc.util;

import java.util.ArrayList;

public class UploadFileTo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4926196003257888798L;
	private String name;
	private long size;
	private String type;
	private String url;
	private String deleteUrl;
	private String thumbnailUrl;
	private String deleteType;
	private String logicUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}

	public String getLogicUrl() {
		return logicUrl;
	}

	public void setLogicUrl(String logicUrl) {
		this.logicUrl = logicUrl;
	}

}
