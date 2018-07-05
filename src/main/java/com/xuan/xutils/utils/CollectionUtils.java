package com.xuan.xutils.utils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java集合内置类库工具类
 * @author kotomiko
 * 
 * @see Collection
 * @see List
 * @see Set
 * @see ArrayList
 * @see LinkedList
 * @see TreeSet
 * @see HashSet
 * @see EnumSet
 * @see Map
 * @see HashMap
 * @see TreeMap
 * @see ConcurrentMap
 * @see ConcurrentHashMap
 */
public final class CollectionUtils {
	
	/**
	 * 检测集合中是否不包含任何元素
	 * @param collection 集合实现对象
	 * @return true 集合中不包含任何元素或是集合为{@code null},false 集合中包含元素
	 * @see #isEmpty(Map)
	 */
	public static boolean isEmpty(Collection<?> collection){
		return collection==null||collection.isEmpty();
	}
	
	/**
	 * 检测集合中是否包含元素
	 * @param collection 集合实现对象
	 * @return true集合中包含元素,false 集合中不包含任何元素或是集合为{@code null}
	 */
	public static boolean notEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	
	/**
	 * 检测map映射中是否不包含任何元素
	 * @param map map映射实现类
	 * @return true map中不包含任何元素或是map为{@code null},false map中包含元素
	 * @see #isEmpty(Collection)
	 */
	public static boolean isEmpty(Map<?, ?> map){
		return map==null||map.isEmpty();
	}
	
	/**
	 * 检测map映射中是否包含元素
	 * @param map map映射实现类
	 * @return true map中包含元素,false map中不包含任何元素或是map为{@code null}
	 */
	public static boolean notEmpty(Map<?, ?> map){
		return !isEmpty(map);
	}
	
	/**
	 * 检测map中是否包含所有的key
	 * @param map map
	 * @param objects 多个键
	 * @return true 全部包含,false 至少存在一个不包含
	 */
	public static boolean contains(Map<?, ?> map, Object...objects){
		return Stream.of(objects).allMatch(e->map.containsKey(e));
	}
	
	/**
	 * 检测map中是否至少有一个键不存在
	 * @param map map
	 * @param objects 多个键
	 * @return true 至少存在一个不包含,false 全部包含
	 */
	public static boolean notContains(Map<?, ?> map, Object...objects){
		return !contains(map, objects);
	}
	
	/**
	 * 快捷创建一个map,赋值由用户自定义
	 * @param mapper 赋值操作
	 * @return map实例
	 */
	public static<K, V> Map<K, V> createMap(Consumer<Map<K, V>> mapper){
		Map<K, V> map=new HashMap<>();
		mapper.accept(map);
		return map;
	}
	
	/**
	 * 将给定数组转换为相应的集合实体
	 * @param t 数组
	 * @return 集合框架的容器表现形式
	 * @throws NullPointerException 如果参数为{@code null}
	 */
	public static<T> List<T> toList(@SuppressWarnings("unchecked") T... t){
		return Arrays.asList(t);
	}
	
	/**
	 * 检查集合中是否拥有重复元素,重复的判定为值相同或{@code Object#equals(Object)}满足条件
	 * @param collection 需要被检查的集合列表
	 * @return true 集合拥有重复的元素,false 集合中的元素都是唯一的
	 */
	public static boolean haveRepeat(final Collection<?> collection){
		return !notHaveRepeat(collection);
	}
	
	/**
	 * 检查集合中是否每个元素都是唯一的,对于是否唯一的判定为值相同或{@code Object#equals(Object)}满足条件
	 * @param collection 需要被检查的集合列表
	 * @return true 集合没有重复的元素,false 集合中有重复的元素
	 */
	public static boolean notHaveRepeat(final Collection<?> collection){
		return collection.stream().allMatch(new HashSet<>()::add);
	}
	
	/**
	 * 去除集合中的重复元素,判断是否重复遵守{@code Object#hashCode()}与{@code Object#equals(Object)}规则
	 * 不会操作原始列表,而是生成一个新的集合返回
	 * @param collection 集合
	 * @return 每个元素都唯一的新的集合
	 */
	public static<E> List<E> deleteRepeat(final Collection<E> collection){
		if(isEmpty(collection)){
			return Collections.emptyList();
		}
		return collection.stream().sequential().distinct().collect(Collectors.toList());
	}
	
	/**
	 * 将指定的集合列表转换成枚举列表,基于{@link Enum#valueOf(Class, String)}
	 * @param collection 原始列表
	 * @param enumType 希望转换的枚举类型
	 * @return 转换后的枚举列表
	 */
	public static<T extends Enum<T>> List<T> collectionToEnum(Collection<?> collection,Class<T> enumType){
		if(isEmpty(collection)){
			return Collections.emptyList();
		}
		return collection.stream().map(s->Enum.valueOf(enumType, s.toString())).collect(Collectors.toList());
	}

	/**
	 * 计算列表中的和值,计算的因子由用户自定义
	 * 因为列表可能是一个复杂的对象
	 * @param collection 列表
	 * @param longMapper 自定义的计算因子函数
	 * @return 总和值
	 */
	public static<T> long sumToLong(Collection<T> collection, Function<T, Long> longMapper){
		return collection.stream().mapToLong(t->longMapper.apply(t)).sum();
	}
	
	/**
	 * 将指定的Properties合并到参数的Map中,如果Properties中存在{@code null}值
	 * 则可能会被默认值覆盖.该方法以副作用的方式更改Map
	 * @param properties 映射键值对
	 * @param map 将要合并的Map
	 */
	@SuppressWarnings("unchecked")
	public static<K,V> void mergePropertiesIntoMap(Properties properties, Map<K, V> map){
		if(properties!=null){
			for(Enumeration<?> enumeration=properties.propertyNames();enumeration.hasMoreElements();){
				String key=(String)enumeration.nextElement();
				Object value=properties.get(key);
				if(value==null){
					value=properties.getProperty(key);
				}
				map.put((K)key, (V)value);
			}
		}
	}
	
	private CollectionUtils(){}
}
