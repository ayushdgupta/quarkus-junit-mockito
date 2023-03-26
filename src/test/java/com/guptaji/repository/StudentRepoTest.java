package com.guptaji.repository;

import com.guptaji.entity.StudentEntity;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StudentRepoTest {

    @Inject
    private StudentRepo studentRepo;

    @Test
    void getStudentsByBranch() {

        List<StudentEntity> studentEntities = studentRepo.getStudentsByBranch("CSE");

        assertFalse(studentEntities.isEmpty());
        assertEquals(4, studentEntities.size());

        studentEntities = studentRepo.getStudentsByBranch("ME");

        assertFalse(studentEntities.isEmpty());
        assertEquals(1, studentEntities.size());

    }
}