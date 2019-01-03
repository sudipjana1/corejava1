package core.java.service;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import core.java.model.Book;
import core.java.model.Subject;

public class SubjectService {
	Set <Subject> subjects = new HashSet<Subject>();


	public SubjectService() {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(new File("/Users//sudipjana//IIHT//Assignment//subjects.txt"));
			ois = new ObjectInputStream(fis);
			/*Book book = (Book) ois.readObject();
			books.add(book);*/
			//System.out.println(book.toString());
			boolean cont = true;
			while(cont) {
				Subject subject = (Subject) ois.readObject();
				if(subject != null) {
					subjects.add(subject);
					System.out.println(subject.toString());
				}else {
					ois.close();
					fis.close();
					cont = false;
				}
			}
			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}catch(EOFException e) {

			System.out.println("End of File");

		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addSubject(Subject book) {
		subjects.add(book);
	}
	
	@SuppressWarnings("unused")
	public Subject searchSubjectbyId(long subjectId) {
		Subject subject = new Subject();
		Iterator<Subject> it = subjects.iterator();
		while(it.hasNext()) {
			subject = it.next();
			if( subject.getSubjectId() == subjectId ) {
				System.out.println("Subject Found: " + subject.toString());
				break;	
			}
		}
		
		if(subject == null) {
			System.out.println("No Subject found wih ihis ID");
		}
		return subject;
	}
	public void deleteSubjectbyId(long subjectId) {
		Subject subject = searchSubjectbyId(subjectId);
		if(subject != null) {
			subjects.remove(subject);
			System.out.println("Subject Deleted" + subject.toString());
		}else
			System.out.println("No Book found wih ihis ID" + subjectId);
		
	}
	
	public void saveSubjects() {
		if(subjects.isEmpty()) {
			return;
		}else {
			try {
				FileOutputStream fos = new FileOutputStream(new File("/Users//sudipjana//IIHT//Assignment//subjects.txt"));
				ObjectOutputStream oos = new ObjectOutputStream(fos);
			
				Iterator<Subject> it = subjects.iterator();
				while(it.hasNext()) {
					oos.writeObject(it.next());
				}
				oos.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

