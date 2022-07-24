package eu.wodrobina.homeenvironment;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class InMemoryRepository<ENTITY, ID> implements JpaRepository<ENTITY, ID> {
    private final Map<ID, ENTITY> store = new ConcurrentHashMap<>();

    private Function<ENTITY, ID> getID;

    public InMemoryRepository(Function<ENTITY, ID> getID) {
        this.getID = getID;
    }

    @Override
    public List<ENTITY> findAll() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<ENTITY> findAll(Sort sort) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<ENTITY> findAllById(Iterable<ID> ids) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY> List<S> saveAll(Iterable<S> entities) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void flush() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY> S saveAndFlush(S entity) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteAllInBatch(Iterable<ENTITY> entities) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<ID> ids) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteAllInBatch() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ENTITY getOne(ID id) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ENTITY getById(ID id) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ENTITY getReferenceById(ID id) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY> List<S> findAll(Example<S> example) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY> List<S> findAll(Example<S> example, Sort sort) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Page<ENTITY> findAll(Pageable pageable) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY> S save(S entity) {
        store.put(getID.apply(entity), entity);
        return entity;
    }

    @Override
    public Optional<ENTITY> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsById(ID id) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long count() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteById(ID id) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void delete(ENTITY entity) {
        store.remove(getID.apply(entity));
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends ENTITY> entities) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY> Optional<S> findOne(Example<S> example) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY> long count(Example<S> example) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY> boolean exists(Example<S> example) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <S extends ENTITY, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new RuntimeException("Not implemented");
    }
}
