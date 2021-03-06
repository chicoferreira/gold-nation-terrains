package com.github.chicoferreira.goldnation.terrains.database.mongo;

import com.github.chicoferreira.goldnation.terrains.database.Dao;
import com.github.chicoferreira.goldnation.terrains.database.credentials.DatabaseCredentials;
import com.github.chicoferreira.goldnation.terrains.database.mapper.Mapper;
import com.github.chicoferreira.goldnation.terrains.database.provider.DatabaseProvider;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MongoDatabaseProvider implements DatabaseProvider {

    private MongoClient mongoClient;

    private Datastore datastore;

    @Override
    public void connect(DatabaseCredentials credentials) {
        Morphia morphia = new Morphia();

        ServerAddress serverAddress = new ServerAddress(credentials.getHost(), credentials.getPort());

        MongoCredential credential = MongoCredential.createCredential(
                credentials.getUsername(),
                credentials.getDatabase(),
                credentials.getPassword().toCharArray()
        );

        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        builder.connectTimeout(3000);
        builder.serverSelectionTimeout(3000);

        this.mongoClient = new MongoClient(serverAddress, Collections.singletonList(credential), builder.build());

        this.datastore = morphia.createDatastore(this.mongoClient, "terrain");
    }

    @Override
    public void close() {
        this.mongoClient.close();
    }

    @Override
    @Nonnull
    public <T, R> Dao<T> generateDao(Class<R> rClass, @Nonnull Mapper<T, R> mapper) {
        return new Dao<T>() {
            private BasicDAO<R, String> dao = new BasicDAO<>(rClass, MongoDatabaseProvider.this.getDatastore());

            @Override
            public T getEntity(String name) {
                R r = dao.get(name);
                return r != null ? mapper.from(r) : null;
            }

            @Override
            public void saveEntity(T t) {
                if (t != null) {
                    dao.save(mapper.to(t));
                }
            }

            @Override
            public void removeEntity(T t) {
                if (t != null) {
                    dao.delete(mapper.to(t));
                }
            }

            @Override
            public List<T> getAll() {
                List<T> list = new ArrayList<>();
                for (R r : dao.find()) {
                    if (r != null) {
                        list.add(mapper.from(r));
                    }
                }
                return list;
            }
        };
    }

    public Datastore getDatastore() {
        return datastore;
    }

}
