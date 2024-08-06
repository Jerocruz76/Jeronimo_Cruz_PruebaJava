package riwi.filtro.controllers;

import riwi.filtro.entities.registration;
import riwi.filtro.models.interfaces.IRegistrationModel;

import java.util.Optional;

public class registrationsController {
    private final IRegistrationModel registrationModel;

    public registrationsController(IRegistrationModel registrationModel) {
        this.registrationModel = registrationModel;
    }
    public Optional<registration> create(registration registration){
        return this.registrationModel.create(registration);
    }
    public boolean update(int id, registration registrationToUpdate){
        return this.registrationModel.update(id, registrationToUpdate);
    }
    public boolean delete(int id){
        return this.registrationModel.delete(id);
    }
}
