package com.acme.catchup.platform.news.domain.model.aggregates;

import com.acme.catchup.platform.news.domain.model.valueobjects.NewsApiKey;
import com.acme.catchup.platform.news.domain.model.valueobjects.SourceId;
import com.acme.catchup.platform.news.infrastructure.persistence.jpa.converters.NewsApiKeyAttributeConverter;
import com.acme.catchup.platform.news.infrastructure.persistence.jpa.converters.SourceIdAttributeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "news_api_key", "source_id"}, name = FavoriteSource.NEWS_API_KEY_SOURCE_ID_UNIQUE_CONSTRAINT)
})
public class FavoriteSource {
    public static final String NEWS_API_KEY_SOURCE_ID_UNIQUE_CONSTRAINT = "uk_news_api_key_source_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Convert(converter = NewsApiKeyAttributeConverter.class)
    private NewsApiKey newsApiKey;

    @Column(nullable = false)
    @Convert(converter = SourceIdAttributeConverter.class)
    private SourceId sourceId;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant updatedAt;
}
