package com.chengdw.queue;

import java.util.Scanner;

import com.chengdw.queue.model.ArrayQueueModel;

public class ArrayQueue {

	/**
	 * 队列 @Title: main @Description: TODO @param args @return void @time 2020
	 * 9:28:03 PM @throws
	 */
	public static void main(String[] args) {
		// 验证队列，创建队列
		ArrayQueueModel arrayQueueModel = new ArrayQueueModel(3);
		// 用户数据
		char key = ' ';
		// 创建扫描器，接受
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			// 打出菜单
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出程序");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列取数据");
			System.out.println("h(head):查看队列头部数据");
			System.out.println("t(tail):查看队列尾数据");
			// 接受从控制台输入的字符
			key = scanner.next().charAt(0);
			switch (key) {
			case 's':
				arrayQueueModel.showQueue();
				break;
			case 'e':
				// 关闭，不管会有警告
				scanner.close();
				loop = false;
				break;
			case 'a':
				System.out.print("请输入一个数字：");
				int nextInt = scanner.nextInt();
				arrayQueueModel.addQueue(nextInt);
				break;
			case 'g':
				try {
					int getInt = arrayQueueModel.getQueue();
					System.out.printf("取出的数据时：%d\n", getInt);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int headQueue = arrayQueueModel.headQueue();
					System.out.printf("队列头部数据是：%d\t\n", headQueue);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 't':
				try {
					int endQueue = arrayQueueModel.endQueue();
					System.out.printf("队列尾部数据是：%d\t\n", endQueue);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				break;
			}
		}
		System.out.println("程序退出！");
	}
}
