import java.util.ArrayList;
import java.util.List;

public class MagazinePublisher implements Subject {
    private List<Observer> subscribers;  // List of subscribers
    private String magazineName;
    private String currentIssue;

    public MagazinePublisher(String magazineName) {
        this.magazineName = magazineName;
        this.subscribers = new ArrayList<>();  // Initialize the list
    }

    @Override
    public void registerObserver(Observer o) {
        subscribers.add(o);  // Add a subscriber
    }

    @Override
    public void removeObserver(Observer o) {
        subscribers.remove(o);  // Remove a subscriber
    }

    @Override
    public void notifyObservers() {
        for (Observer subscriber : subscribers) {
            subscriber.update(magazineName, currentIssue);  // Notify all subscribers
        }
    }

    public void publishNewIssue(String issue) {
        this.currentIssue = issue;
        notifyObservers();  // New issue is available
    }
}
