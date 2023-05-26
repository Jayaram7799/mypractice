package org.springboot.org.SpringbootDemo.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springboot.org.SpringbootDemo.Repository.StudentRepository;
import org.springboot.org.SpringbootDemo.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public List<Student> getPaginatedStudents(int page, int pageSize) {
		List<Student> allStudents = studentRepository.findAll();

		int startIndex = (page - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, allStudents.size());

		if (startIndex >= allStudents.size()) {
			return Collections.emptyList();
		}

		return allStudents.subList(startIndex, endIndex);
	}

	public List<Student> filterStudents(String filterCriteria) {
		List<Student> allStudents = studentRepository.findAll();
		List<Student> filteredStudents = new ArrayList<>();

		for (Student student : allStudents) {
			// Implement your filtering logic based on the filter criteria
			if (matchesFilterCriteria(student, filterCriteria)) {
				filteredStudents.add(student);
			}
		}

		return filteredStudents;
	}

	private boolean matchesFilterCriteria(Student student, String filterCriteria) {
		// Parse the filter criteria and compare with student properties
		// Here's an example assuming the filter criteria is in the format
		// "property:value"
		String[] filterParts = filterCriteria.split(":");
		if (filterParts.length != 2) {
			// Invalid filter criteria format
			return false;
		}

		String property = filterParts[0];
		String value = filterParts[1];
		// Apply the filter based on the property
		switch (property.toLowerCase()) {
		case "id":
			// Convert value to integer and compare with student's ID
			try {
				int idFilter = Integer.parseInt(value);
				return student.getId() == idFilter;
			} catch (NumberFormatException e) {
				// Invalid ID filter value
				return false;
			}
		case "name":
			// Compare the student's name with the filter value
			return student.getName().equalsIgnoreCase(value);
		case "totalmarks":
			// Convert value to integer and compare with student's total marks
			try {
				int totalMarksFilter = Integer.parseInt(value);
				return student.getTotalMarks() == totalMarksFilter;
			} catch (NumberFormatException e) {
				// Invalid total marks filter value
				return false;
			}
		default:
			// Invalid property for filtering
			return false;
		}

	}
}
