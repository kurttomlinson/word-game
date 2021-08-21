import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Dictionary source: https://github.com/first20hours/google-10000-english
// google-10000-english-usa.txt

public class Dictionary {
  private HashMap<Character, Integer> countByLetterForLetterBag = new HashMap<>();

  private static ArrayList<String> dictionaryWords = new ArrayList<>();
  private static String dictionaryFileName = "dictionary.txt";

  public Dictionary(String letterBag) {
    loadDictionary();
    this.countByLetterForLetterBag = countCharacters(letterBag.toLowerCase());
  }

  public ArrayList<String> getLetterBagWords() {
    ArrayList<String> result = new ArrayList<>();

    for (String candidateWord : dictionaryWords) {
      if (letterBagContainsWord(candidateWord)) {
        result.add(candidateWord);
      }
    }

    return result;
  }

  private boolean letterBagContainsWord(String word) {
    HashMap<Character, Integer> countByLetterForWord = new HashMap<>();
    for (int i = 0; i < word.length(); i++) {
      char character = word.charAt(i);
      if (!countByLetterForLetterBag.containsKey(character)) {
        return false;
      }

      if (!countByLetterForWord.containsKey(character)) {
        countByLetterForWord.put(character, 0);
      }
      int oldValue = countByLetterForWord.get(character);
      countByLetterForWord.put(character, oldValue + 1);

      if (countByLetterForWord.get(character) > countByLetterForLetterBag.get(character)) {
        return false;
      }
    }

    return true;
  }

  private HashMap<Character, Integer> countCharacters(String string) {
    HashMap<Character, Integer> countByLetter = new HashMap<>();

    for (int i = 0; i < string.length(); i++) {
      char character = string.charAt(i);
      if (!countByLetter.containsKey(character)) {
        countByLetter.put(character, 0);
      }

      int oldValue = countByLetter.get(character);
      countByLetter.put(character, oldValue + 1);
    }

    return countByLetter;
  }

  private void loadDictionary() {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dictionaryFileName))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (line.length() > 2) {
          dictionaryWords.add(line.toLowerCase());
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
      System.exit(1);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }
}
