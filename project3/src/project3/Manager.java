package project3;

import java.util.ArrayList;

public class Manager {
	private String name, userName, passWord;

	Manager(String Name, String PassWord) {
		this.name = Name;
		this.passWord = PassWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void del(Students student) {
		for(int i = 0; i < Students.students.size(); i++) {
			if(Students.students.get(i).getStudentId() == student.getStudentId()) {
				Students.students.remove(i);
			}
		}
	}

	public void add(String name, int id) {
		Students student = new Students(name, id);
		Students.students.add(student);
	}

	public void choose(Room room, Students student) {
		if(room.roomMem.size() < room.getRoomCap()) {
			room.roomMem.add(student);
		}else {
			System.out.println("This Room Is Full!!");
		}
	}

	public void edit(Students student) {
		student.edit(student.getName());
	}

	public void showRoommates(Students student) {
		ArrayList<Students> temp = student.getRoom().roomMem;
		temp.remove(student);
		System.out.println(temp);
	}

	public void showRoomMem(Room room) {
		System.out.println(room.roomMem);
	}

	public Students search(int ID) {
		int a = 0;
		for(int i = 0; i < Students.students.size(); i++) {
			if(Students.students.get(i).getStudentId() == ID) {
				a = i;
				break;
			}
		}
		return Students.students.get(a);
	}

}
