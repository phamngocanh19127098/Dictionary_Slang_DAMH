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

    public void showWord(String setKey){
        Set<String> result = this.data_word.get(setKey);

        if(result==null){
            System.out.println("The slang does not match with any word in slang dictionary !!!");
            return;
        }
        System.out.println(setKey.toUpperCase()+" -----> "+ result);

    }

    public Set<String> searchWordSlang(String slang_word){
        return data_word.get(slang_word);
    }

    public void readFileSlang(String path){
        data_word = new TreeMap<String, Set<String>>();
        File file = new File(path);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while((line = bufferedReader.readLine())!=null){

                String[] hayStack = line.split("`");
                if(hayStack.length!=2){
                    continue;
                }

                String [] meanList = hayStack[1].split("\\|");
                Set<String> meanSet = new HashSet<String>(List.of(meanList));
                data_word.put(hayStack[0],meanSet);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    List<String> searchByDefinition(String word_define){
        List<String> FoundDef = new ArrayList<>();
        Set<Map.Entry<String ,Set<String>>> entries = data_word.entrySet();
        for(Map.Entry<String, Set<String>> indexEntry : entries){
            Set<String> definition = indexEntry.getValue();
            for(String indexValue : definition){
                String wordUpperCase = word_define.toUpperCase();
                String upperFirstWord = word_define.substring(0,1);
                String lowerWord = word_define.toLowerCase();
                String startWord = upperFirstWord + word_define.substring(1).toLowerCase();
                if(indexValue.contains(wordUpperCase)||indexValue.contains(startWord)
                        ||indexValue.contains(lowerWord)||indexValue.contains(word_define)){
                        FoundDef.add(indexEntry.getKey());
                }
            }
        }
        return FoundDef;
    }
    public static void main(String[] args) {
	// write your code here
        Dictionary_Slang dictionary_slang = new Dictionary_Slang();
        Scanner scanner = new Scanner(System.in);
        String definition_word = scanner.nextLine();
        List<String> mean = dictionary_slang.searchByDefinition(definition_word);
        for(int i = 0;i<mean.size();i++){
            dictionary_slang.showWord(mean.get(i));
        }
      //  String slang_word = scanner.nextLine();
      //  Set <String >set = dictionary_slang.searchWordSlang(slang_word);
       // dictionary_slang.showWord(slang_word);

        //System.out.println(dictionary_slang.data_word);
      // System.out.println(set);
        //set.get(sss);
        // set.get(s)
        // if (s != null) {
            //     System.out.println("\t" + key.toUpperCase() + "\t->\t" + s.toString());
            // }
        // else
        //     System.out.println("No matches");
    }

}
