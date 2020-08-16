package com.chengdw.linkedlist;

/**
 * 双向链表
 * 
 * @author dawei
 *
 */
public class DoubleLinkedListDemo {
	public static void main(String[] args) {
		// 双向链表的测试
		System.out.println("这是双向链表测试～");
		// 创建节点
		DoubleLinkedHeroNodeModel luzs = new DoubleLinkedHeroNodeModel(3, "吴用", "智多星");
		DoubleLinkedHeroNodeModel songjiang = new DoubleLinkedHeroNodeModel(1, "松江", "及时雨");
		DoubleLinkedHeroNodeModel guansheng = new DoubleLinkedHeroNodeModel(5, "关圣", "大刀");
		DoubleLinkedHeroNodeModel wusong = new DoubleLinkedHeroNodeModel(14, "武松", "行者");
		DoubleLinkedHeroNodeModel yanqing = new DoubleLinkedHeroNodeModel(36, "燕青", "浪子");
		DoubleLinkedHeroNodeModel shijin = new DoubleLinkedHeroNodeModel(23, "史进", "九纹龙");
		DoubleLinkedHeroNodeModel hukoukou = new DoubleLinkedHeroNodeModel(1, "胡扣扣", "铁公鸡");

		// 创建双向链表
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

		// 链表中添加节点
		System.out.println("链表中添加节点～");
		doubleLinkedList.addDoubleLinkedList(luzs);
		doubleLinkedList.addDoubleLinkedList(songjiang);
		doubleLinkedList.addDoubleLinkedList(wusong);
		doubleLinkedList.addDoubleLinkedList(guansheng);
		doubleLinkedList.addDoubleLinkedList(shijin);
		doubleLinkedList.addDoubleLinkedList(yanqing);
		doubleLinkedList.doubelLinkedList();
		System.out.println("完成添加～");

		// 测试显示链表
		System.out.println("展示添加的链表～");
		doubleLinkedList.doubelLinkedList();

		// 修改测试
		System.out.println("修改链表");
		doubleLinkedList.updateDoubleLinkedList(hukoukou);
		System.out.println("完成修改～");
		doubleLinkedList.doubelLinkedList();

		// 删除节点
		System.out.println("删除编号为1的节点");
		doubleLinkedList.delDoubleLinkedList(1);
		System.out.println("完成删除～");
		doubleLinkedList.doubelLinkedList();

		// 按照编号大小添加节点
		System.out.println("按照编号顺序添加节点");
		// 创建双向链表
		DoubleLinkedList doubleLinkedListByOrder = new DoubleLinkedList();
		doubleLinkedListByOrder.addByOrder(luzs);
		doubleLinkedListByOrder.addByOrder(songjiang);
		doubleLinkedListByOrder.addByOrder(wusong);
		doubleLinkedListByOrder.addByOrder(guansheng);
		doubleLinkedListByOrder.addByOrder(shijin);
		doubleLinkedListByOrder.addByOrder(yanqing);
		doubleLinkedListByOrder.doubelLinkedList();
	}
}

/**
 * 创建双向链表的类
 * 
 * @author dawei
 *
 */
class DoubleLinkedList {
	// 初始化双向链表
	private DoubleLinkedHeroNodeModel dlh = new DoubleLinkedHeroNodeModel(0, "", "");

	// 获取头节点
	public DoubleLinkedHeroNodeModel getHeadNode() {
		return dlh;
	}

	// 遍历双向链表
	public void doubelLinkedList() {
		if (dlh.next == null) {
			System.out.println("当前链表为空～");
		}
		// 创建辅助指针
		DoubleLinkedHeroNodeModel temp = dlh.next;
		while (true) {
			if (temp == null) {
				// 辅助指针为空，跳出循环
				break;
			}
			// 打印节点
			System.out.println(temp);
			// 指针后移
			temp = temp.next;
		}
	}

	// 添加节点(默认添加到尾部，不考虑编号)
	public void addDoubleLinkedList(DoubleLinkedHeroNodeModel newNode) {
		// 创建辅助节点
		DoubleLinkedHeroNodeModel temp = dlh;
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		// 当退出循环后，当前辅助节点指向的就是需要添加的位置，构成双向链表
		temp.next = newNode;
		newNode.pre = temp;
	}

	// 修改一个节点的类（与修改单项链表的一样）
	public void updateDoubleLinkedList(DoubleLinkedHeroNodeModel newNode) {
		DoubleLinkedHeroNodeModel temp = dlh.next;
		boolean flag = false;
		// 判断是否为空，如果是空，则不能修改
		if (temp == null) {
			System.out.println("当前双向链表为空，不能修改～");
			return;
		}
		while (true) {
			if (temp.no == newNode.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			temp.name = newNode.name;
			temp.nikeName = newNode.nikeName;
		} else {
			System.out.printf("没有找到编号为：%d 的节点，无法修改\t", newNode.no);
		}
	}

	// 对于一个双向链表，可以直接找到这个节点
	public void delDoubleLinkedList(int no) {
		// 判断当前链表是否为空
		if (dlh.next == null) {
			System.out.println("当前链表为空，不能删除～");
			return;
		}
		DoubleLinkedHeroNodeModel temp = dlh.next;
		boolean flag = false;
		// 遍历链表
		while (true) {
			if (temp == null) {
				break;
			}
			if (temp.no == no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			// 删除链表
			// temp.pre.next = temp.next;
			// temp.next.pre = temp.pre;
			// 如果按照上面的写，代码会有问题。加入要删除的节点是最后一个节点，则要删除的节点的next为空
			temp.pre.next = temp.next;
			if (temp.next != null) {
				temp.next.pre = temp.pre;
			}
		} else {
			System.out.printf("没有找到要删除的编号为：%d 的节点\t", no);
		}
	}

	// 按照编号从小到达排序
	public void addByOrder(DoubleLinkedHeroNodeModel newNode) {
		// 创建辅助指针
		DoubleLinkedHeroNodeModel temp = dlh;
		boolean flag = false;
		while (true) {
			// 当链表为空时，跳出，直接添加
			if (temp.next == null) {
				flag = true;
				break;
			}
			// 若当前指针指向的节点，大于添加的节点，跳出
			if (temp.next.no > newNode.no) {
				flag = true;
				break;
			}
			// 都不符合，指针后移
			temp = temp.next;
		}
		if (flag) {
			// 新节点的下一个指向当前节点的下一个
			newNode.next = temp.next;
			if (temp.next != null) {
				// 若当前节点的下一个不是最后一个，当前节点下一个的前一个节点指向添加的节点
				temp.next.pre = newNode;
			}
			// 当前节点的下一个指向添加的节点
			temp.next = newNode;
			// 新添加的节点的前一个指向当前节点的下一个
			newNode.pre = temp.next;
		}
	}
}

/**
 * 创建双向链表的节点
 * 
 * @author dawei
 *
 */
class DoubleLinkedHeroNodeModel {
	int no;
	String name;
	String nikeName;
	DoubleLinkedHeroNodeModel pre;
	DoubleLinkedHeroNodeModel next;

	public DoubleLinkedHeroNodeModel(int no, String name, String nikeName) {
		super();
		this.no = no;
		this.name = name;
		this.nikeName = nikeName;
		this.pre = pre;
		this.next = next;
	}

	@Override
	public String toString() {
		return "DoubleLinkedHeroNodeModel [no=" + no + ", name=" + name + ", nikeName=" + nikeName + "]";
	}

}
