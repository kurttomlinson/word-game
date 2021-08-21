import java.util.ArrayList;

class WordGame {
  public static void main(String[] args) {
    String letterBag = "poison";

    Dictionary dictionary = new Dictionary(letterBag);

    ArrayList<String> hiddenWords = dictionary.getLetterBagWords();

    hiddenWords.sort((String a, String b) -> {
      if (a.length() > b.length()) {
        return -1;
      }
      if (a.length() < b.length()) {
        return 1;
      }

      return a.compareTo(b);
    });

    System.out.println("hiddenWords.size()" + ": " + hiddenWords.size());
    System.out.println("hiddenWords" + ": " + hiddenWords);

  }
}
