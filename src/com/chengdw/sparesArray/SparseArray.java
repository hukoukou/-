package com.chengdw.sparesArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SparseArray {

	public static void main(String[] args) {
		// 1.创建二维数组11*11
		// 2.0表示没有棋子，1表示黑子，2表示白子
		int chessArr[][] = new int[11][11];
		// 创建棋子
		chessArr[1][2] = 1;
		chessArr[2][3] = 2;
		chessArr[5][7] = 2;
		// 打印棋盘
		System.out.println("原始的二维数组");
		for (int i = 0; i < chessArr.length; i++) {
			for (int j = 0; j < chessArr[i].length; j++) {
				System.out.printf("%d\t", chessArr[i][j]);
			}
			System.out.println();
		}

		// 存入文件
		// 检查是否存在文件
		File chessArrFile = new File("/Users/chengdawei/Desktop/chessArr.data");
		if (!chessArrFile.exists()) {
			// 若文件不存在，创建
			try {
				chessArrFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(chessArrFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 写入文件
		String s = "";
		for (int i = 0; i < chessArr.length; i++) {
			for (int j = 0; j < chessArr[i].length; j++) {
				s = s + chessArr[i][j] + "\t";
			}
			s = s + "\n";
		}
		try {
			fos.write(s.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int sum = 0;
		// 原始二维数组转换为稀疏数组
		// 1.遍历原始二维数组，获取所有非零数据
		for (int i = 0; i < chessArr.length; i++) {
			for (int j = 0; j < chessArr[i].length; j++) {
				if (chessArr[i][j] != 0) {
					sum++;
				}
			}
		}
		// 2.创建稀疏数组
		int sparseArray[][] = new int[sum + 1][3];
		sparseArray[0][0] = chessArr.length;
		sparseArray[0][1] = chessArr[0].length;
		sparseArray[0][2] = sum;
		// 3遍历二维数组，将非0的值存入到稀疏数组中
		// count 用来记录第几个非零数据
		int count = 0;
		for (int i = 0; i < chessArr.length; i++) {
			for (int j = 0; j < chessArr[i].length; j++) {
				if (chessArr[i][j] != 0) {
					count++;
					sparseArray[count][0] = i;
					sparseArray[count][1] = j;
					sparseArray[count][2] = chessArr[i][j];
				}
			}
		}
		// 输出稀疏数组
		System.out.println("输出稀疏数组：");
		for (int i = 0; i < sparseArray.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
		}
		// 写入文件
		File sparseFile = new File("/Users/chengdawei/Desktop/sparse.data");
		if (!sparseFile.exists()) {
			// 若文件不存在，创建
			try {
				sparseFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream sparseFileFos = null;
		try {
			sparseFileFos = new FileOutputStream(sparseFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 写入文件
		String sparseString = "";
		for (int i = 0; i < sparseArray.length; i++) {
			sparseString = sparseString + sparseArray[i][0] + "\t" + sparseArray[i][1] + "\t" + sparseArray[i][2] + "\n";
		}
		try {
			sparseFileFos.write(sparseString.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				sparseFileFos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 将稀疏数组复原成二维数组
		int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
		for (int i = 1; i < sparseArray.length; i++) {
			chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
		}
		// 打印原始二维数组
		System.out.println("打印原始二维数组");
		for (int i = 0; i < chessArr2.length; i++) {
			for (int j = 0; j < chessArr2[i].length; j++) {
				System.out.printf("%d\t", chessArr2[i][j]);
			}
			System.out.println();
		}
	}
}
