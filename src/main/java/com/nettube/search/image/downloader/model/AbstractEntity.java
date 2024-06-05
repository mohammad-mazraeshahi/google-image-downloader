package com.nettube.search.image.downloader.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.id.OptimizableGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity<T extends Serializable> {
    public static final String DEFAULT_SEQ_GEN = "DEFAULT_SEQ_GEN";
    public static final String IDENTITY_STRATEGY = "identity";
    public static final String SEQUENCE_STRATEGY = "sequence";
    public static final String INCREMENT_SIZE = "1";
    public static final String INCREMENT_PARAM = OptimizableGenerator.INCREMENT_PARAM;
    public static final String SEQUENCE_PARAM = SequenceStyleGenerator.SEQUENCE_PARAM;
    public static final String DEF_SEQUENCE_SUFFIX = SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(generator = DEFAULT_SEQ_GEN)
    protected T id;

    @Column(updatable = false)
    @CreationTimestamp
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    protected LocalDateTime updatedAt;

    @Version
    @Column(nullable = false)
    protected Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}