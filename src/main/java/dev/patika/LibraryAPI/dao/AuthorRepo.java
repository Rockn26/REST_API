package dev.patika.LibraryAPI.dao;

import dev.patika.LibraryAPI.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
