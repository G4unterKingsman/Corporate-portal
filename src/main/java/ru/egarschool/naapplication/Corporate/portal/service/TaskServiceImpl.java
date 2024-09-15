package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.TaskDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.TaskEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.enums.TaskStatus;
import ru.egarschool.naapplication.Corporate.portal.exception.TaskNotFoundException;
import ru.egarschool.naapplication.Corporate.portal.mapper.TaskMapper;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.TaskRepo;
import ru.egarschool.naapplication.Corporate.portal.service.intefraces.TaskService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final EmployeeRepo employeeRepo;
    private final EmployeeServiceImpl employeeService;
    private final SecurityService securityService;


    /**
     * Отображеие всех задач
     * Используем стримы чтобы смаппить entity в dto, и вернуть
     */
    public List<TaskDto> findAll(){
        List<TaskEntity> tasks = taskRepo.findAll();
        return tasks.stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Получение задачи по id, если задача не найдена, выбрасываем исключение
     */

    public TaskDto getById(Long id){
        TaskEntity task =  taskRepo.findById(id).orElseThrow(
                () ->  new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));

        // обновляем статус задачи при получении по id
        updateTaskStatus(task);
        return taskMapper.toDto(task);
    }


    /**
     * создание задачи
     * Маппим задачу из дто
     * находим текущего авторизированного сотрудника
     * находим сотрудника по имени из дто
     * присваиваем задаче статус СОЗДАН
     * присваиваем задачу дату создания(сейчас)
     * присваиваем найденного авторизированного сотрудника как хозяина задачи
     * присваиваем найденного сотрудника по имени как адресата задачи\
     * считаем дату дедлайна
     * сохраняем задачу
     */
    public TaskDto create(TaskDto taskDto){
        TaskEntity task = taskMapper.toEntity(taskDto);
        String username = securityService.getCurrentUsername();
        EmployeeEntity employeeWhoGave = employeeRepo.findEmployeeEntityByUserAccount_Username(username).orElseThrow(
                () ->  new UsernameNotFoundException("Сотрудник с username " + username + " не найден"));
        EmployeeEntity employeeWhoGiven = employeeService.findEmployeeByName(taskDto.getWhoGivenTask().getName());

        taskDto.setStatus(TaskStatus.CREATED);
        taskDto.setCreated(LocalDateTime.now());
        taskDto.setWhoGaveTask(employeeWhoGave);
        task.setWhoGivenTask(employeeWhoGiven);
        taskDto.setDeadline(calculateTimeDeadline(taskDto)); // считаем для дто дату дедлайна
        task.setDeadline(calculateTimeDeadline(taskDto));

        taskMapper.toUpdateTaskFromDto(taskDto,task);
        taskRepo.save(task);
        return taskDto;
    }

    /**
     * создание задачи
     * находим задачу по id
     * находим текущего авторизированного сотрудника
     * находим сотрудника по имени из дто\\
     * присваиваем найденного авторизированного сотрудника как хозяина задачи
     * присваиваем найденного сотрудника по имени как адресата задачи
     * присваиваем найденного сотрудника по имени как адресата задачи
     * считаем дату дедлайна
     * обновляем статус задачи
     * сохраняем задачу
     */
    public TaskDto update(TaskDto taskDto, Long id) {
        TaskEntity task = taskRepo.findById(id).orElseThrow(
                () ->  new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));

        String username = securityService.getCurrentUsername();
        EmployeeEntity employeeWhoGave = employeeRepo.findEmployeeEntityByUserAccount_Username(username).orElseThrow(
                () ->  new UsernameNotFoundException("Сотрудник с username " + username + " не найден"));
        EmployeeEntity employeeWhoGiven = employeeService.findEmployeeByName(taskDto.getWhoGivenTask().getName());


        taskDto.setWhoGaveTask(employeeWhoGave); // сеттим текущего сотрудника-пользователя как хозяина задачи
        task.setWhoGivenTask(employeeWhoGiven); // вручную сеттим сотрудника, кому направлена задача
        taskDto.setCreated(task.getCreated()); // вручную мапим дату создания
        taskDto.setDeadline(calculateTimeDeadline(taskDto)); // считаем для dto дто дату дедлайна
        task.setDeadline(calculateTimeDeadline(taskDto)); // считаем для сущности дто дату дедлайна


        updateTaskStatus(task);
        taskMapper.toUpdateTaskFromDto(taskDto,task);
        taskRepo.save(task);
        return taskDto;
    }



    /**
     * удаление задачи из базы по id
     * @param id = идентификатор удаляемой задачи
     */
    @Override
    public void delete(Long id) {
        taskRepo.deleteById(id);
    }


    /** метод изминения статуса по кнопкам "начать" "завершить" "отменить"
     * @param id - id задачи, у которой меняем статус
     * @param isCancel - маркер, указывающий что кнопка "отменить".
     * 1 проверка если у задачи нет статуса - задаём статус "создан"
     * 2 если задача создана и это не "Отменить" - задайм статус "в процессе"
     * 3 - если "в процессе" и это не "Отменить" - задаём статус "завершить"
     *                 присваиваем дату завершения(сейчас)
     *                 считаем фактически затраченное время в часах. Дата создания + время завершения
     * 4 - если "в процессе" и это "отменить" - задаём статус "отменен"
     * обновляем дату редактирования
     * сохраняем изменения
     */
    @Override
    public void switchStatus(Long id, boolean isCancel) {
        TaskEntity task = taskRepo.findById(id).orElseThrow(
                () ->  new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));
        if (task.getStatus() == null && !isCancel) {
            task.setStatus(TaskStatus.CREATED);
        }
        else if (task.getStatus() == TaskStatus.CREATED && !isCancel) {
            task.setStatus(TaskStatus.IN_PROGRESS);
        }
        else if (task.getStatus() == TaskStatus.IN_PROGRESS && !isCancel) {
            task.setStatus(TaskStatus.COMPLETED);
            task.setCompleted(LocalDateTime.now());
            task.setTimeCancelled((int)Duration.between(task.getCreated(), LocalDateTime.now()).toHours());
        }
        else if (task.getStatus() == TaskStatus.IN_PROGRESS && isCancel) {
            task.setStatus(TaskStatus.CANCELLED);
        }
        task.setUpdated(LocalDateTime.now());
        taskRepo.save(task);
    }


    /** метод получения хозяина задачи, используется для безопасности в контроллере
     */
    public String getOwnerUsername(Long id) {
        TaskEntity task = taskRepo.findById(id).orElseThrow(
                () ->  new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));
        return task.getWhoGaveTask().getUserAccount().getUsername();
    }

    /** метод получения адресата задачи, используется для безопасности в контроллере
     */
    public String getAssigneeUsername(Long id) {
        TaskEntity task = taskRepo.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));
        return task.getWhoGivenTask().getUserAccount().getUsername();
    }


    /** метод подсчёта дедлайна
     *
     * @param taskDto - передаваемый дто для которой считается дедлайн
     * @return дата дедлайна = дата создания + часы отведенные на выполнение
     */
    private LocalDateTime calculateTimeDeadline(TaskDto taskDto) {
        return taskDto.getCreated().plusHours(taskDto.getTimeAllowed());
    }

    /** метод нахождения связанной с отчётом задачи, используется в ReportService
     * т.к это получение по id - обновляем статус задачи
     */
    public TaskEntity findTaskById(Long id) {
        TaskEntity task = taskRepo.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Задачи с идентификатором " + id + " нет"));
        updateTaskStatus(task);
        return task;
    }

    /** Автоматическое обновление статуса задачи в зависимости от даты делайна и текущего статуса
     *  Вызывается в методах получения по id, и обновлении задачи
     * @param task - задача, у которой меняем статус
     * не сохраняем изменения в базу т.к там где метод вызывается это уже сделано
     */
    public void updateTaskStatus(TaskEntity task) {
        if (task.getDeadline().isBefore(LocalDateTime.now()) && task.getStatus() != TaskStatus.COMPLETED) {
            task.setStatus(TaskStatus.CANCELLED);
        }
        else if (task.getDeadline().isAfter(LocalDateTime.now()) && task.getStatus() != TaskStatus.COMPLETED) {
            task.setStatus(TaskStatus.IN_PROGRESS);
        }
        else if (task.getDeadline().isAfter(LocalDateTime.now()) && task.getStatus() == TaskStatus.CANCELLED) {
            task.setStatus(TaskStatus.IN_PROGRESS);
        }
    }
}
