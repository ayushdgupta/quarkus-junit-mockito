package com.guptaji.resource;

import com.guptaji.entity.StudentEntity;
import com.guptaji.repository.StudentRepo;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student")
public class StudentResource {

    @Inject
    public StudentRepo studentRepo;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudenDetails(){
        List<StudentEntity> studentEntities = studentRepo.listAll();
        if (studentEntities == null || studentEntities.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok(studentEntities).build();
        }
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertOneNewStudent(StudentEntity studentEntity){
        studentRepo.persist(studentEntity);
        if (studentRepo.isPersistent(studentEntity)){
            return Response.ok("Done dana done done").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudenDetailsById(@PathParam("id") int id){
        StudentEntity studentEntity = studentRepo.findById(id);
        if (studentEntity == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok(studentEntity).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteById(@PathParam("id") int id){
        boolean dataDeleted = studentRepo.deleteById(id);
        if (dataDeleted){
            return Response.ok("Done dana done done").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
}
