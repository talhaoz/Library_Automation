
package hw1;

/**
 * interface 
 * @author TALHA
 */
public interface Person
{

    /**
     *
     * @param personId
     * @return checkid prototipi yorumları yazıldı*
     * @throws Exception
     */
    public boolean checkId(int personId) throws Exception;
    
    public void readTheFiles();
    public void writeTheFiles();
    
}
