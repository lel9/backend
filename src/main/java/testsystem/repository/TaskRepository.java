package testsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testsystem.domain.Category;
import testsystem.domain.Task;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    public List<Task> findByCategory(Category category);
}
