package hanson;

import hanson.data.Book;
import hanson.data.Student;

interface AidlInterface2 {
    List<Book> getBooks(in Student student);
    void getStudent(out Student student);
    void getStudentWithInOutTag(inout Student student);
}