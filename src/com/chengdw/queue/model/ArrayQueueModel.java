package com.chengdw.queue.model;

/**
 * 当前存在问题，取出去之后，空的位置就不能再用了，使用环形队列
 * @author dawei
 *
 */
public class ArrayQueueModel {
	private int maxSize;
	private int front;
	private int rear;
	private int[] arr;

	/**
	 * 创建构造器
	 */
	public ArrayQueueModel(int arrMaxSize) {
		// 初始化最大容量
		maxSize = arrMaxSize;
		// 初始化队首指针，当为空时，指针在下标0的前一个，也就是负一
		front = -1;
		// 初始化队尾指针，同队首指针
		rear = -1;
		// 初始化容器
		arr = new int[maxSize];
	}

	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return rear == front;
	}

	/**
	 * 判断是否为满
	 */
	public boolean isFull() {
		//最大值-1表示下标
		return rear == maxSize - 1;
	}

	/**
	 * 添加数据，入队列
	 */
	public void addQueue(int n) {
		// 判断是否满
		if (isFull()) {
			System.out.println("当前队列已满，无法再添加！");
			return;
		}
		// 队尾指针后移，存入数据
		rear++;
		arr[rear] = n;
	}

	/**
	 * 获取队列数据，出队列
	 */
	public int getQueue() {
		// 判断是否为空
		if (isEmpty()) {
			// 抛出异常
			throw new RuntimeException("队列为空，不能取数据");
		}
		// 队首指针++，下次获取只能获取之后的数据
		return arr[++front];
	}

	/**
	 * 遍历数组，展示所有数据
	 */
	public void showQueue() {
		if (isEmpty()) {
			System.out.println("当前数组为空。");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i, arr[i]);
		}
	}

	/**
	 * 显示头数据，不是拿数据
	 */
	public int headQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空，没有数据～～");
		}
		return arr[front + 1];
	}

	/**
	 * 显示尾数据
	 */
	public int endQueue() {
		if (isEmpty()) {
			throw new RuntimeException("当前队列为空，没有数据～～");
		}
		return arr[rear];
	}
}
