package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("ToDoList ��ɾ� ����");
        System.out.println("( add ) - �׸� �߰�");
        System.out.println("( del ) - �׸� ����");
        System.out.println("( edit ) - �׸� ����");
        System.out.println("( ls ) - ��ü ���");
        System.out.println("( ls_title_asc ) - ����� ����");
        System.out.println("( ls_title_desc ) - ���񿪼� ����");
        System.out.println("( ls_cate ) - ��ü ī�װ� ����");
        System.out.println("( ls_date ) - ��¥�� ����");
        System.out.println("( ls_complete ) - �Ϸ�üũ�� ��� ����");
        System.out.println("( find <Ű����> ) - Ű���� ���� �׸� �˻�");
        System.out.println("( find_cate <ī�װ���> ) - �Է��� ī�װ��� �ش��ϴ� ��� �˻�");
        System.out.println("( find_importance <�߿䵵> ) - �Է��� �߿䵵�� ���� ��� �˻�");
        System.out.println("( complete ) - �Ϸ�üũ�ϱ�");
        System.out.println("( exit ) - ����");
    }
    public static void prompt()
    {
    	System.out.print("\n��ɾ� > ");
    }
}