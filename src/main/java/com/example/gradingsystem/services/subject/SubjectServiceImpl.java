package com.example.gradingsystem.services.subject;

import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Subject;
import com.example.gradingsystem.exceptions.SubjectNotFoundException;
import com.example.gradingsystem.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubject(int id) {
        return subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException("Subject with id " + id + " was not found"));
    }

    @Override
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject updateSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public String deleteSubject(int id) {
        subjectRepository.deleteById(id);
        return "Subject deleted " + id;
    }

    @Override
    public List<Subject> findSubjectsByName(String name) {
        return subjectRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Group> getGroupList(int id) {
        Subject subject = getSubject(id);
        return subject.getGroups();
    }
}
