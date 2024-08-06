package riwi.filtro.models.interfaces;

import riwi.filtro.entities.registration;

import java.util.Optional;

public interface IRegistrationModel {

    Optional<registration> create(registration registration);
    boolean update(int id, registration registrationToUpdate);
    boolean delete(int id);
}
