package net.gong.com.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionTest {

	/**
	 * @param yushun
	 *            gong
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		Collection coll = new ArrayList();
		System.out.println(coll.size());
		coll.add(123);
		coll.add("ads");
		System.out.println(coll.size());
		System.out.println(coll.isEmpty());
		coll.clear();
		coll.contains("123");

	}

}
