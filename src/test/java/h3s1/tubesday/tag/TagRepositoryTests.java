package h3s1.tubesday.tag;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TagRepositoryTests {

    @Autowired
    TagRepository tagRepository;

    private static final String TAG_NAME = "백예린";
    private static final LocalDateTime CREATED_AT = LocalDateTime.now();

    @Test
    @Transactional
    public void save_and_findByTagName_test() {
        Tag tag = Tag.builder()
                .tagName(TAG_NAME)
                .createdAt(CREATED_AT)
                .updatedAt(CREATED_AT)
                .build();

        tagRepository.save(tag);

        Tag foundTag = tagRepository.findByTagName(TAG_NAME);
        assertEquals(tag, foundTag);
    }
}
