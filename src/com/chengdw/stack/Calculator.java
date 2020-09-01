package com.chengdw.stack;

/**
 * 使用栈实现输入文本格式的计算时的数据计算 当前算法存在问题： 1.不能进行多位数的运算（可以在取数时，对下一位进行判断，判断是否时运算符）
 * 2.不能进行负数运算
 * 
 * @author dawei
 *
 */
public class Calculator {
	public static void main(String[] args) {
		// 创建需要计算的式子
		String exp = "700+2*6-4";
		// 创建符号栈和数字栈
		ArrayStackComp operStack = new ArrayStackComp(10);
		ArrayStackComp numStack = new ArrayStackComp(10);
		// 初始化计算需要的变量
		int index = 0;
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		// 将扫描得到的char先存放到ch中
		char ch = ' ';
		// 用于拼接多位数
		String keepNmu = "";
		// 循环遍历
		while (true) {
			ch = exp.subSequence(index, index + 1).charAt(0);
			// 判断是否是运算符
			if (operStack.isOper(ch)) {
				// 若是运算符，判断当前栈是否为空
				if (operStack.isEmpty()) {
					// 若为空，直接入栈
					operStack.push(ch);
				} else {
					// 若不为空，判断栈顶的运算符的优先级是否小于等于准备入栈的运算符，若小于等于，则数据栈弹出两个数据，符合栈弹出两个数据进行计算，计算结果push到数据栈
					if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.comp(num1, num2, oper);
						// 计算结果入数据栈
						numStack.push(res);
						// 新增的符号入符号栈
						operStack.push(ch);
					} else {
						// 如果当前运算符号的优先级大于栈顶运算符
						operStack.push(ch);
					}
				}
			} else {
				// 若不是运算符，直接入栈，注意，要将‘1’=》1，将字符1，转化为数字1，ASCII表对照，相差48
				// numStack.push(ch - 48);
				// 若存在多位数，则出现问题
				// 优化思路：
				// 1.当发现是数字时，不能直接入栈，要进行判断
				// 2.在处理数字时，需要想exp多看一位，如果时数，则继续扫描，如果时符合位，才停止
				// 3.需要定义一个变量，用于拼接
				keepNmu += ch;
				// 如果ch是最后一个时，直接入栈
				if (index == exp.length() - 1) {
					numStack.push(Integer.parseInt(keepNmu));
				} else {
					// 判断下一位是否时数字
					// 如果时数字，则继续扫描，如果时符号，则入数据栈
					if (operStack.isOper(exp.subSequence(index + 1, index + 2).charAt(0))) {
						// 如果后一位运算符，则入栈
						numStack.push(Integer.parseInt(keepNmu));
						// 重要，结束后需要将keepNum清空
						keepNmu = "";
					}
				}
			}
			// index+1，判断是否扫描到exp的最后
			index++;
			if (index >= exp.length()) {
				break;
			}
		}
		// 当表达式扫描结束，按照顺序pop出数字和符号，进行计算
		while (true) {
			// 若符号栈为空，则循环结束
			if (operStack.isEmpty()) {
				break;
			}
			// 计算
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.comp(num1, num2, oper);
			numStack.push(res);
		}
		// 输出打印
		System.out.printf("表达式：%s = %d", exp, numStack.pop());
	}
}

// 创建栈
class ArrayStackComp {
	// 最大容量
	private int maxSize;
	// 容器
	private int[] stack;
	// 栈顶
	private int top = -1;

	// 构造器
	public ArrayStackComp(int maxSiez) {
		this.maxSize = maxSiez;
		stack = new int[this.maxSize];
	}

	// 栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// 栈空
	public boolean isEmpty() {
		return top == -1;
	}

	// 入栈
	public void push(int value) {
		// 判断是否栈满
		if (isFull()) {
			System.out.println("当前栈满～");
			return;
		}
		top++;
		stack[top] = value;
	}

	// 出栈
	public int pop() {
		// 判断是否栈空
		if (isEmpty()) {
			throw new RuntimeException("当前栈空～");
		}
		int value = stack[top];
		top--;
		return value;
	}

	// 遍历
	public void showStack() {
		if (isEmpty()) {
			System.out.println("当前栈空，不能遍历～");
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("当前元素stack[%d]为：%d\n", i, stack[i]);
		}
	}

	// 判断当前如餐是否时一个运算符合
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}

	// 检查操作符的优先级，右键级由程序确定
	// 设定：数字越大，优先级越高，且当前只有加减乘除四个运算符
	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1;
		}
	}

	// 计算
	// 注意栈的弹出方式，存在减数与被减数，除数与被除数的问题，注意顺序
	public int comp(int num1, int num2, int oper) {
		// 初始化
		int res = 0;
		switch (oper) {
		case '+':
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1;
			break;
		case '*':
			res = num1 * num2;
			break;
		case '/':
			res = num2 / num1;
			break;
		default:
			break;
		}
		return res;
	}

	// 查看当前栈顶的值，但是不弹出栈
	public int peek() {
		return stack[top];
	}
}
