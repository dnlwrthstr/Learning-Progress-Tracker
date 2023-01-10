package tracker;

import java.util.*;
import java.util.stream.Collectors;

class CourseStudentStatistic {

    CourseType courseType;
    Integer studentId;
    Integer score;
    Double percentageCompleted;

    public CourseStudentStatistic(CourseType courseType, Integer studentId, Integer score, Double percentageCompleted) {
        this.courseType = courseType;
        this.studentId = studentId;
        this.score = score;
        this.percentageCompleted = percentageCompleted;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getScore() {
        return score;
    }

    public Double getPercentageCompleted() {
        return percentageCompleted;
    }

}

class CourseStudentComparator implements Comparator<CourseStudentStatistic> {
    public int compare(CourseStudentStatistic s1, CourseStudentStatistic s2) {
        if (Objects.equals(s1.score, s2.score)) {
            if (Objects.equals(s1.studentId, s2.studentId)) {
                return 0;
            } else {
                return s1.studentId > s2.studentId ? -1 : 1;
            }
        } else {
            return s1.score > s2.score ? 1 : -1;
        }
    }
}

public class Course {

    private static List<CourseStudentStatistic> retrieveCourseStudentStatistic() {
        List<CourseStudentStatistic> courseStudentStatistics = new ArrayList<>();
        for (Student s : Student.getStudents()) {
            Map<CourseType, Integer> courseScores = s.getCourseScores();
            courseScores.forEach((courseType, score) -> {
                double percentageCompleted = 100 * score / (double) courseType.getPassScore();
                courseStudentStatistics.add(new CourseStudentStatistic(courseType, s.getId(), score, percentageCompleted));
            });
        }
        return courseStudentStatistics;
    }

    public static List<CourseStudentStatistic> getCourseStudentStatistic(CourseType courseType) {
        return retrieveCourseStudentStatistic().
                stream().
                filter(courseStudentStatistic -> courseStudentStatistic.getCourseType() == courseType &&
                        (courseStudentStatistic.getScore() > 0))
                .collect(Collectors.toList());
    }

    public static void printStatistics(CourseType courseType) {
        System.out.println(courseType.getName());
        System.out.println("id\t\tpoints\tcompleted");
        List<CourseStudentStatistic> unsorted = getCourseStudentStatistic(courseType);

        unsorted.sort(new CourseStudentComparator().reversed());

        unsorted.forEach(css ->
                System.out.printf("%d\t%d\t\t%.1f%%\n", css.getStudentId(), css.getScore(), css.getPercentageCompleted()));
    }


    public static void showStatistics() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        new MostPopular().printStatistic();
        new LeastPopular().printStatistic();
        new HighestActivity().printStatistic();
        new LowestActivity().printStatistic();
        new EasiestCourse().printStatistic();
        new HardestCourse().printStatistic();

        do {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine().strip();
            if (Objects.equals(line, "back")) {
                break;
            }
            try {
                CourseType courseType = CourseType.valueOf(line.toUpperCase());
                switch (courseType) {
                    case JAVA:
                        Course.printStatistics(CourseType.JAVA);
                        break;
                    case DSA:
                        Course.printStatistics(CourseType.DSA);
                        break;
                    case DATABASES:
                        Course.printStatistics(CourseType.DATABASES);
                        break;
                    case SPRING:
                        Course.printStatistics(CourseType.SPRING);
                        break;
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Unknown course.");
            }
        } while (true);
    }
}
