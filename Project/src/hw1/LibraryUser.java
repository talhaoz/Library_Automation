package hw1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * libraryUser class personabstract i extend eder
 * @author TALHA
 */
public class LibraryUser extends PersonAbstract {
    
    // filenames
    private String usersId;
    private String books;
    
    // data nin tutulucak oldugu listler
    private ArrayList<StringBuffer> theUsers;
    private ArrayList<StringBuffer> theBooks;

    /**
     *constructor la yer alıyorum
     *
     * 
     */
    public LibraryUser() {
        theUsers = new ArrayList<StringBuffer>();
        theBooks = new ArrayList<StringBuffer>();

    }
    
    /**
     * file name leri set ediyorum
     * @param users
     * @param bookLib 
     */
    public void setFileNames(String users, String bookLib) {
        usersId = users;
        books = bookLib;
    }

    /**
     * file ları okyorum ve listlere koyuyorum
     * @param fileName
     * @param lines
     */
    public void readFile(String fileName, ArrayList<StringBuffer> lines) {

        try {
            FileReader myFile = new FileReader(fileName);

            BufferedReader theFile = new BufferedReader(myFile);

            String newLine;
            StringBuffer newBufferLine;
            
            
            while ((newLine = theFile.readLine()) != null) {
                newBufferLine = new StringBuffer(newLine); //converting to StringBuffer
                lines.add(newBufferLine);
            }
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            System.exit(0);
        }

    }

    /**
     * method personId alır ve listede kayıtlı olup olmadıgını kontrol eder
     * @param personId
     * @return true veya false
     * @throws Exception
     */
    @Override
    public boolean checkId(int personId) throws Exception {
        if (personId < 0) {
            throw new Exception("Entered id cant be lower than zero!");
        }

        for (int i = 0; i < theUsers.size(); ++i) {
            String line = theUsers.get(i).toString();
            // tokenizer la parcalıyorum
            StringTokenizer tokenizer = new StringTokenizer(line, ";");

            int currentId = Integer.parseInt(tokenizer.nextToken().trim());

            if (currentId == personId) {
                return true;
            }

        }

        return false;

    }

    /**
     * file ları okumak icin readfile la filename leri ve listleri gönderiyorum
     */
    @Override
    public void readTheFiles() {
        readFile(usersId, theUsers);
        readFile(books, theBooks);
    }

    /**
     * file ları geri dosyaya yazıyorum
     */
    @Override
    public void writeTheFiles() {
        writeFile(books, theBooks);
    }

    /**
     * bu method kitap almak icin id alır ve kitabın avaiblity sini 
     *                              kontrol eder eger varsa kitap odunc alınır
     * @param bookId
     * @return true doner eger kitap alınırsa aksi halde false
     * @throws Exception
     */
    public boolean borrowBook(int bookId) throws Exception {
        int check = 0;
        
        
        for (int i = 0; i < theBooks.size(); ++i) {
            String line = theBooks.get(i).toString();
            StringTokenizer tokenizer = new StringTokenizer(line, ";");

            int currentId = Integer.parseInt(tokenizer.nextToken());

            if (currentId == bookId) {
                check++;
                String bookName = tokenizer.nextToken();

                int availability = Integer.parseInt(tokenizer.nextToken());

                if (availability > 0) {
                    StringBuffer linebuff = theBooks.get(i);
                    String availabilityString = Integer.toString(availability - 1);

                    int indexx = linebuff.lastIndexOf(";");
                    
               //kitabın mevcut degerini 1 dusurmek ıcın string replace ediyorum
                    linebuff.replace(indexx+1 , linebuff.length(), availabilityString);

                    System.out.println(bookName + " has borrowed!");

                    return true;

                } else {
                    System.out.println(bookName + " is not available!");
                    return false;
                }
            }

        }

        if (check == 0) {
            System.err.println("No record for this book!");
            return false;
        }

        return false;
    }

    /**
     * book ları geri teslim etmek icin kitabın id sini alir ve geri ekler
     * @param bookId
     * @return ekleme basarılıysa true doner
     * @throws Exception
     */
    public boolean returnBook(int bookId) throws Exception {
        int check = 0;

        for (int i = 0; i < theBooks.size(); ++i) {
            StringBuffer linebuff = theBooks.get(i);

            String line = linebuff.toString();
            StringTokenizer tokenizer = new StringTokenizer(line, ";");

            int currentId = Integer.parseInt(tokenizer.nextToken());

            if (currentId == bookId) {
                check++;
                String bookName = tokenizer.nextToken();

                int availability = Integer.parseInt(tokenizer.nextToken());

                String availabilityString = Integer.toString(availability + 1);

                int indexx = linebuff.lastIndexOf(";");
              //kitabın mevcut degerini 1 dusurmek ıcın string replace ediyorum
                linebuff.replace(indexx, linebuff.length(), availabilityString);

                System.out.println(bookName + " has returned , Thank You!");

                return true;

            }
        }

        if (check == 0) {
            throw new Exception("No record for this book! "
                    + " For adding a new book pls contact with a library staff!");
        }

        return false;
    }

    /**
     * toString kitaplistesini bastırıyor.Book ids, book names ve mevcut
     * @return string return eder
     */
    @Override
    public String toString() {
        String str = "Book Ids    Book Names     Availability" + "\n";
       
        for (int i = 0; i < theBooks.size(); ++i) {
            String line = theBooks.get(i).toString();
            StringTokenizer tokenizer = new StringTokenizer(line, ";");

            str = str + "#" + tokenizer.nextToken() + " ----> "
                    + tokenizer.nextToken() + " ----> "
                    + tokenizer.nextToken() + "\n";

        }

        str = str + "\n" + "####### END OF THE LIST #######" + "\n";

        return str;
    }

}
