import java.io.*;
import java.util.*;

public class Dictionary_Slang {
    public TreeMap<String, Set<String>> data_word, add_history,edit_history;
    public List<String> history, delete_history;
    Scanner scanner;
    String thisDaySlangWord ;
    public Dictionary_Slang(){
        readFileSlang("data/slang.txt");
        loadSlangHistory("activities/search_history.txt");
        loadAdd_history("activities/add.txt");
        loadDelete("activities/delete.txt");
        loadEdit_history("activities/edit.txt");
        thisDaySlangWord = randomASlangWord();
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
    public void loadAdd_history(String path){
        add_history = new TreeMap<String,Set<String>>();
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br= new BufferedReader(fr);
            String line ;
            while((line=br.readLine())!=null){
                String[] splitedArray = line.split("`");
                if(line.length()!=2){
                    continue;
                }
                String [] rawDef = splitedArray[1].split("\\|");
                Set<String> setWord = new HashSet<String>(List.of(rawDef));
                add_history.put(splitedArray[0],setWord);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadEdit_history(String path){
        edit_history = new TreeMap<String,Set<String>>();
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br= new BufferedReader(fr);
            String line ;
            while((line=br.readLine())!=null){
                String[] splitedArray = line.split("`");
                if(line.length()!=2){
                    continue;
                }
                String [] rawDef = splitedArray[1].split("\\|");
                Set<String> setWord = new HashSet<String>(List.of(rawDef));
                edit_history.put(splitedArray[0],setWord);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadDelete(String path){
        delete_history = new ArrayList<>();
        File file = new File(path);
        try {
            FileReader fr= new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line ;
            while ((line = br.readLine())!=null){
                delete_history.add(line);
            }
            fr.close();
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
    public void saveAddHistory(String path){
        File file = new File(path);

        try {
            FileWriter fw = new FileWriter(file);
            Set<Map.Entry<String,Set<String>>> entrySet = add_history.entrySet();
            for(Map.Entry<String,Set<String>> indexSet:entrySet){
                fw.write(indexSet.getKey()+"`");
                Set <String> saved;
                saved = indexSet.getValue();
                int size = saved.size();
                for(String indexString : saved){
                    size--;
                    if(size!=0){
                        fw.write(indexString+"|");

                    }
                    else {
                        fw.write(indexString+"\n");
                    }
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveEditHistory(String path){
        File file = new File(path);

        try {
            FileWriter fw = new FileWriter(file);
            Set<Map.Entry<String,Set<String>>> entrySet = edit_history.entrySet();
            for(Map.Entry<String,Set<String>> indexSet:entrySet){
                fw.write(indexSet.getKey()+"`");
                Set <String> saved;
                saved = indexSet.getValue();
                int size = saved.size();
                for(String indexString : saved){
                    size--;
                    if(size!=0){
                        fw.write(indexString+"|");

                    }
                    else {
                        fw.write(indexString+"\n");
                    }
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void saveDeleteHistory(String path){
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file);
            for(String i:delete_history){
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
            add_history.put(slangWord,def);
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


    public void editSlangWord(){
        System.out.print("Enter the slang word you want to edit: ");
        String word = scanner.nextLine();
        if(data_word.containsKey(word)){
         //
         //
            Set <String>def = new HashSet<>();
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
            data_word.put(word,def);
            edit_history.put(word,def);
        }
        else {
            System.out.println("word not exist in this dictionary !!!");
        }


    }
    public boolean deleteSlangWord(){
            System.out.println("Enter slang word you want to delete");
            String word = scanner.nextLine();

            if(data_word.containsKey(word)){
                int num=0;
                while(true){
                    try{
                        System.out.print("Do you want to delete this slang word (1)YES, (2)NO: ");
                        String str = scanner.nextLine();
                        num = Integer.parseInt(str);
                    }
                    catch (NumberFormatException ex){
                        System.out.println("You must enter a integer, please enter again!!!");
                    }
                    if(num<1||num>2){
                        continue;
                    }
                    break;
                }
                if(num==1){
                    word = word.toUpperCase();
                    if(data_word.remove(word)!=null){
                        delete_history.add(word);
                        return true;
                    }

                }
            }
            System.out.println("Slang word does not exist in this dictionary");

           return false;
    }
    public void resetDictionary(){
        int num=0;
        while(true){
            try{
                System.out.print("Do you want to reset this dictionary (1)YES, (2)NO: ");
                String str = scanner.nextLine();
                num = Integer.parseInt(str);
            }
            catch (NumberFormatException ex){
                System.out.println("You must enter a integer, please enter again!!!");
            }
            if(num<1||num>2){
                System.out.println("The input must be 1 or 2");
                continue;
            }
            break;
        }
        if(num==1){
            readFileSlang("data/slang.txt");
            edit_history.clear();
            add_history.clear();
            delete_history.clear();
        }

    }
    public String randomASlangWord(){
        Object[] convertedToArray = data_word.keySet().toArray();
        return (String)convertedToArray[new Random().nextInt(convertedToArray.length)];
    }
    public Map<String,Set<String>> generateResultQuiz(){
        Map<String, Set<String>> result = new HashMap<String,Set<String>>();
        for(int i=0;i<4;i++){
            String wordRandom = randomASlangWord();
            Set<String> def = data_word.get(wordRandom);
            result.put(wordRandom,def);
        }
        return result;
    }
    public void generateSlangQuiz(){
        System.out.println("\n<---------- You're participate in SLANG QUIZ ---------->");
        Map<String, Set<String>> QuizSet = generateResultQuiz();
        int question = new Random().nextInt(4)+1;
        String SavedQuestion="";
        Set<String> SavedAnswer =new HashSet<>();
        int answer = 0;
        int count = 0;
        for(Map.Entry<String,Set<String>> indexEntry : QuizSet.entrySet()){
            count++;
            System.out.println(count+". "+indexEntry.getValue());
            if(count==question){
                answer=count;
                SavedQuestion = indexEntry.getKey();
                SavedAnswer = indexEntry.getValue();
            }
        }
        System.out.println("The question is "+SavedQuestion);

        int choice = 0;

        while(true){
            try {
                System.out.print("Enter your choice (from 1 to 4): ");
                choice = Integer.parseInt(scanner.nextLine());

            }
            catch (NumberFormatException ex){
                System.out.println("You must enter an integer, please try again!!");
            }
            if(choice<1||choice>4){
                System.out.println("The value must be an integer from 1 to 4");
                continue;
            }
            break;
        }
        if(choice==answer){
            System.out.println("You're right");
        }
        else{
            System.out.println("\nYou're wrong");
            System.out.println("The answer is "+SavedAnswer );
        }
    }
    public void generateDefinitionQuiz(){
        System.out.println("\n<---------- You're participate in DEFINITION QUIZ ---------->");
        Map<String, Set<String>> DefSet = generateResultQuiz();
        int question = new Random().nextInt(4)+1;

        String SavedAnswer="";
        Set<String> Questions =new HashSet<>();
        int answer = 0;
        int count = 0;
        for(Map.Entry<String,Set<String>> indexEntry : DefSet.entrySet()){
            count++;
            System.out.println(count+". "+indexEntry.getKey());
            if(count==question){
                answer=count;
                Questions= indexEntry.getValue();
               SavedAnswer = indexEntry.getKey();
            }
        }
        System.out.println("The question is "+Questions);

        int choice = 0;

        while(true){
            try {
                System.out.print("Enter your choice (from 1 to 4): ");
                choice = Integer.parseInt(scanner.nextLine());

            }
            catch (NumberFormatException ex){
                System.out.println("You must enter an integer, please try again!!");
            }
            if(choice<1||choice>4){
                System.out.println("The value must be an integer from 1 to 4");
                continue;
            }
            break;
        }
        if(choice==answer){
            System.out.println("You're right");
        }
        else{
            System.out.println("\nYou're wrong");
            System.out.println("The answer is "+SavedAnswer );
        }
    }
    public void GUI(){
        while (true){
            System.out.println("\n<----------------------------------------------------------------------->");
            System.out.println("1.  Slang Search");
            System.out.println("2.  Definition Search");
            System.out.println("3.  Show Slang Search History");
            System.out.println("4.  Add Slang");
            System.out.println("5.  Edit Slang");
            System.out.println("6.  Delete Slang");
            System.out.println("7.  Reset dictionary");
            System.out.println("8.  On this day slang word");
            System.out.println("9.  Slang quiz");
            System.out.println("10.  Definition quiz");
            System.out.println("0.  Exit");
            System.out.print("Enter a number to select task: ");
            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                    System.out.print("Enter slang word : ");
                    String word = scanner.nextLine();
                    searchWordSlang(word);
                    showWord(word);
                    break;
                case "2":
                    System.out.print("Enter definition: ");
                    String def = scanner.nextLine();
                    List<String>result = searchByDefinition(def);
                    int lengthResult = result.size();
                    if(lengthResult!=0){
                        System.out.println("Find "+lengthResult+" slang word");
                        for(String i:result){
                            showWord(i);
                        }
                    }
                    else {
                        System.out.println("There no result to display");
                    }
                    break;
                case "3":
                    for(String i : history){
                        System.out.println(i);
                    }
                    break;
                case "4":
                    addSlangWord();
                    break;
                case "5":
                    editSlangWord();
                    break;
                case "6":
                    deleteSlangWord();
                    break;
                case "7":
                    resetDictionary();
                    break;
                case "8":
                    showWord(thisDaySlangWord);
                    break;
                case "9":
                    generateSlangQuiz();
                    break;
                case "10":
                    generateDefinitionQuiz();
                    break;
                case "0":
                    saveHistory("activities/search_history.txt");
                    saveAddHistory("activities/add.txt");
                    saveDeleteHistory("activities/delete.txt");
                    saveEditHistory("activities/edit.txt");
                    return;
            }

        }

    }
    public static void main(String[] args) {
        Dictionary_Slang dictionary_slang = new Dictionary_Slang();
        dictionary_slang.GUI();

    }

}
