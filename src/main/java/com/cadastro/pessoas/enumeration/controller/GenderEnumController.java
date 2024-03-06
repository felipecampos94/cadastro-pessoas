package com.cadastro.pessoas.enumeration.controller;

import com.cadastro.pessoas.enumeration.GenderEnum;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author Felipe
 */
@Named("genderEnumController")
@SessionScoped
public class GenderEnumController implements Serializable {

    public SelectItem[] getGenders() {
        SelectItem[] items = new SelectItem[GenderEnum.values().length];
        int i = 0;
        for (GenderEnum t : GenderEnum.values()) {
            items[i++] = new SelectItem(t, t.getValue());
        }
        return items;
    }
}
