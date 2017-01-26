package com.dms.useful.pagination;

import com.dms.exceptions.ValidateException;

/**
 * This class defines the minimum and maximum limit of pages to be generated.
 * Maintains a range based on the limit passed in the constructor.
 * 
 * @author Diorgenes Morais
 * @version 1.0.3
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
	 * @throws ValidateException
	 *             If the values passed in the parameters are not valid
	 * @see PagesLimitControl#LIMIT_MAX_PAGE
	 */
	public PagesLimitControl(int current, int totalPages) throws ValidateException {
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
	 * @throws ValidateException
	 *             If the values passed in the parameters are not valid
	 */
	public PagesLimitControl(int current, int totalPages, final int limitMaximum) throws ValidateException {
		if (current >= totalPages || totalPages < 1 || limitMaximum < 1) {
			throw new ValidateException("The values passed in the parameters are not valid");
		}
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
