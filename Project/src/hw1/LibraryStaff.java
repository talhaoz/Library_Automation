

package hw1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Bu class personabstract i extend eder
 * @author TALHA
 */
public class LibraryStaff extends PersonAbstract {
    
    private String usersId;
    private String staffsId;
    private String books;

    private ArrayList<StringBuffer> theStaffs;
    private ArrayList<StringBuffer> theUsers;
    private ArrayList<StringBuffer> theBooks;

    /**
     * filename leri atar
     * @param users
     * @param staffs
     * @param bookLib
     */
    public void setFileNames(String users, String staffs, String bookLib) {
        usersId = users;
        staffsId = staffs;
        books = bookLib;
    }
   /**
    * constructor
    */
    public LibraryStaff() {
        theUsers = new ArrayList<StringBuffer>();
        theBooks = new ArrayList<StringBuffer>();
        theStaffs = new ArrayList<StringBuffer>();

    }
    
    /**
     *  gönderilen file ları okur
     * @param fileName
     * @param lines 
     */
    public void readFile(String fileName, ArrayList<StringBuffer> lines) {
        
        try {
            FileReader myFile = new FileReader(fileName);

            BufferedReader theFile = new BufferedReader(myFile);

            String newLine;
            StringBuffer newBufferLine = new StringBuffer();
            
            while ((newLine = theFile.readLine()) != null) {

                newBufferLine = new StringBuffer(newLine); //converting to StringBuffer

                lines.add(new StringBuffer(newLine));

            }
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            System.exit(0);
        }

    }

    /**
     *
     * @param personId
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkId(int personId) throws Exception {

        if (personId < 0) {
            throw new Exception("Entered id cant be lower than zero!");
        }
        
        for (int i = 0; i < theStaffs.size(); ++i) {
            String line = theStaffs.get(i).toString();
            StringTokenizer tokenizer = new StringTokenizer(line, ";");

            int currentId = Integer.parseInt(tokenizer.nextToken());

            if (currentId == personId) {
                return true;
            }

        }

        return false;

    }

    /**
     *
     */
    @Override
    public void readTheFiles() 
    {   
        readFile(staffsId, theStaffs);  
        readFile(usersId, theUsers);
        readFile(books, theBooks);
        
    }

    /**
     *
     */
    @Override
    public void writeTheFiles() {
        writeFile(usersId, theUsers);
        writeFile(books, theBooks);
    }

    /**
     * random yeni id yapar ve ve daha önceden varmı diye kontrol eder 
     *                                          eger varsa baska bir tane yapar
     * @param lines
     * @return id return eder
     */
    public int newIdMaker(ArrayList<StringBuffer> lines) {
        Random randNum = new Random();
        int newId = randNum.nextInt(2000) + 1;
        
        for (int i = 0; i < lines.size(); ++i) {
            String line = lines.get(i).toString();
            StringTokenizer tokenizer = new StringTokenizer(line, ";");

            int theId = Integer.parseInt(tokenizer.nextToken());

            if (theId == newId) {
                newId = randNum.nextInt(2000) + 1;
                i = -1;
            }
        }

        return newId;
    }

    /**
     * yeni kitap ekler
     * @param bookName
     * @param bookNum
     * @throws Exception
     */
    public void addBook(String bookName, int bookNum) throws Exception {
        
        if (bookNum <= 0) {
            throw new Exception("Please enter at least 1 book!");
        }

        int newId = newIdMaker(theBooks);

        String newBook = newId + ";" + bookName + ";" + bookNum;

        StringBuffer newBookBuff = new StringBuffer(newBook);
        theBooks.add(newBookBuff);

        System.out.println(bookName + " has added to the library!");

    }

    /**
     * kitap kaldırır
     * @param bookId
     */
    public void removeBook(int bookId) {
        int flag = 0;

        for (int i = 0; (i < theBooks.size()) && flag != 1; ++i) {
            String line = theBooks.get(i).toString();
            StringTokenizer tokenizer = new StringTokenizer(line, ";");

            int currentId = Integer.parseInt(tokenizer.nextToken());

            if (currentId == bookId) {
                theBooks.remove(i);
                flag++;
                System.out.println("The book has removed!");
            }

        }
    }
    
    /**
     * kitap listesini basar
     * @return string
     */
    public String printBookList(){
            String str =  "Book Ids      Book Names     Availability" + "\n";

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

    /**
     * yeni user
     * @param personName
     */
    public void registerNewUser(String personName) {
        
        int newId = newIdMaker(theUsers);
        
        String newUser = newId + ";" + personName;

        StringBuffer newUserBuff = new StringBuffer(newUser);
        theUsers.add(newUserBuff);

        System.out.println("UserId: #" + newId + "  " + personName + " has added as a User!\n");

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String str = "\nUsers Ids and Users Names" + "\n";
        
        for (int i = 0; i < theUsers.size(); ++i) {
            String line = theUsers.get(i).toString();
            StringTokenizer tokenizer = new StringTokenizer(line, ";");

            str = str + "#" + tokenizer.nextToken() + " -------> "
                    + tokenizer.nextToken() + "\n";

        }

        str = str + "\n" + "####### END OF THE LIST #######" + "\n";

        return str;
    }

}
