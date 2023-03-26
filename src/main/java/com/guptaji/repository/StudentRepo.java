package com.guptaji.repository;

import com.guptaji.entity.StudentEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class StudentRepo implements PanacheRepositoryBase<StudentEntity, Integer> {

    //    @Override
//    public List<StudentEntity> list(String query, Object... params) {
//        return PanacheRepositoryBase.super.list(query, params);
//    }

    public List<StudentEntity> getStudentsByBranch(String branch){
        return list("select s from StudentEntity s where s.branch = ?1", branch);
    }
}
