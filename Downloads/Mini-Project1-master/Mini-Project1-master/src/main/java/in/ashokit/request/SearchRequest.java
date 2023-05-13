package in.ashokit.request;





import lombok.Data;


@Data
public class SearchRequest {
	
	
	private String planName;
	private String planStatus;
	private String gender;
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String startDate;
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String endDate;

}
