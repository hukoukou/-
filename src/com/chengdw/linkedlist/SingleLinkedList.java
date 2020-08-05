package com.chengdw.linkedlist;

import com.chengdw.linkedlist.model.HeroNodeModel;
import com.chengdw.linkedlist.model.SingleLinkedListModel;

/**
 * 
 * @author dawei
 *
 */
public class SingleLinkedList {

	public static void main(String[] args) {
		// 测试链表，创建几个节点
		HeroNodeModel luzs = new HeroNodeModel(3, "吴用", "智多星");
		HeroNodeModel songjiang = new HeroNodeModel(1, "松江", "及时雨");
		HeroNodeModel guansheng = new HeroNodeModel(5, "关圣", "大刀");
		HeroNodeModel wusong = new HeroNodeModel(14, "武松", "行者");
		HeroNodeModel yanqing = new HeroNodeModel(36, "燕青", "浪子");
		HeroNodeModel shijin = new HeroNodeModel(23, "史进", "九纹龙");
		SingleLinkedListModel single = new SingleLinkedListModel();
		SingleLinkedListModel singleList = new SingleLinkedListModel();
		// 直接添加
		single.add(luzs);
		single.add(songjiang);
		single.add(guansheng);
		single.add(wusong);
		single.add(yanqing);
		single.add(shijin);
		single.list();
		// 按照no大小顺序添加，直接在内存中进行排序，比在数据库中快
		singleList.addByOrder(luzs);
		singleList.addByOrder(songjiang);
		singleList.addByOrder(guansheng);
		singleList.addByOrder(wusong);
		singleList.addByOrder(yanqing);
		singleList.addByOrder(shijin);
		// 这一个用来测试校验的
		singleList.addByOrder(wusong);
		singleList.list();
	}
}
