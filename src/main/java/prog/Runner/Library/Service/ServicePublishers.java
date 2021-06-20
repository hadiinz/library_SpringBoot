package prog.Runner.Library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prog.Runner.Library.Model.Book;
import prog.Runner.Library.Model.Publisher;
import prog.Runner.Library.Repository.PublisherRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServicePublishers {
    @Autowired
    PublisherRepository publisherRepository;


    /**
     * READ
     * @return list of all publishers
     */
    public List<Publisher> getPublishers(){
        return  publisherRepository.findAll();
    }

    /**
     * READ a publisher
     * @param name of publisher
     * @return publisher
     */
    public Publisher getPublisherByName(String name){
        Publisher publisher=publisherRepository.findPublisherByName(name);
        return publisher;
    }

    /**
     * SAVE UPDATE
     * @param publisher that u wanna save
     */
    public void saveOrUpdate(Publisher publisher){
        publisherRepository.save(publisher);
    }

    /**
     * DELETE publisher by name
     * @param namePublisher name of publisher you wanna delete
     * @return
     */
    public Publisher deletePublisherByName(String namePublisher){
        publisherRepository.deletePublisherByName(namePublisher);
        return publisherRepository.findPublisherByName(namePublisher);
    }


    public List<Book> getBooksOfPublisher(String namePublisher){
        Publisher publisher=publisherRepository.findPublisherByName(namePublisher);
        return publisher.getBookList();
    }


    public List<Publisher> findPublishersByCharacter(String name){
       return publisherRepository.findPublishersByNameContains(name);
    }


    public Publisher updatePublisher(String nameOfOldPublisher,Publisher newPublisher){
        Publisher oldPublisher=publisherRepository.findPublisherByName(nameOfOldPublisher);
        oldPublisher.setName(newPublisher.getName());
        oldPublisher.setEstablishedDate(newPublisher.getEstablishedDate());

        publisherRepository.save(oldPublisher);
        return oldPublisher;
    }
}
