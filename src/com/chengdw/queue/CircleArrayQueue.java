package com.chengdw.queue;

import java.util.Scanner;

import com.chengdw.queue.model.CircleArrayQueueModel;

/**
 * 环形队列，解决线形队列无法使用空余空间的问题
 * 
 * @author dawei
 *
 */
public class CircleArrayQueue {

	public static void main(String[] args) {
		// 验证队列，创建队列，环形队列，最后一个默认约定为空，所以这里只有三个可以空间可以用
		CircleArrayQueueModel circleArrayQueueModel = new CircleArrayQueueModel(4);
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
				circleArrayQueueModel.showQueue();
				break;
			case 'e':
				// 关闭，不管会有警告
				scanner.close();
				loop = false;
				break;
			case 'a':
				System.out.print("请输入一个数字：");
				int nextInt = scanner.nextInt();
				circleArrayQueueModel.addQueue(nextInt);
				break;
			case 'g':
				try {
					int getInt = circleArrayQueueModel.getQueue();
					System.out.printf("取出的数据时：%d\n", getInt);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					circleArrayQueueModel.headQueue();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 't':
				try {
					circleArrayQueueModel.endQueue();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				break;
			}
		}
	}

}
