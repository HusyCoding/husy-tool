package cn.husytool.web.api;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @description: 自定义分页对象
 * @author: husy
 * @date 2020/4/30
 */
public class PageEntity<T> implements Serializable {
	private long current;
	private long size;
	private long total;
	private List<T> records;

	public PageEntity() {
		this.records = Collections.emptyList();
		this.total = 0L;
		this.size = 10L;
		this.current = 1L;
	}
	public PageEntity(long current, long size) {
		this(current, size, 0L);
	}

	public PageEntity(long current, long size, long total) {
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
