package com.chengdw.linkedlist;

/**
 * 约瑟夫问题
 * 
 * @author dawei
 *
 */
public class Josephus {

	public static void main(String[] args) {
		// 测试构建环形链表
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(5);
		circleSingleLinkedList.showList();
	}

}

/**
 * 创建单项环形链表类
 * 
 * @author dawei
 *
 */
class CircleSingleLinkedList {
	// 创建fist节点
	private Boy first = new Boy(-1);

	// 添加节点(添加几个节点)
	public void addBoy(int num) {
		// 对入参校验，这里可做优化，要求至少要有两个节点
		if (num < 1) {
			System.out.println("num的值不正确");
			return;
		}
		// 创建辅助指针，帮助循环链表
		Boy curBoy = null;
		// 使用循环创建环形链表
		for (int i = 1; i <= num; i++) {
			Boy boy = new Boy(i);
			// 如果是第一个节点，要单独处理 first-->boy(1)
			if (i == 1) {
				first = boy;
				// 创建环形
				first.setNext(boy);
				// 让辅助指针指向第一个
				curBoy = first;
			} else {
				// 让当前节点的下一个指向新建节点
				curBoy.setNext(boy);
				// 让新建节点的下一个指向第一个，形成环状链表
				boy.setNext(first);
				// 移动辅助指针
				curBoy = boy;
			}
		}
	}

	// 遍历当前环形链表
	public void showList() {
		// 判断链表是否为空
		if (first == null) {
			System.out.println("链表为空～");
			return;
		}
		// 链表不为空，创建辅助指针遍历，first指针不能动，已经存在链表了，直接把可以移动的辅助指针指向第一个就可以
		Boy curBoy = first;
		while (true) {
			System.out.printf("当前节点的编号是：%d\n", curBoy.getNo());
			if (curBoy.getNext() == first) {
				// 若辅助指针的下一个节点是第一个，那么遍历完成，结束循环
				break;
			}
			// 若没有遍历完成，辅助指针后移
			curBoy = curBoy.getNext();
		}
	}

	// 根据用户输入，输出节点出链表的顺序（从哪个节点开始，每隔几个节点出圈，一共有多少个节点（用作校验））
	public void count(int startNo, int countNo, int sumNo) {
		// 校验
		if (first == null || startNo < 1 || startNo > sumNo) {
			System.out.println("输入的参数有误～");
			return;
		}
		// 创建辅助指针，帮助节点出圈
		Boy helperBoy = first;
		while(true) {
			if(helperBoy.getNext() == first) {
				// 如果辅助指针指向了第一个节点，那么链表遍历结束
				break;
			}
			// 否则，指针后移
			helperBoy = helperBoy.getNext();
		}
		
	}
}

/**
 * 创建节点
 * 
 * @author dawei
 *
 */
class Boy {
	// 编号
	private int no;
	// 下一个节点
	private Boy next;

	public Boy(int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}

}