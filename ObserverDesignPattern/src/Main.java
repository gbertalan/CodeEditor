public class Main {
    public static void main(String[] args) {
        MagazinePublisher techMag = new MagazinePublisher("Tech Magazine");
        
        Subscriber subscriber1 = new Subscriber("Alice", techMag);
        Subscriber subscriber2 = new Subscriber("Bob", techMag);

        techMag.publishNewIssue("July 2024 Issue");  // Publish new magazine issue
        techMag.publishNewIssue("August 2024 Issue");  // Another issue
    }
}
