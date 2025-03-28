package hanson;

import hanson.data.Book;
import hanson.data.Student;

interface AidlInterface2 {
    List<Book> getBooks(in Student student);
    void getStudent(out Student student);
    void getStudentWithInOutTag(inout Student student);
}


/*
AIDL的三个定向tag：in、out、inout

所有非基本类型的参数都需要一个定向tag来表明数据是如何走向的，
要么是in、out或者inout。基本数据类型只能是in

in      表示数据只能由客户端流向服务端，
out     表示数据只能由服务端流向客户端，
inout   表示数据可以在客户端与服务端之间双向流通

in和out的区别：
    1. in表示输入参数，out表示输出参数
    2. in类型是值传递，out类型是引用传递
对于out，服务端将会收到客户端对象，该对象不为空，但它里面的字段为空
对于inout ，服务端将会接收到客户端传来对象的完整信息，并且客户端会同步服务端对该对象的变动

*/