package tracker;

import org.junit.jupiter.api.Test;

class NotificationTest {
/*
> notify
    To: johnd@email.net
    Re: Your Learning Progress
    Hello, John Doe! You have accomplished our Java course!
    To: johnd@email.net
    Re: Your Learning Progress
    Hello, John Doe! You have accomplished our DSA course!
    Total 1 students have been notified.
            > notify
    Total 0 students have been notified.
            > exit
    Bye!
  */

    void setUp() {
        Student.clearStudents();
        Student.addStudent("John", "Doe", "jdoe@mail.net");
        Student.addStudent("Jane", "Spark", "jane.doe@yahoo.com");

        Student john = Student.getStudentByEmail("jdoe@mail.net");
        //Student jane = Student.getStudentByEmail("jane.doe@yahoo.com");

        john.updateScore(john.getId(), new int[]{600, 400, 0, 0});

    }

    @Test
    void sendCertificate() {
        setUp();
        Notification.sendCertificate();
        Notification.sendCertificate();
    }
}