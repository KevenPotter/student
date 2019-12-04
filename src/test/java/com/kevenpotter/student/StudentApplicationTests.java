package com.kevenpotter.student;

import com.kevenpotter.student.dao.StudentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentApplicationTests {

    @Autowired
    StudentDao studentDao;

    @Test
    public void testXmlMybatis() {
    }

}
