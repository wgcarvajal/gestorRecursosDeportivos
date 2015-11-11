package com.unicauca.gestorrecursosdeportivos.validadores;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="ValidarNombreEscenario")
public class ValidarNombreEscenario implements Validator 
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String nombre=String.valueOf(value);
        if(nombre.equals(""))
        {
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo nombre obligatorio","Campo nombre obligatorio");
            throw new ValidatorException(msg);
        }
    }
 
}
