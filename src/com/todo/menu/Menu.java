package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("ToDoList 명령어 도움말");
        System.out.println("( add ) - 항목 추가");
        System.out.println("( del ) - 항목 삭제");
        System.out.println("( edit ) - 항목 수정");
        System.out.println("( ls ) - 전체 목록");
        System.out.println("( ls_title_asc ) - 제목순 정렬");
        System.out.println("( ls_title_desc ) - 제목역순 정렬");
        System.out.println("( ls_cate ) - 전체 카테고리 보기");
        System.out.println("( ls_date ) - 날짜순 정렬");
        System.out.println("( ls_complete ) - 완료체크한 목록 보기");
        System.out.println("( find <키워드> ) - 키워드 포함 항목 검색");
        System.out.println("( find_cate <카테고리명> ) - 입력한 카테고리에 해당하는 목록 검색");
        System.out.println("( find_importance <중요도> ) - 입력한 중요도를 가진 목록 검색");
        System.out.println("( complete ) - 완료체크하기");
        System.out.println("( exit ) - 종료");
    }
    public static void prompt()
    {
    	System.out.print("\n명령어 > ");
    }
}