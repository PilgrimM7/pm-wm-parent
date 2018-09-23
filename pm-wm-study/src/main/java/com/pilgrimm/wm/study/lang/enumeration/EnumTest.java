package com.pilgrimm.wm.study.lang.enumeration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnumTest {

	private String a;

	public static void main(String[] args) {

		String a;

		SeasonEnum se = SeasonEnum.SPRING;

		System.out.println(se);
		System.out.println(se.name());

		System.out.println(new EnumTest().toString());

		System.out.println(new EnumTest().hashCode());

		List<String> list = new ArrayList();
		list.add("1");
		list.add("2");
		for (String item : list) {
			System.out.println(item);
			if ("2".equals(item)) {
				list.remove(item);
			}
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate.parse("", formatter);

	}

}
