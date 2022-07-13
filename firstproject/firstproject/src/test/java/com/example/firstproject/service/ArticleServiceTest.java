package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다
class ArticleServiceTest {

    @Autowired ArticleService articleService;
    @Autowired ArticleRepository articleRepository;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));


        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공____존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패____존재하지_않는_id_입력() {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공____title과_content만_있는_dto입력() {
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_실패____id가_포함된_dto_입력() {
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공____존재하는_id와_title_content가_있는_dto_입력() {
        // 예상
        ArticleForm dto = new ArticleForm(1L, "하하하하", "호호호호");
        Article expected = new Article(1L, "하하하하", "호호호호");


        // 실제
        Article article = articleService.update(1L, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
        // Equals라서 주소가 다르기 때문에 Equals랑 HashCode를 오버라이드 해줘야 비교할 수 있다
        // 오버라이드 하지 않고 toString()을 붙여서 비교해도 되지만 entity.Article에 @EqualsAndHashCode을 추가해서 그냥 toString없이 해도됨
    }

    @Test
    @Transactional
    void update_성공____존재하는_id와_title만_있는_dto_입력() {
        // 예상
        ArticleForm dto = new ArticleForm(1L, "히히히", null);
        Article expected = new Article(1L, "히히히", "1111");


        // 실제
        Article article = articleService.update(1L, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
        // Equals라서 주소가 다르기 때문에 Equals랑 HashCode를 오버라이드 해줘야 비교할 수 있다
        // 오버라이드 하지 않고 toString()을 붙여서 비교해도 되지만 entity.Article에 @EqualsAndHashCode을 추가해서 그냥 toString없이 해도됨
    }

    @Test
    @Transactional
    void update_실패____존재하지_않는_id의_dto_입력() {
        // 예상
        ArticleForm dto = new ArticleForm(4L, "히히히", null);
        Article expected = null;


        // 실제
        Article article = articleService.update(4L, dto);

        // 비교
        assertEquals(expected, article);
        // Equals라서 주소가 다르기 때문에 Equals랑 HashCode를 오버라이드 해줘야 비교할 수 있다
        // 오버라이드 하지 않고 toString()을 붙여서 비교해도 되지만 entity.Article에 @EqualsAndHashCode을 추가해서 그냥 toString없이 해도됨
    }

    @Test
    @Transactional
    void update_실패____id만_있는_dto_입력() {
        // 예상
        ArticleForm dto = new ArticleForm(5L, null, null);
        Article expected = null;


        // 실제
        Article article = articleService.update(5L, dto);

        // 비교
        assertEquals(expected, article);
        // Equals라서 주소가 다르기 때문에 Equals랑 HashCode를 오버라이드 해줘야 비교할 수 있다
        // 오버라이드 하지 않고 toString()을 붙여서 비교해도 되지만 entity.Article에 @EqualsAndHashCode을 추가해서 그냥 toString없이 해도됨
    }

    @Test
    @Transactional
    void delete_성공____존재하는_id_입력() {
        // 예상
        Article expectedBeforeDelete = new Article(1L, "가가가가", "1111");
        Article expectedAfterDelete = null;

        // 실제
        Article defaultValue = articleService.show(1L);
        articleService.delete(1L);
        Article deleted = articleRepository.findById(1L).orElse(null);

        // 비교
        assertThat(expectedBeforeDelete.toString()).isEqualTo(defaultValue.toString());
        assertThat(expectedAfterDelete).isEqualTo(deleted);
    }

    @Test
    @Transactional
    void delete_실패____존재하지_않는_id_입력() {
        // 예상
        Article expected = null;

        // 실제
        articleService.delete(500L);
        Article deleted = articleRepository.findById(500L).orElse(null);

        // 비교
        assertThat(expected).isEqualTo(deleted);
    }
}