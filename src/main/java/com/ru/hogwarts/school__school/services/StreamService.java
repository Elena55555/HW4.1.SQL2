package com.ru.hogwarts.school__school.services;

import com.ru.hogwarts.school__school.models.Student;
import com.ru.hogwarts.school__school.repositories.StreamRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional

public class StreamService {

    private final StreamRepository streamRepository;

    public StreamService(StreamRepository streamRepository  ) {
        this.streamRepository = streamRepository;
    }

    public Optional<String> getAllNamesStudentsOnA() {
        //

        return Optional.of(streamRepository.findAll().parallelStream()

                .map(Student::getName)
                .filter(p -> p.startsWith("А"))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Student not found")));

    }

    public Optional<Integer> getAvgAgeStudentName() {
        return Optional.of((int) streamRepository.findAll().parallelStream()

                .mapToInt(Student::getAge)
                        .summaryStatistics()
                        .getAverage()
                );
     }

    String sentence = "Когтевран Гриффиндор Пуффендуй Слизерин  ";
    public List<String> findLongestWords( ) {

        if (sentence == null || sentence.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String[] words = sentence.split("\\s");
        int maxWordLength = Arrays.stream(words)
                .mapToInt(String::length)
                .max()
                .orElseThrow();
        return Arrays.stream(words)
                .filter(word -> word.length() == maxWordLength)
                .collect(Collectors.toList());
    }
    public long getIntValue2() {
        return Stream.iterate(1L, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0L, Long::sum);
    }
}

