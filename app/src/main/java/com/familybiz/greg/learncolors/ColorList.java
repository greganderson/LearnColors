package com.familybiz.greg.learncolors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A random list of colors that you can select from over and over again.  Once a color has been selected,
 * it won't be selected again until every other color in the list has been selected.  Once every
 * color has been selected at least once, the process starts over again.
 */
public class ColorList {

	private int[] colorList;
	private List<Integer> colors;
	private Random rand;
	private int lastColorSeen;

	/**
	 * Takes an array of Colors to use.
	 */
	public ColorList(int[] colorList) {
		this.colorList = colorList;
		colors = new ArrayList<Integer>();
		rand = new Random();
		lastColorSeen = -1;
		resetColors();
	}

	/**
	 * Returns the next random color.
	 */
	public int nextColor() {
		if (colors.size() == 0)
			resetColors();

		// Make sure the color selected wasn't the last color seen (in the case of a reset)
		int next = lastColorSeen;
		while (next == lastColorSeen)
			next = colors.get(rand.nextInt(colors.size()));
		lastColorSeen = next;

		// Find the color that was selected and remove it.  Would just use list.remove, but
		// since it is a list of ints, that doesn't work out so well.
		for (int i = 0; i < colors.size(); i++) {
			if (colors.get(i) == next) {
				colors.remove(i);
				break;
			}
		}
		return next;
	}

	/**
	 * Adds all of the colors in the color array to the color List.
	 */
	private void resetColors() {
		for (Integer color : colorList)
			colors.add(color);
	}
}
