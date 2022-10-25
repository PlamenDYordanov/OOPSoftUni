package Abstraction.Lab.P03_StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private static Map<String, Student> repository;

    public StudentSystem() {
        repository = new HashMap<>();
    }

    public void addToRepository(Student student) {
        repository.putIfAbsent(student.getName(), student);
    }


    public Student Show(String name) {
        return repository.values().stream().filter((left ->
                left.getName().equals(name))).findFirst().orElse(null);
    }
}
