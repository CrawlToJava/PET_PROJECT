package application.repository.impl;

import application.config.DataBase;
import application.entity.Model;
import application.repository.ModelRepository;
import customspring.metadata.Repository;
import lombok.NoArgsConstructor;

@Repository
@NoArgsConstructor
public class ModelRepositoryImpl extends AbstractJpaRepositoryImpl<Model, Long> implements ModelRepository {

    public ModelRepositoryImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(Model.class);
    }
}
