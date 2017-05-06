
package hw1;


import java.util.*;
import java.io.*;

/**
 * Bu abstact class person interface ini implement eder ve 
 *                                                     ortak fonksiyonlar icerir
 * @author TALHA
 */
public abstract class PersonAbstract implements Person
{   
    
    /**
     * filename ve bir list alır listedekileri file a yazar
     * @param fileName
     * @param lines 
     */
    public void writeFile(String fileName, ArrayList<StringBuffer> lines)
    {   
        try{
            FileWriter myFile=new FileWriter(fileName);

            BufferedWriter writeFile = new BufferedWriter(myFile);

            for(int i=0; i<lines.size(); ++i)
            {
                String line=lines.get(i).toString();
                line=line + "\n";
                // writing line to the file
                writeFile.write(line);

            }
            
            // stream i temizle
            writeFile.flush();

            // stream i kapat
            writeFile.close();
        }
        catch(Exception ex)
        {   System.err.println("**********");
            System.out.println("Exception : " + ex);
            System.exit(0);
        }
    }

}
