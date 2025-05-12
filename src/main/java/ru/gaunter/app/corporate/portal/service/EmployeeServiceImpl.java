package ru.gaunter.app.corporate.portal.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gaunter.app.corporate.portal.dto.EmployeeDto;
import ru.gaunter.app.corporate.portal.entity.EmployeeEntity;
import ru.gaunter.app.corporate.portal.entity.UserAccount;
import ru.gaunter.app.corporate.portal.exception.EmployeeNotFoundException;
import ru.gaunter.app.corporate.portal.mapper.EmployeeMapper;
import ru.gaunter.app.corporate.portal.repository.EmployeeRepo;
import ru.gaunter.app.corporate.portal.repository.UserRepo;
import ru.gaunter.app.corporate.portal.service.intefraces.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;


    /**
     * Отображеие всех сотрудиков
     * Используем стримы чтобы смаппить entity в dto, и вернуть
     */
    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> employees = employeeRepo.findAll();
        return employees.stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());

    }

    /**
     * Получение сотрудника по id, если сотрудник не найден, выбрасываем исключение
     */
    @Override
    public EmployeeDto getById(Long id){
        return employeeMapper.toDto(employeeRepo.findById(id).orElseThrow(
                () ->  new EmployeeNotFoundException("Сотрудник с идентификатором " + id + " не найден")));
    }

    /**
     * создание сотрудника (доступно только Администраторам)
     * создаём нового пользователя, и присваиваем ему данные полученные из дто сотрудника
     * маппим сотрудника из дто, и связываем созданного пользователя с сотрудником
     * сохранаяем сотрудника и пользователя в базу
     */
    @Transactional
    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        UserAccount user = new UserAccount();
        user.setUsername(employeeDto.getUserAccount().getUsername());
        user.setPassword(passwordEncoder.encode(employeeDto.getUserAccount().getPassword()));
        user.setActive(true);
        user.setRoles(employeeDto.getUserAccount().getRoles());

        EmployeeEntity employee = employeeMapper.toEntity(employeeDto);

        user.setEmployee(employee);
        employee.setUserAccount(user);

        employeeRepo.save(employee);
        userRepo.save(user);

        return employeeDto;
    }

    /**
     * редактирование сотрудника (доступно администратору и пользователю владельцу профиля)
     * находим юзернейм текущего пользователя, и находим по нему авторизированного пользователя
     * находим сотрудника по предоставленному id
     * обновляем сотрудника из дто
     * присваиваем к дто текущего пользователя
     * сохранаяем обновлённого сотрудника в базу
     * возвращаем дто сотрудника
     */
    @Transactional
    @Override
    public EmployeeDto update(EmployeeDto employeeDto, Long id) {
        String username = securityService.getCurrentUsername();
        UserAccount userAccount = userRepo.findByUsername(username).orElseThrow(
                () ->  new UsernameNotFoundException("Такого username " + username + " не существует"));
        EmployeeEntity employee = employeeRepo.findById(id).orElseThrow(
                () ->  new EmployeeNotFoundException("Сотрудник с идентификатором " + id + " не найден"));

        employeeMapper.updateEmployeeFromDTO(employeeDto,employee);
        employeeDto.setUserAccount(userAccount);

        employeeRepo.save(employee);
        return employeeDto;
    }



    /**
     * удаление сотрудника из базы по id
     * @param id = идентификатор удаляемого сотрудника
     */

    @Override
    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }


    /**
     * метод нахождения сотрудника-хозяина задачи\отчёта
     * Используется в TaskService и ReportService
     * Находится сотрудник по переданному id
     * возвращаем юзернейм сотрудника-хозяина отчёта или задачи
     */

    public String getOwnerUsername(Long id) {
        EmployeeEntity employee = employeeRepo.findById(id).orElseThrow(
                () ->  new EmployeeNotFoundException("Сотрудник с индентификатором " + id + " не найден"));
        return employee.getUserAccount().getUsername();
    }


    /**
     * нахождение сотрудника по имени
     * Нужно для создания задачи в TaskService
     */
    public EmployeeEntity findEmployeeByName(String name) {
        return employeeRepo.findByName(name).orElseThrow(
                () ->  new EmployeeNotFoundException("Сотрудник с именем " + name + " не найден"));
    }



}
