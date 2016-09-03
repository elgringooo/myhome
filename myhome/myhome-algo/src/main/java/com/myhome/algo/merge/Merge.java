package com.myhome.algo.merge;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class Merge {
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		AddressBean update = new AddressBean();
		update.setCityName("marseille");
		update.setFirstName("Gerald");
		DetailBean d1 = new DetailBean();
		d1.setTest("jojo");
		update.setDetail(d1);
	 
		
		
		
		
		AddressBean current = new AddressBean();
		current.setCountry("FR");
		current.setFirstName("Nono");
		current.setLastName("LEVI");
		DetailBean d = new DetailBean();
		d.setTest("tes");
		NiveauBean c = new NiveauBean();
		c.setValue("niv2");
		List<NiveauBean> list = new ArrayList<>();
		d.setMyList(list);
		current.setDetail(d);

		// marseille
		// gerald
		// LEVI
		// fr

//		try {
//			Object settings = extend(current, update);
//			BeanUtils.copyProperties(update, settings);
//			 
//			System.out.println(current);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

//	// Merge object2 into object1
//	// Cette méthode vous permettra d’étendre l’objet « obj1 » avec les
//	// propriétés de l’objet « obj2 ». En cas de propriétés communes celles de «
//	// obj2 » écraseront celles de « obj1 ».
//	public static Object extend(Object object1, Object object2)
//			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
//
//		Object tmp = BeanUtils.cloneBean(object1);
//
//		new BeanUtilsBean() {
//			public void copyProperty(Object dest, String name, Object value)
//					throws IllegalAccessException, InvocationTargetException {
//				if (value != null) {
//					super.copyProperty(dest, name, value);
//				}
//			}
//		}.copyProperties(tmp, object2);
//
//		return tmp;
//
//	}
}
