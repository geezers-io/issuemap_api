package com.ex.befinal.repository;

import com.ex.befinal.issue.domain.HotIssueSummaryProjection;
import com.ex.befinal.models.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPostRepository extends JpaRepository<Post, Long> {


  Optional<Long> countByUser_Id(Long userId);

  @SuppressWarnings("checkstyle:Indentation")
  @Query(value = """
SELECT
    p.id as id,
    p.title as title,
    a.upload_path as thumbnailUrl,
    p.description as description,
    GROUP_CONCAT(t.tag) AS tags,
    p.created_at as createdAt,
    COALESCE(lad_count.like_count, 0) AS like_count
FROM
    posts p
        LEFT JOIN (
        SELECT
            a1.post_id,
            MAX(a1.id) AS max_attachment_id
        FROM
            befinal.attachments a1
        GROUP BY
            a1.post_id
    ) AS max_a ON p.id = max_a.post_id
        LEFT JOIN befinal.attachments a ON a.id = max_a.max_attachment_id
        LEFT JOIN befinal.posts_tags pt ON p.id = pt.post_id
        LEFT JOIN befinal.tags t ON pt.tag_id = t.id
        LEFT JOIN (
        SELECT
            lad.post_id,
            COUNT(lad.id) AS like_count
        FROM
            befinal.likes_and_dislikes lad
        WHERE
                lad.status = 'LIKE'
        GROUP BY
            lad.post_id
    ) AS lad_count ON p.id = lad_count.post_id
GROUP BY
    p.id, p.title, a.upload_path, p.description, p.created_at, COALESCE(lad_count.like_count, 0)
ORDER BY
    like_count DESC
LIMIT 20;
""", nativeQuery = true
  )
  List<HotIssueSummaryProjection> findPosts();
}
