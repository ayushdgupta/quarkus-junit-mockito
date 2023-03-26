package com.guptaji.resource;

import com.guptaji.entity.StudentEntity;
import com.guptaji.repository.StudentRepo;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StudentResourceTest {

    @Inject
    private StudentResource studentResource;

    @InjectMock
    private StudentRepo mockedStudentRepo;

    public StudentEntity studentEntity;

    @BeforeEach
    void setUp() {

        studentEntity = new StudentEntity(12, "Manjit", "CSE", "NIT RKL");
    }

    @Test
    void getAllStudenDetails() {

        List<StudentEntity> studentEntities = new ArrayList<>();
        studentEntities.add(new StudentEntity(1, "Ayush", "CSE", "NIT RKL"));
        studentEntities.add(new StudentEntity(2, "Vinay", "ME", "NIT RKL"));
        studentEntities.add(new StudentEntity(1, "Achint", "CSE", "IISC Banglore"));

        Mockito.when(mockedStudentRepo.listAll()).thenReturn(studentEntities);

        Response response = studentResource.getAllStudenDetails();

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(3, ((List<StudentEntity>) response.getEntity()).size());
    }

    @Test
    void insertOneNewStudent() {

        // Here if you see in insertOneNewStudent() we are using two repositories methods but in one method
        // (persist()) we are not doing anything with the response so for this method we will use Mockito in a
        // different way but for other method we are using the response so we will mock in a normal way.
        Mockito.doNothing().when(mockedStudentRepo).persist(this.studentEntity);
        Mockito.when(mockedStudentRepo.isPersistent(this.studentEntity)).thenReturn(true);

        Response response = studentResource.insertOneNewStudent(this.studentEntity);

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    void getAllStudenDetailsById() {
        // in below line we are passing a static data but we can also pass data dynamically
//        Mockito.when(mockedStudentRepo.findById(12)).thenReturn(this.studentEntity);
        Mockito.when(mockedStudentRepo.findById(ArgumentMatchers.any(Integer.class))).thenReturn(this.studentEntity);

        Response response = studentResource.getAllStudenDetailsById(12);

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Manjit", ((StudentEntity) response.getEntity()).getName());
    }

    @Test
    void deleteById() {
        Mockito.when(mockedStudentRepo.deleteById(12)).thenReturn(true);

        Response response = studentResource.deleteById(12);

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}