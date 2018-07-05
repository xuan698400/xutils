package com.xuan.xutils.utils;

import org.junit.*;

import java.util.*;

public class CollectionUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsEmptyCollection() {
		Assert.assertTrue(CollectionUtils.isEmpty(new ArrayList<>()));
		Assert.assertTrue(CollectionUtils.isEmpty(Collections.emptyList()));
		Assert.assertTrue(CollectionUtils.isEmpty(EnumSet.noneOf(TestEnum.class)));
		
		Assert.assertFalse(CollectionUtils.isEmpty(EnumSet.of(TestEnum.A)));
		Assert.assertFalse(CollectionUtils.isEmpty(EnumSet.of(TestEnum.A,TestEnum.B)));
	}
	
	private static enum TestEnum{
		A,B,C,D
	}

	@Test
	public void testNotEmptyCollection() {
		Assert.assertTrue(CollectionUtils.notEmpty(EnumSet.of(TestEnum.A)));
		Assert.assertTrue(CollectionUtils.notEmpty(EnumSet.of(TestEnum.A,TestEnum.B)));
		
		Assert.assertFalse(CollectionUtils.notEmpty(new ArrayList<>()));
		Assert.assertFalse(CollectionUtils.notEmpty(Collections.emptyList()));
		Assert.assertFalse(CollectionUtils.notEmpty(EnumSet.noneOf(TestEnum.class)));
	}
	
	@Test
	public void testIsEmptyMap() {
		Assert.assertTrue(CollectionUtils.isEmpty(new HashMap<>()));
		Assert.assertTrue(CollectionUtils.isEmpty(Collections.emptyMap()));
		Assert.assertTrue(CollectionUtils.isEmpty(new EnumMap<>(TestEnum.class)));
		
		Map<Integer, Integer> testMap=new HashMap<>();
		for(int i=0;i<10;i++){
			testMap.put(i, i<<31);
		}
		Assert.assertFalse(CollectionUtils.isEmpty(testMap));
	}
	
	@Test
	public void testNotEmptyMap() {
		Map<Integer, Integer> testMap=new HashMap<>();
		for(int i=0;i<10;i++){
			testMap.put(i, i<<31);
		}
		Assert.assertTrue(CollectionUtils.notEmpty(testMap));
		
		Assert.assertFalse(CollectionUtils.notEmpty(new HashMap<>()));
		Assert.assertFalse(CollectionUtils.notEmpty(Collections.emptyMap()));
		Assert.assertFalse(CollectionUtils.notEmpty(new EnumMap<>(TestEnum.class)));
	}
	
	@Test
	public void testContainsMapObjects() {
		Map<Integer, Integer> testMap=new HashMap<>();
		List<Integer> list=new ArrayList<>();
		for(int i=0;i<10;i++){
			testMap.put(i, i<<31);
			list.add(i);
			Assert.assertTrue(CollectionUtils.contains(testMap, list.toArray()));
			Assert.assertFalse(CollectionUtils.contains(testMap, Arrays.asList(i, i+1)));
		}
	}
	
	@Test
	public void testNotContainsMapObjects() {
		Map<Integer, Integer> testMap=new HashMap<>();
		List<Integer> list=new ArrayList<>();
		for(int i=0;i<10;i++){
			testMap.put(i, i<<31);
			list.add(i);
			Assert.assertFalse(CollectionUtils.notContains(testMap, list.toArray()));
			Assert.assertTrue(CollectionUtils.notContains(testMap, Arrays.asList(i, i+1)));
		}
	}
	
	@Test
	public void testHaveRepeatCollection() {
		List<Integer> list=new ArrayList<>();
		for(int i=0;i<10;i++){
			list.add(i);
			list.add(10-i);
		}
		Assert.assertTrue(CollectionUtils.haveRepeat(list));
		Assert.assertFalse(CollectionUtils.haveRepeat(new HashSet<>(list)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHaveRepeatCollectionIllegalArgunmentException() {
		CollectionUtils.haveRepeat(null);
	}
	
	@Test
	public void testNotHaveRepeatCollection() {
		List<Integer> list=new ArrayList<>();
		for(int i=0;i<10;i++){
			list.add(i);
			list.add(10-i-1);
		}
		Assert.assertFalse(CollectionUtils.notHaveRepeat(list));
		Assert.assertTrue(CollectionUtils.notHaveRepeat(new HashSet<>(list)));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNotHaveRepeatCollectionIllegalArgunmentException() {
		CollectionUtils.notHaveRepeat(null);
	}
	
	@Test
	public void testDeleteRepeatCollection() {
		Assert.assertEquals(Arrays.asList(1,2,3,4,5,6), CollectionUtils.deleteRepeat(Arrays.asList(1,2,2,3,4,3,4,5,6,6,5)));
		Assert.assertNotEquals(Arrays.asList(1,2,2,3,4,3,4,5,6,6,5), CollectionUtils.deleteRepeat(Arrays.asList(1,2,2,3,4,3,4,5,6,6,5)));
	}
	
	@Test
	public void testCollectionToEnum() {
		List<String> list=new ArrayList<>();
		List<TestEnum> enums=new ArrayList<>();
		for(TestEnum testEnum:TestEnum.values()){
			list.add(testEnum.toString());
			enums.add(testEnum);
		}
		Assert.assertNotEquals(list, enums);
		Assert.assertEquals(CollectionUtils.collectionToEnum(list, TestEnum.class), enums);
	}
	
	@Test
	public void testSumToLong() {
		List<Long> list=new ArrayList<>();
		long sum=0;
		for(long i=0;i<10;i++){
			list.add(i);
			sum+=i;
		}
		Assert.assertSame(sum, CollectionUtils.sumToLong(list, l->l));
	}
}
