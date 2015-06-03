package au.com.sportsbet.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CollectionUtilsTest {

	@Test
	public void testIsEmpty() {
		assertTrue(CollectionUtils.isEmpty(null));
		assertTrue(CollectionUtils.isEmpty(new ArrayList<String>()));
		assertTrue(CollectionUtils.isEmpty(new ArrayList<Object>()));
		assertTrue(CollectionUtils.isEmpty(new HashSet()));
	}
	
	@Test
	public void testIsNotEmpty() {
		assertFalse(CollectionUtils.isNotEmpty(null));
		List<String> strings = new ArrayList<String>();
		strings.add("abc");
		assertTrue(CollectionUtils.isNotEmpty(strings));
		List<Object> objects = new ArrayList<Object>();
		objects.add(new Object());
		assertTrue(CollectionUtils.isNotEmpty(objects));
		Set objectSet = new HashSet();
		objectSet.add(objects);
		assertTrue(CollectionUtils.isNotEmpty(objectSet));
	}
}
