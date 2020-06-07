package com.intsmaze.hbase;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.Lists;

/**
 * HBase表管理器, 对Hbase表的rowkey规则生成,表名规则定义
 * 
 * @author WJ
 *
 */
public class HTableManager {

	public static final byte[] DEFAULT_FAMILY_NAME = "f1".getBytes();

	private static final String[] PARTITIONS = generatPartitionSeed();

	/**
	 * 生成3844个分区种子
	 * 
	 * @return String[]
	 */
	public static String[] generatPartitionSeed() {
		List<Character> seeds = Lists.newArrayList();
		for (int i = '0'; i <= '9'; i++) {
			seeds.add((char) i);
		}
		for (int i = 'A'; i <= 'Z'; i++) {
			seeds.add((char) i);
		}
		for (int i = 'a'; i <= 'z'; i++) {
			seeds.add((char) i);
		}
		int k = 0;
		String[] partions = new String[seeds.size() * seeds.size()];
		for (int i = 0; i < seeds.size(); i++) {
			for (int j = 0; j < seeds.size(); j++) {
				partions[k] = StringUtils.join(seeds.get(i), seeds.get(j));
				k++;
			}
		}
		return partions;
	}

	/**
	 * 按指定数量生成分区种子
	 * 
	 * @param limit
	 * @return String[]
	 */
	public static String[] generatPartitionSeed(int limit) {
		int size = PARTITIONS.length;
		int[] space = new int[limit];
		for (int pt = 0; pt < size;) {
			for (int j = 0; j < space.length; j++) {
				++space[j];
				pt++;
				if (pt == size) {
					break;
				}
			}
		}
		String[] seed = new String[limit + 1];
		int position = 0;
		for (int i = 0; i < space.length; i++) {
			seed[i] = PARTITIONS[position];
			position += space[i];
		}
		seed[seed.length - 1] = PARTITIONS[PARTITIONS.length - 1];
		return seed;
	}

	public static String generatRowkey(String str) {
		int i = Math.abs(str.hashCode() % PARTITIONS.length);
		return StringUtils.join(PARTITIONS[i], "-", str);
	}
	
	public static String generatRowkey2(String str) {
		int i = Math.abs(str.hashCode() % PARTITIONS.length);
		return StringUtils.join(PARTITIONS[i], str);
	}

	public static byte[] generatByteRowkey(String str) {
		int i = Math.abs(str.hashCode() % PARTITIONS.length);
		return StringUtils.join(PARTITIONS[i], "-", str).getBytes();
	}



}