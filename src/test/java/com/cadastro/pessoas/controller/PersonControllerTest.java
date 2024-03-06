/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cadastro.pessoas.controller;

import com.cadastro.pessoas.controller.util.JsfUtil;
import com.cadastro.pessoas.entity.Person;
import com.cadastro.pessoas.facade.PersonFacade;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;

/**
 *
 * @author Felipe
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    public static final Integer ID = 1;
    public static final String NAME = "Paulo Bento";
    public static final int AGE = 55;
    public static final String GENDER = "MASCULINO";

    @Mock
    private PersonFacade personFacade;

    @Mock
    private ResourceBundle bundle;

    @Mock
    private JsfUtil jsfUtil;

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

    /**
     * Test of getItems method, of class PersonController.
     */
    @Test
    public void testGetItems() {
        when(personFacade.findAll()).thenReturn(Arrays.asList(person));

        List<Person> result = personController.getItems();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Person.class, result.get(0).getClass());
        assertEquals(ID, result.get(0).getId());
    }

    /**
     * Test of getPerson method, of class PersonController.
     */
    @Test
    public void testGetPerson() {
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
