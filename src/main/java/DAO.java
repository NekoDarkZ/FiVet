/*
 * Copyright 2022 Sebastián Murquio Castillo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.util.List;
import java.util.Optional;

/**
 * Interface DAO.
 * @param <T> to use.
 */
public interface DAO <T>{
    /**
     * get Method to search a Entity by id.
     * @param id to search.
     * @return an Entity.
     */
    Optional<T> get(Integer id);

    /**
     * get all the Entitys in a List.
     * @return a List of Entitys.
     */
    List<T> getAll();

    /**
     * inserts a new Entity.
     * @param t to save.
     */
    void save(T t);

    /**
     * delete a Entity by passing an Entity as parameter.
     * @param t to delete.
     */
    void delete(T t);

    /**
     * delete a Entity by passing an id.
     * @param id of the Entity to delete.
     */
    void delete(Integer id);
}
