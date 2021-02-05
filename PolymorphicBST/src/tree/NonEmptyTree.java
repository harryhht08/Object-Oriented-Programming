package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
 public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */

	/**
	 * Only constructor we need.
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) {
		throw new UnsupportedOperationException("You must implement this method.");
	}

	public V search(K key) {
		throw new UnsupportedOperationException("You must implement this method.");
	}
	
	public NonEmptyTree<K, V> insert(K key, V value) {
		throw new UnsupportedOperationException("You must implement this method.");
	}
	
	public Tree<K, V> delete(K key) {
		throw new UnsupportedOperationException("You must implement this method.");
	}

	public K max() {
		throw new UnsupportedOperationException("You must implement this method.");
	}

	public K min() {
		throw new UnsupportedOperationException("You must implement this method.");
	}

	public int size() {
		throw new UnsupportedOperationException("You must implement this method.");
	}

	public void addKeysToCollection(Collection<K> c) {
		throw new UnsupportedOperationException("You must implement this method.");
	}
	
	public Tree<K,V> subTree(K fromKey, K toKey) {
		throw new UnsupportedOperationException("You must implement this method.");
	}
	
	public int height() {
		throw new UnsupportedOperationException("You must implement this method.");
	}
	
	public void inorderTraversal(TraversalTask<K,V> p) {
		throw new UnsupportedOperationException("You must implement this method.");
	}
	
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {
		throw new UnsupportedOperationException("You must implement this method.");
	}	
}