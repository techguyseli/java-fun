package dao;

import java.util.List;

public interface IDAO<T, ID>{
  List<T> findAll();
  T findById(ID id);
  List<T> findByKeywordLike(String keyWord);

  T save(T t);
  List<T> saveAll(List<T> list);
  T update(T t);

  Boolean delete(T t);
  Boolean deleteById(ID id);
  Boolean deleteAll(List<T> items);
}
