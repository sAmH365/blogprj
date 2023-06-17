package com.blogprj.repository;

import com.blogprj.domain.Post;
import com.blogprj.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
