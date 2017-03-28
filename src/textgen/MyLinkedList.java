package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		if (element == null){
			return false;
		}
		if (size == Integer.MAX_VALUE) {
			return false;
		}
		
		LLNode<E> addedNode = new LLNode<E>(element);
		addedNode.next = tail.prev.next;
		addedNode.prev = tail.prev;
		tail.prev = addedNode;
		tail.prev.next = addedNode;
		size += 1;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{		
		return this.getNodeAtIndex(index).data;
	}
	
	public LLNode<E> getNodeAtIndex(int index) {
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> node = head.next;
			
		for ( int i = 0; i < index; i++ ) {
		node = node.next;
		}
		
		return node;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (index == size) {
			
			this.add(element);
			
		} else if (index > size){
			
			throw new IndexOutOfBoundsException();
			
		} else {
			
			LLNode<E> nodeAtIndex = this.getNodeAtIndex(index);
			LLNode<E> newNode = new LLNode<E>(element);
			
			nodeAtIndex.prev.next = newNode;
			nodeAtIndex.prev = newNode;
			size++;
		}
	}

	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> nodeToRemove = this.getNodeAtIndex(index);
		nodeToRemove.prev.next = nodeToRemove.next;
		nodeToRemove.next.prev = nodeToRemove.prev;
		size++;
		
		return nodeToRemove.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
		LLNode<E> nodeAtIndex = getNodeAtIndex(index);
		E oldValue = nodeAtIndex.data;
		nodeAtIndex.data = element;
		
		return oldValue;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e, LLNode<E> prev, LLNode<E> next) {
		this.data = e;
		this.prev = prev;
		this.next = next;
	}
}
