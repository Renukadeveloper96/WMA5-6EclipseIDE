
package com.upendra.rai.entities;
import com.upendra.rai.enums.FileAccess;
import com.upendra.rai.enums.FileType;
import com.upendra.rai.enums.ItemType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "gallery_items",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"client_id", "parent_gallery_item_id", "fileType", "item_name"}))
@Getter
@Setter
@ToString(callSuper = true, exclude = {"client", "parent"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class GalleryItem extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  @Column(name = "item_name", nullable = false, length = 100)
  private String itemName;

  @Column(length = 100)
  private String description;

  @Column(length = 100)
  private String contentType;

  @Column
  private Long sizeInBytes;

  @Column(length = 100)
  private String itemKey;

  @Enumerated(EnumType.STRING)
  @Column
  private FileAccess access = FileAccess.PUBLIC;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ItemType type;

  @Enumerated(EnumType.STRING)
  @Column
  private FileType fileType;

  @ManyToOne
  @JoinColumn(name = "parent_gallery_item_id")
  private GalleryItem parent;

}
