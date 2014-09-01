package com.familybiz.greg.learncolors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A random list of items that you can select from over and over again.  Once an item has been selected,
 * it won't be selected again until every other item in the list has been selected.  Once every
 * item has been selected at least once, the process starts over again.
 */
public class RandomItemList<E> {

	private E[] itemList;
	private List<E> items;
	private Random rand;
	private E lastItemSeen;

	/**
	 * Takes an array of <E> to use.
	 */
	public RandomItemList(E[] itemList) {
		this.itemList = itemList;
		items = new ArrayList<E>();
		rand = new Random();
		lastItemSeen = null;
		resetItems();
	}

	/**
	 * Returns the next random item.
	 */
	public E nextItem() {
		if (items.size() == 0)
			resetItems();

		// Make sure the item selected wasn't the last item seen (in the case of a reset)
		E next = lastItemSeen;
		while (next == lastItemSeen)
			next = items.get(rand.nextInt(items.size()));
		lastItemSeen = next;

		items.remove(lastItemSeen);

		return next;
	}

	/**
	 * Adds all of the items in the item array to the item List.
	 */
	private void resetItems() {
		for (int i = 0; i < itemList.length; i++)
			items.add(itemList[i]);
	}
}
