package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.request.SearchRequest;

public interface ReportService {

	public List<String>getPlanNames();
	List<String>getPlanStatuses();
	public List<CitizenPlan>search(SearchRequest request);//service layer->repo->db->entity(CitizenPlan)
	public boolean exportExcel();
	public boolean exportPdf();
}
