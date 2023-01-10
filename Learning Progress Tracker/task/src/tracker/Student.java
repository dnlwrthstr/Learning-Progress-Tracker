package tracker;

import java.util.*;

public class Student {
    static final String patternName = "\\b[A-Za-z]+([A-Za-z]|['\\-\\s](?!['\\-)]))*[A-Za-z]+\\b";
    // REGEX - "[A-Za-z]+([A-Za-z]|['\-\s](?!['\-)]))*[A-Za-z]+"
    @SuppressWarnings({"escape", "RegExpRedundantEscape"})
    static final String patternEmail = "\\b[A-Za-z0-9\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9]+\\b";
    // REGEX - "\b[A-Za-z0-9\\.]+@[A-Za-z0-9\-]+\.[A-Za-z0-9]+\b"

    static List<Student> students = new ArrayList<>();

    private static int ID = 10000;
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Map<CourseType, List<Integer>> scores = new LinkedHashMap<>();

    private final List<CourseType> completedCourses = new LinkedList<>();

    private Student(String firstName, String lastName, String email) {
        this.id = Student.getNextId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static boolean isEmailUnique(String email) {
        for (Student s : students) {
            if (Objects.equals(s.getEmail(), email)) {
                return false;
            }
        }
        return true;
    }

    public List<CourseType> checkCourseCompleted() {
        List<CourseType> obtained = new LinkedList<>();
        for (CourseType courseType : scores.keySet()) {
            int score = scores.get(courseType)
                    .stream()
                    .mapToInt(v -> v)
                    .sum();
            if (score >= courseType.getPassScore()) {
                if (!completedCourses.contains(courseType)) {
                    completedCourses.add(courseType);
                    obtained.add(courseType);
                }
            }
        }
        return obtained;
    }


    public static boolean addStudent(String firstName, String lastName, String email) {
        boolean isAdded = false;
        if (!firstName.matches(patternName)) {
            System.out.println("Incorrect first name.");
        } else if (!lastName.strip().matches(patternName)) {
            System.out.println("Incorrect last name.");
        } else if (!email.matches(patternEmail)) {
            System.out.println("Incorrect email.");
        } else if (!isEmailUnique(email)) {
            System.out.println("This email is already taken.");
        } else {
            students.add(new Student(firstName, lastName, email));
            System.out.println("The student has been added.");
            isAdded = true;
        }
        return isAdded;
    }

    public static void getCredentials() {
        System.out.println("Enter student credentials or 'back' to return:");
        do {
            Scanner scanner = new Scanner(System.in);
            String[] line = scanner.nextLine().split(" ");
            if (line.length == 1 && line[0].equals("back")) {
                System.out.printf("Total %d students have been added.\n", students.size());
                break;
            }
            if (line.length >= 3) {
                String firstName = line[0];
                StringBuilder lastName = new StringBuilder().append(line[1]);
                for (int i = 2; i < line.length - 1; i++) {
                    lastName.append(" ");
                    lastName.append(line[i]);
                }
                String email = line[line.length - 1];
                addStudent(firstName, lastName.toString(), email);
            } else {
                System.out.println("Incorrect credentials.");
            }
        } while (true);
    }

    public static void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students:");
            for (Student s : students) {
                System.out.println(s.getId());
            }
        }
    }

    public void updateScore(int id, int[] scores) {
        Student s = Student.getStudentById(id);
        if (s != null) {
            for (int i = 0; i < scores.length; i++) {
                //if (scores[i] != 0) {
                CourseType course = CourseType.findByCourseNumber(i);
                updateScore(course, scores[i]);
                //}
            }
        }
    }

    public void updateScore(CourseType course, int score) {
        List<Integer> scoreList = scores.getOrDefault(course, new ArrayList<>());
        scoreList.add(score);
        scores.put(course, scoreList);
    }

    public static void updateScore(String input) {
        String[] numbers = input.split(" ");
        try {
            if (input.matches("^[0-9A-Za-z]{0,50}(\\s[0-9]{1,10}){4}")) {

                int id = Integer.parseInt(numbers[0]);
                Student student = Student.getStudentById(id);
                if (student == null) {
                    System.out.printf("No student is found for id=%d.\n", id);
                } else {
                    int score1 = Integer.parseInt(numbers[1]);
                    int score2 = Integer.parseInt(numbers[2]);
                    int score3 = Integer.parseInt(numbers[3]);
                    int score4 = Integer.parseInt(numbers[4]);
                    int[] scores = new int[]{score1, score2, score3, score4};
                    student.updateScore(id, scores);
                    System.out.println("Points updated.");
                }
            } else {
                System.out.println("Incorrect points format.");
            }
        } catch (NumberFormatException ex) {
            System.out.printf("No student is found for id=%s.\n", numbers[0]);
        }
    }

    public static void addScore() {
        System.out.println("Enter an id and points or 'back' to return:");
        boolean isBack = false;
        do {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Objects.equals(input, "back")) {
                isBack = true;
            } else {
                updateScore(input);
            }
        } while (!isBack);
    }

    public static void findStudent(String input) {
        if (input.matches("^[0-9]{0,50}")) {
            int id = Integer.parseInt(input);
            Student s = Student.getStudentById(id);
            if (s == null) {
                System.out.printf("No student is found for id=%d.\n", id);
            } else {
                s.printScore();
            }
        } else {
            //System.out.println("Incorrect id format.");
            String[] stringId = input.split(" ");
            System.out.printf("No student is found for id=%s.\n", stringId[0]);
        }
    }

    public static void find() {
        System.out.println("Enter an id or 'back' to return:");
        boolean isBack = false;
        do {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Objects.equals(input, "back")) {
                isBack = true;
            } else {
                findStudent(input);
            }
        } while (!isBack);
    }

    public static int getNextId() {
        return ID++;
    }

    public static Student getStudentById(int id) {
        Student student = null;
        for (Student s : students) {
            if (id == s.getId()) {
                student = s;
                break;
            }
        }
        return student;
    }

    public static List<Student> getStudents() {
        return students;
    }

    public Map<CourseType, List<Integer>> getScores() {
        return scores;
    }

    public static Student getStudentByEmail(String email) {
        Student student = null;
        for (Student s : students) {
            if (Objects.equals(email, s.getEmail())) {
                student = s;
                break;
            }
        }
        return student;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public String getFirstName() {
        return firstName;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public String getLastName() {
        return lastName;
    }

    public static void clearStudents() {
        ID = 10000;
        students.clear();
    }

    public Map<CourseType, Integer> getCourseScores() {
        Map<CourseType, List<Integer>> scoreMap = getScores();
        Map<CourseType, Integer> courseScore = new LinkedHashMap<>();
        scoreMap.forEach((courseType, value) ->
                courseScore.put(courseType, value.stream().mapToInt(i -> i)
                        .sum()));
        return courseScore;
    }

    public void printScore() {
        System.out.printf("%d points: ", id);
        for (int i = 0; i < CourseType.values().length; i++) {
            CourseType courseType = CourseType.findByCourseNumber(i);
            String courseName = courseType == null ? "" : courseType.getName();
            List<Integer> scoreList =
                    scores.getOrDefault(CourseType.findByCourseNumber(i), new LinkedList<>() {
                    });
            Integer score = scoreList
                    .stream()
                    .mapToInt(v -> v)
                    .sum();
            System.out.printf("%s=%d", courseName, score);
            if (CourseType.values().length - 1 != i) {
                System.out.print("; ");
            } else {
                System.out.print("\n");
            }
        }
    }
}
