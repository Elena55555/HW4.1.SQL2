package com.ru.hogwarts.school__school._pos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findByName(String position);

    List<Position> findByNameAndId(String position, Long id);

    List<Position> findByNameLike(String position);


    @Query("SELECT pos FROM Position pos WHERE pos.name IS NOT NULL")
    List<Position> findPositionWithNotNullName();
}
