package tracker;

public enum CourseType {
    JAVA(0, "Java", 600),
    DSA(1, "DSA", 400),
    SPRING(3, "Spring", 550),
    DATABASES(2, "Databases", 480);

    final String name;
    final int courseNumber;

    final int passScore;

    CourseType(int courseNumber, String name, int passScore) {
        this.courseNumber = courseNumber;
        this.name = name;
        this.passScore = passScore;
    }

    public String getName() {
        return name;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public int getPassScore() { return passScore; }

    public static CourseType findByCourseNumber(int courseNumber) {
        for (CourseType value : values()) {
            if (value.courseNumber == courseNumber) {
                return value;
            }
        }
        return null;
    }
}
