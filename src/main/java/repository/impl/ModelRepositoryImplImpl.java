package repository.impl;

import config.DataBase;
import entity.Model;
import repository.ModelRepository;

public class ModelRepositoryImplImpl extends AbstractJpaRepositoryImpl<Model, Long> implements ModelRepository {
    public ModelRepositoryImplImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(Model.class);
    }
}
