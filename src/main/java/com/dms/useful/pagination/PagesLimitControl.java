package com.dms.useful.pagination;

import com.dms.exceptions.ValidateException;

/**
 * This class defines the minimum and maximum limit of pages to be generated.
 * Maintains a range based on the limit passed in the constructor.
 * 
 * @author Diorgenes Morais
 * @version 1.0.4
 */
public class PagesLimitControl {

	/**
	 * Value pattern to be used.
	 */
	public static final int LIMIT_MAX_PAGE = 5;
	private int first;
	private int last;

	/**
	 * Configures the default constructor with
	 * {@link PagesLimitControl#LIMIT_MAX_PAGE}
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
		int max = generateMaximumNumberOfPages(current, limitMaximum, limitMaximum);
		this.last = Math.min(totalPages, max);
		this.first = generateMinimumNumberOfPages(max, limitMaximum);
	}

	private int generateMaximumNumberOfPages(final int current, int numberOfLastPage, final int limitMaximum) {
		while (current >= numberOfLastPage) {
			numberOfLastPage += limitMaximum;
		}
		return numberOfLastPage;
	}

	private int generateMinimumNumberOfPages(final int max, final int limitMaximum) {
		return (max <= limitMaximum) ? 1 : (max - limitMaximum) + 1;
	}

	public int getFirst() {
		return first;
	}

	public int getLast() {
		return last;
	}

}
