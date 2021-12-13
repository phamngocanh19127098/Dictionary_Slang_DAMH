import java.io.*;
import java.util.*;

public class Dictionary_Slang {
    public TreeMap<String, Set<String>> data_word, changes;
    public List<String> history, trash;
    Scanner scanner;
    public Dictionary_Slang(){
        readFileSlang("data/slang.txt");
        scanner = new Scanner(System.in);
    }
    public Set<String> searchWordSlang(){
        System.out.print("Enter the word that you want to search: ");
        String word_slang = scanner.nextLine();
     //   System.out.println(word_slang);
        return data_word.get(word_slang);
    }
    public void readFileSlang(String path){
        data_word = new TreeMap<String, Set<String>>();
        File file = new File(path);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line ;

            while((line = bufferedReader.readLine())!=null){
                String[] hayStack = line.split("`");
             //   System.out.println(hayStack[0]+" "+hayStack[1]);
                if(hayStack.length!=2){
                    continue;
                }
                String [] meanList = hayStack[1].split("\\|");
                Set<String> meanSet = new HashSet<String>(List.of(meanList));
                (this.data_word).put(hayStack[0],meanSet);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
	// write your code here
        Dictionary_Slang dictionary_slang = new Dictionary_Slang();
        Set <String >set = dictionary_slang.searchWordSlang();
        //System.out.println(dictionary_slang.data_word);
       System.out.println(set);
        //set.get(sss);
        // set.get(s)
        // if (s != null) {
            //     System.out.println("\t" + key.toUpperCase() + "\t->\t" + s.toString());
            // }
        // else
        //     System.out.println("No matches");
    }

}
