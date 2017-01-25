package com.dms.useful;

/**
 * This class defines the minimum and maximum limit of pages to be generated.
 * Maintains a range based on the limit passed in the constructor.
 * 
 * @author Diorgenes Morais
 * @version 1.0.2
 */
public class PagesLimitControl {

	/**
	 * Limit maximum pages
	 */
	public static final int LIMIT_MAX_PAGE = 5;
	private final int limitMax;
	private int first;
	private int last;

	/**
	 * Configures the default constructor with
	 * {@code PagesLimitControl#LIMIT_MAX_PAGE}
	 * 
	 * @param current
	 *            value of page.
	 * @param totalPages
	 *            page total to generate.
	 * 
	 */
	public PagesLimitControl(int current, int totalPages) {
		this(current, totalPages, PagesLimitControl.LIMIT_MAX_PAGE);
	}

	/**
	 * Pattern constructor
	 * 
	 * @param current
	 *            value of page
	 * @param totalPages
	 *            page total to generate.
	 * @param limitMaximum
	 *            set the maximum pages limit
	 */
	public PagesLimitControl(int current, int totalPages, final int limitMaximum) {
		this.limitMax = limitMaximum;
		int max = generateMaximumNumberOfPages(current, totalPages, this.limitMax);
		this.last = Math.min(totalPages, max);
		this.first = generateMinimumNumberOfPages(max, this.limitMax);
	}

	private int generateMaximumNumberOfPages(int current, int totalPages, int numberOfLastPage) {
		if (current < numberOfLastPage) {
			return numberOfLastPage;
		} else {
			return generateMaximumNumberOfPages(current, totalPages, numberOfLastPage += this.limitMax);
		}
	}

	private int generateMinimumNumberOfPages(final int last, int limitMax) {
		return (last <= limitMax) ? 1 : (last - limitMax) + 1;
	}

	public int getFirst() {
		return first;
	}

	public int getLast() {
		return last;
	}

}
