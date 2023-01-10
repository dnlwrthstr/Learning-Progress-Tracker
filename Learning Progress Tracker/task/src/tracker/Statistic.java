package tracker;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.max;
import static java.util.Collections.min;

public abstract class Statistic {
    String statisticName;

    public abstract String[] retrieve();

    public void printStatistic() {
        String[] result = retrieve();
        System.out.print(statisticName + ": ");
        if (result.length == 0) {
            System.out.print("n/a\n");
        } else {
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i]);
                if (i == result.length - 1) {
                    System.out.print("\n");
                } else {
                    System.out.print(", ");
                }
            }
        }
    }


    protected static Map<CourseType, Integer> getCourseEnrollments() {
        Map<CourseType, Integer> courseActivity = new HashMap<>();
        for (CourseType courseType : CourseType.values()) {
            courseActivity.put(courseType, 0);
        }
        List<Student> students = Student.getStudents();
        for (Student student : students) {
            Map<CourseType, List<Integer>> scores = student.getScores();
            courseActivity.forEach((course, enrollments) -> {
                List<Integer> scoreList = scores.getOrDefault(course, new ArrayList<>() {
                });
                int enrolled = (scoreList
                        .stream()
                        .mapToInt(v -> v)
                        .sum()) > 0 ? 1 : 0;
                int oldEnrollments = courseActivity.getOrDefault(course, 0);
                courseActivity.put(course, oldEnrollments + enrolled);
            });
        }
        return courseActivity;
    }


    protected static Map<CourseType, List<Integer>> getScoreListByCourse() {
        Map<CourseType, List<Integer>> scoreListByCourse = new HashMap<>();
        for (CourseType courseType : CourseType.values()) {
            scoreListByCourse.put(courseType, new ArrayList<>() {
            });
            for (Student student : Student.getStudents()) {
                List<Integer> scoreList = new ArrayList<>();
                List<Integer> studentScoreList = student.getScores().getOrDefault(courseType, new ArrayList<>());
                scoreList.addAll(studentScoreList);
                scoreList.addAll(scoreListByCourse.get(courseType));
                scoreListByCourse.put(courseType, scoreList);
            }
        }
        return scoreListByCourse;
    }

    protected static Map<CourseType, Integer> getCourseActivity() {
        Map<CourseType, List<Integer>> scoreListByCourse = getScoreListByCourse();
        Map<CourseType, Integer> courseActivity = new HashMap<>();
        for (CourseType courseType : scoreListByCourse.keySet()) {
            int activity = scoreListByCourse.get(courseType)
                    .stream()
                    .mapToInt(v -> v > 0 ? 1 : 0)
                    .sum();
            courseActivity.put(courseType, activity);
        }

        return courseActivity;
    }
}

abstract class Enrollments extends Statistic {

    public abstract String[] retrieve();


    protected List<CourseType> minEnrollmentsCourse() {
        Map<CourseType, Integer> enrollments = getCourseEnrollments();
        Integer value = enrollments.get(min(enrollments.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
        return enrollments
                .keySet()
                .stream()
                .filter(k -> Objects.equals(value, enrollments.get(k)) && value != 0)
                .collect(Collectors.toList());
    }

    protected List<CourseType> maxEnrollmentsCourse() {
        Map<CourseType, Integer> enrollments = getCourseEnrollments();
        Integer value = enrollments.get(max(enrollments.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
        return enrollments
                .keySet()
                .stream()
                .filter(k -> Objects.equals(value, enrollments.get(k)) && value != 0)
                .collect(Collectors.toList());
    }

    public List<CourseType> getMostPopular() {
        List<CourseType> mostPopular;
        mostPopular = maxEnrollmentsCourse();
        //mostPopular.removeAll(minEnrollmentsCourse());
        return mostPopular;
    }

    public List<CourseType> getLeastPopular() {
        List<CourseType> leastPopular;
        leastPopular = minEnrollmentsCourse();
        leastPopular.removeAll(maxEnrollmentsCourse());
        return leastPopular;
    }

}

class MostPopular extends Enrollments {

    private static final String statisticName = "Most popular";

    MostPopular() {
        super.statisticName = statisticName;
    }

    @Override
    public String[] retrieve() {
        return getMostPopular()
                .stream()
                .map(CourseType::getName).toArray(String[]::new);
    }
}

class LeastPopular extends Enrollments {
    private static final String statisticName = "Least popular";

    LeastPopular() {
        super.statisticName = statisticName;
    }

    @Override
    public String[] retrieve() {
        return getLeastPopular()
                .stream()
                .map(CourseType::getName).toArray(String[]::new);
    }
}

abstract class Activity extends Statistic {

    protected List<CourseType> minActivity() {
        Map<CourseType, Integer> minActivity = getCourseActivity();
        Integer value = minActivity.get(min(minActivity.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
        return minActivity
                .keySet()
                .stream()
                .filter(k -> Objects.equals(value, minActivity.get(k)) && value != 0)
                .collect(Collectors.toList());
    }

    protected List<CourseType> maxActivity() {
        Map<CourseType, Integer> maxActivity = getCourseActivity();
        Integer value = maxActivity.get(max(maxActivity.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
        return maxActivity
                .keySet()
                .stream()
                .filter(k -> Objects.equals(value, maxActivity.get(k)) && value != 0)
                .collect(Collectors.toList());
    }

    public List<CourseType> getHighestActivity() {
        List<CourseType> highestActivity;
        highestActivity = maxActivity();
        //highestActivity.removeAll(minActivity());
        return highestActivity;
    }

    public List<CourseType> getLowestActivity() {
        List<CourseType> lowestActivity;
        lowestActivity = minActivity();
        lowestActivity.removeAll(maxActivity());
        return lowestActivity;
    }

    @Override
    public String[] retrieve() {
        return new String[0];
    }
}

class HighestActivity extends Activity {
    private static final String statisticName = "Highest activity";

    HighestActivity() {
        super.statisticName = statisticName;
    }

    @Override
    public String[] retrieve() {
        return getHighestActivity()
                .stream()
                .map(CourseType::getName).toArray(String[]::new);
    }
}

class LowestActivity extends Activity {

    private static final String statisticName = "Lowest activity";

    LowestActivity() {
        super.statisticName = statisticName;
    }

    @Override
    public String[] retrieve() {
        return getLowestActivity()
                .stream()
                .map(CourseType::getName).toArray(String[]::new);
    }
}

abstract class Difficulty extends Statistic {

    protected Map<CourseType, Double> getAveragePointsByCourse() {
        Map<CourseType, List<Integer>> scoreListByCourse = getScoreListByCourse();
        Map<CourseType, Double> averagePointsByCourse = new LinkedHashMap<>() {
        };
        for (CourseType course : scoreListByCourse.keySet()) {
            List<Integer> scores = scoreListByCourse.getOrDefault(course, new ArrayList<>() {
            });
            double average = scores
                    .stream()
                    .filter(score -> score != 0)
                    .mapToDouble(v -> v)
                    .average()
                    .orElse(0.0);
            averagePointsByCourse.put(course, average);
        }
        return averagePointsByCourse;
    }

    protected List<CourseType> minAverageCourse() {
        Map<CourseType, Double> averagePointsByCourse = getAveragePointsByCourse();
        Collection<Double> values = averagePointsByCourse.values();
        double lowest = values
                .stream()
                .mapToDouble(v -> v)
                .min().orElse(0.0);
        return averagePointsByCourse
                .keySet()
                .stream()
                .filter(e -> averagePointsByCourse.get(e) == lowest && lowest != 0.0)
                .collect(Collectors.toList());
    }

    protected List<CourseType> maxAverageCourse() {
        Map<CourseType, Double> averagePointsByCourse = getAveragePointsByCourse();
        Collection<Double> values = averagePointsByCourse.values();
        double highest = values
                .stream()
                .mapToDouble(v -> v)
                .max().orElse(0.0);
        return getAveragePointsByCourse()
                .keySet()
                .stream()
                .filter(e -> averagePointsByCourse.get(e) == highest && highest != 0.0)
                .collect(Collectors.toList());
    }

    public List<CourseType> getEasiestCourse() {
        List<CourseType> easiestCourse;
        easiestCourse = maxAverageCourse();
        easiestCourse.removeAll(minAverageCourse());
        return easiestCourse;
    }

    public List<CourseType> getHardestCourse() {
        List<CourseType> hardestCourse;
        hardestCourse = minAverageCourse();
        //hardestCourse.removeAll(maxAverageCourse());
        return hardestCourse;
    }
}

class EasiestCourse extends Difficulty {

    private static final String statisticName = "Easiest course";

    EasiestCourse() {
        super.statisticName = statisticName;
    }

    @Override
    public String[] retrieve() {

        return getEasiestCourse()
                .stream()
                .map(CourseType::getName).toArray(String[]::new);
    }
}

class HardestCourse extends Difficulty {

    private static final String statisticName = "Hardest course";

    HardestCourse() {
        super.statisticName = statisticName;
    }

    @Override
    public String[] retrieve() {
        return getHardestCourse()
                .stream()
                .map(CourseType::getName).toArray(String[]::new);
    }
}



