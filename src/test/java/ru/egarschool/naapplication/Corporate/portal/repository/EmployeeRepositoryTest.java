package ru.egarschool.naapplication.Corporate.portal.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest {

    private static final Long ID = 1L;
    @Mock
    private EmployeeRepo employeeRepo;

    @Test
    public void findEmployeeById(){
        final EmployeeEntity employee = mock(EmployeeEntity.class);
        when(employee.getId()).thenReturn(ID);

        when(employeeRepo.findById(ID)).thenReturn(Optional.of(employee));
        final EmployeeEntity employee1 = employeeRepo.findById(ID).orElseThrow();

        assertEquals(employee1,employee);
    }


}
