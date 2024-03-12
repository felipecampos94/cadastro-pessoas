package com.cadastro.pessoas.facade;

import com.cadastro.pessoas.entity.Person;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class PersonFacadeTest {

    public static final Integer ID = 1;
    public static final String NAME = "Paulo Bento";
    public static final int AGE = 55;
    public static final String GENDER = "MASCULINO";

    private Person person;

    @InjectMocks
    private PersonFacade personFacade;

    @Mock
    private EntityManager entityManager;

    public PersonFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startPerson();
    }

    @Test
    @DisplayName("When the person is created successfully")
    public void createPersonThenReturnSuccess() {
        personFacade.create(person);
        verify(entityManager, times(1)).persist(person);
    }

    @Test
    @DisplayName("When the person is updated successfully")
    public void editPersonThenReturnSuccess() {
        personFacade.edit(person);
        verify(entityManager, times(1)).merge(person);
    }

    @Test
    @DisplayName("When the person is deleted successfully")
    public void removePersonSuccess() {
        PersonFacade mockedFacade = mock(PersonFacade.class);
        doNothing().when(mockedFacade).remove(person);

        mockedFacade.remove(person);

        verify(mockedFacade, times(1)).remove(person);
    }

    @Test
    @DisplayName("When the search for id is returned successfully")
    public void findByIdPersonThenReturnSuccess() {
        when(entityManager.find(Person.class, 1)).thenReturn(person);
        Person result = personFacade.find(ID);
        assertNotNull(result);
        assertEquals(person, personFacade.find(1));
        assertEquals(Person.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(NAME, result.getName());
        assertEquals(AGE, result.getAge());
        assertEquals(GENDER, result.getGender());
    }

    @Test
    @DisplayName("When the list of persons is returned successfully")
    public void findAllPersons() {
        PersonFacade mockedFacade = mock(PersonFacade.class);
        when(mockedFacade.findAll()).thenReturn(Arrays.asList(person));

        List<Person> response = mockedFacade.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Person.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
    }

    @Test
    @DisplayName("When the search for range is returned successfully")
    public void findPersonByRangeThenReturnSuccess() {
        int[] range = new int[]{0, 10};
        PersonFacade mockedFacade = mock(PersonFacade.class);
        when(mockedFacade.findRange(range)).thenReturn(Arrays.asList(person));

        List<Person> response = mockedFacade.findRange(range);

        assertNotNull(response);
        verify(mockedFacade, times(1)).findRange(range);
    }

    @Test
    @DisplayName("When the count person is returned successfully")
    public void countPersonThenReturnSuccess() {
        PersonFacade mockedFacade = mock(PersonFacade.class);
        int count = 10;
        when(mockedFacade.count()).thenReturn(count);

        int response = mockedFacade.count();

        assertNotNull(response);
        assertEquals(count, response);
        verify(mockedFacade, times(1)).count();
    }

    private void startPerson() {
        person = new Person(ID, NAME, AGE, GENDER);
    }

}
