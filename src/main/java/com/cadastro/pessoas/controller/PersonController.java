package com.cadastro.pessoas.controller;

import com.cadastro.pessoas.entity.Person;
import com.cadastro.pessoas.controller.util.JsfUtil;
import com.cadastro.pessoas.controller.util.JsfUtil.PersistAction;
import com.cadastro.pessoas.facade.PersonFacade;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("personController")
@SessionScoped
public class PersonController implements Serializable {

    @EJB
    private com.cadastro.pessoas.facade.PersonFacade ejbFacade;
    private List<Person> items = null;
    private Person selected;

    public PersonController() {
    }

    public Person getSelected() {
        return selected;
    }

    public void setSelected(Person selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonFacade getFacade() {
        return ejbFacade;
    }

    public Person prepareCreate() {
        selected = new Person();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void cancel() {
        items = null;
        selected = null;
    }

    public List<Person> getItems() {
        return items != null ? items : (items = getFacade().findAll());
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected == null) {
            return;
        }

        setEmbeddableKeys();
        try {
            switch (persistAction) {
                case CREATE:
                    getFacade().create(selected);
                    break;
                case UPDATE:
                    getFacade().edit(selected);
                    break;
                default:
                    if (selected.getAddressCollection().isEmpty()) {
                        getFacade().remove(selected);
                    } else {
                        JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersonDeletedFailRecord"));
                        return;
                    }
            }
            JsfUtil.addSuccessMessage(successMessage);
        } catch (EJBException ex) {
            String msg = ex.getCause() != null ? ex.getCause().getLocalizedMessage() : "";
            JsfUtil.addErrorMessage((Exception) (msg.length() > 0 ? msg : ex), ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public Person getPerson(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Person> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Person> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Person.class)
    public static class PersonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonController controller = (PersonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personController");
            return controller.getPerson(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Person) {
                Person o = (Person) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Person.class.getName()});
                return null;
            }
        }

    }

}
