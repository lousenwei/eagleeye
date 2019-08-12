package com.eagleeye.common.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

/***
 * 对公用的bean的操作.
 * 
 * @author wilson.lou
 * 
 */
public class PublicBean {
	/** log4j instance. */
	private static Logger log = Logger.getLogger(PublicBean.class);

	/***************************************************************************
	 * 将ResultSet 里面的值通过反射到对应的classname实例中.
	 * 
	 * @param calssName
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static Object getBeans(Class calssName, ResultSet rs)
			throws Exception {
		ResultSetMetaData rsmd = rs.getMetaData();
		List<Object> beanList = new ArrayList<Object>();
		String sFieldName = null;
		Object fieldValue = null;
		while (rs.next()) {
			Object newBean = calssName.newInstance();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String typeName = rsmd.getColumnTypeName(i);
				if ("NUMBER".equals(typeName)) {
					fieldValue = rs.getBigDecimal(i);
				} else if ("VARCHAR2".equals(typeName)
						|| "VARCHAR".equals(typeName)
						|| "CHAR".equals(typeName)) {
					fieldValue = rs.getString(i);
				} else if ("DATE".equals(typeName)) {
					fieldValue = rs.getTimestamp(i);
				} else {
					continue;
				}
				if (fieldValue == null) {
					continue;
				}
				sFieldName = convertFieldName(rsmd.getColumnName(i));
				setPropertyValue(newBean, sFieldName, fieldValue);
			}
			beanList.add(newBean);
		}
		return beanList;
	}

	/***************************************************************************
	 * 将ResultSet 里面的值通过反射到对应的classname实例中.
	 * 
	 * @param calssName
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static Object getBeans(Class calssName,
			List<Map<String, Object>> lstMap) throws Exception {

		List<Object> beanList = new ArrayList<Object>();
		String sFieldName = null;
		for (Map<String, Object> map : lstMap) {
			Object newBean = calssName.newInstance();
			for (String key : map.keySet()) {
				String typeName = key;
				// log.info("得到key值："+key);
				Object value = map.get(key);

				if (value == null) {
					continue;
				}
				sFieldName = convertFieldName(typeName);
				// log.info("对应的fieldName:" + sFieldName);
				setPropertyValue(newBean, sFieldName, value);
			}
			beanList.add(newBean);
		}
		return beanList;
	}

	/***
	 * 将对应的属性propName设置值propValue.
	 * 
	 * @param bean
	 * @param propName
	 * @param propValue
	 * @throws Exception
	 */
	public static void setPropertyValue(Object bean, String propName,
			Object propValue) throws Exception {
		int delimiterPos = propName.indexOf(".");
		if (delimiterPos == -1) {
			Method writeMethod = getPropertyWriteMethod(bean.getClass(),
					propName);
			if (writeMethod != null
					&& Modifier.isPublic(writeMethod.getModifiers())) {
				// log.info("对应的：writeMethod:" + writeMethod.toString());
				Class[] paramTypes = writeMethod.getParameterTypes();
				Object[] args = new Object[] { convert(paramTypes[0], propValue) };
				writeMethod.invoke(bean, args);
			}
		} else {
			String memberName = propName.substring(0, delimiterPos);
			Object memberObj = getPropertyValue(bean, memberName);
			if (memberObj == null) {
				memberObj = memberObj.getClass().newInstance();
				setPropertyValue(bean, memberName, memberObj);
			}
			propName = propName.substring(delimiterPos + 1, propName.length());
			setPropertyValue(memberObj, propName, propValue);
		}
	}

	/***
	 * 处理对应的子类信息.
	 * 
	 * @param bean
	 * @param propName
	 * @return
	 * @throws NoSuchFieldException
	 */
	public static Object getPropertyValue(Object bean, String propName)
			throws NoSuchFieldException {
		if (bean == null) {
			return null;
		}
		int delimiterPos = propName.indexOf(".");
		try {
			if (delimiterPos != -1) {
				String memberName = propName.substring(0, delimiterPos);
				Object memberObj = getPropertyValue(bean, memberName);
				if (memberObj != null) {
					return getPropertyValue(
							memberObj,
							propName.substring(delimiterPos + 1,
									propName.length()));
				} else {
					return null;
				}
			} else {
				Method readMethod = getPropertyReadMethod(bean.getClass(),
						propName);
				if (readMethod == null
						|| !Modifier.isPublic(readMethod.getModifiers())) {
					throw new NoSuchFieldException();
				}
				return readMethod.invoke(bean, null);
			}
		} catch (NoSuchFieldException ex) {
			throw ex;
		} catch (Exception ex) {

			return null;
		}
	}

	/***
	 * 将source里面的值转换为对应的classToConvert类型.
	 * 
	 * @param classToConvert
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public static Object convert(Class classToConvert, Object source)
			throws Exception {
		if (source == null) {
			return null;
		}
		if (classToConvert.isInstance(source)) {
			return source;
		}
		if (classToConvert.equals(String.class)) {
			return source.toString();
		}
		if (classToConvert.equals(Integer.class)) {
			return Integer.valueOf(source.toString());
		}
		if (classToConvert.equals(Long.class)) {
			return Long.valueOf(source.toString());
		}
		if (classToConvert.equals(Float.class)) {
			return Float.valueOf(source.toString());
		}

		if (classToConvert.equals(Double.class)) {
			return Double.valueOf(source.toString());
		}
		if (classToConvert.equals(BigDecimal.class)) {
			return new BigDecimal(source.toString());
		}
		if (classToConvert.equals(Date.class)) {
			return toDate(source.toString());
		}
		if (classToConvert.equals(Timestamp.class)) {
			return getTimestamp(source.toString());
		}

		if (classToConvert.equals(int.class)) {
			return Integer.valueOf(source.toString());
		}
		if (classToConvert.equals(long.class)) {
			return Long.valueOf(source.toString());
		}
		if (classToConvert.equals(double.class)) {
			return Double.valueOf(source.toString());
		}
		if (classToConvert.equals(float.class)) {
			return Float.valueOf(source.toString());
		}
		if (classToConvert.equals(boolean.class)) {
			return new Boolean("true".equals(source.toString()) ? true : false);
		}
		if (classToConvert.equals(java.sql.Clob.class)) {
			return Hibernate.createClob(source.toString());
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static Method getPropertyWriteMethod(Class beanClass, String propName) {
		Method readMethod = getPropertyReadMethod(beanClass, propName);
		if (readMethod == null) {
			return null;
		}

		try {
			Class[] argTypes = { readMethod.getReturnType() };
			propName = propName.substring(0, 1).toUpperCase()
					+ propName.substring(1, propName.length());
			return beanClass.getMethod("set" + propName, argTypes);
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static Method getPropertyReadMethod(Class beanClass, String propName) {
		Method method = null;
		try {
			propName = propName.substring(0, 1).toUpperCase()
					+ propName.substring(1, propName.length());
			try {
				method = beanClass.getMethod("get" + propName, null);
			} catch (NoSuchMethodException nsex) {
				method = beanClass.getMethod("is" + propName, null);
			}
		} catch (NoSuchMethodException ex) {
			method = null;
		}
		return method;
	}

	/***************************************************************************
	 * 将s_name 变为 sName;
	 * 
	 * @param sName
	 * @return
	 */
	public static String convertFieldName(String sName) {
		String[] str = sName.toLowerCase().split("_");
		StringBuffer beanS = new StringBuffer(str[0]);
		for (int i = 1; i < str.length; i++) {
			if ("".equals(str) || str == null) {
				continue;
			}
			String info = str[i];
			beanS.append(info.substring(0, 1).toUpperCase()
					+ info.substring(1, info.length()));
		}
		return beanS.toString();
	}

	// 日期格式
	private static SimpleDateFormat dateFormat1 = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static SimpleDateFormat dateFormat2 = new SimpleDateFormat(
			"yyyyMMdd");

	public static java.util.Date toDate(String sDate)
			throws java.text.ParseException {
		java.util.Date result = null;
		if (sDate == null)
			result = null;
		else if (sDate.length() == 10 && sDate.indexOf("-") == 4) {
			result = dateFormat1.parse(sDate);
		} else if (sDate.length() == 8) {
			result = dateFormat2.parse(sDate);
		}
		return result;
	}// 根据年，月，日，转化为Timestamp类型

	public static Timestamp getTimestamp(String sDate) {
		Timestamp ts = null;
		if (sDate == null || "".equals(sDate))
			return null;
		ts = Timestamp.valueOf(sDate + " 00:00:00.000000000");
		return ts;
	}
}
