package com.chengdw.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式实现计算
 * 
 * @author dawei
 *
 */
public class PolandNotaction {

	public static void main(String[] args) {
		// 定义给出逆波兰表达式(30+4)*5-6 --> 30 4 + 5 * 6 -
		String suffixExp = "30 4 + 5 * 6 -";
		// 将表达式方式到list中，遍历，配合栈，完成计算
		List<String> listString = getListString(suffixExp);
		// 计算
		int calculate = calculate(listString);
		System.out.printf("逆波兰表达式：" + suffixExp + "的计算结果是  %d\n", calculate);
	}

	// 将逆波兰表达式依次放入到list中
	public static List<String> getListString(String suffixExp) {
		String[] split = suffixExp.split(" ");
		ArrayList<String> arrayList = new ArrayList<String>();
		for (String exp : split) {
			arrayList.add(exp);
		}
		return arrayList;
	}

	// 计算
	public static int calculate(List<String> listString) {
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < listString.size(); i++) {
			String string = listString.get(i);
			// 使用正则表达式，判断是否是多位数
			if (string.matches("\\d+")) {
				// 如果是多位数，入栈
				stack.push(string);
			} else {
				// 若不是多位数，弹出两个数，进行计算，结算结果入栈
				int num1 = Integer.parseInt(stack.pop());
				int num2 = Integer.parseInt(stack.pop());
				int res = 0;
				switch (string) {
				case "+":
					res = num1 + num2;
					break;
				case "-":
					res = num2 - num1;
					break;
				case "*":
					res = num2 * num1;
					break;
				case "/":
					res = num2 / num1;
					break;
				default:
					break;
				}
				stack.push(res + "");
			}
		}
		return Integer.parseInt(stack.pop());
	}
}
