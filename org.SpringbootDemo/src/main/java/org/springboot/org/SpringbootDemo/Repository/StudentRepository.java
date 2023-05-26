package org.springboot.org.SpringbootDemo.Repository;


import org.springboot.org.SpringbootDemo.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
