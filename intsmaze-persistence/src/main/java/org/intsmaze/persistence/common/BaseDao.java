package org.intsmaze.persistence.common;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.DateUtil;
import org.intsmaze.core.util.StringUtil;
import org.intsmaze.core.util.Util;
import org.intsmaze.persistence.mapper.FDFBAuditLogMapper;
import org.intsmaze.persistence.pojos.FDFBAuditLog;
import org.intsmaze.persistence.util.DBContextHolder;
import org.intsmaze.persistence.util.GenericsUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDao<T> extends SqlSessionDaoSupport implements Serializable {

	private static final Logger log = Logger.getLogger(BaseDao.class);

	private static final long serialVersionUID = 7623507504198633838L;

	private final String POSTFIX = "Mapper";

	private final String PREFIX = "org.intsmaze.persistence.mapper.";

	private final String _INSERT = ".insert";

	private final String _INSERTSELECTIVE = ".insertSelective";

	private final String _SELECTBYPRIMARYKEY = ".selectByPrimaryKey";

	private final String _UPDATEBYPRIMARYKEY = ".updateByPrimaryKey";

	private final String _UPDATEBYPRIMARYKEYSELECTIVE = ".updateByPrimaryKeySelective";

	private final String _DELETEBYPRIMARYKEY = ".deleteByPrimaryKey";

	private final String _UPDATEBYPRIMARYKEYWITHBLOBS = ".updateByPrimaryKeyWithBLOBs";

	private final String LOG_TYPE_INSERT = "新增";

	private final String LOG_TYPE_UPDATE = "修改";

	private final String LOG_TYPE_DELETE = "删除";

	private final String METHOD_PREFIX = "get";

	private final String EXCLUDE_METHODNAME = "getBeginDate;getEndDate;getOrderByStr;getClass;getNewMofifyon";// BasePojo中的字段不进行判断，所以在auditlog中不包含这些字段

	private final String EXCLUDE_ENTITY = "FDFBAuditLog;FDFBLoginfo";// 排除哪些类不用记录auditlog，以;分割

	private final String INCLUDE_ENTITY = "FDFBUser;FDFBRole";// 哪些表
																// 需要记录auditlog，已;分割

	private final String SET_PK_METHOD_NAME = "setSeqno";

	private final String GET_PK_METHOD_NAME = "getSeqno";

	private final String METHOD_NAME_SET_NEW_MODIFYON = "setNewMofifyon";

	private final String METHOD_NAME_SET_MODIFYON = "setModifyon";

	private final String METHOD_NAME_SET_CREATEON = "setCreateon";

	@Autowired
	FDFBAuditLogMapper auditLogMapper;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/*
	 * GenericsUtils为工具类，请见下方代码
	 * 泛型获得XXXEntity，将其转换为XXXEntityDao，具体操作替换掉Entity变成XXXDao，对应Mapper.
	 * xml中的namespace命名
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getNampSpace() {
		Class<T> clazz = (Class) GenericsUtils.getSuperClassGenricType(this.getClass());
		String simpleName = PREFIX + clazz.getSimpleName() + POSTFIX;
		log.debug("namespace is -------------------------: " + simpleName);
		return simpleName;
	}

	public int insertWithoutSeqno(T entity) {

		int result = 0;

		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		result = getSqlSession().insert((this.getNampSpace().contains("Entity")
				? this.getNampSpace().replace("Entity", "") : this.getNampSpace()) + _INSERT, entity);
		if (result > 0) {
			try {
				insertAuditLog(LOG_TYPE_INSERT, entity, null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insert(T entity) {

		int result = 0;

		try {
			setValue(entity, String.class, SET_PK_METHOD_NAME, Util.generateUUID());// 为entity对象赋seqno
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}

		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		result = getSqlSession().insert((this.getNampSpace().contains("Entity")
				? this.getNampSpace().replace("Entity", "") : this.getNampSpace()) + _INSERT, entity);
		if (result > 0) {
			try {
				insertAuditLog(LOG_TYPE_INSERT, entity, null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	public void insert(List<T> list) {

		for (T entity : list) {
			int result = 0;
			try {
				setValue(entity, String.class, SET_PK_METHOD_NAME, Util.generateUUID());
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
			DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
			result = getSqlSession().insert((this.getNampSpace().contains("Entity")
					? this.getNampSpace().replace("Entity", "") : this.getNampSpace()) + _INSERT, entity);
			if (result > 0) {
				try {
					insertAuditLog(LOG_TYPE_INSERT, entity, null);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int insertSelective(T record) {// 这个不建议使用
		try {
			setValue(record, String.class, SET_PK_METHOD_NAME, Util.generateUUID());// 为entity对象赋seqno
			setValue(record, Timestamp.class, METHOD_NAME_SET_MODIFYON, DateUtil.getTimestamp());
			setValue(record, Timestamp.class, METHOD_NAME_SET_CREATEON, DateUtil.getTimestamp());
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		return getSqlSession().insert((this.getNampSpace().contains("Entity")
				? this.getNampSpace().replace("Entity", "") : this.getNampSpace()) + _INSERTSELECTIVE, record);
	}

	public T selectByPrimaryKey(String id) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return getSqlSession().selectOne((this.getNampSpace().contains("Entity")
				? this.getNampSpace().replace("Entity", "") : this.getNampSpace()) + _SELECTBYPRIMARYKEY, id);
	}

	
	public List<T> selectByForeignKey(String id) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return getSqlSession().selectList((this.getNampSpace().contains("Entity")
				? this.getNampSpace().replace("Entity", "") : this.getNampSpace()) + _SELECTBYPRIMARYKEY, id);
	}
	
	
	public int updateByPrimaryKey(T record) {

		String seqno = null;
		try {
			setValue(record, Timestamp.class, METHOD_NAME_SET_NEW_MODIFYON, DateUtil.getTimestamp());
			seqno = getSeqno(record);
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		T origEntity = selectByPrimaryKey(seqno);
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		int result = getSqlSession().update((this.getNampSpace().contains("Entity")
				? this.getNampSpace().replace("Entity", "") : this.getNampSpace()) + _UPDATEBYPRIMARYKEY, record);
		if (result > 0) {
			try {
				insertAuditLog(LOG_TYPE_UPDATE, record, origEntity);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateByPrimaryKeySelective(T record) {

		String seqno = null;
		try {
			setValue(record, Timestamp.class, METHOD_NAME_SET_NEW_MODIFYON, DateUtil.getTimestamp());
			seqno = getSeqno(record);
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		T origEntity = selectByPrimaryKey(seqno);
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		int result = getSqlSession().update((this.getNampSpace().contains("Entity")
				? this.getNampSpace().replace("Entity", "") : this.getNampSpace()) + _UPDATEBYPRIMARYKEYSELECTIVE,
				record);
		if (result > 0) {
			try {
				insertAuditLog(LOG_TYPE_UPDATE, record, origEntity);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateByPrimaryKeyWithBLOBs(T record) {

		String seqno = null;
		try {
			setValue(record, Timestamp.class, METHOD_NAME_SET_NEW_MODIFYON, DateUtil.getTimestamp());
			seqno = getSeqno(record);
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		T origEntity = selectByPrimaryKey(seqno);
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		int result = getSqlSession().update((this.getNampSpace().contains("Entity")
				? this.getNampSpace().replace("Entity", "") : this.getNampSpace()) + _UPDATEBYPRIMARYKEYWITHBLOBS,
				record);

		if (result > 0) {
			try {
				insertAuditLog(LOG_TYPE_UPDATE, record, origEntity);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteByPrimaryKey(String id) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		return getSqlSession().delete((this.getNampSpace().contains("Entity")
				? this.getNampSpace().replace("Entity", "") : this.getNampSpace()) + _DELETEBYPRIMARYKEY, id);
	}

	/**
	 * 组织audit loginfo，如果只有destEntity，则认为是insert或delete，如果两个参数都有，则认为是update
	 * 
	 * @param origEntity
	 * @param destEntity
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private String formatLoginfo(String logType, T destEntity, T origEntity)
			throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (destEntity == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer(logType);
		sb.append(" 对象:").append(destEntity.getClass()).append("［");
		Class class1 = destEntity.getClass();
		Method[] methodList = class1.getMethods();
		if (methodList == null || methodList.length < 1) {
			return null;
		} else {
			for (int i = 0, n = methodList.length; i < n; i++) {
				Method method = methodList[i];
				String methodName = method.getName();
				if (methodName.startsWith(METHOD_PREFIX) && !EXCLUDE_METHODNAME.contains(methodName)) {
					Object objValue = method.invoke(destEntity);
					if (origEntity != null) {
						Object origValue = method.invoke(origEntity);
						if (!"".equals(StringUtil.isNullString(String.valueOf(objValue)))
								&& !String.valueOf(objValue).equals(String.valueOf(origValue))) {
							sb.append(methodName.replaceAll(METHOD_PREFIX, "")).append(":")
									.append(String.valueOf(origValue)).append(" -> ").append(String.valueOf(objValue))
									.append(";");
						}
					} else {
						sb.append(methodName.replaceAll(METHOD_PREFIX, "")).append(":").append(String.valueOf(objValue))
								.append(";");
					}
				}

			}
			sb.append("］");
			return sb.toString();
		}
	}

	/**
	 * 所有数据库的增、删、改都插入auditlog
	 * 
	 * @param logType
	 * @param logInfo
	 * @param destEntity
	 * @param origEntity
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private void insertAuditLog(String logType, T destEntity, T origEntity)
			throws IllegalArgumentException, ClassNotFoundException, IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		Class<T> clazz = (Class) GenericsUtils.getSuperClassGenricType(this.getClass());
		String simpleName = clazz.getSimpleName();
		// if(!EXCLUDE_ENTITY.contains(simpleName))
		if (INCLUDE_ENTITY.contains(simpleName))// 按具体项目要求进行记录
		{
			String auditLogInfo = formatLoginfo(logType, destEntity, origEntity);
			FDFBAuditLog auditLog = new FDFBAuditLog();
			auditLog.setSeqno(Util.generateUUID());
			auditLog.setLogInfo(auditLogInfo);
			auditLog.setLogType(logType);
			auditLog.setOperEntity(simpleName);
			auditLog.setRefrenceSeqno(getSeqno(destEntity));
			auditLog.setCreateby(Constant.BY_ENGINE);
			auditLog.setCreateon(DateUtil.getTimestamp());
			auditLog.setModifyby(Constant.BY_ENGINE);
			auditLog.setModifyon(DateUtil.getTimestamp());
			DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
			auditLogMapper.insert(auditLog);
		}
	}

	private T setValue(T entity, Class type, String methodName, Object value)
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class class1 = entity.getClass();
		Class<?>[] typeArray = new Class[1];
		typeArray[0] = type;
		Method method = class1.getMethod(methodName, type);
		method.invoke(entity, value);
		return entity;
	}

	private String getSeqno(T entity) throws ClassNotFoundException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class class1 = entity.getClass();
		Method method = class1.getMethod(GET_PK_METHOD_NAME);
		Object seqno = method.invoke(entity);
		return seqno.toString();
	}

}
