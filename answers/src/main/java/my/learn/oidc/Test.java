package my.learn.oidc;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 1. used eclipse to quickly generate getters and setters
 */

class NoEntityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3299909180753051675L;

	public NoEntityException(Class<?> entity, Long entityId) {
		super(String.format("Entity %s:%s not found!", entity.getSimpleName(), entityId));
	}

}

@Entity
@Table(name = "task")
@Getter
@Setter
class Task {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "description", length = 200)
	private String description;

	@Column(name = "priority", columnDefinition = "bigint")
	private Integer priority;

}

@Getter
@Setter
class TaskDTO {

	@NotEmpty(message = "description is required")
	private String description;
	@NotNull(message = "priority is required")
	private Integer priority;

	public Task toBO() {
		Task t = new Task();
		t.setDescription(getDescription());
		t.setPriority(getPriority());
		return t;
	}
}

@AllArgsConstructor
@Getter
class ErrorResponseDTO {
	private final int status;
	private final String message;
}

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
class TaskController {
	private static Logger log = Logger.getLogger("Solution");

	private final TaskService service;

	@PutMapping("/{id}")
	public void updateTask(
			@PathVariable(name = "id", required = true) Long id,
			@Valid @RequestBody TaskDTO dto) throws NoEntityException {
		log.log(Level.INFO, "request received to update task {0}", id);

		Task task = dto.toBO();
		task.setId(id);
		service.updateTask(id, task);
	}

	@ExceptionHandler(NoEntityException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponseDTO> handleNoEntityException(NoEntityException ex) {
		log.log(Level.INFO, "Exception: {0}", ex.getMessage());
		ErrorResponseDTO err = new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
		log.log(Level.INFO, "Validation Exception: {0}", ex.getMessage());

		ErrorResponseDTO err = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
				ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining(",")));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
}

@Service
@AllArgsConstructor
class TaskService {

	private final TaskRepository repo;

	public void updateTask(Long id, Task task) throws NoEntityException {
		boolean exists = repo.existsById(id);
		if(!exists) {
			throw new NoEntityException(Task.class, id);
		}

		repo.save(task);
	}
}

@Repository
interface TaskRepository extends JpaRepository<Task, Long> {

}
