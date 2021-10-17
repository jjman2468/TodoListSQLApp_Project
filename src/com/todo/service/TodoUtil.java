package com.todo.service;

import com.todo.dao.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {

	public static void createItem(TodoList list) {

		String title, desc, category, due_date, importance, time;
		Scanner sc = new Scanner(System.in);

		System.out.println("[목록에 추가]\n" + "제목은? > ");

		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("중복된 제목이 있습니다");
			return;
		}

		System.out.println("카테고리는? > ");
		category = sc.nextLine().trim();

		System.out.println("내용은? > ");
		desc = sc.nextLine().trim();

		System.out.println("마감일자는?(yyyy/mm/dd) > ");
		due_date = sc.nextLine().trim();

		System.out.println("중요도는?(1~10) > ");
		importance = sc.nextLine().trim();

		System.out.println("걸리는 시간은? (분단위) > ");
		time = sc.nextLine().trim();
		TodoItem t = new TodoItem(title, desc, category, due_date, importance, time);
		if (list.addItem(t) > 0) {
			System.out.println("목록에 추가되었습니다.");
		}
	}

	/*public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);
		/*
		 * System.out.println("\n" + "삭제할 항목의 제목을 입력하세요 > ");
		 * 
		 * String title = sc.next();
		 * 
		 * for (TodoItem item : l.getList()) { if (title.equals(item.getTitle())) {
		 * l.deleteItem(item); System.out.println("목록에서 삭제되었습니다."); break; } }
		 */
		/*System.out.println("\n" + "삭제할 항목의 번호를 입력하세요 > ");

		int num = sc.nextInt();
		if (l.deleteItem(num) > 0) {
			System.out.println("목록에서 삭제되었습니다.");
		}
	} */

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 항목의 번호들을 입력하세요");
		
		String ids = sc.nextLine();
		
		String[]id = ids.split(" ");
		
		for(int i=0; i<id.length; i++) {
			l.deleteItem(Integer.parseInt(id[i]));
		}
		System.out.println("삭제되었습니다.");
	}

	public static void updateItem(TodoList l) {

		String new_title, new_desc, new_category, new_due_date, new_importance, new_time;
		Scanner sc = new Scanner(System.in);

		/*
		 * System.out.println("\n" + "수정할 항목의 제목을 입력하세요 > "); String title =
		 * sc.next().trim(); if (!l.isDuplicate(title)) {
		 * System.out.println("존재하지 않는 제목입니다..."); return; }
		 */
		System.out.println("\n" + "수정할 항목의 번호를 입력하세요 > ");
		int num = Integer.parseInt(sc.nextLine().trim()); // 엔터까지 받기 위해 이렇게 적었다.
		/*
		 * if (num > l.getSize() || num < 0) { System.out.println("존재하지 않는 번호입니다...");
		 * return; } sc.nextLine();
		 */
		System.out.println("새 제목을 입력하세요 > ");
		new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 있는 제목입니다.");
			return;
		}

		System.out.println("새 카테고리를 입력하세요 > ");
		new_category = sc.nextLine().trim();

		System.out.println("새 내용을 입력하세요 > ");
		new_desc = sc.nextLine().trim();

		System.out.println("새 마감일자를 입력하세요(yyyy/mm/dd) > ");
		new_due_date = sc.nextLine().trim();

		System.out.println("새 중요도를 입력하세요(1~10) > ");
		new_importance = sc.nextLine().trim();

		System.out.println("새 진행시간을 입력하세요(분단위) > ");
		new_time = sc.nextLine().trim();
		/*
		 * for (TodoItem item : l.getList()) { if (item.getTitle().equals(title)) {
		 * l.deleteItem(item); TodoItem t = new TodoItem(new_title, new_description,
		 * new_category, new_duedate); l.addItem(t); System.out.println("항목이 수정되었습니다.");
		 * } }
		 */
		// l.deleteItemByNumber(num);
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date, new_importance, new_time);
		t.setId(num);
		if (l.updateItem(t) > 0) {
			System.out.println("항목이 수정되었습니다.");
		}
	}

	public static void listAll(TodoList l) {
		System.out.println("[전체목록, 총 " + l.getCount() + "개]");
		int i = 1;
		for (TodoItem item : l.getList()) {

			System.out.print(i + ". ");
			System.out.println(item.toString());
			i++;
		}
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("[전체목록, 총 " + l.getCount() + "개]");
		int i = 1;
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {

			System.out.print(i + ". ");
			System.out.println(item.toString());
			i++;
		}
	}

	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}

		System.out.println("\n총 " + count + " 개의 카테고리를 찾았습니다.");
	}

	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다. \n", count);
	}

	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + " 개의 항목을 찾았습니다.");
	}

	public static void findImportantList(TodoList  l, String importance) {
		int count = 0;
		for(TodoItem item : l.getListImportant(importance)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + " 개의 항목을 찾았습니다.");
	}
	
	public static void findComplete(TodoList l, int keyword) {
		int count = 0;
		for (TodoItem item : l.getCompleteList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();

			System.out.println("목록 저장 완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void completeItem(TodoList l) {
		Scanner sc = new Scanner(System.in);

		System.out.println("완료표시할 항목들의 번호(띄어쓰기로 구분) : ");

		String ids = sc.nextLine();

		String[] id = ids.split(" ");

		for (int i = 0; i < id.length; i++) {
			l.completeItem(Integer.parseInt(id[i]));
		}
	}
	/*
	 * public static void loadList(TodoList l, String filename) { try {
	 * BufferedReader br = new BufferedReader(new FileReader(filename));
	 * 
	 * String oneline;
	 * 
	 * while ((oneline = br.readLine()) != null) { StringTokenizer st = new
	 * StringTokenizer(oneline, "##"); String category = st.nextToken(); String
	 * title = st.nextToken(); String desc = st.nextToken(); String due_date =
	 * st.nextToken(); String date = st.nextToken(); TodoItem t = new
	 * TodoItem(title, desc, category, due_date, date); l.addItem(t); } br.close();
	 * System.out.println("\n목록 불러오기 완료"); } catch (FileNotFoundException e) {
	 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } }
	 */

}
