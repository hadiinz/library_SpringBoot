package prog.Runner.Library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prog.Runner.Library.Model.Book;
import prog.Runner.Library.Model.Publisher;
import prog.Runner.Library.Service.ServicePublishers;

import javax.transaction.Transactional;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/publisher")
public class PublisherController {
    @Autowired
    ServicePublishers servicePublishers;

    /**
     * CREATE UPDATE
     * @param publisher
     * @return
     */
    @PostMapping(value = "/addPublisher")
    public List<Publisher> addPublisher(@RequestBody Publisher publisher){
        servicePublishers.saveOrUpdate(publisher);
        return servicePublishers.getPublishers();
    }


    /**
     * get a publisher by its name
     * @param name
     * @return publisher
     */
    @GetMapping(value = "/{name}")
    public Publisher getPublisherByName(@PathVariable (value = "name")String name){
        Publisher publisher=servicePublishers.getPublisherByName(name);
        return publisher;
    }

    /**
     * READ
     * @return list of all publishers
     */
    @GetMapping(value = "/allPublishers")
    public List<Publisher> getAllPublisher(){
        return servicePublishers.getPublishers();
    }

    /**
     * DELETE be name of publisher
     * @param namePublisher
     * @return
     */
    @Transactional
    @DeleteMapping(value = "/delete/{name}")
    public List<Publisher> deletePublisherByMame(@PathVariable(value = "name")String namePublisher){
        servicePublishers.deletePublisherByName(namePublisher);
        return servicePublishers.getPublishers();
    }

    /**
     * get all books of a publisher
     * @param namePublisher
     * @return list of books
     */
    @GetMapping(value = "/books/{namePublisher}")
    public List<Book> getBooksOfPublisher(@PathVariable(value = "namePublisher")String namePublisher){
        return servicePublishers.getBooksOfPublisher(namePublisher);
    }

    @GetMapping(value = "/search/{str}")
    public List<Publisher>search(@PathVariable(value = "str")String str){
        if (str.equals(null)||str.equals(""))return servicePublishers.getPublishers();
        return servicePublishers.findPublishersByCharacter(str);
    }

    @PostMapping(value = "/edit/{name}")
    public Publisher edit(@RequestBody Publisher newPublisher,@PathVariable (value = "name")String name){
        return servicePublishers.updatePublisher(name,newPublisher);
    }
}
