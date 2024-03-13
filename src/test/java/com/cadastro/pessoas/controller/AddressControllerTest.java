package com.cadastro.pessoas.controller;

import com.cadastro.pessoas.entity.Address;
import com.cadastro.pessoas.entity.Person;
import com.cadastro.pessoas.facade.AddressFacade;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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

public class AddressControllerTest {

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

    @Mock
    private AddressFacade addressFacade;

    @InjectMocks
    private AddressController addressController;

    private Address address;

    private Person person;

    public AddressControllerTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startAddress();
    }

    @Test
    @DisplayName("When the address is created successfully")
    public void createAddressThenReturnSuccess() {
        AddressController mockedController = mock(AddressController.class);
        doNothing().when(mockedController).create();

        mockedController.create();
        verify(mockedController, times(1)).create();

        assertNotNull(mockedController.getItems());
    }

    @Test
    @DisplayName("When the address is updated successfully")
    public void updateAddressThenReturnSuccess() {
        AddressController mockedController = mock(AddressController.class);
        doNothing().when(mockedController).update();

        mockedController.update();
        verify(mockedController, times(1)).update();

        assertNotNull(mockedController.getItems());
    }

    @Test
    @DisplayName("When the address is deleted successfully")
    public void destroyAddressSuccess() {
        AddressController mockedController = mock(AddressController.class);
        doNothing().when(mockedController).destroy();

        mockedController.destroy();

        verify(mockedController, times(1)).destroy();
        assertTrue(mockedController.getItems().isEmpty());
    }

    @Test
    @DisplayName("When the list of addresses is returned successfully")
    public void findAllAddresses() {
        when(addressFacade.findAll()).thenReturn(Arrays.asList(address));

        List<Address> result = addressController.getItems();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Address.class, result.get(0).getClass());
        assertEquals(ID, result.get(0).getId());
    }

    @Test
    @DisplayName("When the search for id is returned successfully")
    public void findByIdAddressThenReturnSuccess() {
        when(addressFacade.find(anyInt())).thenReturn(address);

        Address result = addressController.getAddress(ID);

        assertNotNull(result);
        assertEquals(Address.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(STATE, result.getState());
        assertEquals(CITY, result.getCity());
        assertEquals(STREET, result.getStreet());
        assertEquals(NUMBER, result.getNumber());
        assertEquals(ZIP_CODE, result.getZipCode());
        assertEquals(person, result.getPersonId());
    }

    private void startAddress() {
        person = new Person(ID, NAME, AGE, GENDER);
        address = new Address(ID, STATE, CITY, STREET, NUMBER, ZIP_CODE, person);
    }

}
