package project3;

import java.util.ArrayList;
import java.util.Scanner;

public class Students {

	private String name, field;
	private int studentId, entranceYear;
	private long debt;
	private Room room;

	private Scanner input = new Scanner(System.in);
	static ArrayList<Students> students = new ArrayList<Students>();

	public Students(String name, int studentId) {
		super();
		this.name = name;
		this.studentId = studentId;
		Students.students.add(this);
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + studentId;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Students other = (Students) obj;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		if (studentId != other.studentId)
//			return false;
//		return true;
//	}

	@Override
	public String toString() {
		return "Students [name=" + name + ", studentId=" + studentId + "]";
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
		this.room.roomMem.add(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getEntranceYear() {
		return entranceYear;
	}

	public void setEntranceYear(int entranceYear) {
		this.entranceYear = entranceYear;
	}

	public long getDebt() {
		return debt;
	}

	public void setDebt(long debt) {
		this.debt = debt;
	}

	public void edit(String name) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getName().contentEquals(name)) {
				System.out.println("Enter new ID : ");
				students.get(i).setStudentId(input.nextInt());
				System.out.println("Enter new Field : ");
				students.get(i).setField(input.next());
			}
		}
	}
}
