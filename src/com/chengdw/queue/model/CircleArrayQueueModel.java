package com.chengdw.queue.model;

public class CircleArrayQueueModel {
	private int maxSize;
	private int front;
	private int rear;
	private int[] arr;

	/**
	 * 构造
	 */
	public CircleArrayQueueModel(int arrMaxSzie) {
		// 当前队列的最大值
		maxSize = arrMaxSzie;
		// 队首的位置，环形队列定义变更，front为队首元素的位置
		int front = 0;
		// 队尾的位置，环形队列定义变更，rear为队尾的元素位置+1，空出的这一个作为约定
		int rear = 0;
		arr = new int[maxSize];
	}

	/**
	 * 队列是否满
	 */
	public boolean isFull() {
		// 队尾元素除以最大值，取余，元素位置==队首元素位置，则队列为满
		return (rear + 1) % maxSize == front;
	}

	/**
	 * 是否是空
	 */
	public boolean isEmpty() {
		return rear == front;
	}

	/**
	 * 新增元素
	 */
	public void addQueue(int n) {
		// 判断队列中是否有空余位置可以添加元素
		if (isFull()) {
			System.out.println("当前队列已满，无法添加～～");
			return;
		}
		// 因为队尾的位置rear已经加一，所以直接添加就可以
		arr[rear] = n;
		// 队尾的位置向后移动一位，但是不可以直接后移，这是一个环形队列，直接后移一位，会造成越界
		rear = (rear + 1) % maxSize;
	}

	/**
	 * 取出数据
	 */
	public int getQueue() {
		// 判断是否为空
		if (isEmpty()) {
			throw new RuntimeException("当前队列为空，不能取出数据～～");
		}
		// 取出数据，如果先后移，则没有机会将指针后移；
		int n = arr[front];
		// 指针后移，因为是环形队列，需要取余运算，否则数组越觉
		front = (front + 1) % maxSize;
		return n;
	}

	/**
	 * 显示队列内所有的数据
	 */
	public void showQueue() {
		// 判断是否为空
		if (isEmpty()) {
			System.out.println("当前队列为空，队列内没有数据~~");
			return;
		}
		// 遍历显示所有数据 i=front,因为环形时，front定义变更,i从下标位置开始，后移size()个
		for (int i = front; i < front + size(); i++) {
			// i可以看作是从front开始，因为是环形的，所以用取模之后的下标取队列中的元素
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	/**
	 * 获取当前队列的大小
	 */
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}

	/**
	 * 显示头元素
	 */
	public void headQueue() {
		if (isEmpty()) {
			System.out.println("当前队列为空～～");
		}
		System.out.printf("当前队列的头元素是：arr[%d]=%d\n", front, arr[front]);
	}

	/**
	 * 显示队尾的元素
	 */
	public void endQueue() {
		if (isEmpty()) {
			System.out.println("当前队列为空～～");
		}
		System.out.printf("当前队列的尾元素是：arr[%d]=%d\n", (front + size() - 1) % maxSize, arr[(front + size() - 1) % maxSize]);
	}
}
