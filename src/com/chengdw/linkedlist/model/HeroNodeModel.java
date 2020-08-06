package com.chengdw.linkedlist.model;

/**
 * 存放节点
 * 
 * @author dawei
 * 
 */
public class HeroNodeModel {
	public int no;
	public String name;
	public String nikeName;
	// 下一个节点
	public HeroNodeModel next;

	/**
	 * 有参构造
	 * 
	 * @param no
	 * @param name
	 * @param nikeName
	 */
	public HeroNodeModel(int no, String name, String nikeName) {
		this.no = no;
		this.name = name;
		this.nikeName = nikeName;
	}

	@Override
	public String toString() {
		return "HeroNodeModel [no=" + no + ", name=" + name + ", nikeName=" + nikeName + "]";
	}
}
