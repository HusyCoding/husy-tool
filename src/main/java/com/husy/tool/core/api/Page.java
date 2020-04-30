package com.husy.tool.core.api;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @description: 分页对象
 * @author: husy
 * @date 2020/4/30
 */
public class Page<T> implements Serializable {
	private List<T> records;
	private long total;
	private long size;
	private long current;

	public Page() {
		this.records = Collections.emptyList();
		this.total = 0L;
		this.size = 10L;
		this.current = 1L;
	}
	public Page(long current, long size) {
		this(current, size, 0L);
	}

	public Page(long current, long size, long total) {
		this.records = Collections.emptyList();
		this.total = 0L;
		this.size = 10L;
		this.current = 1L;
	}
	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}
}
