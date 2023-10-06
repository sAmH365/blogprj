package com.blogprj.service;

import com.blogprj.domain.Post;
import com.blogprj.exception.PostNotFound;
import com.blogprj.repository.PostRepository;
import com.blogprj.request.PostCreate;
import com.blogprj.request.PostEdit;
import com.blogprj.request.PostSearch;
import com.blogprj.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        // when
        postService.write(postCreate);

        // then
        Assertions.assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        Assertions.assertEquals("제목입니다.", post.getTitle());
        Assertions.assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        // given
        Post requestPost = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(requestPost);

        // when
        PostResponse response = postService.get(requestPost.getId());

        // then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, postRepository.count());
        Assertions.assertEquals("제목", response.getTitle());
        Assertions.assertEquals("내용", response.getContent());
    }

    @Test
    @DisplayName("글 1페이지 조회")
    void test3() {
        // given
        List<Post> requestPosts = IntStream.range(0, 20)
                        .mapToObj(i -> Post.builder()
                                    .title("블로그 제목 : " + i)
                                    .content("블로그 내용 : " + i)
                                    .build()
                        )
                        .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();

        // when
        List<PostResponse> posts = postService.getList(postSearch);

        // then
        Assertions.assertNotNull(posts);
        Assertions.assertEquals(10, posts.size());
        Assertions.assertEquals("블로그 제목 : 19", posts.get(0).getTitle());
    }

    @Test
    @DisplayName("글 제목 수정")
    void test4() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("수정된 제목")
                .content("내용")
                .build();

        // when
        postService.edit(post.getId(), postEdit);

        // then
        Post chanedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재 하지 않습니다. id = " + post.getId()));

        Assertions.assertEquals("수정된 제목", chanedPost.getTitle());
        Assertions.assertEquals("내용", chanedPost.getContent());
    }

    @Test
    @DisplayName("글 내용 수정")
    void test5() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title(null)
                .content("수정된 내용")
                .build();

        // when
        postService.edit(post.getId(), postEdit);

        // then
        Post chanedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재 하지 않습니다. id = " + post.getId()));

        Assertions.assertEquals("제목", chanedPost.getTitle());
        Assertions.assertEquals("수정된 내용", chanedPost.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void test6() {
        // given
        Post post = Post.builder()
                .title("제목").content("내용")
                .build();
        postRepository.save(post);

        // when
        postService.delete(post.getId());

        // then
        Assertions.assertEquals(0, postRepository.count());
    }

    @Test
    @DisplayName("글 1개 조회 - 존재하지 않는 글")
    void test7() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        // expected
        Assertions.assertThrows(PostNotFound.class, () -> {
            postService.get(post.getId() + 1);
        }, "예외처리가 잘못 되었어요.");
    }

    @Test
    @DisplayName("게시글 삭제 - 존재하지 않는 글")
    void test8() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        // expected
        Assertions.assertThrows(PostNotFound.class, () -> {
            postService.delete(post.getId() + 1);
        });
    }

    @Test
    @DisplayName("글 내용 수정 - 존재하지 않는 글")
    void test9() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title(null)
                .content("수정된 내용")
                .build();

        // expected
        Assertions.assertThrows(PostNotFound.class, () -> {
            postService.edit(post.getId() + 1, postEdit);
        });
    }
}