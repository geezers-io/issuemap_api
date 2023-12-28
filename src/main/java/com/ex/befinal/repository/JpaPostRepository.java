package com.ex.befinal.repository;

import com.ex.befinal.issue.domain.GeoIssueSummaryProjection;
import com.ex.befinal.issue.domain.HotIssueSummaryProjection;
import com.ex.befinal.issue.domain.IssueSummary;
import com.ex.befinal.issue.domain.PostCountProjection;
import com.ex.befinal.models.Post;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.Date;
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
            MIN(a1.id) AS max_attachment_id
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
WHERE
    p.created_at >= CURRENT_DATE()- INTERVAL 3 MONTH
    AND p.removed_at IS NULL
    AND p.disable_at IS NULL
GROUP BY
    p.id, p.title, a.upload_path, p.description, p.created_at, COALESCE(lad_count.like_count, 0)
ORDER BY
    like_count DESC
LIMIT 20;
""", nativeQuery = true
  )
  List<HotIssueSummaryProjection> findHotPosts();


  @Query(value = """
SELECT
    p.id as id,
    p.title as title,
    a.upload_path as thumbnailUrl,
    p.description as description,
    GROUP_CONCAT(t.tag) AS tags,
    p.created_at as createdAt,
    (6371
        *ACOS(COS(RADIANS(:lat))
                  *COS(RADIANS(p.latitude))
                  *COS(RADIANS(p.longitude)-RADIANS(:lng))
            +SIN(RADIANS(:lat))*SIN(RADIANS(p.latitude)))
        ) / 10 as distance,
    p.latitude as latitude,
    p.longitude as longitude
FROM
    posts p
        LEFT JOIN (
        SELECT
            a1.post_id,
            MIN(a1.id) AS max_attachment_id
        FROM
            befinal.attachments a1
        GROUP BY
            a1.post_id
    ) AS max_a ON p.id = max_a.post_id
        LEFT JOIN befinal.attachments a ON a.id = max_a.max_attachment_id
        LEFT JOIN befinal.posts_tags pt ON p.id = pt.post_id
        LEFT JOIN befinal.tags t ON pt.tag_id = t.id
WHERE
        p.created_at >= CURRENT_DATE() - INTERVAL 3 MONTH
  AND p.removed_at IS NULL
  AND p.disable_at IS NULL
GROUP BY
    p.id, p.title, a.upload_path, p.description, p.created_at ,p.latitude, p.longitude
HAVING
    distance <= 100
ORDER BY
    distance
LIMIT 10;
""", nativeQuery = true)

  List<GeoIssueSummaryProjection> findGeoPosts(
      @Param("lat") Double lat,
      @Param("lng") Double lng
  );

  @Query(value = """
SELECT
    p.id as id,
    p.title as title,
    a.upload_path as thumbnailUrl,
    p.description as description,
    GROUP_CONCAT(t.tag) AS tags,
    p.created_at as createdAt,
    (6371
        *ACOS(COS(RADIANS(:lat))
                  *COS(RADIANS(p.latitude))
                  *COS(RADIANS(p.longitude)-RADIANS(:lng))
            +SIN(RADIANS(:lat))*SIN(RADIANS(p.latitude)))
        ) / 10 as distance,
    p.latitude as latitude,
    p.longitude as longitude
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
WHERE
        p.created_at >= CURRENT_DATE() - INTERVAL 3 MONTH
  AND p.removed_at IS NULL
  AND p.disable_at IS NULL
  AND (:title IS NULL OR p.title LIKE CONCAT('%', :title, '%'))
  AND (:startDate IS NULL OR p.created_at >= :startDate)
  AND (:endDate IS NULL OR p.created_at <= :endDate)
GROUP BY
    p.id, p.title, a.upload_path, p.description, p.created_at, p.latitude, p.longitude
HAVING
        distance <= 1000
ORDER BY
    distance
LIMIT :pageSize
    OFFSET :offset

""", nativeQuery = true)

  List<GeoIssueSummaryProjection> findAroundPage(
      @Param("title") String title,
      @Param("lat") Double lat,
      @Param("lng") Double lng,
      @Param("startDate") Date startDate,
      @Param("endDate") Date endDate,
      @Param("pageSize") Integer pageSize,
      @Param("offset") Long offset);

}


