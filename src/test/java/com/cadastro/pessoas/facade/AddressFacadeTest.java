/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cadastro.pessoas.facade;

import static com.cadastro.pessoas.controller.AddressControllerTest.CITY;
import static com.cadastro.pessoas.controller.AddressControllerTest.ID;
import static com.cadastro.pessoas.controller.AddressControllerTest.NUMBER;
import static com.cadastro.pessoas.controller.AddressControllerTest.STATE;
import static com.cadastro.pessoas.controller.AddressControllerTest.STREET;
import static com.cadastro.pessoas.controller.AddressControllerTest.ZIP_CODE;
import com.cadastro.pessoas.entity.Address;
import com.cadastro.pessoas.entity.Person;
import static com.cadastro.pessoas.facade.PersonFacadeTest.AGE;
import static com.cadastro.pessoas.facade.PersonFacadeTest.GENDER;
import static com.cadastro.pessoas.facade.PersonFacadeTest.ID;
import static com.cadastro.pessoas.facade.PersonFacadeTest.NAME;
import java.util.Arrays;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Felipe
 */
public class AddressFacadeTest {

    //Address
    public static final Integer ID = 1;
    public static final String STATE = "Rio Grande do Sul";
    public static final String CITY = "Passo Fundo";
    public static final String STREET = "Brasil";
    public static final int NUMBER = 863;
    public static final String ZIP_CODE = "99040000";

    //Person
    public static final Integer PERSON_ID = 1;
    public static final String NAME = "Paulo Bento";
    public static final int AGE = 55;
    public static final String GENDER = "MASCULINO";

    @InjectMocks
    private AddressFacade addressFacade;

    @Mock
    private EntityManager entityManager;

    private Address address;

    private Person person;

    public AddressFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startAddress();
    }

    @Test
    @DisplayName("When the address is created successfully")
    public void createAddressThenReturnSuccess() {
        addressFacade.create(address);
        verify(entityManager, times(1)).persist(address);
    }

    @Test
    @DisplayName("When the address is updated successfully")
    public void editAddressThenReturnSuccess() {
        addressFacade.edit(address);
        verify(entityManager, times(1)).merge(address);
    }

    @Test
    @DisplayName("When the address is deleted successfully")
    public void removeAddressSuccess() {
        AddressFacade mockedFacade = mock(AddressFacade.class);
        doNothing().when(mockedFacade).remove(address);

        mockedFacade.remove(address);

        verify(mockedFacade, times(1)).remove(address);
    }

    @Test
    @DisplayName("When the search for id is returned successfully")
    public void findByIdAddressThenReturnSuccess() {
        when(entityManager.find(Address.class, 1)).thenReturn(address);
        Address result = addressFacade.find(ID);
        assertNotNull(result);
        assertEquals(address, addressFacade.find(1));
        assertEquals(Address.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(STATE, result.getState());
        assertEquals(CITY, result.getCity());
        assertEquals(STREET, result.getStreet());
        assertEquals(NUMBER, result.getNumber());
        assertEquals(ZIP_CODE, result.getZipCode());
        assertEquals(person, result.getPersonId());
    }

    @Test
    @DisplayName("When the list of addresses is returned successfully")
    public void findAllAddresses() {
        AddressFacade mockedFacade = mock(AddressFacade.class);
        when(mockedFacade.findAll()).thenReturn(Arrays.asList(address));

        List<Address> response = mockedFacade.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Address.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
    }

    @Test
    @DisplayName("When the search for range is returned successfully")
    public void findAddressByRangeThenReturnSuccess() {
        int[] range = new int[]{0, 10};
        AddressFacade mockedFacade = mock(AddressFacade.class);
        when(mockedFacade.findRange(range)).thenReturn(Arrays.asList(address));

        List<Address> response = mockedFacade.findRange(range);

        assertNotNull(response);
        verify(mockedFacade, times(1)).findRange(range);
    }

    @Test
    @DisplayName("When the count address is returned successfully")
    public void countAddressThenReturnSuccess() {
        AddressFacade mockedFacade = mock(AddressFacade.class);
        int count = 10;
        when(mockedFacade.count()).thenReturn(count);

        int response = mockedFacade.count();

        assertNotNull(response);
        assertEquals(count, response);
        verify(mockedFacade, times(1)).count();
    }

    private void startAddress() {
        person = new Person(ID, NAME, AGE, GENDER);
        address = new Address(ID, STATE, CITY, STREET, NUMBER, ZIP_CODE, person);
    }
}
