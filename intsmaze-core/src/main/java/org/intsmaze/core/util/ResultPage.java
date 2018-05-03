package org.intsmaze.core.util;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 */
public class ResultPage extends Result{
    private Object page;

    public ResultPage setCode(ResultCode resultCode) {
        super.setCode(resultCode);
        return this;
    }

    public ResultPage setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    public ResultPage setData(Object data) {
        super.setData(data);
        return this;
    }
    
    public Object getPage() {
		return page;
	}

    public Result setPage(Object page) {
        this.page = page;
        return this;
    }
    
	@Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
