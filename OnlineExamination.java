import java.util.*;

class User {
    private String username;
    private String password;
    private String profile;

    public User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void updateProfile(String newProfile) {
        this.profile = newProfile;
        System.out.println("Profile updated successfully.");
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password updated successfully.");
    }

    public String getProfile() {
        return profile;
    }
}

class Exam {
    private List<String> questions;
    private List<String> answers;
    private Map<String, String> userAnswers;
    private int timeLimit; // in seconds

    public Exam(List<String> questions, List<String> answers, int timeLimit) {
        this.questions = questions;
        this.answers = answers;
        this.userAnswers = new HashMap<>();
        this.timeLimit = timeLimit;
    }

    public void startExam(User user) {
        Timer timer = new Timer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exam started. You have " + timeLimit + " seconds.");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Auto-submitting your exam.");
                submitExam(user);
                timer.cancel();
            }
        }, timeLimit * 1000);

        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Q" + (i + 1) + ": " + questions.get(i));
            System.out.print("Enter your answer: ");
            String answer = scanner.nextLine();
            userAnswers.put(questions.get(i), answer);
        }

        timer.cancel();
        submitExam(user);
    }

    private void submitExam(User user) {
        System.out.println("Exam submitted. Thank you for participating, " + user.getUsername() + ".");
    }
}

public class OnlineExamination {
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nOnline Examination System");
            System.out.println("1. Login");
            System.out.println("2. Update Profile");
            System.out.println("3. Update Password");
            System.out.println("4. Start Exam");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    login(username, password);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    User user = users.get(username);
                    if (user != null) {
                        System.out.print("Enter new profile: ");
                        String newProfile = scanner.nextLine();
                        user.updateProfile(newProfile);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    user = users.get(username);
                    if (user != null) {
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        user.updatePassword(newPassword);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 4:
                    List<String> questions = Arrays.asList("What is 2 + 2?", "What is the capital of India?");
                    List<String> answers = Arrays.asList("4", "Delhi");
                    Exam exam = new Exam(questions, answers, 30);
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    user = users.get(username);
                    if (user != null) {
                        exam.startExam(user);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            System.out.println("Login successful. Welcome, " + username + ".");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    static {
        // Adding some default users
        users.put("user1", new User("user1", "pass1", "Profile1"));
        users.put("user2", new User("user2", "pass2", "Profile2"));
        users.put("user3", new User("user3", "pass3", "Profile3"));
        users.put("user4", new User("user4", "pass4", "Profile4"));
        users.put("user5", new User("user5", "pass5", "Profile5"));
    }
}

