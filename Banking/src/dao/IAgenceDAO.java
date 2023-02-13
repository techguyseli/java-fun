package dao;

import presentation.models.Agence;

public interface IAgenceDAO extends IDAO<Agence, Long> {
    Agence findByNom(String nom);
}
