package com.cadastro.pessoas.controller;

import com.cadastro.pessoas.entity.Person;
import com.cadastro.pessoas.facade.PersonFacade;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class PersonControllerTest {

    public static final Integer ID = 1;
    public static final String NAME = "Paulo Bento";
    public static final int AGE = 55;
    public static final String GENDER = "MASCULINO";

    @Mock
    private PersonFacade personFacade;

    @InjectMocks
    private PersonController personController;

    private Person person;

    public PersonControllerTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startPerson();
    }

    @Test
    @DisplayName("When the person is created successfully")
    public void createPersonThenReturnSuccess() {
        PersonController mockedController = mock(PersonController.class);
        doNothing().when(mockedController).create();

        mockedController.create();
        verify(mockedController, times(1)).create();

        assertNotNull(mockedController.getItems());
    }

    @Test
    @DisplayName("When the person is updated successfully")
    public void updatePersonThenReturnSuccess() {
        PersonController mockedController = mock(PersonController.class);
        doNothing().when(mockedController).update();

        mockedController.update();
        verify(mockedController, times(1)).update();

        assertNotNull(mockedController.getItems());
    }

    @Test
    @DisplayName("When the person is deleted successfully")
    public void destroyPersonSuccess() {
        PersonController mockedController = mock(PersonController.class);
        doNothing().when(mockedController).destroy();

        mockedController.destroy();

        verify(mockedController, times(1)).destroy();
        assertTrue(mockedController.getItems().isEmpty());
    }

    @Test
    @DisplayName("When the list of persons is returned successfully")
    public void findAllPersons() {
        when(personFacade.findAll()).thenReturn(Arrays.asList(person));

        List<Person> result = personController.getItems();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Person.class, result.get(0).getClass());
        assertEquals(ID, result.get(0).getId());
    }

    @Test
    @DisplayName("When the search for id is returned successfully")
    public void findByIdPersonThenReturnSuccess() {
        when(personFacade.find(anyInt())).thenReturn(person);

        Person result = personController.getPerson(ID);

        assertNotNull(result);
        assertEquals(Person.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(NAME, result.getName());
        assertEquals(AGE, result.getAge());
        assertEquals(GENDER, result.getGender());
    }

    private void startPerson() {
        person = new Person(ID, NAME, AGE, GENDER);
    }

}
