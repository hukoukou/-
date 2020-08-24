package com.chengdw.stack;

import java.util.Scanner;

/**
 * 栈
 * 也可以使用链表来实现：可以按照栈的方式移动辅助指针
 * @author dawei
 *
 */
public class ArrayStackDemo {
	public static void main(String[] args) {
		// 创建栈
		ArrayStack arrayStack = new ArrayStack(4);
		String key = "";
		// 控制台输入
		Scanner scanner = new Scanner(System.in);
		// 控制控制台是否退出
		boolean loop = true;
		while (loop) {
			System.out.println("show：展示栈");
			System.out.println("exit：退出");
			System.out.println("push：入栈");
			System.out.println("pop：出栈");
			System.out.print("请输入你的选择：");
			key = scanner.next();
			switch (key) {
			case "show":
				arrayStack.showStack();
				break;
			case "exit":
				scanner.close();
				loop = false;
				System.out.println("已退出");
				break;
			case "push":
				System.out.print("请输入入栈的数据：");
				int value = scanner.nextInt();
				arrayStack.push(value);
				break;
			case "pop":
				try {
					int pop = arrayStack.pop();
					System.out.printf("出栈的数据是：%d\n", pop);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	}
}

class ArrayStack {
	// 栈顶
	private int top = -1;
	// 栈的大小
	private int maxSize;
	// 容器(数组模拟)
	private int[] stack;

	// 构造方法
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		// 初始化数组
		stack = new int[this.maxSize];
	}

	// 栈满
	public boolean isFull() {
		return this.top == this.maxSize - 1;
	}

	// 栈空
	public boolean isEmpty() {
		return this.top == -1;
	}

	// 入栈
	public void push(int value) {
		// 判断栈是否满
		if (isFull()) {
			System.out.println("这个栈满了～");
			return;
		}
		// 入栈
		top++;
		stack[top] = value;
	}

	// 出栈
	public int pop() {
		// 判断是否空
		if (isEmpty()) {
			throw new RuntimeException("栈空了，不能出栈～");
		}
		int value = stack[top];
		top--;
		return value;
	}

	// 遍历栈
	public void showStack() {
		// 判断栈是否为空
		if (isEmpty()) {
			System.out.println("当前栈为空～");
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("当前元素stack[%d]为：%d\n", i, stack[i]);
		}
	}
}