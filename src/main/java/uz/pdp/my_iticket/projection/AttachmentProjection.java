package uz.pdp.my_iticket.projection;

import java.util.UUID;

public interface AttachmentProjection {
    UUID getId();
    String getFileName();
    String getContentType();
    Long getSize();
}
