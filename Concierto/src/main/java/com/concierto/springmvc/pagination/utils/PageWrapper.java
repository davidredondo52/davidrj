package com.concierto.springmvc.pagination.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class PageWrapper<T> implements Page<T> {

	public static final int MAX_PAGE_ITEM_DISPLAY = 10;
	private Page<T> page;
	private List<PageItem> items;
	private int currentNumber;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PageWrapper(Page<T> page, String url) {
		this.page = page;
		this.url = url;
		items = new ArrayList<PageItem>();

		currentNumber = page.getNumber() + 1;

		int start, size;
		if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
			start = 1;
			size = page.getTotalPages();
		} else {
			if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
				start = 1;
				size = MAX_PAGE_ITEM_DISPLAY;
			} else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
				start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
				size = MAX_PAGE_ITEM_DISPLAY;
			} else {
				start = currentNumber - MAX_PAGE_ITEM_DISPLAY / 2;
				size = MAX_PAGE_ITEM_DISPLAY;
			}
		}

		for (int i = 0; i < size; i++) {
			items.add(new PageItem(start + i, (start + i) == currentNumber));
		}
	}

	public List<PageItem> getItems() {
		return items;
	}

	public int getNumber() {
		return currentNumber;
	}

	public List<T> getContent() {
		return page.getContent();
	}

	public int getSize() {
		return page.getSize();
	}

	public int getTotalPages() {
		return page.getTotalPages();
	}

	public boolean isFirstPage() {
		if (getCurrentNumber() == 1)
			return true;
		else
			return false;

	}

	public boolean isLastPage() {
		if (getCurrentNumber() == getTotalPages())
			return true;
		else
			return false;

	}

	public class PageItem {
		private int number;
		private boolean current;

		public PageItem(int number, boolean current) {
			this.number = number;
			this.current = current;
		}

		public int getNumber() {
			return this.number;
		}

		public boolean isCurrent() {
			return this.current;
		}
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public int getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}

	public static int getMaxPageItemDisplay() {
		return MAX_PAGE_ITEM_DISPLAY;
	}

	public void setItems(List<PageItem> items) {
		this.items = items;
	}

	@Override
	public int getNumberOfElements() {
		return page.getNumberOfElements();
	}

	@Override
	public long getTotalElements() {

		return page.getTotalElements();
	}

	@Override
	public boolean hasPreviousPage() {
		if (getCurrentNumber() > 1 && getSize() > 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean hasNextPage() {
		System.out.println(
				"getCurrentNumber()" + getCurrentNumber() + "< totalPages" + getTotalPages() + "> getSize" + getSize());
		if (getCurrentNumber() < getTotalPages() && getSize() > 1)
			return true;
		else
			return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasContent() {
		return page.hasContent();
	}

	@Override
	public Sort getSort() {
		return page.getSort();
	}

	public boolean isNextPage() {

		return hasNextPage();
	}

	public boolean isPreviousPage() {
		return hasPreviousPage();
	}
}