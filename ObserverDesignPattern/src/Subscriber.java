public class Subscriber implements Observer {
    private String subscriberName;

    public Subscriber(String subscriberName, Subject magazinePublisher) {
        this.subscriberName = subscriberName;
        magazinePublisher.registerObserver(this);  // Subscribe to the magazine company
    }

    @Override
    public void update(String magazineName, String issue) {
        System.out.println(subscriberName + " received " + magazineName + " issue: " + issue);
    }
}
