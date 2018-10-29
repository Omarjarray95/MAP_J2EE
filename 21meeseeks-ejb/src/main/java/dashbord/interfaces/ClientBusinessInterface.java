package dashbord.interfaces;

import java.util.Map;

import entities.Client;

public interface ClientBusinessInterface {

	public Integer countClients();
	public Map<String , Long> getClientsNumberByCountry();
	public Float countClientsByCategoryAndType(String category , String type);
	public Float countProfitByClients();
	public Map<Client , Float> getProfitabilityByClient(); 
	public Map<Client , Float> getMostProfitableClient();
	public Float countClientsWithOngoingProject();
	
}
