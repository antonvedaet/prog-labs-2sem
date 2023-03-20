package app.commands;
import java.time.LocalDate;
import app.data.Person;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.PersonCreator;
import app.utils.CollectionHandler;
/**
 * Класс отвечающий за команду update {id}
 */
public class Update extends AbstractCommand {
    
    PersonCreator personCreator;
    CollectionHandler collectionHandler;

    public Update(PersonCreator personCreator, CollectionHandler collectionHandler){
        super("update", "обновить значение элемента по id");
        this.personCreator = personCreator;
        this.collectionHandler = collectionHandler;
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(arg.equals("placeholderArg")) throw new ElementAmountException();
            Integer.parseInt(arg);
            
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        } catch (NumberFormatException ex) {
            IOHandler.println("Введите число");
        }
        return false;
    }

    @Override
    public void execute(String arg){
        if(argCheck(arg)){
            for(Person person :  collectionHandler.getCollection()){
                if(person.getId()==(Integer.parseInt(arg))){
                    collectionHandler.removePerson(person);
                    collectionHandler.addPerson(new Person(Integer.parseInt(arg), personCreator.nameCreate(), personCreator.coordinatesCreate(), LocalDate.now(), personCreator.heightCreate(), personCreator.bdayCreate(), personCreator.eyeColorChoose(), personCreator.hairColorChoose(), personCreator.locationCreate()));
                }
            }
        }
    }
}