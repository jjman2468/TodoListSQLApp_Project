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

		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);

		System.out.println("[��Ͽ� �߰�]\n" + "������? > ");

		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("�ߺ��� ������ �ֽ��ϴ�");
			return;
		}

		System.out.println("ī�װ���? > ");
		category = sc.nextLine().trim();

		System.out.println("������? > ");
		desc = sc.nextLine().trim();

		System.out.println("�������ڴ�?");
		due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(title, desc, category, due_date);
		if (list.addItem(t) > 0) {
			System.out.println("��Ͽ� �߰��Ǿ����ϴ�.");
		}
	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);
		/*
		 * System.out.println("\n" + "������ �׸��� ������ �Է��ϼ��� > ");
		 * 
		 * String title = sc.next();
		 * 
		 * for (TodoItem item : l.getList()) { if (title.equals(item.getTitle())) {
		 * l.deleteItem(item); System.out.println("��Ͽ��� �����Ǿ����ϴ�."); break; } }
		 */
		System.out.println("\n" + "������ �׸��� ��ȣ�� �Է��ϼ��� > ");

		int num = sc.nextInt();
		if (l.deleteItem(num) > 0) {
			System.out.println("��Ͽ��� �����Ǿ����ϴ�.");
		}
	}

	public static void updateItem(TodoList l) {

		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);

		/*
		 * System.out.println("\n" + "������ �׸��� ������ �Է��ϼ��� > "); String title =
		 * sc.next().trim(); if (!l.isDuplicate(title)) {
		 * System.out.println("�������� �ʴ� �����Դϴ�..."); return; }
		 */
		System.out.println("\n" + "������ �׸��� ��ȣ�� �Է��ϼ��� > ");
		int num = sc.nextInt();
		/*
		 * if (num > l.getSize() || num < 0) { System.out.println("�������� �ʴ� ��ȣ�Դϴ�...");
		 * return; } sc.nextLine();
		 */
		System.out.println("�� ������ �Է��ϼ��� > ");
		new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� �ִ� �����Դϴ�.");
			return;
		}

		System.out.println("�� ī�װ��� �Է��ϼ��� > ");
		new_category = sc.nextLine().trim();

		System.out.println("�� ������ �Է��ϼ��� > ");
		new_desc = sc.nextLine().trim();

		System.out.println("�� �������ڸ� �Է��ϼ���(yyyy/mm/dd) > ");
		new_due_date = sc.nextLine().trim();

		/*
		 * for (TodoItem item : l.getList()) { if (item.getTitle().equals(title)) {
		 * l.deleteItem(item); TodoItem t = new TodoItem(new_title, new_description,
		 * new_category, new_duedate); l.addItem(t); System.out.println("�׸��� �����Ǿ����ϴ�.");
		 * } }
		 */
		// l.deleteItemByNumber(num);
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(num);
		if (l.updateItem(t) > 0) {
			System.out.println("�׸��� �����Ǿ����ϴ�.");
		}
	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü���, �� " + l.getCount() + "��]");
		int i = 1;
		for (TodoItem item : l.getList()) {

			System.out.print(i + ". ");
			System.out.println(item.toString());
			i++;
		}
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("[��ü���, �� " + l.getCount() + "��]");
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

		System.out.println("\n�� " + count + " ���� ī�װ��� ã�ҽ��ϴ�.");
	}

	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�. \n", count);
	}

	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("�� " + count + " ���� �׸��� ã�ҽ��ϴ�.");
	}

	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();

			System.out.println("��� ���� �Ϸ�");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void completeItem(TodoList l, int id) {
		if (l.completeItem(id) > 0) {
			System.out.println("-�۾� �Ϸ� Ȯ��-");
		}

	}

	/*public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));

			String oneline;

			while ((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String date = st.nextToken();
				TodoItem t = new TodoItem(title, desc, category, due_date, date);
				l.addItem(t);
			}
			br.close();
			System.out.println("\n��� �ҷ����� �Ϸ�");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
