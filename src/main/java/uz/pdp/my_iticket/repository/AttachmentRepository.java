package uz.pdp.my_iticket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.my_iticket.model.Attachment;
import uz.pdp.my_iticket.projection.AttachmentProjection;

import java.util.List;
import java.util.UUID;
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {


    Attachment findAttachmentById(UUID castId);

    @Query(nativeQuery = true,value =
            "select cast(a.id as varchar ) as id,\n" +
                    "       a.file_name as fileName,\n" +
                    "       a.content_type as contentType,\n" +
                    "       a.size\n" +
                    "from attachments a join casts c on a.id = c.photo_id\n" +
                    "where c.id=:castId")
    List<AttachmentProjection> findAllAttachmentById(UUID castId);

    @Query(nativeQuery = true,value =
            "select cast(a.id as varchar ) as id," +
                    "a.file_name as fileName," +
                    "a.content_type as contentType," +
                    "a.size" +
                    " from attachments a")
    Page<AttachmentProjection> findAllAttachment(Pageable page);


    @Query(nativeQuery = true,value =
    "select cast(a.id as varchar) as id,\n" +
            "       a.file_name as fileName,\n" +
            "       a.content_type as contentType,\n" +
            "       a.size \n" +
            "from movies m join attachments a on a.id = m.poster_img_id\n" +
            "where m.id=:movieId")
    List<AttachmentProjection> getAttachmentByMovieId(UUID movieId);

    @Query(nativeQuery = true,value =
    "select cast(a.id as varchar) as id,\n" +
            "       a.file_name as fileName,\n" +
            "       a.content_type as contentType,\n" +
            "       a.size\n" +
            "from distributors d join attachments a on d.logo_id = a.id\n" +
            "where d.id=:distributorId;")
    AttachmentProjection getAttachmentByDistributorId(UUID distributorId);
}
