package dao;

import java.util.List;

public interface Dao<K, E> {
    List<E> add(List<E> e);
    boolean update(List<E> e);
    boolean delete(List<K> k);
    List<E> findAll();
    List<E> findById(List<K> k);

}
