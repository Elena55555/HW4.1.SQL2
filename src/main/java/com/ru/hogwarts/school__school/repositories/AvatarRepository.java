
package com.ru.hogwarts.school__school.repositories;

import com.ru.hogwarts.school__school.models.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AvatarRepository extends JpaRepository <Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);

}
