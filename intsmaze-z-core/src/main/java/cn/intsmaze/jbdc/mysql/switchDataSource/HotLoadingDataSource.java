package cn.intsmaze.jbdc.mysql.switchDataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * @author:YangLiu
 * @date:2018年3月5日 下午2:27:42 
 * @describe: 一般刷新数据源我们不会通过直接销毁并重建bean的方式，因为那样的话，
 * 中间会卡一下的。一般是外面包一个datasource，里面包含了真正的datasource，
 * 然后监听到配置变化的时候先创建一个新的datasource，等连接OK后再替换掉老的datasource。
 * 
 * 这种方式也比热加载好多了
 */
public class HotLoadingDataSource extends BasicDataSource {

    private BasicDataSource useDataSource;

    private BasicDataSource replaceDataSource;


    public BasicDataSource getUseDataSource() {
        return useDataSource;
    }

    public void setUseDataSource(BasicDataSource useDataSource) {
        this.useDataSource = useDataSource;
    }

    public BasicDataSource getReplaceDataSource() {
        return replaceDataSource;
    }

    public void setReplaceDataSource(BasicDataSource replaceDataSource) {
        this.replaceDataSource = replaceDataSource;
    }
}
