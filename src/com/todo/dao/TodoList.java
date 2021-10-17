package com.todo.dao;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.todo.service.*;

public class TodoList {
	Connection conn;

	public TodoList() {
		this.conn = DbConnect.getConnection();
	}

	/*
	 * public int getSize() { return list.size(); }
	 */

	public int addItem(TodoItem t) {
		String sql = "insert into list (title, category, desc, due_date, current_date, importance, time)"
				+ " values (?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getCategory());
			pstmt.setString(3, t.getDesc());
			pstmt.setString(4, t.getDue_date());
			pstmt.setString(5, t.getCurrent_date());
			pstmt.setString(6, t.getImportance());
			pstmt.setString(7, t.getTime());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int deleteItem(int index) {
		String sql = "delete from list where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/*
	 * public void deleteItemByNumber(int num) { list.remove(num - 1); }
	 * 
	 * void editItem(TodoItem t, TodoItem updated) { int index = list.indexOf(t);
	 * list.remove(index); list.add(updated); }
	 */

	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, category=?, desc=?, due_date=?, current_date=?, importance=?, time=?"
				+ "where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getCategory());
			pstmt.setString(3, t.getDesc());
			pstmt.setString(4, t.getDue_date());
			pstmt.setString(5, t.getCurrent_date());
			pstmt.setString(6, t.getImportance());
			pstmt.setString(7, t.getTime());
			pstmt.setInt(8, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/*
	 * public int getIndexOf(TodoItem t) { return list.indexOf(t); }
	 */

	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_complete = rs.getInt("is_complete");
				String importance = rs.getString("importance");
				String time = rs.getString("time");
				TodoItem t = new TodoItem(title, description, category, due_date, is_complete, importance, time);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<TodoItem> getList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%" + keyword + "%";
		try {
			String sql = "SELECT * FROM list WHERE title like ? or desc like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_complete = rs.getInt("is_complete");
				String importance = rs.getString("importance");
				String time = rs.getString("time");
				TodoItem t = new TodoItem(title, description, category, due_date, is_complete, importance, time);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TodoItem> getListCategory(String cate) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list where category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_complete = rs.getInt("is_complete");
				String importance = rs.getString("importance");
				String time = rs.getString("time");
				TodoItem t = new TodoItem(title, description, category, due_date, is_complete, importance, time);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list ORDER BY " + orderby;
			if (ordering == 0)
				sql += " desc";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_complete = rs.getInt("is_complete");
				String importance = rs.getString("importance");
				String time = rs.getString("time");
				TodoItem t = new TodoItem(title, description, category, due_date, is_complete, importance, time);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<TodoItem> getCompleteList(int keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE is_complete like?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_Date");
				int is_complete = rs.getInt("is_complete");
				String importance = rs.getString("importance");
				String time = rs.getString("time");
				TodoItem t = new TodoItem(title, description, category, due_date, importance, time);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_complete(is_complete);
				list.add(t);
			}
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TodoItem> getListImportant(String impor) {

		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		/*
		 * try { if (impor.equals("1")) { String sql =
		 * "SELECT * FROM list WHERE importance like?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1,
		 * "1 or importance like 2 or importance like 3 or importance like 4 or importance like 5 or importance like 6 or importance like 7 or importance like 8 or importance like 9 or importance like 10"
		 * ); } else if (impor.equals("2")) { String sql =
		 * "SELECT * FROM list WHERE importance like?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1,
		 * "2 or importance like 3 or importance like 4 or importance like 5 or importance like 6 or importance like 7 or importance like 8 or importance like 9 or importance like 10"
		 * ); } else if (impor.equals("3")) { String sql =
		 * "SELECT * FROM list WHERE importance like?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(2,
		 * "3 or importance like 4 or importance like 5 or importance like 6 or importance like 7 or importance like 8 or importance like 9 or importance like 10"
		 * ); } else if (impor.equals("4")) { String sql =
		 * "SELECT * FROM list WHERE importance like?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1,
		 * "4 or importance like 5 or importance like 6 or importance like 7 or importance like 8 or importance like 9 or importance like 10"
		 * ); } else if (impor.equals("5")) { String sql =
		 * "SELECT * FROM list WHERE importance like?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1,
		 * "5 or importance like 6 or importance like 7 or importance like 8 or importance like 9 or importance like 10"
		 * ); } else if (impor.equals("6")) { String sql =
		 * "SELECT * FROM list WHERE importance like?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1,
		 * "6 or importance like 7 or importance like 8 or importance like 9 or importance like 10"
		 * ); } else if (impor.equals("7")) { String sql =
		 * "SELECT * FROM list WHERE importance like?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1,
		 * "7 or importance like 8 or importance like 9 or importance like 10"); } else
		 * if (impor.equals("8")) { String sql =
		 * "SELECT * FROM list WHERE importance like?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1,
		 * "8 or importance like 9 or importance like 10"); } else if
		 * (impor.equals("9")) { String sql =
		 * "SELECT * FROM list WHERE importance like?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1, "9 or importance like 10"); }
		 * else if (impor.equals("10")) { String sql =
		 * "SELECT * FROM list WHERE importance like?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1, "10"); }
		 */
		try {
			String sql = "SELECT * FROM list WHERE importance like?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, impor);
			ResultSet rs = pstmt.executeQuery();
			/*
			 * String sql = "SELECT * FROM list WHERE importance like?"; pstmt =
			 * conn.prepareStatement(sql); pstmt.setString(1, impor); ResultSet rs =
			 * pstmt.executeQuery();
			 */
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_Date");
				int is_complete = rs.getInt("is_complete");
				String importance = rs.getString("importance");
				String time = rs.getString("time");
				TodoItem t = new TodoItem(title, description, category, due_date, importance, time);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_complete(is_complete);
				list.add(t);
			}
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/*
	 * public void sortByName() { Collections.sort(list, new TodoSortByName());
	 * 
	 * }
	 */

	public void listAll(TodoList l) {
		System.out.println("\n" + "inside list_All method\n");
		for (TodoItem myitem : l.getList()) {
			System.out.println(myitem.getTitle() + myitem.getDesc());
		}
	}

	/*
	 * public void reverseList() { Collections.reverse(list); }
	 * 
	 * public void sortByDate() { Collections.sort(list, new TodoSortByDate()); }
	 * 
	 * public int indexOf(TodoItem t) { return list.indexOf(t); }
	 */

	public Boolean isDuplicate(String title) {
		PreparedStatement pstmt;
		String sql = "select count(*) from list where title like ?;";
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt("count(*)");
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (count >= 1)
			return true;
		return false;
	}

	public ArrayList<String> getCategories() {
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String category = rs.getString("category");
				list.add(category);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int completeItem(int id) {
		String sql = "update list set is_complete=1" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into list (title, category, desc, due_date, current_date, is_complete, importance, time)"
					+ " values (?,?,?,?,?,?,?,?);";
			int records = 0;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String title = st.nextToken();
				String category = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				String is_complete = st.nextToken();
				String importance = st.nextToken();
				String time = st.nextToken();

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, category);
				pstmt.setString(3, desc);
				pstmt.setString(4, due_date);
				pstmt.setString(5, current_date);
				pstmt.setString(6, is_complete);
				pstmt.setString(7, importance);
				pstmt.setString(8, time);
				int count = pstmt.executeUpdate();
				if (count > 0)
					records++;
				pstmt.close();
			}
			System.out.println(records + " records read !!");
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
