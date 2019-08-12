package com.eagleeye.common.dao;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

/**
 * 用来处理实体BEAN的元数据的工具类
 * 
 */
public class AnnotationUtils {

	/** log4j instance. */
	private static Logger log = Logger.getLogger(AnnotationUtils.class);

	/**
	 * 得到对应的实体BEAN的数据库表名称
	 * 
	 * @param clazz
	 *            实体BEANclass对象
	 * @return String 实体BEAN对应的数据库表名称
	 */
	public static String getMappedTableName(Class clazz) {
		String tableName = null;
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = (Table) clazz.getAnnotation(Table.class);
			tableName = table.name();
		}
		return tableName;
	}

	/**
	 * 得到数据库中相对应的表的主健名称
	 * 
	 * @param ob
	 *            实体BEANclass对象
	 * @return String 实体BEAN对应的表的主健名称
	 */
	public static String getMappedKeyColumnName(Class ob) {
		String keyCloumnName = null;
		Method[] methods = ob.getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Id.class)) {
				if (method.isAnnotationPresent(Column.class)) {
					Column column = method.getAnnotation(Column.class);
					keyCloumnName = column.name();
				}
			}
		}
		return keyCloumnName;
	}

	/**
	 * 返回一个主键值
	 * 
	 * @param data
	 *            Entity数据
	 * @return 主键值
	 * @throws Exception
	 */
	public static Serializable getKeyValue(Object data) throws Exception {
		Serializable keyValue = null;
		Method[] methods = data.getClass().getMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get") && !method.getName().equalsIgnoreCase("getClass")) {
				if (method.isAnnotationPresent(Id.class)) {
					try {
						keyValue = (Serializable) method.invoke(data, null);
					} catch (Exception e) {
						log.error("getKeyValue:", e);
						throw new Exception(e);
					}
				}
			}
		}
		return keyValue;
	}

	/**
	 * 得到EntityBean中主见映射的Property
	 * 
	 * @param entity
	 *            entity
	 * @return 主见映射的Property
	 */
	public static String getKeyPropertyName(Object entity) {
		return getKeyPropertyNameByClass(entity.getClass());
	}

	/**
	 * 得到EntityBean中主见映射的Property
	 * 
	 * @param entity
	 *            entity
	 * @return 主见映射的Property
	 */
	public static String getKeyPropertyNameByClass(Class clazz) {
		String keyPropertyName = null;
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get") && !method.getName().equalsIgnoreCase("getClass")) {
				if (method.isAnnotationPresent(Id.class)) {
					String propertyName = method.getName().substring(3);
					keyPropertyName = propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
				}
			}
		}
		// 组合主键
		if (keyPropertyName == null) {
			keyPropertyName = "id";
		}
		return keyPropertyName;
	}

	/**
	 * 根据属性名称，得到属性值
	 * 
	 * @param propertyName
	 *            propertyNAME，
	 * @param voEntity
	 *            vo对象
	 * @return Serializable 属性值
	 * @throws Exception
	 */
	public static Serializable getPropertyValueByName(String propertyName, Object voEntity) throws Exception {
		Method[] methods = voEntity.getClass().getMethods();
		String getKeyPropertyValueMethodName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
		Serializable idValue = null;
		for (Method method : methods) {
			if (method.getName().equals(getKeyPropertyValueMethodName)) {
				try {
					idValue = (Serializable) method.invoke(voEntity, null);
				} catch (Exception e) {
					log.error("getKeyValue:", e);
					throw new Exception(e);
				}
			}
		}
		return idValue;

	}

	/**
	 * 根据VO对象给ENTITY对象设置值，如果VO中有不等于NULL的属性，此属性在ENTITY中也存在就把此值赋给ENTITY中对应的属性
	 * 
	 * @param voEntity
	 * @param entity
	 * @return Object
	 * @throws Exception
	 */
	public static Object getTheMappingEnity(Object voEntity, Object entity) throws Exception {
		Method[] voMethods = voEntity.getClass().getMethods();
		for (Method method : voMethods) {
			if (method.getName().startsWith("get") && !method.getName().equalsIgnoreCase("getClass")) {
				try {
					Object propertyValue = method.invoke(voEntity, null);
					if (propertyValue != null) {
						String getMethodName = method.getName();
						String setMethodName = "set" + getMethodName.substring(3);
						Method setMethod = entity.getClass().getMethod(setMethodName, propertyValue.getClass());
						if (method != null) {
							setMethod.invoke(entity, propertyValue);
						}
					}
				} catch (Exception e) {
					log.error("getKeyValue:", e);
					throw new Exception(e);
				}
			}
		}
		return entity;
	}

	/**
	 * 得到ENTITY主键的值
	 * 
	 * @param entity
	 * @return Object
	 * @throws Exception
	 */
	public static Serializable getVoKeyValue(Object entity) throws Exception {
		String keyPropertyName = getKeyPropertyName(entity);
		Serializable idValue = getPropertyValueByName(keyPropertyName, entity);
		return idValue;
	}

}
