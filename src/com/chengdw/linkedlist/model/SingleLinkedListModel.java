package com.chengdw.linkedlist.model;

import java.awt.HeadlessException;

/**
 * 存放链表
 * 
 * @author dawei
 *
 */
public class SingleLinkedListModel {
	// 初始化一个头节点
	private HeroNodeModel hedaNode = new HeroNodeModel(0, "", "");

	/**
	 * 将节点加入到链表中 当前添加方式没有考虑按照一定顺序添加，只是把所有的新的节点添加到尾部
	 */
	public void add(HeroNodeModel node) {
		// 头节点不能动，动了就找不到这个链表了，因此创建一个辅助节点
		HeroNodeModel temp = hedaNode;
		while (true) {
			// 链表添加数据都是再链表的最后一个位置添加数据，因此检查当前位置是否是最后一个
			if (temp.next == null) {
				break;
			}
			// 若没有找到，将temp后移，节点中有记录下一个节点信息（next）
			temp = temp.next;
		}
		// 若下一个为空，则说明是最后一个，将最后一个指向新的节点。
		temp.next = node;
	}

	/**
	 * 要求： 1.按照顺序添加 2.如果已经存在当前排名了，则添加失败，给出异常提示
	 */
	public void addByOrder(HeroNodeModel node) {
		// 头节点不能动，因此创建一个辅助指针来添加
		HeroNodeModel temp = hedaNode;
		// 循环遍历
		boolean flag = false;
		while (true) {
			// 头节点的下一个为空，表示已经在尾部了，直接添加就好
			// 添加的时候不是在这里添加，而是做一个标记，如果可以添加，直接跳出循环，跳出是temp记录为可以添加的位置
			if (temp.next == null) {
				break;
			}
			// 按照no从小到大排列，从第一个遍历，若下一个大于当前添加的，说明当前就是要插入的位置
			if (temp.next.no > node.no) {
				break;
			} else if (temp.next.no == node.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 当跳出循环后，temp指向的就是要添加的那个位置
		// 判断是否要添加
		if (flag) {
			System.out.printf("当前节点%d已经存在，不能添加～～\n", node.no);
		} else {
			node.next = temp.next;
			temp.next = node;
		}
	}

	/**
	 * 遍历当前链表
	 */
	public void list() {
		// 判断当前链表是否为空，从头开始遍历，如果头节点中下一个节点就是空的，那么这个链表就是空的
		if (hedaNode == null) {
			System.out.println("当前节点为空～～");
			return;
		}
		// 创建辅助指针，将辅助指针指向头节点，随着辅助指针的移动，遍历各个节点
		HeroNodeModel temp = hedaNode;
		while (true) {
			// 如果下一个节点为空，也跳出循环
			if (temp.next == null) {
				break;
			}
			// 因为头节点不存储业务数据，所以从next开始显示内容
			System.out.println(temp.next);
			// 遍历完成后，将指针后移，否则会死循环
			temp = temp.next;
		}
	}
}
