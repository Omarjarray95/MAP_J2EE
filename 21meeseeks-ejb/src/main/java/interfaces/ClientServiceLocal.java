package interfaces;

import java.util.List;
import java.util.Map;
import java.util.Locale.Category;

import javax.ejb.Local;

import entities.Client;
import entities.ClientCategory;

@Local
public interface ClientServiceLocal {
public int addClient(Client c);
public void deleteClientByID(int i);
public Client findClientByID(int i);
public List<Client> getClientsByCriterias(Map<String, String> criterias);
public Client editClient(Client c);

//json converting
public String clienttoJson(Client c);
public String clientlisttoJson(List<Client> c);

//customize category
public ClientCategory findClientCategory(ClientCategory cc);
public int addClientCategory(ClientCategory cc);
public boolean deleteClientCategory(String title);
public List<ClientCategory> listClientCategories();


}
