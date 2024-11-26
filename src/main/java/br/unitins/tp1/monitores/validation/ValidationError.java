package br.unitins.tp1.monitores.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends Error{
    
    private record FieldError(String fieldName, String message) {
    
    }
    private List<FieldError> erros = null;

    public ValidationError(String code, String message) {
        super(code, message);
        
    }

    public List<FieldError> getErros() {
        return erros;
    }

    public void addFieldErro(String fieldName, String message){
        if (erros == null)
            erros = new ArrayList<FieldError>();
        erros.add(new FieldError(fieldName, message));

    }








}
