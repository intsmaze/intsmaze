package com.intsmaze.adapter.base;

import java.sql.ResultSet;
import java.util.Iterator;

import com.intsmaze.adapter.bean.HiveResultSet;
import com.intsmaze.adapter.bean.Result;
import com.intsmaze.adapter.bean.ResultIterator;
import com.intsmaze.adapter.dao.ComDao;
import com.intsmaze.adapter.dao.impl.HiveDao;
import com.intsmaze.adapter.dao.impl.MysqlDao;
import com.intsmaze.adapter.dao.impl.OdpsDao;
import com.intsmaze.adapter.dao.impl.OracleDao;
import com.intsmaze.adapter.util.AdapterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aliyun.odps.data.Record;

/**
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43
 * @describe:
 */
public class SqlAdapter {

    final static Logger logger = LoggerFactory.getLogger(SqlAdapter.class);

    private ComDao dbDao;

    public Result select(String sql) throws Exception {
        logger.info("the sql is {}", sql);

        if (dbDao instanceof OdpsDao) {
            Iterator<Record> records = (Iterator<Record>) dbDao.select(sql);
            return new ResultIterator(records);
        } else if (dbDao instanceof MysqlDao) {
            ResultSet records = (ResultSet) dbDao.select(sql);
            return new HiveResultSet(records);
        } else if (dbDao instanceof HiveDao) {
            ResultSet records = (ResultSet) dbDao.select(sql);
            return new HiveResultSet(records);
        } else if (dbDao instanceof OracleDao) {
            ResultSet records = (ResultSet) dbDao.select(sql);
            return new HiveResultSet(records);
        } else {
            logger.warn("ComDao no match OdpsDao,HiveDao,MysqlDao SQL is {}",
                    sql);
            throw new AdapterException("dao 适配异常");
        }
    }


    public Result select(ComDao dao, String sql) throws Exception {
        logger.info("the sql is {}", sql);
        if (dbDao instanceof OdpsDao) {
            Iterator<Record> records = (Iterator<Record>) dbDao.select(sql);
            return new ResultIterator(records);
        } else if (dao instanceof MysqlDao) {
            ResultSet records = (ResultSet) dao.select(sql);
            return new HiveResultSet(records);
        } else if (dao instanceof HiveDao) {
            ResultSet records = (ResultSet) dao.select(sql);
            return new HiveResultSet(records);
        } else if (dao instanceof OracleDao) {
            ResultSet records = (ResultSet) dao.select(sql);
            return new HiveResultSet(records);
        } else {
            logger.warn("ComDao no match OdpsDao,HiveDao,MysqlDao SQL is {}",
                    sql);
            throw new AdapterException("dao 适配异常");
        }
    }

    public boolean insert(ComDao dao, String sql) throws Exception {
        logger.info("the sql is {}", sql);

        if (dao instanceof OdpsDao) {
            return dao.insert(sql);
        } else if (dao instanceof MysqlDao) {
            return dao.insert(sql);
        } else if (dao instanceof HiveDao) {
            return dao.insert(sql);
        } else if (dao instanceof OracleDao) {
            return dao.insert(sql);
        } else {
            logger.warn("ComDao no match OdpsDao,HiveDao,MysqlDao SQL is {}",
                    sql);
            throw new AdapterException("dao 适配异常");
        }

    }

    public ComDao getDbDao() {
        return dbDao;
    }

    public void setDbDao(ComDao dbDao) {
        this.dbDao = dbDao;
    }

}
