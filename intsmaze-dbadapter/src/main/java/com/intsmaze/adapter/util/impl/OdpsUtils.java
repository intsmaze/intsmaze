package com.intsmaze.adapter.util.impl;

import com.aliyun.odps.Odps;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;
import com.intsmaze.adapter.util.AbstractDataBaseUtils;

/**
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43
 * @describe:
 */
public final class OdpsUtils extends AbstractDataBaseUtils {

	private Odps odps = null;

	public void init() {
		Account account = new AliyunAccount(this.getUser(), this.getPassword());
		odps = new Odps(account);
		odps.setEndpoint(this.getUrl());
		odps.setDefaultProject(this.getDbName());
	}


	private OdpsUtils() {
	}

	public  Odps getOdps() {
		return odps;
	}

	public  void setOdps(Odps odps) {
		this.odps = odps;
	}

}
