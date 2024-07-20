public interface Subject {
    void registerObserver(Observer o);  // Add a subscriber
    void removeObserver(Observer o);    // Remove a subscriber
    void notifyObservers();             // Notify all subscribers
}
