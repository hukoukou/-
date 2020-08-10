package com.chengdw.linkedlist;

import java.util.Stack;

/**
 * 演示栈的使用
 * @author dawei
 *
 */
public class TestStack {
	public static void main(String[] args) {
		//创建栈
		Stack<String> stack = new Stack();
		//入栈
		stack.add("S1");
		stack.add("S2");
		stack.add("S3");
		stack.add("S4");
		System.out.println(stack);
		//出栈
		while(stack.size()>0) {
			System.out.println(stack.pop());
		}
		System.out.println(stack);
	}
}
