package stupidUni;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main2 {
	
	public static void write(Object obj) {
		FileOutputStream f;
		ObjectOutputStream o;
		try {
			f = new FileOutputStream(new File("myObject.bin"));
			o = new ObjectOutputStream(f);
			o.writeObject(obj);
//			o.writeObject(obj);
			f.close();
			o.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		Message p1 = new Message("John", "Male");
//		Message p2 = new Message("Rachel", "Female");
		
		write(p1);
//		write(p2);

		try {
	
			System.out.println(1);

			FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			System.out.println(3);

//			 Read objects
			Message pr1 = (Message) oi.readObject();
//			Message pr2 = (Message) oi.readObject();
			System.out.println(pr1.getContent());
//			System.out.println(pr2.getContent());

			oi.close();
			fi.close();
			System.out.println(4);

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			 e.printStackTrace();

		} catch (IOException e) {
			System.out.println("Error initializing stream");
			 e.printStackTrace();

		}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
	 e.printStackTrace();
		}
	}

}
