package com.chengdw.linkedlist.model;

import java.util.Stack;

/**
 * 存放链表
 * 
 * @author dawei
 *
 */
public class SingleLinkedListModel {
	// 初始化一个头节点
	private HeroNodeModel hedaNode = new HeroNodeModel(0, "", "");

	public HeroNodeModel getHedaNode() {
		return hedaNode;
	}

	public void setHedaNode(HeroNodeModel hedaNode) {
		this.hedaNode = hedaNode;
	}

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

	/**
	 * 修改节点
	 */
	public void updateLinkedList(HeroNodeModel node) {
		// 创建辅助指针，用来在链表上移动
		HeroNodeModel temp = hedaNode.next;
		// 校验当前链表是否为空，如果是空， 则不能修改
		if (temp == null) {
			System.out.println("当前链表为空～～");
			return;
		}
		// 创建是否修改的标记，如果是true，表示可以修改，fales则不能修改
		boolean flag = false;
		while (true) {
			if (temp == null) {
				// 遍历完了，没有找到
				break;
			}
			// 遍历链表
			if (temp.no == node.no) {
				// 如果找到，跳出循环，修改当前节点信息
				flag = true;
				break;
			}
			// 若没有找到，指针后移
			temp = temp.next;
		}
		if (flag) {
			// 修改节点信息
			temp.name = node.name;
			temp.nikeName = node.nikeName;
		} else {
			System.out.printf("没有找到编号为：%d的节点，不能修改！\n", node.no);
		}
	}

	/**
	 * 删除节点
	 */
	public void delLinkedList(HeroNodeModel node) {
		// 判断链表是否为空，若是空的话，没法删除
		if (hedaNode.next == null) {
			System.out.println("当前链表为空～～");
			return;
		}
		// 创建是否删除的标记
		boolean flag = false;
		// 创建辅助指针
		HeroNodeModel temp = hedaNode;
		while (true) {
			if (temp.next == null) {
				// 所有的都遍历完了，没有找到可以删除的节点，跳出循环
				flag = true;
				break;
			}
			if (temp.next.no == node.no) {
				// 找到了跳出循环
				break;
			}
			// 还有节点，将辅助指针后移
			temp = temp.next;
		}
		if (flag) {
			System.out.printf("没有找到编号为：%d 的节点，无法删除～～\t", node.no);
		} else {
			// 将复制指针的下一个，指向下一个的下一个
			temp.next = temp.next.next;
		}
	}

	/**
	 * 检查当前链表的有效节点的个数
	 */
	public int sizeLinkedList(HeroNodeModel headNode) {
		if (headNode == null || headNode.next == null) {
			return 0;
		}
		int size = 0;
		// 创建辅助指针
		HeroNodeModel cur = headNode.next;
		while (cur != null) {
			size++;
			// 移动指针
			cur = cur.next;

		}
		return size;
	}

	/**
	 * 查找倒数第n个节点 
	 * 思路： 
	 * 	1.创建index，index表示倒数第index节点 
	 * 	2.编写方法，接受head节点，同时接受index
	 * 	3.遍历链表，得到链表长度lengthLinked 4.得到链表长度后，再次从头遍历，到size-lengthLinked即可
	 * 	5.如果没有，返回空null
	 * 
	 */
	public HeroNodeModel getLaseIndexNode(int index) {
		// 校验是否有效
		if (hedaNode == null || hedaNode.next == null) {
			System.out.println("当前链表为空～～");
			return null;
		}
		// 获取链表长度
		int sizeLinkedList = sizeLinkedList(hedaNode);
		if (index > sizeLinkedList) {
			System.out.println("查找的位置超过链表长度了～～");
			return null;
		}
		HeroNodeModel res = hedaNode.next;
		for (int i = 0; i < (sizeLinkedList-index); i++) {
			res = res.next;
		}
		return res;
	}
	
	/**
	 * 反转链表
	 * 思路：
	 * 	1.定义一个reverseNode = new HeroNodeModel()节点
	 * 	2.从头到尾遍历旧的链表，每取出一个，放到reverseNode最前面
	 * 	3.将原来节点的头节点的下一个指向reverseNode的下一个，这样原来头节点的位置，就取代了作为临时变量的reverseNode，head.next = reverseNode.next
	 */
	public SingleLinkedListModel getReverseLinkedList(SingleLinkedListModel linkedList) {
		// 检查当前链表是否是空，或者只有一个，因为只有一个的话，那就不需要发转了
		if(linkedList == null || linkedList.hedaNode.next == null) {
			System.out.println("当前链表为空，不能反转");
			return null;
		}
		if(linkedList.sizeLinkedList(linkedList.getHedaNode()) == 1) {
			return linkedList;
		}else {
			// 创建临时变量，也就是当前节点
			HeroNodeModel cru = linkedList.getHedaNode().next;
			// 创建当前节点的下一个
			HeroNodeModel cruNext = null;
			// 创建反转后的头节点
			HeroNodeModel reverseNode = new HeroNodeModel(0,"","");
			// 遍历原来的链表，每遍历一个节点，将他取出来，放到reverseNode最前面
			while(cru!=null) {
				// 先将cru的下一个取出来，做一个备份，否则一会cru移动就找不到了
				cruNext = cru.next;
				// 将cru指向一个替换的变量，可以把reverseNode.next临时变量，这个临时变量存放的就是反转前的上一个数据
				cru.next = reverseNode.next;
				// 上反转前的上一个数据赋值给cru.next，这样cru.next就能指向cru了，reverseNode.next是一个不断移动的指针
				reverseNode.next = cru;
				// 将指针后移
				cru = cruNext;
				//后面循环重复前面的操作
			}
			// 将头指针指向reverseNode的下一个
			linkedList.getHedaNode().next = reverseNode.next;
			/**
			 * 原来o->a1->a2->a3>a4
			 * 程序执行：
			 * 	第一次：
			 * 		a2->res.next->a1
			 * 	第二次：
			 * 		a3->res.next->a2->a1;
			 * 	第三次：
			 * 		a4->res.next->a3->a2->a1
			 * 	第四次：
			 * 		o—>res.next->a4->a3->a2->a1
			 *  循环结束->表示cru.next
			 */
			return linkedList;
		}
	}
	
	/**
	 * 反向打印链表
	 * 思路：
	 * 	1.先将单链表反转，然后遍历打印，这种方法存在问题，会破坏单链表结构，不建议使用
	 * 	2.可是使用栈，先将单链表节点逐个压入栈，然后再出栈打印，利用栈的先进后出（区别与队列的先进先出）就可以实现逆序打印，可以查看TestStack演示
	 */
	public void reversePrint(SingleLinkedListModel linkedList) {
		// 判断链表是否为空
		if (linkedList == null || linkedList.getHedaNode().next == null) {
			System.out.println("当前链表为空～～");
		}
		// 创建栈，将各个节点押入栈内
		Stack<HeroNodeModel> stack = new Stack();
		HeroNodeModel cur = linkedList.getHedaNode().next;
		while (cur != null) {
			// add是继承自Vector的方法，且返回值类型是boolean。
			// push是Stack自身的方法，返回值类型是参数类类型。
			stack.push(cur);
			cur = cur.next;
		}
		
		// 将栈中的结果打印
		while (stack.size() > 0) {
			// stack.peek() 返回栈顶元素，但不在堆栈中删除它。
			// stack.pop() 返回栈顶元素，并在堆栈中删除它。
			// stack特点是先进后出
			System.out.println(stack.pop());
		}
	}
	
	/**
	 * 合并
	 */
	public void addAll(SingleLinkedListModel s1,SingleLinkedListModel s2,SingleLinkedListModel s3) {
		
	}
	
}
