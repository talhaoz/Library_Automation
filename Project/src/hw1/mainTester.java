package hw1;

import java.util.*;

/*
 * @author TALHA
 */
public class mainTester {

    private static String temp;

    /**
     *Person objesi bekler fakat libraryuser veya librarystaff a downcast edilir
     * @param newPerson
     */
    static void testMethod(Person newPerson) {
        try {

            newPerson.readTheFiles();
      
            Scanner readTerm = new Scanner(System.in);
            System.out.println("Please enter your id:");
            temp = readTerm.nextLine();
            int personId = Integer.parseInt(temp);

            if (newPerson.checkId(personId)) {
                
                if (newPerson instanceof LibraryUser) {
                    testUser((LibraryUser) newPerson);
                } else if (newPerson instanceof LibraryStaff) {
                    testStaff((LibraryStaff) newPerson);
                }
            } else {
                throw new Exception("Id is wrong!");
            }
        } catch (Exception ex){ 
            System.out.println("Exception : " + ex);
            System.exit(0);
        }
    }

    /**
     * user ı test etmek için test methodu
     * @param newUser
     */
    static void testUser(LibraryUser newUser) {
        
        System.out.println("Signed In as User!\n");
        String tempor = "";
        do {
            System.out.println("Enter 1 for borrow a book \n"
                    + "Enter 2 for return a book\n"
                    + "Enter 3 for see the booklist\n");

            System.out.println("Your choice:");
            Scanner readTerm = new Scanner(System.in);

            temp = readTerm.nextLine();
            int choice = Integer.parseInt(temp);

            if (choice == 1 || choice == 2) {
                try {
                    System.out.println("Please enter the book id:");
                    temp = readTerm.nextLine();
                    int bookId = Integer.parseInt(temp);

                    if (choice == 1) {
                        newUser.borrowBook(bookId);
                    } else {
                        newUser.returnBook(bookId);
                    }
                } catch (Exception ex) {
                    System.out.println("Exception : " + ex);
                    System.exit(0);
                }
            } else if (choice == 3) {
                
                System.out.println(newUser.toString());
            }

            System.out.println("For continue as user enter Y,for exit enter N\n");
            System.out.println("Your choice:");

            tempor = readTerm.nextLine().toUpperCase();
            // kullanıcı cıkmak istemedigi surece devam eder
        } while (!tempor.equals("N"));
        
        newUser.writeTheFiles();
    }

    /**
     *  staff ı test etmek icin method
     * @param newStaff
     */
    static void testStaff(LibraryStaff newStaff) {
        System.out.println("Signed In as Staff!\n");
        String hypertemp  = "";
        do {

            System.out.println("Enter 1 for add new user\n"
                    + "Enter 2 for add a book\n"
                    + "Enter 3 for remove a book\n"
                    + "Enter 4 for see the users list\n"
                    + "Enter 5 for see the booklist\n");

            System.out.println("Your choice:");
            Scanner readTerm = new Scanner(System.in);

            temp = readTerm.nextLine();
            int choice = Integer.parseInt(temp);

            if (choice == 1) {
                System.out.println("Please enter the new User name:");
               
                String userName = readTerm.nextLine();
                
                newStaff.registerNewUser(userName);
                
                
            } else if (choice == 2) {
                try {
                    System.out.println("Please enter the book name:");
                    String bookName = readTerm.nextLine();
                    System.out.println("Please enter how many of it:");
                    temp = readTerm.nextLine();
                    int bookNum = Integer.parseInt(temp);

                    newStaff.addBook(bookName, bookNum);
                } catch (Exception ex) {
                    System.out.println("Exception : " + ex);
                }
            } else if (choice == 3) {
                System.out.println("Please enter the book id that you want to remove:");
                temp = readTerm.nextLine();
                int bookId = Integer.parseInt(temp);

                newStaff.removeBook(bookId);
            } else if (choice == 4) {
                System.out.println(newStaff.toString());
            }else if (choice == 5) {
                System.out.println(newStaff.printBookList());
            }

            System.out.println("For continue as staff enter Y,for go to the uplist enter N\n");
            System.out.println("Your choice:");
            hypertemp = readTerm.nextLine().toUpperCase();
             // kullanıcı cıkmak istemedigi surece devam eder
        }while (!hypertemp.equals("N"));

        newStaff.writeTheFiles();
    }

    /**
     * main , döngü icinde calısır,cıkılmak ıstenene kadar devam eder
     * @param args
     */
    public static void main(String[] args) {
        
        System.out.println("Welcome To The Library\n\n");
        String hypertemp="";
        do {
            System.out.println("Enter 1 for sign in as a user \n"
                    + "Enter 2 for sign in as staff (Staff added before,id >> 21)\n"
                    + "Enter 0 for exit the program\n\n");

            System.out.println("Your choice:");
            Scanner readTerm = new Scanner(System.in);

            //File names
            String usersFile = "usersIds.csv";
            String staffsFile = "staffsIds.csv";
            String booksFile = "books.csv";

            temp = readTerm.nextLine();
            int choice = Integer.parseInt(temp);

            try {

                if (choice == 0) {
                   break;
                } else if (choice == 1) {
                    LibraryUser newUser = new LibraryUser();
                    newUser.setFileNames(usersFile, booksFile);
                    testMethod(newUser);
                    newUser.writeTheFiles();
                } else if (choice == 2) {
                    LibraryStaff newStaff = new LibraryStaff();
                    newStaff.setFileNames(usersFile, staffsFile, booksFile);
                    testMethod(newStaff);
                    newStaff.writeTheFiles();
                }
            } catch (Exception exception) {
                System.out.println("Exception : " + exception);
                System.exit(0);
            }

            System.out.println("For continue enter Y, for exit the library enter N\n");
            System.out.println("Your choice:");
            hypertemp = readTerm.nextLine().toUpperCase();

        } while (!hypertemp.equals("N"));
        
        System.out.println("####### GOOD BYE #######");
    }
}
