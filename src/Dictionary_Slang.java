import java.io.*;
import java.util.*;

public class Dictionary_Slang {
    public TreeMap<String, Set<String>> data_word, changes;
    public List<String> history, trash;
    Scanner scanner;

    public Dictionary_Slang(){
        readFileSlang("data/slang.txt");
        loadSlangHistory("activities/search_history.txt");
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
        history.add(slang_word);
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
    public void loadSlangHistory(String path){
        history = new ArrayList<>();
        File file = new File(path);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=bufferedReader.readLine())!=null){
                    history.add(line);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  //  public void addSlangHistory(String word){
  //      history.add(word);
  //  }
    public void saveHistory(String path){
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file);
            for(String i:history){
                fileWriter.write(i+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addSlangWord(){
        System.out.print("Enter slang word: ");
        String slangWord = scanner.nextLine();
        slangWord = slangWord.toUpperCase();
        Set <String>def = new HashSet<>();
        boolean isContain = data_word.containsKey(slangWord);

        if(!isContain){

            int numberOfDef = 0;
            while(true){
                try{
                    System.out.print("Enter the number of mean (0 < number of mean < 6): ");
                    String str = scanner.nextLine();
                    numberOfDef = Integer.parseInt(str);
                    if(numberOfDef<=0||numberOfDef>5){
                        System.out.println("The value must be less than 6 and more than 0!!");
                        continue;
                    }
                    break;
                }
                catch (NumberFormatException ex){
                    System.out.println("The input must be integer, please enter again!!!");
                }
            }

            for(int i = 0;i<numberOfDef;i++){
                System.out.print("Enter definition "+ (i+1)+": ");
                String tempInput = scanner.nextLine();
                def.add(tempInput);

            }

            data_word.put(slangWord,def);

        }
        else {

            int check = 0;
            while (true){
                System.out.println("This slang word already exist in this dictionary, please enter the next action");
                System.out.println("Overwrite it, Enter 1");
                System.out.println("Duplicate it, Enter 2");
                System.out.print("Enter your choice: ");
                try{
                    check = Integer.parseInt(scanner.nextLine());
                }
                catch(NumberFormatException ex){
                    System.out.println("You must enter an integer!!, please enter again");
                }
                if(check<1||check>2){
                    continue;
                }
                break;

            }
            int numberOfDef = 0;
            while(true){
                try{
                    System.out.print("Enter the number of mean (0 < number of mean < 6): ");
                    String str = scanner.nextLine();
                    numberOfDef = Integer.parseInt(str);
                    if(numberOfDef<=0||numberOfDef>5){
                        System.out.println("The value must be less than 6 and more than 0!!");
                        continue;
                    }
                    break;
                }
                catch (NumberFormatException ex){
                    System.out.println("The input must be integer, please enter again!!!");
                }
            }

            for(int i = 0;i<numberOfDef;i++){
                System.out.print("Enter definition "+ (i+1)+": ");
                String tempInput = scanner.nextLine();
                def.add(tempInput);

            }
            if(check==1){
                data_word.put(slangWord,def);
            }
            else {
                Set<String> oldValues = data_word.get(slangWord);
                def.addAll(oldValues);
                data_word.put(slangWord,def);
            }

        }
    }
    public static void main(String[] args) {
	// write your code here
        Dictionary_Slang dictionary_slang = new Dictionary_Slang();
        Scanner scanner = new Scanner(System.in);
       // String definition_word = scanner.nextLine();
       // List<String> mean = dictionary_slang.searchByDefinition(definition_word);
       // for(int i = 0;i<mean.size();i++){
       //     dictionary_slang.showWord(mean.get(i));
       // }
        dictionary_slang.addSlangWord();
        String slang_word = scanner.nextLine();
    //     dictionary_slang.loadSlangHistory("activities/search_history.txt");
        Set <String >set = dictionary_slang.searchWordSlang(slang_word);
        dictionary_slang.showWord(slang_word);
 // String slang_word = scanner.nextLine();
 //  dictionary_slang.loadSlangHistory("activities/search_history.txt");
 // Set <String >set = dictionary_slang.searchWordSlang(slang_word);
//
 //  dictionary_slang.showWord(slang_word);
 //  dictionary_slang.saveHistory("activities/search_history.txt");
 //  dictionary_slang.loadSlangHistory("activities/search_history.txt");
 //  for(String i:dictionary_slang.history){
 //      System.out.println(i);
 //  }
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
