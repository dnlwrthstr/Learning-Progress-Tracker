package tracker;

import java.util.LinkedList;
import java.util.List;

public class Notification {


    public static void sendCertificate() {
        List<Student> notifiedStudents = new LinkedList<>();
        for (Student student : Student.getStudents()) {
            student.checkCourseCompleted()
                    .forEach(courseType -> {
                        System.out.println("To: " + student.getEmail());
                        System.out.println("Re: Your Learning Progress");
                        System.out.printf("Hello, %s %s! You have accomplished our %s course!\n",
                                student.getFirstName(), student.getLastName(), courseType.getName());
                        if (!notifiedStudents.contains(student)) {
                            notifiedStudents.add(student);
                        }
                    });
        }
        System.out.printf("Total %d students have been notified.\n", notifiedStudents.size());
    }
}
