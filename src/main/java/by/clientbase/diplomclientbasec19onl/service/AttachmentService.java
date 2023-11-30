package by.clientbase.diplomclientbasec19onl.service;
import by.clientbase.diplomclientbasec19onl.entity.Attachment;
import by.clientbase.diplomclientbasec19onl.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public void save(Attachment attachment) {
        attachmentRepository.save(attachment);
    }

}
