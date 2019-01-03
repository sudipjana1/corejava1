package core.java.service;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import core.java.model.Book;

public class BookService {

	Set <Book> books = new HashSet<Book>();
	
	
	
	public BookService() {
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(new File("/Users//sudipjana//IIHT//Assignment//books.txt"));
			ois = new ObjectInputStream(fis);
			/*Book book = (Book) ois.readObject();
			books.add(book);*/
			//System.out.println(book.toString());
			boolean cont = true;
			while(cont) {
				Book book = (Book) ois.readObject();
				if(book != null) {
					books.add(book);
					System.out.println(book.toString());
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



	public void addBook(Book book) {
		books.add(book);
	}
	
	@SuppressWarnings("unused")
	public Book searchBookbyId(long bookId) {
		Book book = new Book();
		Iterator<Book> it = books.iterator();
		while(it.hasNext()) {
			book = it.next();
			if( book.getBookId() == bookId ) {
				System.out.println("Book Found: " + book.toString());
				break;	
			}
		}
		
		if(book == null) {
			System.out.println("No Book found wih ihis ID");
		}
		return book;
	}
	public void deleteBookbyId(long bookId) {
		Book book = searchBookbyId(bookId);
		if(book != null) {
			books.remove(book);
			System.out.println("Book Deleted" + book.toString());
		}else
			System.out.println("No Book found wih ihis ID" + bookId);
		
	}
	
	public void saveBooks() {
		if(books.isEmpty()) {
			return;
		}else {
			try {
				FileOutputStream fos = new FileOutputStream(new File("/Users//sudipjana//IIHT//Assignment//books.txt"));
				ObjectOutputStream oos = new ObjectOutputStream(fos);
			
				Iterator<Book> it = books.iterator();
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
