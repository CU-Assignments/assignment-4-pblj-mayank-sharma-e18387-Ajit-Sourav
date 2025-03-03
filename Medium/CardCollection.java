import java.util.*;

class CardCollection {
    private final Map<String, List<String>> cardMap = new HashMap<>();

    public void addCard(String symbol, String card) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }
}

public class CardCollectionSystem {
    public static void main(String[] args) {
        CardCollection collection = new CardCollection();
        Scanner scanner = new Scanner(System.in);

        collection.addCard("Hearts", "Ace of Hearts");
        collection.addCard("Hearts", "King of Hearts");
        collection.addCard("Spades", "Queen of Spades");
        collection.addCard("Diamonds", "Jack of Diamonds");
        collection.addCard("Clubs", "10 of Clubs");
        collection.addCard("Spades", "2 of Spades");

        System.out.print("Enter symbol to search (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();
        
        List<String> cards = collection.getCardsBySymbol(symbol);
        if (cards.isEmpty()) {
            System.out.println("No cards found for symbol: " + symbol);
        } else {
            System.out.println("Cards in " + symbol + ": " + cards);
        }
    }
}
