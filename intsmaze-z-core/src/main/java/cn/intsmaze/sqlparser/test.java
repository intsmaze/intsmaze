package cn.intsmaze.sqlparser;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat.*;
import com.alibaba.druid.stat.*;
import com.alibaba.druid.util.JdbcConstants;
/**
 * @Description TODO
 * @Author intsmaze
 * @Date 2018/12/29 10:58
 * @Version 1.0
 **/
public class test {

    /**
    * @Description:
    * @Param:Manipulation : {tar=Insert, boss_table=Select, emp_table=Select, log_table=Select}
     * 可以识别出表的流向
    * @return:
    * @Author: intsmaze
    * @Date: 2018/12/29
    */
    public static void main(String[] args) {

                String sql= ""
                        + "insert into coo.tar select * from lbs.boss_table bo, ("
                        + "select a.f1, ff from icm.emp_table a "
                        + "inner join crm.log_table b "
                        + "on a.f2 = b.f3"
                        + ") f "
                        + "where bo.f4 = f.f5 "
                        + "group by bo.f6 , f.f7 having count(bo.f8) > 0 "
                        + "order by bo.f9, f.f10;"
                        + "select func(f) from test1; "
                        + "";
                String dbType = JdbcConstants.POSTGRESQL;

                //格式化输出
                String result = SQLUtils.format(sql, dbType);
                System.out.println(result); // 缺省大写格式
                List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

                //解析出的独立语句的个数
                System.out.println("size is:" + stmtList.size());
                for (int i = 0; i < stmtList.size(); i++) {

                    SQLStatement stmt = stmtList.get(i);

                    PGSchemaStatVisitor visitor = new PGSchemaStatVisitor();
                    stmt.accept(visitor);
//                    Map<String, String> aliasmap = visitor.getAliasMap();
//
//                    for (Iterator iterator = aliasmap.keySet().iterator(); iterator.hasNext();) {
//                        String key = iterator.next().toString();
//                        System.out.println("[ALIAS]" + key + " - " + aliasmap.get(key));
//                    }
                    Set<Column> groupby_col = visitor.getGroupByColumns();
                    //
                    for (Iterator iterator = groupby_col.iterator(); iterator.hasNext();) {
                        Column column = (Column) iterator.next();
                        System.out.println("[GROUP]" + column.toString());
                    }
                    //获取表名称
                    System.out.println("table names:");
                    Map<Name, TableStat> tabmap = visitor.getTables();
                    for (Iterator iterator = tabmap.keySet().iterator(); iterator.hasNext();) {
                        Name name = (Name) iterator.next();
                        System.out.println(name.toString() + " - " + tabmap.get(name).toString());
                    }
                    //System.out.println("Tables : " + visitor.getCurrentTable());
                    //获取操作方法名称,依赖于表名称
                    System.out.println("Manipulation : " + visitor.getTables());
                    //获取字段名称
                    System.out.println("fields : " + visitor.getColumns());
                }
    }
}